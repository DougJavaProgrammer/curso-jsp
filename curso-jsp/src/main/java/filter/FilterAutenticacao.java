package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
		super();
	}

	/* encerra os processos quando o servidor é parado */
	/* mataria os processos de conexão com o banco */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* intercepta as requisições e a as respostas no sistema */
	/* tudo que fizemos no sistema, irá passar por aqui */
	/* exemplo, validação de autenticação */
	/* dar commit e rolback de transações no bancoo */
	/* validar e fazer redirecionamento de páginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath(); /* url que está sendo acessada */

			/* Validar se está logado, senão redireciona para a tela de login */
			if (usuarioLogado == null && !urlParaAutenticar
					.equalsIgnoreCase("/principal/ServletLogin")) /* condição de não estar logado */ {
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url= " + urlParaAutenticar);
				request.setAttribute("msg", "Por favor, realize o login");
				redireciona.forward(request, response);
				return; /* para a execução e redireciona para o login */
			} else {

				chain.doFilter(request, response);

			}
			
			connection.commit();//deu tudo certo, então comita as alaterações no banco de dados

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/* inicia os processos ou recursos quando o sevidor sobe o projeto */
	public void init(FilterConfig fConfig) throws ServletException {

		connection = SingleConnectionBanco.getConnection();
	}

}

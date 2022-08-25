package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/*O chamado Controller são as Servlets ou ServletLoginController*/
@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" }) /* Mapeamento de URL que vem da tela */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

	public ServletLogin() {
		super();
	}

	/* Recebe os dados pela URL em parametros */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/* Recebe os dados enviados por um formulario */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {

		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

			if (daoLoginRepository.validarAutenticacao(modelLogin)) {/* Simulando o login */
				request.getSession().setAttribute("usuario", modelLogin.getLogin());// atributo de sessão

				if (url == null || url.equals("null")) {
					url = "principal/principal.jsp";

				}

				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);

			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Login ou senha inválida");
				redirecionar.forward(request, response);
			}

		} else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha");
			redirecionar.forward(request, response);

		}

	}catch (Exception e) {
		e.printStackTrace();
	}

}
}
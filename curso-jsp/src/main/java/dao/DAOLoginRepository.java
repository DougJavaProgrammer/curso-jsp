package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	public DAOLoginRepository() {
		
		connection = SingleConnectionBanco.getConnection();
				
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
		String sql = "select * from model_login where upper (login) = upper(?) and upper (senha) = upper (?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true; //se tiver um objeto, será autenticado e retornará true
		}
		
		return false; // se não tiver um objeto, não será autenticado e retornará false
						
	}
	

}

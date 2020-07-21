package model.dao;

import java.awt.FontFormatException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.CadastroLogin;
import view.TelaInicial;

public class CadastroDAOLogin {
	
	public boolean checkLogin(String nomeUsuario, String senha) throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Login where nome_usuario = ? and senha = ?");
			stmt.setString(1, nomeUsuario);
			stmt.setString(2, senha);
			
			
			rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				
				check = true;
				
				String tipoUsuario = rs.getString("tipo_usuario");
                if (tipoUsuario.equals("Administrador")){
                
                	TelaInicial telainicial = new TelaInicial();
					telainicial.lblNewLabel.setText("Bem-vindo, "+nomeUsuario+"!");
					telainicial.setVisible(true);
					telainicial.lblNewLabel_1.setText(nomeUsuario);
                }
                if (tipoUsuario.equals("Padrão")){
                    
                	TelaInicial telainicial = new TelaInicial();
					telainicial.lblNewLabel.setText("Bem-vindo, "+nomeUsuario+"!");
					telainicial.setVisible(true);
					telainicial.btnConfiguracoes.setVisible(false);
					telainicial.lblNewLabel_1.setText(nomeUsuario);
                }
                if (tipoUsuario.equals("Auxiliar")){
                    
                	TelaInicial telainicial = new TelaInicial();
					telainicial.lblNewLabel.setText("Bem-vindo, "+nomeUsuario+"!");
					telainicial.setVisible(true);
					telainicial.btnConfiguracoes.setVisible(false);
					telainicial.btnComercial.setVisible(false);
					telainicial.btnDashboard.setVisible(false);
					telainicial.lblNewLabel_1.setText(nomeUsuario);
                }
			
			
			}
			
		    stmt.close();
		    con.close();
		    rs.close();
			
		}
		catch (SQLException e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		    return false;
		}
		
		return check;
		
			
		}
	
		public boolean create(CadastroLogin cadastro) throws ClassNotFoundException, SQLException {
		
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
		
		String sql = "INSERT INTO Login (nome_usuario, tipo_usuario, senha) VALUES (?,?,?)";
		
		
	
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getnomeUsuario());
		stmt.setString(2, cadastro.gettipoUsuario());
		stmt.setString(3, cadastro.getSenha());
		stmt.executeUpdate();
		stmt.close();
		con.close();
		JOptionPane.showMessageDialog(null,
				"Cadastro salvo!");
		return true;
		}
		catch (SQLException e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		    return false;
		}
			
		}

		public boolean update(CadastroLogin cadastro) throws ClassNotFoundException, SQLException {
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
			String sql = "UPDATE Login set nome_usuario = ?, tipo_usuario = ?, senha = ? where codigo_usuario=?";
		
			
			
			try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cadastro.getnomeUsuario());
			stmt.setString(2, cadastro.gettipoUsuario());
			stmt.setString(3, cadastro.getSenha());
			stmt.setInt(4, cadastro.getCodigo());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,
					"Cadastro atualizado!");
			return true;
			}
			catch (SQLException e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			    System.exit(0);
			    return false;
			}
				
			}	
		
		
		
	
		public boolean delete(CadastroLogin cadastro) throws ClassNotFoundException, SQLException {
			
			String sql = "DELETE from Login where codigo_usuario = ?";
			
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
			try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, cadastro.getCodigo());
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,
					"Cadastro excluído!");
			return true;
			}
			catch (SQLException e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			    System.exit(0);
			    return false;
			}
				
			}
}

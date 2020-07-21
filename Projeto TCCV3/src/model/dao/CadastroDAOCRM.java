package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.CadastroCRM;

public class CadastroDAOCRM {
	
private Connection con = null;
	
	
	
	public CadastroDAOCRM() throws ClassNotFoundException, SQLException  { 
		
		con = ConnectionFactory.getConnection(); 
		
		
		
	}
	public boolean create(CadastroCRM cadastro) {
		
		
		
		String sql = "INSERT INTO CRM (RG, CPF, nome, endereco, telefone, email, dt_cadastro, num_cartao, validade, usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		PreparedStatement stmt = null;
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getRG());
		stmt.setString(2, cadastro.getCPF());
		stmt.setString(3, cadastro.getNome());
		stmt.setString(4, cadastro.getEndereco());
		stmt.setString(5, cadastro.getTelefone());
		stmt.setString(6, cadastro.getEmail());
		stmt.setString(7, cadastro.getDtCadastro());
		stmt.setString(8, cadastro.getNumCartao());
		stmt.setString(9, cadastro.getValidade());
		stmt.setString(10,  cadastro.getUsuario());
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
	
	
	public boolean update(CadastroCRM cadastro) {
		
		
		
		String sql = "UPDATE CRM set RG = ?, CPF = ?, nome = ?, endereco = ?, telefone = ?, email = ?, dt_cadastro = ?, num_cartao = ?, validade = ? where codigo_cliente=?";
		
		
		PreparedStatement stmt = null;
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getRG());
		stmt.setString(2, cadastro.getCPF());
		stmt.setString(3, cadastro.getNome());
		stmt.setString(4, cadastro.getEndereco());
		stmt.setString(5, cadastro.getTelefone());
		stmt.setString(6, cadastro.getEmail());
		stmt.setString(7, cadastro.getDtCadastro());
		stmt.setString(8, cadastro.getNumCartao());
		stmt.setString(9, cadastro.getValidade());
		stmt.setInt(10, cadastro.getCodigo());
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
	
	
	 	

		
	
	public boolean delete(CadastroCRM cadastro) {
		
		String sql = "DELETE from CRM where codigo_cliente = ?";
		
		
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

package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.CadastroRecursosHumanos;

public class CadastroDAORecursosHumanos {

private Connection con = null;
	
	
	
	public CadastroDAORecursosHumanos() throws ClassNotFoundException, SQLException  { 
		
		con = ConnectionFactory.getConnection(); 
		
		
		
	}
	public boolean create(CadastroRecursosHumanos cadastro) {
		
		
		
		String sql = "INSERT INTO RecursosHumanos (rg, cpf, nome, endereco, telefone, email, data_contratacao, cargo, salario, usuario) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		
		PreparedStatement stmt = null;
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getRG());
		stmt.setString(2, cadastro.getCPF());
		stmt.setString(3, cadastro.getNome());
		stmt.setString(4, cadastro.getEndereco());
		stmt.setString(5, cadastro.getTelefone());
		stmt.setString(6, cadastro.getEmail());
		stmt.setString(7, cadastro.getDt_Contratacao());
		stmt.setString(8, cadastro.getCargo());
		stmt.setFloat(9, cadastro.getSalario());
		stmt.setString(10, cadastro.getUsuario());
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
	
	
	public boolean update(CadastroRecursosHumanos cadastro) {
		
		
		
		String sql = "UPDATE RecursosHumanos set rg = ?, CPF= ?, nome = ?, endereco = ?, telefone = ?, email = ?, data_contratacao = ?, cargo = ?, salario = ? where codigo_funcionario=?";
		
		
		PreparedStatement stmt = null;
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getRG());
		stmt.setString(2, cadastro.getCPF());
		stmt.setString(3, cadastro.getNome());
		stmt.setString(4, cadastro.getEndereco());
		stmt.setString(5, cadastro.getTelefone());
		stmt.setString(6, cadastro.getEmail());
		stmt.setString(7, cadastro.getDt_Contratacao());
		stmt.setString(8, cadastro.getCargo());
		stmt.setFloat(9, cadastro.getSalario());
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
	
	
	 	

		
	
	public boolean delete(CadastroRecursosHumanos cadastro) {
		
		String sql = "DELETE from RecursosHumanos where codigo_funcionario = ?";
		
		
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

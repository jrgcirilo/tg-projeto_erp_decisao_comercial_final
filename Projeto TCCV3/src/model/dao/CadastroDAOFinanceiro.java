package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.CadastroFinanceiro;

public class CadastroDAOFinanceiro {
	
private Connection con = null;
	
	
	
	public CadastroDAOFinanceiro() throws ClassNotFoundException, SQLException  { 
		
		con = ConnectionFactory.getConnection(); 
		
		
		
	}
	public boolean create(CadastroFinanceiro cadastro) {
		
		
		
		String sql = "INSERT INTO Financeiro (tipo_conta, descricao, empresa, vencimento, valor, status, usuario) VALUES (?,?,?,?,?,?,?)";
		
		
		PreparedStatement stmt = null;
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getTipoConta());
		stmt.setString(2, cadastro.getDescricao());
		stmt.setString(3, cadastro.getEmpresa());
		stmt.setString(4, cadastro.getVencimento());
		stmt.setFloat(5, cadastro.getValor());
		stmt.setString(6, cadastro.getStatus());
		stmt.setString(7, cadastro.getUsuario());
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
	
	public boolean update(CadastroFinanceiro cadastro) {
		
		
		
		String sql = "UPDATE Financeiro set tipo_conta =?, descricao = ?, empresa = ?, status = ? where codigo_conta=?";
		
		
		PreparedStatement stmt = null;
		
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, cadastro.getTipoConta());
		stmt.setString(2, cadastro.getDescricao());
		stmt.setString(3, cadastro.getEmpresa());
		stmt.setString(4, cadastro.getStatus());
		stmt.setInt(5, cadastro.getCodigoConta());
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
	
	public boolean delete(CadastroFinanceiro cadastro) {
		
		String sql = "DELETE from Financeiro where codigo_conta = ?";
		
		
		PreparedStatement stmt = null;
		
		try {
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, cadastro.getCodigoConta());
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

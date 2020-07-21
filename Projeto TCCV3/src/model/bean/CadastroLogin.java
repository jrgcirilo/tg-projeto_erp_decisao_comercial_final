package model.bean;

public class CadastroLogin {
	
	private int codigo;
	private String nomeUsuario;
	private String senha;
	private String tipoUsuario;
	
	public CadastroLogin(int codigo, String nomeUsuario, String tipoUsuario, String senha)
	{
		this.codigo=codigo;
		this.nomeUsuario=nomeUsuario;
		this.tipoUsuario=tipoUsuario;
		this.senha=senha;
	}
	
	public int getCodigo(){
		return codigo;
	}

	public void setCodigo(int codigo){
		this.codigo=codigo;
	}
	
	public String getnomeUsuario(){
		return nomeUsuario;
	}

	public void setnomeUsuario(String nomeUsuario){
		this.nomeUsuario=nomeUsuario;
	}
	
	public String getSenha(){
		return senha;
	}

	public void setSenha(String senha){
		this.senha=senha;
	}
	
	public String gettipoUsuario(){
		return tipoUsuario;
	}

	public void settipoUsuario(String tipoUsuario){
		this.tipoUsuario=tipoUsuario;
	}
}

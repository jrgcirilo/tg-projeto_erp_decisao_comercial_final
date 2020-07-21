package model.bean;

public class CadastroCRM {

	private int codigo;
	private String RG;
	private String CPF;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String dt_cadastro;
	private String num_cartao;
	private String validade;
	private String usuario;

	public CadastroCRM(int codigo, String RG, String CPF, String nome, String endereco, String telefone, String email, String dt_cadastro, String num_cartao, String validade, String usuario)
	{
		this.codigo=codigo;
		this.RG=RG;
		this.CPF=CPF;
		this.nome=nome;
		this.endereco=endereco;
		this.telefone=telefone;
		this.email=email;
		this.dt_cadastro=dt_cadastro;
		this.num_cartao=num_cartao;
		this.validade=validade;
		this.usuario=usuario;
		
	}

	public CadastroCRM() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		
		return codigo;
	}
	
	public String getRG() {
		
		return RG;
	}
	
	
	public String getCPF() {
		
		return CPF;
	}
	
	public String getNome() {
		
		return nome;
	}
	
	public String getEndereco() {
		
		return endereco;
	}
	
	public String getTelefone() {
		
		return telefone;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public String getDtCadastro() {
		
		return dt_cadastro;
	}
	
	public String getNumCartao() {
		
		return num_cartao;
	}

	public String getValidade() {
	
	return validade;
	}
	
	
	public String getUsuario() {
		
		return usuario;
		}
	
	public void setCodigo(int codigo) {
		
		this.codigo=codigo;
	}
	
	public void setRG(String RG) {
		
		this.RG=RG;
	}
	
	public void setCPF(String CPF) {
		
		this.CPF=CPF;
	}
	
	public void setNome(String nome) {
		
		this.nome=nome;
	}
	
	public void setEndereco(String endereco) {
		
		this.endereco=endereco;
	}
	
	public void setTelefone(String telefone) {
		
		this.telefone=telefone;
	}
	
	public void setEmail(String email) {
		
		this.email=email;
	}
	
	public void setDtCadastro(String dt_cadastro) {
		
		this.dt_cadastro=dt_cadastro;
	}

	public void setNumCartao(String num_cartao) {
	
	this.num_cartao=num_cartao;
}

	public void setValidade(String validade) {
	
	this.validade=validade;
}
	
	public void setUsuario(String usuario) {
		
		this.usuario=usuario;
	}
	
}

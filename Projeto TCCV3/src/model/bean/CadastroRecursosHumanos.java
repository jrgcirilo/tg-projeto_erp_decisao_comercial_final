package model.bean;

public class CadastroRecursosHumanos {
	
	private int codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String cargo;
	private float salario;
	private String rg;
	private String cpf;
	private String dt_contratacao;
	private String usuario;

	public CadastroRecursosHumanos(int codigo, String rg, String cpf, String nome, String endereco, String telefone, String email, String dt_contratacao, String cargo, float salario, String usuario )
	{
		this.codigo=codigo;
		this.nome=nome;
		this.endereco=endereco;
		this.telefone=telefone;
		this.email=email;
		this.cargo=cargo;
		this.salario=salario;
		this.rg=rg;
		this.cpf=cpf;
		this.dt_contratacao=dt_contratacao;
		this.usuario=usuario;
		
	}

	public CadastroRecursosHumanos() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		
		return codigo;
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
	
	public String getCargo() {
		
		return cargo;
	}
	
	public float getSalario() {
		
		return salario;
	}
	
	public String getRG() {
		
		return rg;
	}
	
	public String getCPF() {
		
		return cpf;
	}

	public String getDt_Contratacao() {
	
	return dt_contratacao;
	}
	
	public String getUsuario() {
		
	return usuario;
	}
	
	public void setCodigo(int codigo) {
		
		this.codigo=codigo;
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
	
	public void setCargo(String cargo) {
		
		this.cargo=cargo;
	}
	
	public void setSalario(float salario) {
		
		this.salario=salario;
	}
	
	public void setRG(String rg) {
		
		this.rg=rg;
	}
	
	public void setCPF(String cpf) {
		
		this.cpf=cpf;
	}
	
	public void setDtContratacao(String dt_contratacao) {
		
		this.dt_contratacao=dt_contratacao;
	}
	
public void setUsuario(String usuario) {
		
		this.usuario=usuario;
	}
	
	
}


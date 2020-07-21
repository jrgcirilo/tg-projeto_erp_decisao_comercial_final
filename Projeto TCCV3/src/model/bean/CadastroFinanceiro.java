package model.bean;

public class CadastroFinanceiro {
	
	private int codigo_conta;
	private String tipo_conta;
	private String descricao;
	private String empresa;
	private String vencimento;
	private float valor;
	private String status;
	private String usuario;
	
	public CadastroFinanceiro(int codigo_conta, String tipo_conta, String descricao, String empresa, String vencimento, float valor, String status, String usuario)
	{
		this.codigo_conta=codigo_conta;
		this.tipo_conta=tipo_conta;
		this.descricao=descricao;
		this.empresa=empresa;
		this.vencimento=vencimento;
		this.valor=valor;
		this.status=status;
		this.usuario=usuario;
		
	}
		
	public CadastroFinanceiro() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigoConta() {
		
		return codigo_conta;
	}
	
	public String getTipoConta() {
		
		return tipo_conta;
	}
	
	public String getDescricao() {
		
		return descricao;
	}
	
	public String getEmpresa() {
		
		return empresa;
	}
	
	public String getVencimento() {
		
		return vencimento;
	}
	
	public float getValor() {
		
		return valor;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public String getUsuario() {
		
		return usuario;
	}
	
	public void setCodigoConta(int codigo_conta) {
		
		this.codigo_conta=codigo_conta;
	}
	
public void setTipoConta(String tipo_conta) {
		
		this.tipo_conta=tipo_conta;
	}
	
	public void setDescricao(String descricao) {
		
		this.descricao=descricao;
	}
	
	public void setEmpresa(String empresa) {
		
		this.empresa=empresa;
	}
	
	public void setVencimento(String vencimento) {
		
		this.vencimento=vencimento;
	}
	
	public void setValor(float valor) {
		
		this.valor=valor;
	}
	
	public void setStatus(String status) {
		
		this.status=status;
	}
	
public void setUsuario(String usuario) {
		
		this.status=usuario;
	}
	
	
}

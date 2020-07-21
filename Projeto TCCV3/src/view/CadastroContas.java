package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.bean.CadastroFinanceiro;
import model.dao.CadastroDAOFinanceiro;

public class CadastroContas extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnRetornar;
	private JLabel lblContas;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	JComboBox<String> jComboBox1 = new JComboBox<String>();
	public JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroContas dialog = new CadastroContas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CadastroContas() throws FontFormatException, IOException, ClassNotFoundException, SQLException {
		setResizable(false);
		setTitle("Cadastro - M\u00F3dulo Financeiro - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnLimpar.setBounds(353, 97, 89, 23);
		contentPane.add(btnLimpar);
		btnLimpar.setFont(novaFonte);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					
						
						
						if (	
								(textField.getText().length()>0)
				                &&(textField_1.getText().length()>0)
				                && (textField_2.getText().length()>0)
				                && (textField_3.getText().length()>0)
				                && (textField_4.getText().length()>0)) 
				         
				            
							
						{
						
						CadastroFinanceiro cad = new CadastroFinanceiro(0,(String)jComboBox1.getSelectedItem(),textField.getText(),textField_1.getText(),textField_2.getText(),Integer.parseInt(textField_3.getText()),textField_4.getText(),lblNewLabel_2.getText());
					
						
						CadastroDAOFinanceiro caddao = null;
						try {
							caddao = new CadastroDAOFinanceiro();
							
							
							
						} catch (ClassNotFoundException | SQLException e1) {
							System.out.println("");
						}
						
						cad.setTipoConta((String)jComboBox1.getSelectedItem());
						cad.setDescricao(textField.getText());
						cad.setEmpresa(textField_1.getText());
						cad.setVencimento(textField_2.getText());
						cad.setValor(Integer.parseInt(textField_3.getText()));
						cad.setStatus(textField_4.getText());
						cad.setUsuario(lblNewLabel_2.getText());
						
						try {
							caddao.create(cad);
								
							
							
						
					
							
						} catch (Exception e1) {
							
							System.out.println("");
						
						} 
							
							
							
							
						
							
								
								
							
						
						
						
					
							
						
					
							
					
			
						
				        }
						else {
							   JOptionPane.showMessageDialog(null, "Não deixe campos em branco!");
				        } 
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
							
						
						
					}
					
			
				
				
						
						
						
					
				
			}
		);
		btnSalvar.setBounds(353, 74, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.setFont(novaFonte);
		
		btnRetornar = new JButton("Retornar");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModuloFinanceiro mf;
				
				try {
					mf = new ModuloFinanceiro();
					mf.setVisible(true);
					mf.dispose();
					dispose();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRetornar.setBounds(185, 258, 89, 23);
		contentPane.add(btnRetornar);
		btnRetornar.setFont(novaFonte);
		
		JLabel lblCadastro= new JLabel("Cadastro");
		lblCadastro.setBounds(196, 11, 57, 14);
		contentPane.add(lblCadastro);
		lblCadastro.setFont(novaFonte);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(52, 101, 95, 14);
		contentPane.add(lblDescricao);
		lblDescricao.setFont(novaFonte);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(52, 126, 94, 14);
		contentPane.add(lblEmpresa);
		lblEmpresa.setFont(novaFonte);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(52, 201, 94, 14);
		contentPane.add(lblStatus);
		lblStatus.setFont(novaFonte);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(52, 176, 94, 14);
		contentPane.add(lblValor);
		lblValor.setFont(novaFonte);
		
		JLabel lblVencimento = new JLabel("Vencimento:");
		lblVencimento.setBounds(52, 151, 94, 14);
		contentPane.add(lblVencimento);
		lblVencimento.setFont(novaFonte);
		
		JLabel lblNewLabel = new JLabel("Tipo da Conta:");
		lblNewLabel.setBounds(52, 78, 95, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		lblNewLabel_1 = new JLabel("");
		Image img6 = new ImageIcon(this.getClass().getResource("/logo2.jpg")).getImage();
		contentPane.setLayout(null);
		lblNewLabel_1.setIcon(new ImageIcon(img6));
		lblNewLabel_1.setBounds(10, 11, 107, 42);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(157, 98, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBounds(156, 122, 146, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBounds(156, 147, 146, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setBounds(156, 173, 146, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBounds(156, 198, 146, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		jComboBox1 = new JComboBox<String>();
		jComboBox1.setBackground(Color.WHITE);
		jComboBox1.setBounds(157, 75, 146, 20);
		contentPane.add(jComboBox1);
		jComboBox1.addItem("Pagar");
		jComboBox1.addItem("Receber");
		jComboBox1.setFont(novaFonte);
		
		lblContas = new JLabel("Contas");
		lblContas.setBounds(205, 36, 118, 14);
		contentPane.add(lblContas);
		setLocationRelativeTo(null);
		lblContas.setFont(novaFonte);
		
	
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(157, 229, 46, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_3.setBounds(52, 226, 46, 14);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
	
		
	}

}

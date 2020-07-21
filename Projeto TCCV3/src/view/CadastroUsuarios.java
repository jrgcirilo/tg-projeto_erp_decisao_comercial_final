package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

import model.bean.CadastroLogin;
import model.dao.CadastroDAOLogin;

public class CadastroUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_3;
	private JButton btnSenha;
	private JTextField textField;
	private JTextField textField_2;
	
	JComboBox<String> jComboBox1 = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroUsuarios dialog = new CadastroUsuarios();
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
	 */
	public CadastroUsuarios() throws FontFormatException, IOException {
		setTitle("Cadastro - Usu\u00E1rios - Mini-ERP Decis\u00E3o Comercial");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		JButton btnNewButton = new JButton("Retornar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
			}
		});
		btnNewButton.setBounds(198, 226, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setFont(novaFonte);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (	
						(textField.getText().length()>0)
		                && (textField_2.getText().length()>0)) {
				
				CadastroLogin cad = new CadastroLogin(0,textField.getText(),(String)jComboBox1.getSelectedItem(),textField_2.getText());
			
				
				CadastroDAOLogin caddao = null;
				caddao = new CadastroDAOLogin();
				
				cad.setnomeUsuario(textField.getText());
				cad.settipoUsuario((String)jComboBox1.getSelectedItem());
				cad.setSenha(textField_2.getText());
				
				try {
					caddao.create(cad);
					
				
					
				} catch (Exception e1) {
					
					System.out.println("");
				
				} finally {
					textField.setText(null);
					jComboBox1.setSelectedItem(null);
					textField_2.setText(null);
				}
				
				
			}
				else {
					   JOptionPane.showMessageDialog(null, "Não deixe campos em branco!");
		        } 
			}});
		btnSalvar.setBounds(337, 85, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.setFont(novaFonte);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				jComboBox1.setSelectedItem("Padrão");
				textField_2.setText("");
			}
		});
		btnLimpar.setBounds(337, 131, 89, 23);
		contentPane.add(btnLimpar);
		setLocationRelativeTo(null);
		btnLimpar.setFont(novaFonte);
	
		btnSenha = new JButton("Gerar");
		btnSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String senha = "";
				
			    String[] carct ={"0","1","2","3","4","5","6","7","8","9"};

			   

			    for (int x=0; x<6; x++){
			     int j = (int) (Math.random()*carct.length);
			     senha += carct[j];
			    }
			    
			  

			    textField_2.setText(senha);
			    
			}
		});
		btnSenha.setBounds(337, 177, 89, 23);
		contentPane.add(btnSenha);
		btnSenha.setFont(novaFonte);
		
		jComboBox1 = new JComboBox<String>();
		jComboBox1.setBackground(Color.WHITE);
		jComboBox1.setBounds(170, 132, 143, 20);
		contentPane.add(jComboBox1);
		jComboBox1.setFont(novaFonte);
		
		JLabel lblUsurios = new JLabel("Usu\u00E1rios");
		lblUsurios.setBounds(200, 36, 69, 14);
		contentPane.add(lblUsurios);
		setLocationRelativeTo(null);
		jComboBox1.addItem("Padrão");
		jComboBox1.addItem("Administrador");
		jComboBox1.addItem("Auxiliar");
		lblUsurios.setFont(novaFonte);
		
		JLabel lblCadastroDeUsurio = new JLabel("Cadastro");
		lblCadastroDeUsurio.setBounds(198, 11, 68, 14);
		contentPane.add(lblCadastroDeUsurio);
		lblCadastroDeUsurio.setFont(novaFonte);
		
		JLabel lblNewLabel = new JLabel("Nome de usu\u00E1rio:");
		lblNewLabel.setBounds(50, 89, 110, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de usu\u00E1rio:");
		lblNewLabel_1.setBounds(50, 135, 110, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setBounds(50, 181, 69, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("");
		Image img6 = new ImageIcon(this.getClass().getResource("/logo2.jpg")).getImage();
		contentPane.setLayout(null);
		lblNewLabel_3.setIcon(new ImageIcon(img6));
		lblNewLabel_3.setBounds(10, 11, 107, 42);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(170, 86, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(170, 178, 145, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
	}

}

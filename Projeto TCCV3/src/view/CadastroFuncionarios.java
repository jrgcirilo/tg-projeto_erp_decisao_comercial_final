package view;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.bean.CadastroRecursosHumanos;
import model.dao.CadastroDAORecursosHumanos;

public class CadastroFuncionarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnRetornar;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	public JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroFuncionarios dialog = new CadastroFuncionarios();
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
	public CadastroFuncionarios() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("Cadastro - M\u00F3dulo Recursos Humanos - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(353, 71, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if (	
						(textField.getText().length()>0)
		                &&(textField_1.getText().length()>0)
		                && (textField_2.getText().length()>0)
		                && (textField_3.getText().length()>0)
		                && (textField_4.getText().length()>0)
		                && (textField_5.getText().length()>0)
						&& (textField_6.getText().length()>0)
						&& (textField_7.getText().length()>0)
						&& (textField_8.getText().length()>0)) {
					
				CadastroRecursosHumanos cad = new CadastroRecursosHumanos(0,textField_7.getText(), textField_8.getText(), textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_6.getText(), textField_4.getText(),Float.parseFloat(textField_5.getText()),lblNewLabel_4.getText());
			
				
				CadastroDAORecursosHumanos caddao = null;
				try {
					caddao = new CadastroDAORecursosHumanos();
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("");
				}
				
				cad.setRG(textField_8.getText());
				cad.setCPF(textField_7.getText());
				cad.setNome(textField.getText());
				cad.setEndereco(textField_1.getText());
				cad.setTelefone(textField_2.getText());
				cad.setEmail(textField_3.getText());
				cad.setDtContratacao(textField_6.getText());
				cad.setCargo(textField_4.getText());
				cad.setSalario(Float.parseFloat(textField_5.getText()));
				cad.setUsuario(lblNewLabel_4.getText());;
				
				try {
					caddao.create(cad);
					
				
					
				} catch (Exception e) {
					
					System.out.println("");
				
				} finally {
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null);
					textField_4.setText(null);
					textField_5.setText(null);
					textField_6.setText(null);
					textField_7.setText(null);
					textField_8.setText(null);
				}
				
			
				
				
				
				
				
				}
				else {
					   JOptionPane.showMessageDialog(null, "Não deixe campos em branco!");
		        } 
				
				
					
				
				
			
				
			}
		});
		btnSalvar.setFont(novaFonte);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("AR CENA", Font.PLAIN, 16));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
			}
		});
		btnLimpar.setBounds(353, 96, 89, 23);
		contentPane.add(btnLimpar);
		btnLimpar.setFont(novaFonte);
		
		btnRetornar = new JButton("Retornar");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRetornar.setBounds(177, 304, 89, 23);
		contentPane.add(btnRetornar);
		btnRetornar.setFont(novaFonte);
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setBounds(195, 11, 71, 14);
		contentPane.add(lblCadastro);
		lblCadastro.setFont(novaFonte);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(52, 225, 70, 14);
		contentPane.add(lblCargo);
		lblCargo.setFont(novaFonte);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(52, 177, 70, 14);
		contentPane.add(lblEmail);
		lblEmail.setFont(novaFonte);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(52, 127, 70, 14);
		contentPane.add(lblEndereco);
		lblEndereco.setFont(novaFonte);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(52, 102, 70, 14);
		contentPane.add(lblNome);
		lblNome.setFont(novaFonte);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(52, 152, 70, 14);
		contentPane.add(lblTelefone);
		lblTelefone.setFont(novaFonte);
		
		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");
		lblSalrio.setBounds(52, 250, 70, 14);
		contentPane.add(lblSalrio);
		lblSalrio.setFont(novaFonte);
		
		lblNewLabel_1 = new JLabel("");
		Image img6 = new ImageIcon(this.getClass().getResource("/logo2.jpg")).getImage();
		contentPane.setLayout(null);
		lblNewLabel_1.setIcon(new ImageIcon(img6));
		lblNewLabel_1.setBounds(10, 11, 107, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFuncionrios = new JLabel("Funcion\u00E1rios");
		lblFuncionrios.setBounds(195, 25, 118, 14);
		contentPane.add(lblFuncionrios);
		lblFuncionrios.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBounds(157, 100, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 124, 145, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBounds(157, 148, 145, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setBounds(157, 174, 145, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBounds(157, 222, 145, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setBounds(157, 247, 145, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setFont(novaFonte);
		
		JLabel lblNewLabel = new JLabel("Dt Contrata\u00E7\u00E3o");
		lblNewLabel.setBounds(52, 200, 95, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setBounds(157, 198, 145, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setFont(novaFonte);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(52, 81, 46, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setBounds(157, 78, 145, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setFont(novaFonte);
		
		JLabel lblNewLabel_3 = new JLabel("RG");
		lblNewLabel_3.setBounds(52, 57, 46, 14);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setBounds(157, 55, 145, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setFont(novaFonte);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(157, 278, 46, 14);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		lblNewLabel_5 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_5.setBounds(52, 275, 46, 14);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		/*MaskFormatter mask = new MaskFormatter();
		mask.setValidCharacters("0123456789,"); // adicione os caracteres validos*/
		
	}
}

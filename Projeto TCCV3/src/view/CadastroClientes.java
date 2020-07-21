package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.bean.CadastroCRM;
import model.dao.CadastroDAOCRM;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CadastroClientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblRg;
	private JTextField textField_5;
	private JLabel lblNewLabel;
	private JTextField textField_6;
	private JLabel lblNewLabel_2;
	private JTextField textField_7;
	private JLabel lblNewLabel_3;
	private JTextField textField_8;
	public JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroClientes dialog = new CadastroClientes();
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
	public CadastroClientes() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("Cadastro - Clientes - CRM - M\u00F3dulo Comercial - Mini-ERP Decis\u00E3o Comercial");
		setBounds(100, 100, 480, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		button = new JButton("Salvar");
	
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				if (	
						(textField.getText().length()>0)
		                &&(textField_1.getText().length()>0)
		                && (textField_2.getText().length()>0)
		                && (textField_3.getText().length()>0)
		                && (textField_4.getText().length()>0)
		                && (textField_5.getText().length()>0)
		                && (textField_6.getText().length()>0)
		                && (textField_7.getText().length()>0)
		                && (textField_8.getText().length()>0)
						
						) {
				
				CadastroCRM cad = new CadastroCRM(0,textField_5.getText(),textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_6.getText(),textField_7.getText(),textField_8.getText(),lblNewLabel_4.getText());
			
				
				CadastroDAOCRM caddao = null;
				try {
					caddao = new CadastroDAOCRM();
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("");
				}
				cad.setRG(textField_5.getText());
				cad.setCPF(textField.getText());
				cad.setNome(textField_1.getText());
				cad.setEndereco(textField_2.getText());
				cad.setTelefone(textField_3.getText());
				cad.setEmail(textField_4.getText());
				cad.setDtCadastro(textField_6.getText());
				cad.setNumCartao(textField_7.getText());
				cad.setValidade(textField_8.getText());
				cad.setUsuario(lblNewLabel_4.getText());
				try {
					caddao.create(cad);
					
				
					
				} catch (Exception e1) {
					
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
		button.setBounds(351, 57, 89, 23);
		contentPane.add(button);
		button.setFont(novaFonte);
		
		button_1 = new JButton("Limpar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		button_1.setBounds(351, 81, 89, 23);
		contentPane.add(button_1);
		button_1.setFont(novaFonte);
		
		button_2 = new JButton("Retornar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setBounds(177, 293, 89, 23);
		contentPane.add(button_2);
		button_2.setFont(novaFonte);
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setBounds(195, 11, 71, 14);
		contentPane.add(lblCadastro);
		lblCadastro.setFont(novaFonte);
		
		JLabel lblCargo = new JLabel("Email:");
		lblCargo.setBounds(50, 170, 70, 14);
		contentPane.add(lblCargo);
		lblCargo.setFont(novaFonte);
		
		JLabel lblEmail = new JLabel("Telefone:");
		lblEmail.setBounds(50, 145, 70, 14);
		contentPane.add(lblEmail);
		lblEmail.setFont(novaFonte);
		
		JLabel lblEndereco = new JLabel("Nome:");
		lblEndereco.setBounds(50, 95, 70, 14);
		contentPane.add(lblEndereco);
		lblEndereco.setFont(novaFonte);
		
		JLabel lblNome = new JLabel("CPF:");
		lblNome.setBounds(50, 70, 70, 14);
		contentPane.add(lblNome);
		lblNome.setFont(novaFonte);
		
		JLabel lblTelefone = new JLabel("Endere\u00E7o:");
		lblTelefone.setBounds(50, 120, 70, 14);
		contentPane.add(lblTelefone);
		lblTelefone.setFont(novaFonte);
		
		lblNewLabel_1 = new JLabel("");
		Image img6 = new ImageIcon(this.getClass().getResource("/logo2.jpg")).getImage();
		contentPane.setLayout(null);
		lblNewLabel_1.setIcon(new ImageIcon(img6));
		lblNewLabel_1.setBounds(10, 8, 107, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFuncionrios = new JLabel("Clientes");
		lblFuncionrios.setBounds(195, 28, 118, 14);
		contentPane.add(lblFuncionrios);
		lblFuncionrios.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBounds(155, 70, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 95, 145, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBounds(155, 120, 145, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setBounds(155, 145, 145, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBounds(155, 170, 145, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		lblRg = new JLabel("RG:");
		lblRg.setBounds(50, 46, 46, 14);
		contentPane.add(lblRg);
		lblRg.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setBounds(155, 46, 145, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setFont(novaFonte);
		
		lblNewLabel = new JLabel("Data Cadastro:");
		lblNewLabel.setBounds(50, 195, 95, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setBounds(155, 195, 145, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setFont(novaFonte);
		
		lblNewLabel_2 = new JLabel("N\u00FAmero Cart\u00E3o:");
		lblNewLabel_2.setBounds(50, 220, 95, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setBounds(155, 220, 145, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("Validade:");
		lblNewLabel_3.setBounds(50, 245, 95, 14);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setBounds(155, 245, 145, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setFont(novaFonte);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(155, 270, 46, 14);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		lblNewLabel_5 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_5.setBounds(50, 270, 46, 14);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
	}

}

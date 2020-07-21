package view;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.dao.CadastroDAOLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEntrar;
	private JLabel label;
	private JPasswordField passwordField;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public TelaLogin() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("\"Login - Mini-ERP Decis\u00E3o Comercial\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
	    
	    Font novaFonteTitulo = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(20f);
	    GraphicsEnvironment geT = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    geT.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de usu\u00E1rio:");
		lblNomeDeUsurio.setBounds(351, 321, 114, 14);
		contentPane.add(lblNomeDeUsurio);
		lblNomeDeUsurio.setFont(novaFonte);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(351, 350, 46, 14);
		contentPane.add(lblSenha);
		lblSenha.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBounds(468, 318, 170, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastroDAOLogin dao = new CadastroDAOLogin();
				
				try {
					if(dao.checkLogin(textField.getText(), new String(passwordField.getPassword()))) {
						//TelaInicial telainicial = new TelaInicial();
						//telainicial.lblNewLabel.setText("Bem-vindo, "+textField.getText()+"!");
						//telainicial.setVisible(true);
						
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Senha incorreta!");
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

			
		});
		passwordField = new JPasswordField();
		passwordField.setBounds(468, 346, 170, 20);
		contentPane.add(passwordField);
		passwordField.setFont(novaFonte);
		btnEntrar.setBounds(468, 377, 170, 41);
		contentPane.add(btnEntrar);
		btnEntrar.setFont(novaFonte);
		Image img1= new ImageIcon(this.getClass().getResource("/log.png")).getImage();
		btnEntrar.setIcon(new ImageIcon(img1));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Login");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(480, 36, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img6 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img6));
		panel_2.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
			
	}
}

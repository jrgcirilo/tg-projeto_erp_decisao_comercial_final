package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.ConnectionFactory;
import view.ModuloFinanceiro;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class TelaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnComercial;
	public JButton btnConfiguracoes;
	public JButton btnDashboard;
	public JButton btnFinanceiro;
	public JButton btnRecursosHumanos;
	private JButton btnLogoff;
	private JButton btnSobre;
	private JLabel label;
	public JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection=null;
	public JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public TelaInicial() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setResizable(false);
		connection=ConnectionFactory.getConnection();
		setTitle("M\u00F3dulos - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
	    
	    Font novaFonteTitulo = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(20f);
	    GraphicsEnvironment geT = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    geT.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		btnComercial = new JButton("Comercial");
		Image img2 = new ImageIcon(this.getClass().getResource("/target.png")).getImage();
		btnComercial.setIcon(new ImageIcon(img2));
		btnComercial.setBounds(305, 162, 258, 205);
		btnComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModuloComercial mc = new ModuloComercial();
					mc.setVisible(true);
					mc.lblNewLabel.setText(lblNewLabel_1.getText());
				} catch (ClassNotFoundException | SQLException e) {
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
		contentPane.add(btnComercial);
		btnComercial.setFont(novaFonte);
		
		btnConfiguracoes = new JButton("Configura\u00E7\u00F5es");
		btnConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaConfiguracoes().setVisible(true);
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConfiguracoes.setBounds(484, 653, 170, 41);
		contentPane.add(btnConfiguracoes);
		btnConfiguracoes.setFont(novaFonte);
		Image img12= new ImageIcon(this.getClass().getResource("/config.png")).getImage();
		btnConfiguracoes.setIcon(new ImageIcon(img12));
		
		btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaDashboard().setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
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
		Image img4 = new ImageIcon(this.getClass().getResource("/dashboard.png")).getImage();
		btnDashboard.setIcon(new ImageIcon(img4));
		btnDashboard.setBounds(573, 378, 258, 189);
		contentPane.add(btnDashboard);
		btnDashboard.setFont(novaFonte);
		
		btnFinanceiro = new JButton("Financeiro");
		Image img3 = new ImageIcon(this.getClass().getResource("/money.png")).getImage();
		btnFinanceiro.setIcon(new ImageIcon(img3));
		btnFinanceiro.setBounds(573, 162, 258, 205);
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					
					ModuloFinanceiro modulofinanceiro = new ModuloFinanceiro();
					modulofinanceiro.setVisible(true);
					modulofinanceiro.consultaDAOFinanceiro();
					modulofinanceiro.rdbtnNewRadioButton_2.setSelected(true);
					modulofinanceiro.lblNewLabel_1.setText(lblNewLabel_1.getText());
				
				} catch (ClassNotFoundException | SQLException e1) {
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
		contentPane.add(btnFinanceiro);
		btnFinanceiro.setFont(novaFonte);
		Image img7 = new ImageIcon(this.getClass().getResource("/logoff.png")).getImage();
		
		btnRecursosHumanos = new JButton("<html>Recursos<br>Humanos</html>");
		Image img = new ImageIcon(this.getClass().getResource("/people.png")).getImage();
		btnRecursosHumanos.setIcon(new ImageIcon(img));
		btnRecursosHumanos.setBounds(305, 378, 258, 189);
		btnRecursosHumanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModuloRecursosHumanos mrh = new ModuloRecursosHumanos();
					mrh.setVisible(true);
					mrh.lblNewLabel_11.setText(lblNewLabel_1.getText());
					
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnRecursosHumanos);
		btnRecursosHumanos.setFont(novaFonte);
		
		btnSobre = new JButton("Sobre");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaSobre().setVisible(true);
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSobre.setBounds(484, 705, 170, 41);
		contentPane.add(btnSobre);
		btnSobre.setFont(novaFonte);
		Image img13= new ImageIcon(this.getClass().getResource("/about.png")).getImage();
		btnSobre.setIcon(new ImageIcon(img13));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Módulos");
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
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(696, 40, 240, 14);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(novaFonteTitulo);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(938, 61, 46, 14);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnLogoff = new JButton("Logoff");
		btnLogoff.setBounds(5, 11, 170, 41);
		panel_4.add(btnLogoff);
		btnLogoff.setToolTipText("");
		btnLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaLogin().setVisible(true);
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnLogoff.setIcon(new ImageIcon(img7));
		btnLogoff.setFont(novaFonte);
		
		connection.close();
	
	}
}

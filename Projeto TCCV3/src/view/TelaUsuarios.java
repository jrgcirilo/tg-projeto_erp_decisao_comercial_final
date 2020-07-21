package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.bean.CadastroLogin;
import model.dao.CadastroDAOLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

public class TelaUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCadastrar;
	private JButton btnDireita;
	private JButton btnEditar;
	private JButton btnEsquerda;
	private JButton btnPassword;
	private JButton btnRetornar;
	private JButton btnRemover;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtPesquisar;
	private JLabel label;
	boolean hasBeenClicked = false;
	
	int pos=0;
	
	JComboBox<String> jComboBox1 = new JComboBox<String>();
	private JPanel panel;
	private JTextField textField_3;
	
	public static Connection getConnection() {
		
		String strConexao = "jdbc:mysql://localhost/dbtcc?useTimezone=true&serverTimezone=America/Sao_Paulo";
	  	String user = "root";
	  	String senha = "1234";
		    try {
		    	return DriverManager.getConnection(
						strConexao,
						user,
						senha);
				
		    	
				
		    	
		    } catch ( SQLException e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Banco aberto com sucesso");
			return null;
	}
	
	public static ArrayList<CadastroLogin> BindList(){
		
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Login");
			ArrayList<CadastroLogin> list = new ArrayList<CadastroLogin>();
			while(rs.next()) {
				CadastroLogin u = new CadastroLogin(rs.getInt("codigo_usuario"),rs.getString("nome_usuario"),rs.getString("tipo_usuario"),rs.getString("senha"));
			list.add(u);
			
		}
		return list;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
	
	public void ShowPosInfo(int index) {
		textField.setText(Integer.toString(BindList().get(index).getCodigo()));
		textField_1.setText(BindList().get(index).getnomeUsuario());
		textField_2.setText(BindList().get(index).gettipoUsuario());
		textField_3.setText(BindList().get(index).getSenha());
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarios frame = new TelaUsuarios();
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
	public TelaUsuarios() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("Usu\u00E1rios - Configura\u00E7\u00F5es - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
	    Font novaFonteTitulo = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(20f);
	    GraphicsEnvironment geT = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    geT.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
	    
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new CadastroUsuarios().setVisible(true);
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(790, 183, 170, 41);
		contentPane.add(btnCadastrar);
		btnCadastrar.setFont(novaFonte);
		Image img12= new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnCadastrar.setIcon(new ImageIcon(img12));
		
		btnEditar = new JButton("Alterar");
		btnEditar.setFont(new Font("AR CENA", Font.PLAIN, 16));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(!hasBeenClicked) {
						
						
					
					textField_1.setEditable(true);
					jComboBox1.setVisible(true);
					textField_2.setVisible(false);
				
					btnEditar.setText("Confirmar");
					
					
						hasBeenClicked = true;
						
					
					}
					else
						{
						CadastroLogin cad = new CadastroLogin(Integer.parseInt(textField.getText()),textField_1.getText(),(String)jComboBox1.getSelectedItem(),textField_3.getText());
						
						CadastroDAOLogin caddao = null;
						caddao = new CadastroDAOLogin();
						
						
							
							
							
							
							cad.settipoUsuario((String)jComboBox1.getSelectedItem());
							
							try {
								caddao.update(cad);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						jComboBox1.setVisible(false);
						textField_2.setVisible(true);
						textField_2.setEditable(false);
						textField_3.setVisible(true);
						textField_3.setEditable(false);
						btnEditar.setText("Alterar");
							hasBeenClicked = false;
						}
					ShowPosInfo(pos);
			
			}
				
				
			}
		);
		btnEditar.setBounds(790, 235, 170, 41);
		contentPane.add(btnEditar);
		Image img6 = new ImageIcon(this.getClass().getResource("/left.png")).getImage();
		Image img7 = new ImageIcon(this.getClass().getResource("/right.jpg")).getImage();
		Image img3 = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		btnEditar.setFont(novaFonte);
		Image img13= new ImageIcon(this.getClass().getResource("/edit.png")).getImage();
		btnEditar.setIcon(new ImageIcon(img13));
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastroLogin cad = new CadastroLogin(Integer.parseInt(textField.getText()),textField_1.getText(),textField_2.getText(),textField_3.getText());
				
				CadastroDAOLogin caddao = null;
				caddao = new CadastroDAOLogin();
				
				cad.setCodigo(Integer.parseInt(textField.getText()));
			
				
				try {
					caddao.delete(cad);
					
			
					
				} catch (Exception e1) {
					
					System.out.println("");
				
				} finally {
					pos--;
					if(pos>0) {
						ShowPosInfo(pos);
					}
					else {
						pos=0;
						ShowPosInfo(pos);
					}
			}
				
				
			}
		});
		btnRemover.setBounds(790, 287, 170, 41);
		contentPane.add(btnRemover);
		setLocationRelativeTo(null);
		btnRemover.setFont(novaFonte);
		Image img14= new ImageIcon(this.getClass().getResource("/remove.png")).getImage();
		btnRemover.setIcon(new ImageIcon(img14));
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(227, 183, 541, 578);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(175, 327, 344, 20);
		panel.add(textField_2);
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		lblNewLabel_2 = new JLabel("C\u00F3digo:");
		lblNewLabel_2.setBounds(62, 130, 103, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBounds(175, 127, 344, 20);
		panel.add(textField);
		textField.setEnabled(false);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("Nome de usu\u00E1rio:");
		lblNewLabel_3.setBounds(62, 230, 103, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 227, 344, 20);
		panel.add(textField_1);
		textField_1.setEnabled(false);
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		lblNewLabel_4 = new JLabel("Tipo de usu\u00E1rio:");
		lblNewLabel_4.setBounds(62, 330, 103, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		jComboBox1  = new JComboBox<String>();
		jComboBox1.setBounds(175, 327, 344, 20);
		panel.add(jComboBox1);
		jComboBox1.setBackground(Color.WHITE);
		jComboBox1.setFont(novaFonte);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(175, 27, 344, 20);
		panel.add(txtPesquisar);
		txtPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtPesquisar.setText("");
				txtPesquisar.setFont(novaFonte);
			}
		});
		txtPesquisar.setText("Pesquisar");
		txtPesquisar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{	
				CadastroLogin cad = new CadastroLogin(Integer.parseInt(textField.getText()),textField_1.getText(),"",textField_2.getText()); 
				String texto =txtPesquisar.getText();
				cad.setnomeUsuario(texto);
				
					for(int i = 0; i < BindList().size(); i ++){
			            if(BindList().get(i).getnomeUsuario().contains(cad.getnomeUsuario())) {
			            	//System.out.println("encontrado"+BindList().get(i).getnomeUsuario());
			            	textField.setText(Integer.toString(BindList().get(i).getCodigo()));
			            	textField_1.setText(BindList().get(i).getnomeUsuario());
			            	textField_2.setText(BindList().get(i).gettipoUsuario());
			            	textField_3.setText(BindList().get(i).getSenha());
			            	break;
			            }
			            
			               
			}
					
						
            
					
		}}});
		txtPesquisar.setToolTipText("Digite o nome de usuário de um funcionário");
		txtPesquisar.setColumns(10);
		txtPesquisar.setFont(novaFonte);
		
		jComboBox1.addItem("Padrão");
		jComboBox1.addItem("Administrador");
		jComboBox1.addItem("Auxiliar");
		jComboBox1.setVisible(false);
		jComboBox1.setFont(novaFonte);
		
		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setBounds(62, 427, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setBounds(175, 427, 344, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Usu\u00E1rios");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(480, 36, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img1 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img1));
		panel_2.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.setBounds(4, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		Image img11 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img11));
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConfiguracoes tc;
				try {
					tc = new TelaConfiguracoes();
					tc.setVisible(true);
					dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRetornar.setFont(novaFonte);
		
		
		btnPassword = new JButton("Senha");
		btnPassword.setBounds(4, 63, 170, 39);
		panel_4.add(btnPassword);
		btnPassword.setToolTipText("");
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String senha = "";
				CadastroLogin cad = new CadastroLogin(Integer.parseInt(textField.getText()),textField_1.getText(),textField_2.getText(),senha);
		        CadastroDAOLogin caddao = new CadastroDAOLogin();

			    String[] carct ={"0","1","2","3","4","5","6","7","8","9"};

			   

			    for (int x=0; x<6; x++){
			     int j = (int) (Math.random()*carct.length);
			     senha += carct[j];
			    }
			    
			  

			    System.out.println("A senha gerada é: "+senha);
			    JOptionPane.showMessageDialog(null,
			    		"A senha gerada é: "+senha);
			    
			    System.out.println(senha);
			    
				cad.setSenha(senha);
			    try {
					caddao.update(cad);
					ShowPosInfo(pos);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPassword.setIcon(new ImageIcon(img3));
		btnPassword.setFont(novaFonte);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(227, 122, 541, 50);
		contentPane.add(panel_1);
		
		btnEsquerda = new JButton("");
		panel_1.add(btnEsquerda);
		btnEsquerda.setToolTipText("Recuar");
		btnEsquerda.setIcon(new ImageIcon(img6));
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos--;
				if(pos>0) {
					ShowPosInfo(pos);
				}
				else {
					pos=0;
					ShowPosInfo(pos);
				}
			}
		});
		
		btnDireita = new JButton("");
		panel_1.add(btnDireita);
		btnDireita.setToolTipText("Avan\u00E7ar");
		btnDireita.setIcon(new ImageIcon(img7));
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos++;
				if(pos<BindList().size()) {
					ShowPosInfo(pos);
				}
				else {
					pos=BindList().size() -1;
					ShowPosInfo(pos);
					JOptionPane.showMessageDialog(null, "Fim da lista!");
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Contas");
		lblNewLabel_1.setBounds(480, 97, 46, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		ShowPosInfo(pos);
	}
}

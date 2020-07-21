package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import model.bean.CadastroCRM;
import model.dao.CadastroDAOCRM;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;


public class TelaCRM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRetornar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel label;

	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	public JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField txtPesquisar;
	boolean hasBeenClicked = false;
	
	int pos=0;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JLabel lblNewLabel_8;
	private JTextField textField_9;
	public JLabel lblNewLabel_9;
	
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
	
	public static ArrayList<CadastroCRM> BindList(){
		
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM CRM");
			ArrayList<CadastroCRM> list = new ArrayList<CadastroCRM>();
			while(rs.next()) {
				CadastroCRM u = new CadastroCRM(rs.getInt("codigo_cliente"),rs.getString("RG"),rs.getString("CPF"),rs.getString("nome"),rs.getString("endereco"),rs.getString("telefone"),rs.getString("email"),rs.getString("dt_cadastro"),rs.getString("num_cartao"),rs.getString("validade"),rs.getString("usuario"));
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
		textField_9.setText(BindList().get(index).getRG());
		textField_1.setText(BindList().get(index).getCPF());
		textField_2.setText(BindList().get(index).getNome());
		textField_3.setText(BindList().get(index).getEndereco());
		textField_4.setText(BindList().get(index).getTelefone());
		textField_5.setText(BindList().get(index).getEmail());
		textField_6.setText(BindList().get(index).getDtCadastro());
		textField_7.setText(BindList().get(index).getNumCartao());
		textField_8.setText(BindList().get(index).getValidade());
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCRM frame = new TelaCRM();
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
	public TelaCRM() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("CRM - M\u00F3dulo Comercial - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
	    
	    Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
	    
	    Font novaFonteTitulo = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(20f);
	    GraphicsEnvironment geT = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    geT.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(227, 183, 541, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		Image img2 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		Image img8 = new ImageIcon(this.getClass().getResource("/left.png")).getImage();
		Image img9 = new ImageIcon(this.getClass().getResource("/report.png")).getImage();
		Image img10 = new ImageIcon(this.getClass().getResource("/right.jpg")).getImage();
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CadastroClientes cc = new CadastroClientes();
					cc.setVisible(true);
					cc.lblNewLabel_4.setText(lblNewLabel_9.getText());
					
					
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		});
		btnNewButton.setBounds(790, 183, 170, 41);
		contentPane.add(btnNewButton);
		btnNewButton.setFont(novaFonte);
		Image img12= new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img12));
		
		btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(!hasBeenClicked) {
					
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					textField_3.setEditable(true);
					textField_4.setEditable(true);
					textField_5.setEditable(true);
					textField_6.setEditable(true);
					textField_7.setEditable(true);
					textField_8.setEditable(true);
					textField_9.setEditable(true);
				
					btnNewButton_1.setText("Confirmar");
					
					
						hasBeenClicked = true;
						
					
					}
					else
						{
						CadastroCRM cad = new CadastroCRM(Integer.parseInt(textField.getText()),textField_9.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_5.getText(),textField_6.getText(),textField_7.getText(),textField_8.getText(),"");
						
						CadastroDAOCRM caddao = null;
						try {
							caddao = new CadastroDAOCRM();
							
						} catch (ClassNotFoundException | SQLException e1) {
							System.out.println("");
						}
						
						
						if (	
								(textField_1.getText().length()>0)
				                && (textField_2.getText().length()>0)
				                && (textField_3.getText().length()>0)
				                && (textField_4.getText().length()>0)
				                && (textField_5.getText().length()>0)
				                && (textField_6.getText().length()>0)
				                && (textField_7.getText().length()>0)
				                && (textField_8.getText().length()>0)
				                && (textField_9.getText().length()>0)
								) {	
							
							cad.setRG(textField_9.getText());
							cad.setCPF(textField_1.getText());
							cad.setNome(textField_2.getText());
							cad.setEndereco(textField_3.getText());
							cad.setTelefone(textField_4.getText());
							cad.setEmail(textField_5.getText());
							cad.setDtCadastro(textField_6.getText());
							cad.setNumCartao(textField_7.getText());
							cad.setValidade(textField_8.getText());
							cad.setCodigo(Integer.parseInt(textField.getText()));
							caddao.update(cad);
						
						
						
						textField_1.setEditable(false);
						textField_2.setEditable(false);
						textField_3.setEditable(false);
						textField_4.setEditable(false);
						textField_5.setEditable(false);
						textField_6.setEditable(false);
						textField_7.setEditable(false);
						textField_8.setEditable(false);
						textField_9.setEditable(false);
						btnNewButton_1.setText("Alterar");
							hasBeenClicked = false;
						}else {
							JOptionPane.showMessageDialog(null, "Não deixe campos vazios!");
						}
						}
			}
		});
		btnNewButton_1.setBounds(790, 236, 170, 41);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setFont(novaFonte);
		Image img13= new ImageIcon(this.getClass().getResource("/edit.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img13));
		
		btnNewButton_2 = new JButton("Remover");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				CadastroCRM cad = new CadastroCRM(Integer.parseInt(textField.getText()),textField_9.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_5.getText(),textField_6.getText(),textField_7.getText(),textField_8.getText(),"");
				
				CadastroDAOCRM caddao = null;
				try {
					caddao = new CadastroDAOCRM();
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("");
				}
				
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
		btnNewButton_2.setBounds(790, 288, 170, 41);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setFont(novaFonte);
		Image img14= new ImageIcon(this.getClass().getResource("/remove.png")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(img14));

		lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(26, 219, 94, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("Endere\u00E7o:");
		lblNewLabel_3.setBounds(26, 267, 94, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		lblNewLabel_4 = new JLabel("Telefone:");
		lblNewLabel_4.setBounds(26, 316, 94, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		lblNewLabel_5 = new JLabel("E-mail:");
		lblNewLabel_5.setBounds(26, 363, 94, 14);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(26, 130, 94, 14);
		panel.add(lblCpf);
		lblCpf.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(130, 77, 401, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setBounds(130, 127, 401, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setBounds(130, 216, 401, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setBounds(130, 264, 401, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		textField_4.setBounds(130, 313, 401, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setBackground(Color.WHITE);
		textField_5.setEditable(false);
		textField_5.setBounds(130, 360, 401, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setFont(novaFonte);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(26, 80, 94, 14);
		panel.add(lblCdigo);
		lblCdigo.setFont(novaFonte);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(130, 31, 401, 20);
		panel.add(txtPesquisar);
		txtPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtPesquisar.setText("");
				txtPesquisar.setFont(novaFonte);
			}
		});
		txtPesquisar.setFont(novaFonte);
		txtPesquisar.setText("Pesquisar");
		txtPesquisar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{	
				CadastroCRM cad = new CadastroCRM(Integer.parseInt(textField.getText()),textField_9.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_5.getText(),textField_6.getText(),textField_7.getText(),textField_8.getText(),""); 
				String texto =txtPesquisar.getText();
				cad.setNome(texto);
				
					for(int i = 0; i < BindList().size(); i ++){
			            if(BindList().get(i).getNome().contains(cad.getNome())) {
			            	//System.out.println("encontrado"+BindList().get(i).getNome());
			            	textField.setText(Integer.toString(BindList().get(i).getCodigo()));
			            	textField_9.setText(BindList().get(i).getRG());
			            	textField_1.setText(BindList().get(i).getCPF());
			            	textField_2.setText(BindList().get(i).getNome());
			            	textField_3.setText(BindList().get(i).getEndereco());
			            	textField_4.setText(BindList().get(i).getTelefone());
			            	textField_5.setText(BindList().get(i).getEmail());
			            	textField_6.setText(BindList().get(i).getDtCadastro());
			            	textField_7.setText(BindList().get(i).getNumCartao());
			            	textField_8.setText(BindList().get(i).getValidade());
			            	break;
			            }
			            
			               
			}
					
						
            
					
		}}});
		txtPesquisar.setToolTipText("Digite o nome de um cliente");
		txtPesquisar.setColumns(10);
		txtPesquisar.setFont(novaFonte);
		
		lblNewLabel_8 = new JLabel("RG:");
		lblNewLabel_8.setBounds(26, 174, 94, 14);
		panel.add(lblNewLabel_8);
		lblNewLabel_8.setFont(novaFonte);
		
		textField_9 = new JTextField();
		textField_9.setBackground(Color.WHITE);
		textField_9.setEditable(false);
		textField_9.setBounds(130, 171, 401, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setFont(novaFonte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("CRM");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(480, 36, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
	
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img11 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img11));
		panel_2.add(label);
		
		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(938, 61, 46, 14);
		panel_2.add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.setBounds(4, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		btnRetornar.setIcon(new ImageIcon(img2));
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					dispose();
				
			}
		});
		btnRetornar.setFont(novaFonte);
		
		JButton btnRelatrio = new JButton("Relat\u00F3rio");
		btnRelatrio.setBounds(4, 63, 170, 41);
		panel_4.add(btnRelatrio);
		btnRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					RelatorioCRM frame;
					frame = new RelatorioCRM();
					frame.setVisible(true);
					frame.importarClientes();
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
		btnRelatrio.setToolTipText("");
		btnRelatrio.setIcon(new ImageIcon(img9));
		btnRelatrio.setFont(novaFonte);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(227, 122, 541, 50);
		contentPane.add(panel_1);
		
		JButton button = new JButton("");
		panel_1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		button.setToolTipText("Recuar");
		button.setIcon(new ImageIcon(img8));
		
		JButton button_2 = new JButton("");
		panel_1.add(button_2);
		button_2.addActionListener(new ActionListener() {
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
		button_2.setToolTipText("Avan\u00E7ar");
		button_2.setIcon(new ImageIcon(img10));
		
		lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setBounds(473, 97, 46, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cart\u00E3o Fidelidade", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(227, 614, 541, 147);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Dt Cadastro:");
		lblNewLabel_1.setBounds(26, 38, 94, 14);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		JLabel lblNewLabel_6 = new JLabel("N\u00BA Cart\u00E3o");
		lblNewLabel_6.setBounds(26, 76, 94, 14);
		panel_3.add(lblNewLabel_6);
		lblNewLabel_6.setFont(novaFonte);
		
		JLabel lblNewLabel_7 = new JLabel("Validade:");
		lblNewLabel_7.setBounds(26, 111, 94, 14);
		panel_3.add(lblNewLabel_7);
		lblNewLabel_7.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.WHITE);
		textField_6.setEditable(false);
		textField_6.setBounds(130, 35, 401, 20);
		panel_3.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setBackground(Color.WHITE);
		textField_7.setEditable(false);
		textField_7.setBounds(130, 73, 401, 20);
		panel_3.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setBackground(Color.WHITE);
		textField_8.setEditable(false);
		textField_8.setBounds(130, 108, 401, 20);
		panel_3.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setFont(novaFonte);
		
		ShowPosInfo(pos);
		
	}
}

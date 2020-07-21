package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.bean.CadastroRecursosHumanos;
import model.dao.CadastroDAORecursosHumanos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.border.TitledBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ModuloRecursosHumanos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCadastrar;
	private JButton btnEsquerda;
	private JButton btnEditar;
	private JButton btnDireita;
	private JButton btnHollerith;
	private JButton btnRemover;
	private JButton btnRelatorio;
	private JButton btnRetornar;
	private JLabel label;
	private JPanel panel;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField txtPesquisar;
	private JPanel panel_1;
	private JPanel panel_5;
	private JLabel lblNewLabel_7;
	
	boolean hasBeenClicked = false;
	
	int pos=0;
	
	Locale localeBR = new Locale("pt","BR");
	
	private JLabel lblNewLabel_8;
	private JTextField textField_7;
	private JLabel lblNewLabel_9;
	private JTextField textField_8;
	private JLabel lblNewLabel_10;
	private JTextField textField_9;
	public JLabel lblNewLabel_11;
	
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
	
	public static ArrayList<CadastroRecursosHumanos> BindList(){
		
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM RecursosHumanos");
			ArrayList<CadastroRecursosHumanos> list = new ArrayList<CadastroRecursosHumanos>();
			while(rs.next()) {
				CadastroRecursosHumanos u = new CadastroRecursosHumanos(rs.getInt("codigo_funcionario"),rs.getString("rg"),rs.getString("cpf"),rs.getString("nome"),rs.getString("endereco"),rs.getString("telefone"),rs.getString("email"),rs.getString("data_contratacao"),rs.getString("cargo"),rs.getFloat("salario"),"");
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
		textField_8.setText(BindList().get(index).getRG());
		textField_9.setText(BindList().get(index).getCPF());
		textField_1.setText(BindList().get(index).getNome());
		textField_2.setText(BindList().get(index).getEndereco());
		textField_3.setText(BindList().get(index).getTelefone());
		textField_4.setText(BindList().get(index).getEmail());
		textField_7.setText(BindList().get(index).getDt_Contratacao());
		textField_5.setText(BindList().get(index).getCargo());
		textField_6.setText(Float.toString((BindList().get(index).getSalario())));
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModuloRecursosHumanos frame = new ModuloRecursosHumanos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param telaRecursosHumanos 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public ModuloRecursosHumanos() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("M\u00F3dulo Recursos Humanos - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000,800);
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
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(227, 183, 541, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CadastroFuncionarios cf = new CadastroFuncionarios();
					cf.setVisible(true);
					cf.lblNewLabel_4.setText(lblNewLabel_11.getText());
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(790, 183, 170, 41);
		contentPane.add(btnCadastrar);
		btnCadastrar.setFont(novaFonte);
		Image img12= new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnCadastrar.setIcon(new ImageIcon(img12));
		
		Image img8 = new ImageIcon(this.getClass().getResource("/right.jpg")).getImage();
		
		btnEditar = new JButton("Alterar");
		btnEditar.addActionListener(new ActionListener() {
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
				
					btnEditar.setText("Confirmar");
					
					
						hasBeenClicked = true;
						
					
					}
					else
						{
						CadastroRecursosHumanos cad = new CadastroRecursosHumanos(Integer.parseInt(textField.getText()),textField_8.getText(),textField_9.getText(), textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_7.getText(),textField_5.getText(),Float.parseFloat(textField_6.getText()),"");
						
						CadastroDAORecursosHumanos caddao = null;
						try {
							caddao = new CadastroDAORecursosHumanos();
							
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
							
							cad.setRG(textField_8.getText());
							cad.setCPF(textField_9.getText());
							cad.setNome(textField_1.getText());
							cad.setEndereco(textField_2.getText());
							cad.setTelefone(textField_3.getText());
							cad.setEmail(textField_4.getText());
							cad.setCargo(textField_5.getText());
							cad.setDtContratacao(textField_7.getText());
							cad.setSalario(Float.parseFloat(textField_6.getText()));
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
						btnEditar.setText("Alterar");
							hasBeenClicked = false;
							}else {
								JOptionPane.showMessageDialog(null, "Não deixe campos vazios!");
							}
							
							
						}
					
			}
		});
		btnEditar.setBounds(790, 235, 170, 41);
		contentPane.add(btnEditar);
		btnEditar.setFont(novaFonte);
		Image img13= new ImageIcon(this.getClass().getResource("/edit.png")).getImage();
		btnEditar.setIcon(new ImageIcon(img13));
		
		Image img7 = new ImageIcon(this.getClass().getResource("/left.png")).getImage();
		Image img1 = new ImageIcon(this.getClass().getResource("/hollerith.png")).getImage();
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				CadastroRecursosHumanos cad = new CadastroRecursosHumanos(Integer.parseInt(textField.getText()),textField_8.getText(),textField_9.getText(), textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_7.getText(),textField_5.getText(),Float.parseFloat(textField_6.getText()),"");
				
				CadastroDAORecursosHumanos caddao = null;
				try {
					caddao = new CadastroDAORecursosHumanos();
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
		btnRemover.setBounds(790, 287, 170, 41);
		contentPane.add(btnRemover);
		btnRemover.setFont(novaFonte);
		Image img14= new ImageIcon(this.getClass().getResource("/remove.png")).getImage();
		btnRemover.setIcon(new ImageIcon(img14));
		
		Image img3 = new ImageIcon(this.getClass().getResource("/report.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(26, 89, 115, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(26, 225, 115, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o:");
		lblNewLabel_2.setBounds(26, 267, 115, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone:");
		lblNewLabel_3.setBounds(26, 311, 115, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(26, 358, 115, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBounds(151, 86, 355, 20);
		panel.add(textField);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 222, 355, 20);
		panel.add(textField_1);
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 264, 355, 20);
		panel.add(textField_2);
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setBounds(151, 308, 355, 20);
		panel.add(textField_3);
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBounds(151, 355, 355, 20);
		panel.add(textField_4);
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(151, 33, 355, 20);
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
				CadastroRecursosHumanos cad = new CadastroRecursosHumanos(Integer.parseInt(textField.getText()),textField_8.getText(),textField_9.getText(), textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),textField_7.getText(),textField_5.getText(),Float.parseFloat(textField_6.getText()),""); 
				String texto =txtPesquisar.getText();
				cad.setNome(texto);
				
				for(int i = 0; i < BindList().size(); i ++){
			            if(BindList().get(i).getNome().contains(cad.getNome())) {
			            	textField.setText(Integer.toString(BindList().get(i).getCodigo()));
			            	textField_8.setText(BindList().get(i).getRG());
			            	textField_9.setText(BindList().get(i).getCPF());
			            	textField_1.setText(BindList().get(i).getNome());
			            	textField_2.setText(BindList().get(i).getEndereco());
			            	textField_3.setText(BindList().get(i).getTelefone());
			            	textField_4.setText(BindList().get(i).getEmail());
			            	textField_7.setText(BindList().get(i).getDt_Contratacao());
			            	textField_5.setText(BindList().get(i).getCargo());
			            	textField_6.setText(Float.toString(BindList().get(i).getSalario()));
			            	
			            }
			            
				}
			               
			}
					
						
            
					
		}});
		txtPesquisar.setToolTipText("Digite o nome de um funcionário");
		txtPesquisar.setColumns(10);
		txtPesquisar.setFont(novaFonte);
		
		lblNewLabel_9 = new JLabel("CPF:");
		lblNewLabel_9.setBounds(26, 134, 115, 14);
		panel.add(lblNewLabel_9);
		lblNewLabel_9.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setBackground(Color.WHITE);
		textField_8.setEditable(false);
		textField_8.setBounds(151, 131, 355, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setFont(novaFonte);
		
		lblNewLabel_10 = new JLabel("RG:");
		lblNewLabel_10.setBounds(26, 180, 115, 14);
		panel.add(lblNewLabel_10);
		lblNewLabel_10.setFont(novaFonte);
		
		textField_9 = new JTextField();
		textField_9.setBackground(Color.WHITE);
		textField_9.setEditable(false);
		textField_9.setBounds(151, 177, 355, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setFont(novaFonte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Recursos Humanos");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(480, 36, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img16 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img16));
		panel_2.add(label);
		
		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setBounds(938, 61, 46, 14);
		panel_2.add(lblNewLabel_11);
		lblNewLabel_11.setVisible(false);
		
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
		
		btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.setBounds(4, 115, 170, 41);
		panel_4.add(btnRelatorio);
		btnRelatorio.setToolTipText("");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					RelatorioRecursosHumanos rrh;
					rrh = new RelatorioRecursosHumanos();
					rrh.setVisible(true);
					rrh.calculaRelatorio();
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
		btnRelatorio.setIcon(new ImageIcon(img3));
		btnRelatorio.setFont(novaFonte);
		
		btnHollerith = new JButton("Pagamento");
		btnHollerith.setToolTipText("");
		btnHollerith.setBounds(4, 63, 170, 41);
		panel_4.add(btnHollerith);
		btnHollerith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					TelaFolha th;
					th = new TelaFolha();
					th.setVisible(true);
					th.importarFuncionarios();
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnHollerith.setIcon(new ImageIcon(img1));
		btnHollerith.setFont(novaFonte);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(227, 122, 541, 50);
		contentPane.add(panel_1);
		
		btnEsquerda = new JButton("");
		panel_1.add(btnEsquerda);
		btnEsquerda.setToolTipText("Recuar");
		btnEsquerda.setIcon(new ImageIcon(img7));
		
		btnDireita = new JButton("");
		panel_1.add(btnDireita);
		btnDireita.setToolTipText("Avan\u00E7ar");
		btnDireita.setIcon(new ImageIcon(img8));
		
		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Fun\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(226, 614, 542, 147);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Cargo:");
		lblNewLabel_5.setBounds(26, 59, 115, 21);
		panel_5.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		JLabel lblNewLabel_6 = new JLabel("Sal\u00E1rio:");
		lblNewLabel_6.setBounds(26, 96, 115, 21);
		panel_5.add(lblNewLabel_6);
		lblNewLabel_6.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setBounds(151, 96, 355, 20);
		panel_5.add(textField_6);
		textField_6.setBackground(Color.WHITE);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setBounds(151, 59, 355, 20);
		panel_5.add(textField_5);
		textField_5.setBackground(Color.WHITE);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setFont(novaFonte);
		
		lblNewLabel_8 = new JLabel("Dt de Contrata\u00E7\u00E3o:");
		lblNewLabel_8.setBounds(26, 25, 119, 14);
		panel_5.add(lblNewLabel_8);
		lblNewLabel_8.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setBackground(Color.WHITE);
		textField_7.setEditable(false);
		textField_7.setBounds(151, 22, 355, 20);
		panel_5.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setFont(novaFonte);
		
		lblNewLabel_7 = new JLabel("Funcion\u00E1rios");
		lblNewLabel_7.setBounds(464, 97, 89, 14);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setFont(novaFonte);
		
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
		
		
		ShowPosInfo(pos);
	
	}
}

package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import connection.ConnectionFactory;
import model.bean.CadastroFinanceiro;
import model.dao.CadastroDAOFinanceiro;
import net.proteanit.sql.DbUtils;
import repository.FormatRenderer;
import repository.NumberRenderer;

import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.border.EtchedBorder;

public class ModuloFinanceiro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JButton btnCadastrar;
	private JButton btnEditar;
	private JButton btnFluxo;
	private JButton btnRemover;
	private JButton btnRelatorio;
	private JButton btnRetornar;
	private JLabel label;
	public JRadioButton rdbtnNewRadioButton;
	public JRadioButton rdbtnNewRadioButton_1;
	public JRadioButton rdbtnNewRadioButton_2;
	public JScrollPane scrollPane;
	public JTable table;
	boolean hasBeenClicked = false;
	Connection connection=null;
	private JTextField txtPesquisar;
	
	private TableRowSorter<TableModel> filtro;
	
	int pos=0;
	private JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	
	
	public void filtroTabela() {
		
	  filtro.setRowFilter(RowFilter.regexFilter("(?i).*\\Q" + txtPesquisar.getText() + "\\E.*"));
		
		
	}
	
	public void consultaDAOFinanceiro() throws SQLException {
		Connection connection=getConnection();
		String query = "SELECT codigo_conta AS Código, tipo_conta AS 'Tipo de Conta',descricao AS Descrição, empresa AS Empresa, vencimento AS Vencimento, valor AS Valor, status AS Status FROM Financeiro";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
	}
	
	public void consultaPagarDAOFinanceiro() throws SQLException {
		Connection connection=getConnection();
		String query = "SELECT codigo_conta AS Código, tipo_conta AS 'Tipo de Conta',descricao AS Descrição, empresa AS Empresa, vencimento AS Vencimento, valor AS Valor, status AS Status FROM Financeiro WHERE tipo_conta='Pagar'";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
	}
	
	public void consultaReceberDAOFinanceiro() throws SQLException {
		Connection connection=getConnection();
		String query = "SELECT codigo_conta AS Código, tipo_conta AS 'Tipo de Conta',descricao AS Descrição, empresa AS Empresa, vencimento AS Vencimento, valor AS Valor, status AS Status FROM Financeiro WHERE tipo_conta='Receber'";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
	}
	
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
	
	public static ArrayList<CadastroFinanceiro> BindList(){
		
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Financeiro");
			ArrayList<CadastroFinanceiro> list = new ArrayList<CadastroFinanceiro>();
			while(rs.next()) {
				CadastroFinanceiro u = new CadastroFinanceiro(rs.getInt("codigo_conta"),rs.getString("tipo_conta"),rs.getString("descricao"),rs.getString("empresa"),rs.getString("vencimento"),rs.getInt("valor"),rs.getString("status"),rs.getString("usuario"));
			list.add(u);
		}
		return list;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
	
	public void ShowPosInfo(int index) {
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModuloFinanceiro frame = new ModuloFinanceiro();
					frame.setVisible(true);
					frame.rdbtnNewRadioButton_2.setSelected(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public ModuloFinanceiro() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		connection=ConnectionFactory.getConnection();
		setResizable(false);
		setTitle("M\u00F3dulo Financeiro - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo( null );
		
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
					CadastroContas cc = new CadastroContas();
					cc.setVisible(true);
					cc.lblNewLabel_2.setText(lblNewLabel_1.getText());
					
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(790, 148, 170, 41);
		contentPane.add(btnCadastrar);
		btnCadastrar.setFont(novaFonte);
		Image img12= new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnCadastrar.setIcon(new ImageIcon(img12));
		
		btnEditar = new JButton("Alterar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					if(table.getSelectedRow()!=-1) {
					
					
					try {
						CadastroFinanceiro c = new CadastroFinanceiro();
						CadastroDAOFinanceiro cDAO = null;
						cDAO = new CadastroDAOFinanceiro();
						c.setTipoConta((String)table.getValueAt(table.getSelectedRow(), 1));
						c.setDescricao((String) table.getValueAt(table.getSelectedRow(), 2));
						c.setEmpresa((String) table.getValueAt(table.getSelectedRow(), 3));
						c.setStatus((String) table.getValueAt(table.getSelectedRow(), 6));
						c.setCodigoConta((int)table.getValueAt(table.getSelectedRow(), 0));
						
						cDAO.update(c);
						
						
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					if(rdbtnNewRadioButton.isSelected()) {
						try {
							consultaPagarDAOFinanceiro();
							TableColumnModel m = table.getColumnModel();
							m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
							m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(rdbtnNewRadioButton_1.isSelected()) {
						try {
							consultaReceberDAOFinanceiro();
							TableColumnModel m = table.getColumnModel();
							m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
							m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(rdbtnNewRadioButton_2.isSelected()) {
						try {
							consultaDAOFinanceiro();
							TableColumnModel m = table.getColumnModel();
							m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
							m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					}
				
				
			}
		});
		btnEditar.setBounds(790, 200, 170, 41);
		contentPane.add(btnEditar);
		Image img3 = new ImageIcon(this.getClass().getResource("/cash.png")).getImage();
		Image img5 = new ImageIcon(this.getClass().getResource("/report.png")).getImage();
		btnEditar.setFont(novaFonte);
		Image img13= new ImageIcon(this.getClass().getResource("/edit.png")).getImage();
		btnEditar.setIcon(new ImageIcon(img13));
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(table.getSelectedRow()!=-1) {
					
					
					try {
						CadastroFinanceiro c = new CadastroFinanceiro();
						CadastroDAOFinanceiro cDAO = null;
						cDAO = new CadastroDAOFinanceiro();
						c.setCodigoConta((int)table.getValueAt(table.getSelectedRow(), 0));
						cDAO.delete(c);
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					if(rdbtnNewRadioButton.isSelected()) {
						try {
							consultaPagarDAOFinanceiro();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(rdbtnNewRadioButton_1.isSelected()) {
						try {
							consultaReceberDAOFinanceiro();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(rdbtnNewRadioButton_2.isSelected()) {
						try {
							consultaDAOFinanceiro();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
		}});
		btnRemover.setBounds(790, 252, 170, 41);
		contentPane.add(btnRemover);
		btnRemover.setFont(novaFonte);
		Image img14= new ImageIcon(this.getClass().getResource("/remove.png")).getImage();
		btnRemover.setIcon(new ImageIcon(img14));
		
		rdbtnNewRadioButton = new JRadioButton("Contas a Pagar");
		rdbtnNewRadioButton.setBounds(262, 118, 131, 23);
		contentPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT codigo_conta AS Código, tipo_conta AS 'Tipo de Conta',descricao AS Descrição, empresa AS Empresa, vencimento AS Vencimento, valor AS Valor, status AS Status FROM Financeiro WHERE tipo_conta='Pagar'";
				PreparedStatement pst;
				try {
					Connection connection=getConnection();
					pst = connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					connection.close();
					TableColumnModel m = table.getColumnModel();
					m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
					m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			
		
			}
				
		});
		rdbtnNewRadioButton.setFont(novaFonte);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Contas a Receber");
		rdbtnNewRadioButton_1.setBounds(561, 118, 131, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT codigo_conta AS Código, tipo_conta AS 'Tipo de Conta',descricao AS Descrição, empresa AS Empresa, vencimento AS Vencimento, valor AS Valor, status AS Status FROM Financeiro WHERE tipo_conta='Receber'";
				PreparedStatement pst;
				try {
					Connection connection=getConnection();
					pst = connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					connection.close();
					TableColumnModel m = table.getColumnModel();
					m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
					m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
				
		});
		rdbtnNewRadioButton_1.setFont(novaFonte);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Todas");
		rdbtnNewRadioButton_2.setBounds(437, 118, 109, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT codigo_conta AS Código, tipo_conta AS 'Tipo de Conta',descricao AS Descrição, empresa AS Empresa, vencimento AS Vencimento, valor AS Valor, status AS Status FROM Financeiro";
				PreparedStatement pst;
				try {
					Connection connection=getConnection();
					pst = connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					connection.close();
					TableColumnModel m = table.getColumnModel();
					m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
					m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
				
		});
		rdbtnNewRadioButton_2.setFont(novaFonte);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNewRadioButton);
		btnGroup.add(rdbtnNewRadioButton_1);
		btnGroup.add(rdbtnNewRadioButton_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(190, 148, 576, 588);
		contentPane.add(scrollPane);
		
		scrollPane.getViewport().setBackground(Color.white);
		
		table = new JTable(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int colIndex) 
		{if( colIndex !=0 && colIndex !=1 &&  colIndex !=4 && colIndex !=5 ) return true; else return false; } };;
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setFont(novaFonte);
		
		txtPesquisar = new JTextField();
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
			public void keyTyped(KeyEvent arg0) {
				
				txtPesquisar.addKeyListener(new KeyAdapter() {
					
					public void keyReleased (final KeyEvent e) {
					
						String cadena = (txtPesquisar.getText());
						txtPesquisar.setText(cadena);
						filtroTabela();
					}
			});
				
				filtro = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(filtro);
				
		}});
		txtPesquisar.setBounds(790, 119, 170, 20);
		contentPane.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		txtPesquisar.setFont(novaFonte);
		
		ShowPosInfo(pos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Financeiro");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(440, 36, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img77 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img77));
		panel_2.add(label);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(938, 61, 46, 14);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.setBounds(4, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		Image img7 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img7));
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
			public void actionPerformed(ActionEvent e) {
				try {
					RelatorioFinanceiro rf = new RelatorioFinanceiro();
					rf.setVisible(true);
					rf.calculaRelatorio();
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
		btnRelatorio.setIcon(new ImageIcon(img5));
		btnRelatorio.setFont(novaFonte);
		
		btnFluxo = new JButton("Finan\u00E7as");
		btnFluxo.setToolTipText("");
		btnFluxo.setBounds(4, 63, 170, 41);
		panel_4.add(btnFluxo);
		btnFluxo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaFluxo().setVisible(true);
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
		btnFluxo.setIcon(new ImageIcon(img3));
		btnFluxo.setFont(novaFonte);
		
		lblNewLabel = new JLabel("Contas");
		lblNewLabel.setBounds(459, 97, 46, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
	
		consultaDAOFinanceiro();
		
		connection.close();
		
		TableColumnModel m = table.getColumnModel();
		m.getColumn(4).setCellRenderer(FormatRenderer.getDateTimeRenderer());
		m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		
	}
}		


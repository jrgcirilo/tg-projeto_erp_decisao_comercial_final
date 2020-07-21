package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JFrame;

import connection.ConnectionFactory;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import repository.FormatRenderer;
import repository.NumberRenderer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ModuloComercial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JButton btnCRM;
	private JButton btnRelatorio;
	private JButton btnRetornar;
	private JLabel lblDetalhes;
	private JLabel label;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	public JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField txtPesquisar;
	private TableRowSorter<TableModel> filtro;
	Connection connection=null;
	public JLabel lblNewLabel;
	
	public void filtroTabela() {
		
	  filtro.setRowFilter(RowFilter.regexFilter("(?i).*\\Q" + txtPesquisar.getText() + "\\E.*"));
		
		
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModuloComercial frame = new ModuloComercial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	public String vai() {
	int linha = table.getSelectedRow();
	int coluna = table.getSelectedColumn();
	String valor = String.valueOf(table.getValueAt(linha, coluna));
	return valor;
	}
	
	public void consultaDAOVendas() throws SQLException {
		connection=ConnectionFactory.getConnection();
		String query = "SELECT codigo_venda AS 'Código da Venda', data AS Data, valor AS Valor, tipo_pagamento AS 'Tipo do Pagamento', codigo_cliente AS 'Código Cliente' FROM Comercial";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
	}
	
	public void consultaDAODetalhesVendas() throws SQLException, ClassNotFoundException {
		connection=ConnectionFactory.getConnection();
		String query = "SELECT dv.codigo_produto_venda AS 'Código do Produto', dv.descricao_produto AS 'Descrição do Produto', dv.quantidade_unitaria AS 'Quantidade Unitária', dv.valor_unitario AS 'Valor Unitário' FROM comercial c INNER JOIN detalhesVendas dv ON c.codigo_venda = dv.codigo_produto_venda WHERE c.codigo_venda="+textField.getText();
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
		TableColumnModel m1 = table_1.getColumnModel();
		m1.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public ModuloComercial() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setResizable(false);
		connection=ConnectionFactory.getConnection();
		setTitle("M\u00F3dulo Comercial - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		lblDetalhes = new JLabel("Produtos:");
		lblDetalhes.setBounds(470, 439, 71, 14);
		contentPane.add(lblDetalhes);
		lblDetalhes.setFont(novaFonte);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 122, 590, 297);
		contentPane.add(scrollPane);
		scrollPane.setEnabled(false);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(190, 464, 590, 297);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int colIndex) 
		{return false; } };
		scrollPane_1.setViewportView(table_1);
		table_1.getTableHeader().setResizingAllowed(false);
		table_1.setFont(novaFonte);
		
		table = new JTable(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int colIndex) 
		{return false; } };
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if(table.getSelectedRow()!=-1) {
						
						
						int index1 = 0;
				        int index2 = 0;

						if (index1 < 0 || index2 < 0 ||
				                index1 >= table.getColumnCount() ||
				                index2 >= table.getColumnCount()) {
				                JOptionPane.showMessageDialog(table, "Selection out of range!");
				            } else {
				                table.setColumnSelectionInterval(index1, index2);
				            }	
						
					
					textField.setText(vai());
					
					try {
						consultaDAODetalhesVendas();
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				}

		});;
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(551, 436, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		textField.setVisible(false);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setText("Pesquisar:");
		txtPesquisar.setBounds(790, 97, 170, 20);
		contentPane.add(txtPesquisar);
		txtPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtPesquisar.setText("");
				txtPesquisar.setFont(novaFonte);
			}
		});
		txtPesquisar.setColumns(10);
		
		JButton btnNewButton = new JButton("Detalhes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new DetalhesProdutos().setVisible(true);
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
		btnNewButton.setBounds(790, 122, 170, 41);
		contentPane.add(btnNewButton);
		btnNewButton.setFont(novaFonte);
		Image img12= new ImageIcon(this.getClass().getResource("/details.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img12));
		
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
		txtPesquisar.setFont(novaFonte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Comercial");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(480, 37, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img33 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img33));
		panel_2.add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(938, 61, 46, 14);
		panel_2.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					dispose();
				
			}
		});
		btnRetornar.setBounds(4, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		Image img23 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img23));
		btnRetornar.setFont(novaFonte);	
		
		btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.setBounds(4, 115, 170, 41);
		panel_4.add(btnRelatorio);
		btnRelatorio.setToolTipText("");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RelatorioComercial rc = new RelatorioComercial();
					rc.setVisible(true);
					rc.exibirRelatorio();
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
		Image img5 = new ImageIcon(this.getClass().getResource("/report.png")).getImage();
		btnRelatorio.setIcon(new ImageIcon(img5));
		btnRelatorio.setFont(novaFonte);	
		
		btnCRM = new JButton("CRM");
		btnCRM.setToolTipText("");
		btnCRM.setBounds(4, 63, 170, 41);
		panel_4.add(btnCRM);
		btnCRM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCRM tcrm = new TelaCRM();
					tcrm.setVisible(true);
					tcrm.lblNewLabel_9.setText(lblNewLabel.getText());
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/client.png")).getImage();
		btnCRM.setIcon(new ImageIcon(img4));
		btnCRM.setFont(novaFonte);	
		
		JLabel label_1 = new JLabel("Vendas");
		label_1.setBounds(480, 97, 157, 14);
		contentPane.add(label_1);
		label_1.setFont(novaFonte);	
		
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane_1.getViewport().setBackground(Color.white);
		
		consultaDAOVendas();
		
		connection.close();
		
		TableColumnModel m = table.getColumnModel();
		m.getColumn(1).setCellRenderer(FormatRenderer.getDateTimeRenderer());
		m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		
	
	}
}

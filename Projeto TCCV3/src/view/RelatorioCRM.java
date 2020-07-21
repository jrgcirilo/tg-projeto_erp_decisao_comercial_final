package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connection.ConnectionFactory;
import net.proteanit.sql.DbUtils;
import repository.FormatRenderer;
import repository.NumberRenderer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumnModel;

public class RelatorioCRM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRetornar;
	private JButton btnImprimir;
	private JComboBox<String> combobox;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblValorTotalcompras;
	private JLabel lblMdiaDeGastos;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_5;
	Connection connection=null;
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

	
	/*public String vai() {
		int linha = table.getSelectedRow();
		int coluna = table.getSelectedColumn();
		String valor = String.valueOf(table.getValueAt(linha, coluna));
		return valor;
		}*/
	
	public void importarClientes() {

		String str = null;  
		 
        for(int i = 0;i<TelaCRM.BindList().size(); i++ ){  
            str = (String) TelaCRM.BindList().get(i).getNome();  
            combobox.addItem(str);
            
       }    

	}
	
	public void calculaRelatorio() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		textField.setText(Integer.toString(numCliente()));
		textField_3.setText(Integer.toString(numCompras()));
		textField_1.setText(dinheiro.format(somaCompras()));
		float media = somaCompras() / numCompras();
		textField_2.setText(dinheiro.format(media));
		historicoCompras();
		historicoProdutosQ();
		historicoProdutosF();
	}
	
	public int numCliente() throws ClassNotFoundException, SQLException {
		
		//moduloComercial mc = new moduloComercial();
		String str = null;
		String cli = (String) combobox.getSelectedItem();
		int codcli = 0;
		//int nrc = 0;
		for(int i = 0;i<TelaCRM.BindList().size(); i++ ) {
			str = TelaCRM.BindList().get(i).getNome();
				if(str.equals(cli)) {
					codcli=TelaCRM.BindList().get(i).getCodigo();
				}
		}	
		return codcli;
	
	}
	
	public int numCompras() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		ModuloComercial mc = new ModuloComercial();
		
		String valor=textField.getText();
		int cp=0;
		for (int i=0; i<mc.table.getRowCount();i++){
			String str2 = String.valueOf(mc.table.getValueAt(i, 4));
			
		if(str2.equals(valor)) {
		cp=cp+1;
			
		}
		}
		return cp;
		}
	
public float somaCompras() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		ModuloComercial mc = new ModuloComercial();
		
		String valor=textField.getText();
		float vtc=0;
		for (int i=0; i<mc.table.getRowCount();i++){
			String str2 = String.valueOf(mc.table.getValueAt(i, 4));
			
		if(str2.equals(valor)) {
			float vc = (float) mc.table.getValueAt(i, 2);
			vtc+=vc;
			

			
		}
		}
		return vtc;
		}

public void historicoCompras() throws SQLException{
	Connection connection=ConnectionFactory.getConnection();
	String query = "SELECT codigo_venda AS 'Código Venda', data AS Data, valor AS Valor, tipo_pagamento AS 'Tipo de Pagamento' FROM Comercial WHERE codigo_cliente ='"+textField.getText()+"'";
	PreparedStatement pst=connection.prepareStatement(query);
	ResultSet rs=pst.executeQuery();
	table.setModel(DbUtils.resultSetToTableModel(rs));
	pst.close();
	rs.close();
	connection.close();
	TableColumnModel m = table.getColumnModel();
	m.getColumn(1).setCellRenderer(FormatRenderer.getDateTimeRenderer());
	m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
}

	public void historicoProdutosQ() throws SQLException{
	Connection connection=ConnectionFactory.getConnection();
	String query = "SELECT dv.codigo_produto_venda AS 'Código Venda', dv.descricao_produto AS 'Descrição do Produto', dv.quantidade_unitaria AS 'Quantidade Unitária', SUM(quantidade_unitaria) AS 'Quantidade Total' FROM detalhesVendas dv WHERE dv.codigo_produto_cliente='"+textField.getText()+"'GROUP BY descricao_produto ORDER BY SUM(quantidade_unitaria) DESC";
	PreparedStatement pst=connection.prepareStatement(query);
	ResultSet rs=pst.executeQuery();
	table_1.setModel(DbUtils.resultSetToTableModel(rs));
	pst.close();
	rs.close();
	connection.close();
}

public void historicoProdutosF() throws SQLException{
	Connection connection=ConnectionFactory.getConnection();
	String query = "SELECT dv.codigo_produto_venda AS 'Código Venda', dv.descricao_produto AS 'Descrição do Produto', dv.valor_unitario AS 'Valor Unitário', SUM(quantidade_unitaria*valor_unitario) AS 'Valor Total' FROM detalhesVendas dv WHERE dv.codigo_produto_cliente='"+textField.getText()+"'GROUP BY descricao_produto ORDER BY SUM(quantidade_unitaria*valor_unitario) DESC";
	PreparedStatement pst=connection.prepareStatement(query);
	ResultSet rs=pst.executeQuery();
	table_2.setModel(DbUtils.resultSetToTableModel(rs));
	pst.close();
	rs.close();
	connection.close();
//	TableColumnModel m = table_2.getColumnModel();
//	m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
//	m.getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioCRM frame = new RelatorioCRM();
					frame.setVisible(true);
					frame.importarClientes();
					frame.historicoCompras();
					frame.historicoProdutosQ();
					frame.historicoProdutosF();
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
	public RelatorioCRM() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setResizable(false);
		connection=ConnectionFactory.getConnection();
		setTitle("Relat\u00F3rio - CRM - M\u00F3dulo Comercial - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		combobox = new JComboBox<String>();
		combobox.setFont(new Font("AR CENA", Font.PLAIN, 16));
		combobox.setBackground(Color.WHITE);
		combobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					calculaRelatorio();
					numCompras();
					TableColumnModel m = table.getColumnModel();
					m.getColumn(1).setCellRenderer(FormatRenderer.getDateTimeRenderer());
					m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
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
		combobox.setBounds(464, 94, 208, 20);
		combobox.setFont(novaFonte);
		contentPane.add(combobox);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Cliente:");
		lblNewLabel_2.setBounds(408, 97, 46, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(682, 94, 86, 20);
		contentPane.add(textField);
		textField.setVisible(false);
		textField.setFont(novaFonte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Relat\u00F3rio");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(480, 36, 167, 23);
		panel_2.add(lblMdulos);
		lblMdulos.setFont(novaFonteTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(45, -16, 242, 122);
		Image img7 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img7));
		panel_2.add(label);
		label.setFont(novaFonte);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.setBounds(4, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		Image img2 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img2));
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();	
			}
		});
		btnRetornar.setFont(novaFonte);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setJobName("Print data");
				job.setPrintable(new Printable() {
					
					@Override
					public int print(java.awt.Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException  {
					if(pageIndex>0) {
						return Printable.NO_SUCH_PAGE;
					}
					
					Graphics2D g2 = (Graphics2D) graphics;
					g2.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY()*2);
					g2.scale(1.0, 1.0);
					panel.paint(g2);
					return Printable.PAGE_EXISTS;
					}
					
					
				});	
					boolean ok = job.printDialog();
					if(ok) {
						try {
							job.print();
						}catch(PrinterException ex) {  
							
						}
					}
			}
		});
		btnImprimir.setBounds(4, 63, 170, 41);
		panel_4.add(btnImprimir);
		btnImprimir.setToolTipText("");
		Image img8 = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		btnImprimir.setIcon(new ImageIcon(img8));
		btnImprimir.setFont(novaFonte);
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(190, 125, 769, 636);
		contentPane.add(panel);
		panel.setLayout(null);
		
		label_1 = new JLabel("Quantidade:");
		label_1.setBounds(289, 11, 68, 14);
		panel.add(label_1);
		label_1.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(367, 8, 86, 20);
		panel.add(textField_3);
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(242, 60, 150, 144);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblValorTotalcompras = new JLabel("<html>Valor total<br>(compras):</html>");
		lblValorTotalcompras.setBounds(10, 11, 130, 39);
		panel_1.add(lblValorTotalcompras);
		lblValorTotalcompras.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(20, 61, 86, 20);
		panel_1.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(novaFonte);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(402, 60, 150, 144);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblMdiaDeGastos = new JLabel("M\u00E9dia de gastos:");
		lblMdiaDeGastos.setBounds(10, 11, 130, 14);
		panel_3.add(lblMdiaDeGastos);
		lblMdiaDeGastos.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(20, 61, 86, 20);
		panel_3.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(novaFonte);
		
		panel_5 = new JPanel();
		panel_5.setBounds(10, 217, 749, 408);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Vendas:");
		lblNewLabel_1.setBounds(371, 11, 66, 14);
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 729, 88);
		panel_5.add(scrollPane);
		
		table = new JTable() 
			{/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) 
			{return false; } };
			
		
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.getViewport().setBackground(Color.white);
		table.setFont(novaFonte);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 178, 729, 88);
		scrollPane_1.getViewport().setBackground(Color.white);
		panel_5.add(scrollPane_1);
		
		table_1 = new JTable(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int colIndex) 
		{return false; } };
		scrollPane_1.setViewportView(table_1);
		table_1.setFont(novaFonte);
		
		JLabel lblNewLabel = new JLabel("Quantidade:");
		lblNewLabel.setBounds(371, 153, 66, 14);
		panel_5.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 309, 729, 88);
		scrollPane_2.getViewport().setBackground(Color.white);
		panel_5.add(scrollPane_2);
		
		table_2 = new JTable(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int colIndex) 
		{return false; } };
		scrollPane_2.setViewportView(table_2);
		table_2.setFont(novaFonte);
		
		JLabel lblNewLabel_3 = new JLabel("Faturamento:");
		lblNewLabel_3.setBounds(371, 277, 89, 14);
		panel_5.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		connection.close();
	
		
	}
}

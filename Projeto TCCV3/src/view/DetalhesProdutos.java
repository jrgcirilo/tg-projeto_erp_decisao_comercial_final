package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import connection.ConnectionFactory;
import net.proteanit.sql.DbUtils;
import repository.NumberRenderer;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumnModel;

public class DetalhesProdutos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	public JScrollPane scrollPane_1;
	public JTable table_1;
	private JButton btnImprimir;
	private JButton btnRetornar;
	private JLabel label;
	Connection connection=null;
	private JPanel panel;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_4;
	public JLabel lblNewLabel_5;
	public JLabel lblNewLabel_6;
	public JLabel lblNewLabel_7;
	public JLabel lblNewLabel_8;
	public JLabel lblNewLabel_9;
	public JLabel label_1;
	public JLabel label_3;
	public JLabel label_9;
	public JLabel label_7;
	public JLabel label_5;
	public JLabel label_10;
	public JLabel label_8;
	public JLabel label_6;
	public JLabel label_4;
	public JLabel label_2;
	
    public void pegardados() {
    	lblNewLabel.setText((String)table.getModel().getValueAt(0, 0));
    	lblNewLabel_1.setText(String.valueOf(table.getModel().getValueAt(0, 1)));
    	lblNewLabel_2.setText((String)table.getModel().getValueAt(1, 0));
    	lblNewLabel_3.setText(String.valueOf(table.getModel().getValueAt(1, 1)));
    	lblNewLabel_4.setText((String)table.getModel().getValueAt(2, 0));
    	lblNewLabel_5.setText(String.valueOf(table.getModel().getValueAt(2, 1)));
    	lblNewLabel_6.setText((String)table.getModel().getValueAt(3, 0));
    	lblNewLabel_7.setText(String.valueOf(table.getModel().getValueAt(3, 1)));
    	lblNewLabel_8.setText((String)table.getModel().getValueAt(4, 0));
    	lblNewLabel_9.setText(String.valueOf(table.getModel().getValueAt(4, 1)));
    }
	
    public void pegarvendas() {
    	label_1.setText((String)table_1.getModel().getValueAt(0, 0));
    	label_2.setText(String.valueOf(table_1.getModel().getValueAt(0, 1)));
    	label_3.setText((String)table_1.getModel().getValueAt(1, 0));
    	label_4.setText(String.valueOf(table_1.getModel().getValueAt(1, 1)));
    	label_5.setText((String)table_1.getModel().getValueAt(2, 0));
    	label_6.setText(String.valueOf(table_1.getModel().getValueAt(2, 1)));
    	label_7.setText((String)table_1.getModel().getValueAt(3, 0));
    	label_8.setText(String.valueOf(table_1.getModel().getValueAt(3, 1)));
    	label_9.setText((String)table_1.getModel().getValueAt(4, 0));
    	label_10.setText(String.valueOf(table_1.getModel().getValueAt(4, 1)));
    }
    
	public void calculaRelatorio() throws SQLException {
		rankingVendas();
		rankingFaturamento();
	}
	
	public void rankingVendas() throws SQLException{
		Connection connection=ConnectionFactory.getConnection();
		String query = "SELECT descricao_produto AS 'Descrição do Produto', SUM(quantidade_unitaria) AS 'Quantidade total' FROM detalhesVendas GROUP BY descricao_produto ORDER BY quantidade_unitaria DESC   ";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		//table.setDefaultRenderer(1, renderer);
		pst.close();
		rs.close();
		connection.close();
	}
	
	public void rankingFaturamento() throws SQLException{
		Connection connection=ConnectionFactory.getConnection();
		String query = "SELECT descricao_produto AS 'Descricão do Produto', SUM(quantidade_unitaria*valor_unitario) AS 'Valor total' FROM detalhesVendas GROUP BY descricao_produto ORDER BY SUM(quantidade_unitaria*valor_unitario) DESC  ";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
	}
	
	public int somaProdutos() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		int somap=0;
		for (int i=0; i<table.getRowCount();i++){
			String v= String.valueOf(table.getValueAt(i, 1));
			somap+=Float.parseFloat(v);
		
		}
		//System.out.println(somap);
		return somap;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalhesProdutos frame = new DetalhesProdutos();
					frame.setVisible(true);
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
	public DetalhesProdutos() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		connection=ConnectionFactory.getConnection();
		setTitle("Relat\u00F3rio Produtos - M\u00F3dulo Comercial - Mini-ERP Decis\u00E3o Comercial");
		setResizable(false);
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 994, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Detalhes");
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(0, 86, 180, 686);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.setBounds(4, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		Image img9 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img9));
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
		panel.setBounds(190, 97, 769, 664);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRankingVendas = new JLabel("Vendas");
		lblRankingVendas.setBounds(350, 20, 47, 21);
		panel.add(lblRankingVendas);
		lblRankingVendas.setFont(new Font("AR CENA", Font.BOLD, 16));
		lblRankingVendas.setFont(novaFonte);
		
		JLabel lblProdutosMaisVendidos = new JLabel("Faturamento");
		lblProdutosMaisVendidos.setBounds(350, 345, 157, 14);
		panel.add(lblProdutosMaisVendidos);
		lblProdutosMaisVendidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblProdutosMaisVendidos.setFont(novaFonte);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 43, 590, 297);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(novaFonte);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 367, 590, 286);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table_1.setFont(novaFonte);
		
		
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(700, 97, 22, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(732, 97, 27, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(700, 122, 22, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(732, 122, 27, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(700, 147, 22, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(732, 147, 27, 14);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(700, 172, 22, 14);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(732, 172, 27, 14);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);
		
		lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setBounds(700, 194, 22, 14);
		panel.add(lblNewLabel_8);
		lblNewLabel_8.setVisible(false);
		
		lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setBounds(732, 194, 27, 14);
		panel.add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);
		
		label_1 = new JLabel("New label");
		label_1.setBounds(700, 413, 22, 14);
		panel.add(label_1);
		label_1.setVisible(false);
		
		label_3 = new JLabel("New label");
		label_3.setBounds(700, 438, 22, 14);
		panel.add(label_3);
		label_3.setVisible(false);
		
		label_9 = new JLabel("New label");
		label_9.setBounds(700, 510, 22, 14);
		panel.add(label_9);
		label_9.setVisible(false);
		
		label_7 = new JLabel("New label");
		label_7.setBounds(700, 488, 22, 14);
		panel.add(label_7);
		label_7.setVisible(false);
		
		label_5 = new JLabel("New label");
		label_5.setBounds(700, 463, 22, 14);
		panel.add(label_5);
		label_5.setVisible(false);
		
		label_10 = new JLabel("New label");
		label_10.setBounds(732, 510, 27, 14);
		panel.add(label_10);
		label_10.setVisible(false);
		
		label_8 = new JLabel("New label");
		label_8.setBounds(732, 488, 27, 14);
		panel.add(label_8);
		label_8.setVisible(false);
		
		label_6 = new JLabel("New label");
		label_6.setBounds(732, 463, 27, 14);
		panel.add(label_6);
		label_6.setVisible(false);
		
		label_4 = new JLabel("New label");
		label_4.setBounds(732, 438, 27, 14);
		panel.add(label_4);
		label_4.setVisible(false);
		
		label_2 = new JLabel("New label");
		label_2.setBounds(732, 413, 27, 14);
		panel.add(label_2);
		label_2.setVisible(false);
		
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane_1.getViewport().setBackground(Color.white);
		
		calculaRelatorio();
		
		connection.close();
		
		TableColumnModel m = table_1.getColumnModel();
		m.getColumn(1).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		
		pegardados();
		pegarvendas();
		somaProdutos();
		
	}
}

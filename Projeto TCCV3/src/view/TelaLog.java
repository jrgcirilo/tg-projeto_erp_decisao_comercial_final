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

public class TelaLog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private JButton btnImprimir;
	private JButton btnRetornar;
	private JLabel label;
	Connection connection=null;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
  
    
	public void mostraLog() throws SQLException {
		tabelaLog();
		tabelaLog2();
		tabelaLog3();
	}
	
	public void tabelaLog() throws SQLException{
		Connection connection=ConnectionFactory.getConnection();
		String query = "SELECT codigo_cliente AS 'Código Cliente', usuario AS Usuário FROM CRM ORDER BY codigo_cliente ASC   ";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		//table.setDefaultRenderer(1, renderer);
		pst.close();
		rs.close();
		connection.close();
	}
	
	public void tabelaLog2() throws SQLException{
		Connection connection=ConnectionFactory.getConnection();
		String query = "SELECT codigo_conta AS 'Código Conta', usuario AS 'Usuário' FROM Financeiro ORDER BY codigo_conta ASC   ";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		//table.setDefaultRenderer(1, renderer);
		pst.close();
		rs.close();
		connection.close();
	}
	
	public void tabelaLog3() throws SQLException{
		Connection connection=ConnectionFactory.getConnection();
		String query = "SELECT codigo_funcionario AS 'Código Funcionário', usuario AS 'Usuário' FROM RecursosHumanos ORDER BY codigo_funcionario ASC";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table_2.setModel(DbUtils.resultSetToTableModel(rs));
		//table.setDefaultRenderer(1, renderer);
		pst.close();
		rs.close();
		connection.close();
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLog frame = new TelaLog();
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
	public TelaLog() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
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
		
		JLabel lblMdulos = new JLabel("Log");
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
				try {
					TelaConfiguracoes tc;
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
		
		JLabel lblRankingVendas = new JLabel("CRM");
		lblRankingVendas.setBounds(350, 11, 156, 21);
		panel.add(lblRankingVendas);
		lblRankingVendas.setFont(new Font("AR CENA", Font.BOLD, 16));
		lblRankingVendas.setFont(novaFonte);
		
		
		
	
		
		
		
	
		
		lblNewLabel = new JLabel("M\u00F3dulo Financeiro");
		lblNewLabel.setBounds(350, 222, 156, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		lblNewLabel_1 = new JLabel("M\u00F3dulo Recursos Humanos");
		lblNewLabel_1.setBounds(353, 426, 153, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 43, 590, 168);
		panel.add(scrollPane_2);
		scrollPane_2.getViewport().setBackground(Color.white);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		table.setFont(novaFonte);
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 247, 590, 168);
		panel.add(scrollPane);
		scrollPane.getViewport().setBackground(Color.white);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setFont(novaFonte);
		table_1.getTableHeader().setReorderingAllowed(false);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 451, 590, 168);
		panel.add(scrollPane_1);
		scrollPane_1.getViewport().setBackground(Color.white);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		table_2.setFont(novaFonte);
		table_2.getTableHeader().setReorderingAllowed(false);
		
		mostraLog();
		
		connection.close();
		
	}
}

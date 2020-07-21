package view;

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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import connection.ConnectionFactory;
import net.proteanit.sql.DbUtils;
import repository.NumberRenderer;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTabbedPane;

public class TelaFluxo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btnRetornar;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblPerodoDe;
	private JLabel lblNewLabel_3;
	private JLabel lblSaldo;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnImprimir;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JLabel lblNewLabel_8;
	Connection connection=null;
	
	String var;
	String var1;
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	
	
	
	public void consultaContas() throws SQLException, ClassNotFoundException {
		consultaContasP();
		consultaContasR();
	}
	
	public float resolveContas() throws ClassNotFoundException, SQLException {
		float saldo = somaContaR() - somaContaP(); 
		return saldo;
	}
	
	public void consultaContasR() throws SQLException{
		connection=ConnectionFactory.getConnection();
		String query = "SELECT codigo_conta AS Código, valor AS Valor FROM Financeiro WHERE tipo_conta = 'Receber' AND vencimento BETWEEN '"+var+ "' AND '"+var1+"'UNION ALL SELECT codigo_venda AS Código, valor AS Valor FROM Comercial WHERE data BETWEEN '"+var+ "' AND '"+var1+"'";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
		
	}
	
	public float somaContaR() throws ClassNotFoundException, SQLException {
		
		float vtr=0;
		for (int i=0; i<table.getRowCount();i++){
			float vr= (float) table.getValueAt(i, 1);
			vtr+=vr;
			
		
		}
		return vtr;
	}
	
	public void consultaContasP() throws SQLException{
		connection=ConnectionFactory.getConnection();
		String query = "SELECT codigo_conta AS Código, valor AS Valor FROM Financeiro WHERE tipo_conta = 'Pagar' AND vencimento BETWEEN '"+var+ "' AND '"+var1+"'";
		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		connection.close();
		
	}
	
	public float somaContaP() throws ClassNotFoundException, SQLException {
		
		float vtp=0;
		for (int i=0; i<table_1.getRowCount();i++){
			float vp= (float) table_1.getValueAt(i, 1);
			vtp+=vp;
			
		
		}
		return vtp;
	}
	
	public void fazDRE() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		DetalhesProdutos dv = new DetalhesProdutos();
		
		float deducoes=resolveContas()*0;
		textField_2.setText(dinheiro.format(deducoes));
		float recl=somaContaR()-deducoes;
		textField_5.setText(dinheiro.format(recl));
		float cmv=dv.somaProdutos()*0;
		textField_6.setText(dinheiro.format(cmv));
		float lucb=recl-cmv;
		textField_7.setText(dinheiro.format(lucb));
		textField_8.setText(dinheiro.format(somaContaP()));
		float lucop=lucb-somaContaP();
		textField_9.setText(dinheiro.format(lucop));
		float irpf=lucop*0;
		textField_10.setText(dinheiro.format(irpf));
		float lucl=lucop-irpf;
		textField_11.setText(dinheiro.format(lucl));
		
		
	}
	
	
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFluxo frame = new TelaFluxo();
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
	public TelaFluxo() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		connection=ConnectionFactory.getConnection();
		setResizable(false);
		setTitle("Fluxo de Caixa - M\u00F3dulo Financeiro - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Image img2 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		
	
		
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
		
		JLabel lblMdulos = new JLabel("Fluxo de Caixa");
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
					panel_3.paint(g2);
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
		Image img7 = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		btnImprimir.setIcon(new ImageIcon(img7));
		btnImprimir.setFont(novaFonte);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(190, 97, 759, 664);
		contentPane.add(tabbedPane);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Fluxo de Caixa", null, panel_3, null);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setLayout(null);
		
		lblPerodoDe = new JLabel("Per\u00EDodo de:");
		lblPerodoDe.setBounds(204, 15, 67, 21);
		panel_3.add(lblPerodoDe);
		lblPerodoDe.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("a");
		lblNewLabel_3.setBounds(382, 12, 7, 21);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.setBounds(510, 11, 93, 29);
		panel_3.add(btnNewButton);
		btnNewButton.setFont(novaFonte);
		
		panel = new JPanel();
		panel.setBounds(107, 57, 541, 250);
		panel_3.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entradas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Total a receber:");
		lblNewLabel.setBounds(297, 219, 103, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 28, 443, 180);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(novaFonte);
		scrollPane.getViewport().setBackground(Color.white);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(410, 216, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		panel_1 = new JPanel();
		panel_1.setBounds(107, 356, 541, 250);
		panel_3.add(panel_1);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sa\u00EDdas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Total a pagar:");
		lblNewLabel_2.setBounds(298, 218, 96, 14);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(53, 27, 443, 180);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setFont(novaFonte);
		scrollPane_1.getViewport().setBackground(Color.white);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setBounds(410, 218, 86, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(410, 611, 46, 14);
		panel_3.add(lblSaldo);
		lblSaldo.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBounds(517, 608, 86, 20);
		panel_3.add(textField_4);
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
			
					consultaContas();
					resolveContas();
					textField.setText(dinheiro.format(somaContaR()));
					textField_3.setText(dinheiro.format(somaContaP()));
					textField_4.setText(dinheiro.format(resolveContas()));
					textField_1.setText(dinheiro.format(somaContaR()));	
					fazDRE();
					TableColumnModel m = table.getColumnModel();
					m.getColumn(1).setCellRenderer(NumberRenderer.getCurrencyRenderer());
					
					TableColumnModel m2 = table_1.getColumnModel();
					m2.getColumn(1).setCellRenderer(NumberRenderer.getCurrencyRenderer());
				} catch (SQLException e) {  
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
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
		textField_4.setFont(novaFonte);
		
		
		
		dateChooser = new JDateChooser();
		dateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
					        String date = fmt.format(dateChooser.getDate()); 
					        var=date;
			            }
			        }
			    });
		
		
	
		dateChooser.setBounds(281, 15, 87, 20);
		panel_3.add(dateChooser);
		dateChooser.setDateFormatString("yyyy/MM/dd");
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
					        String date = fmt.format(dateChooser_1.getDate()); 
					        var1=date;
			            }
			        }
			    });
		dateChooser_1.setBounds(399, 16, 87, 20);
		panel_3.add(dateChooser_1);
		dateChooser_1.setDateFormatString("yyyy/MM/dd");
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("DRE", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("RECEITA BRUTA:");
		lblNewLabel_1.setBounds(174, 63, 252, 14);
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		JLabel lblNewLabel_5 = new JLabel("Dedu\u00E7\u00F5es de Vendas:");
		lblNewLabel_5.setBounds(174, 123, 252, 14);
		panel_5.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		JLabel lblNewLabel_7 = new JLabel("RECEITA L\u00CDQUIDA:");
		lblNewLabel_7.setBounds(174, 181, 252, 14);
		panel_5.add(lblNewLabel_7);
		lblNewLabel_7.setFont(novaFonte);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(322, 206, 149, 14);
		panel_5.add(lblNewLabel_8);
		lblNewLabel_8.setFont(novaFonte);
		
		JLabel lblNewLabel_9 = new JLabel("Custo do Produto:");
		lblNewLabel_9.setBounds(174, 238, 252, 14);
		panel_5.add(lblNewLabel_9);
		lblNewLabel_9.setFont(novaFonte);
		
		JLabel lblNewLabel_11 = new JLabel("LUCRO BRUTO:");
		lblNewLabel_11.setBounds(174, 299, 252, 14);
		panel_5.add(lblNewLabel_11);
		lblNewLabel_11.setFont(novaFonte);
		
		JLabel lblNewLabel_13 = new JLabel("Despesas Operacionais:");
		lblNewLabel_13.setBounds(174, 361, 252, 14);
		panel_5.add(lblNewLabel_13);
		lblNewLabel_13.setFont(novaFonte);
		
		JLabel lblNewLabel_15 = new JLabel("LUCRO OPERACIONAL:");
		lblNewLabel_15.setBounds(174, 423, 252, 14);
		panel_5.add(lblNewLabel_15);
		lblNewLabel_15.setFont(novaFonte);
		
		JLabel lblNewLabel_17 = new JLabel("IRPF/CSLL:");
		lblNewLabel_17.setBounds(174, 490, 197, 14);
		panel_5.add(lblNewLabel_17);
		lblNewLabel_17.setFont(novaFonte);
		
		JLabel lblNewLabel_18 = new JLabel("LUCRO L\u00CDQUIDO:");
		lblNewLabel_18.setBounds(175, 560, 196, 14);
		panel_5.add(lblNewLabel_18);
		lblNewLabel_18.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setBounds(436, 61, 86, 20);
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setBounds(436, 121, 86, 20);
		panel_5.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setBackground(Color.WHITE);
		textField_5.setEditable(false);
		textField_5.setBounds(436, 179, 86, 20);
		panel_5.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.WHITE);
		textField_6.setEditable(false);
		textField_6.setBounds(436, 236, 86, 20);
		panel_5.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setBackground(Color.WHITE);
		textField_7.setEditable(false);
		textField_7.setBounds(436, 297, 86, 20);
		panel_5.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setBackground(Color.WHITE);
		textField_8.setEditable(false);
		textField_8.setBounds(436, 359, 86, 20);
		panel_5.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setFont(novaFonte);
		
		textField_9 = new JTextField();
		textField_9.setBackground(Color.WHITE);
		textField_9.setEditable(false);
		textField_9.setBounds(436, 421, 86, 20);
		panel_5.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setFont(novaFonte);
		
		textField_10 = new JTextField();
		textField_10.setBackground(Color.WHITE);
		textField_10.setEditable(false);
		textField_10.setBounds(436, 488, 86, 20);
		panel_5.add(textField_10);
		textField_10.setColumns(10);
		textField_10.setFont(novaFonte);
		
		textField_11 = new JTextField();
		textField_11.setBackground(Color.WHITE);
		textField_11.setEditable(false);
		textField_11.setBounds(436, 558, 86, 20);
		panel_5.add(textField_11);
		textField_11.setColumns(10);
		textField_11.setFont(novaFonte);
		
		
	
	
		connection.close();
		
		
		
	}
}

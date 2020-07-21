package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import view.RelatorioFinanceiro;

public class RelatorioComercial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRetornar;
	private JButton btnImprimir;
	private JLabel label;
	public JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPanel panel_1;
	private JLabel label_1;
	private JPanel panel_3;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	
	DetalhesProdutos dv = new DetalhesProdutos();
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
	NumberFormat percentual = NumberFormat.getPercentInstance(localeBR);

	Date now = new Date(System.currentTimeMillis());
	
	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	

	
	
	public void exibirRelatorio() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		calculaRelatorio();
		calculaMetricas();
	}
	
	public void calculaRelatorio() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		ModuloComercial mc = new ModuloComercial();
		
			textField.setText(Integer.toString(mc.table.getRowCount()));
			textField_1.setText(dinheiro.format(somaVendas()));
			textField_2.setText(dinheiro.format(mediaVendas()));
		
	}
		
	public int numVendas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		ModuloComercial mc = new ModuloComercial();
		
		int numv = mc.table.getRowCount();
		
		return numv;
	}
	
			
	public float somaVendas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		ModuloComercial mc = new ModuloComercial();
		float vt=0;
		for (int i=0; i<mc.table.getRowCount();i++){
			float v= (float) mc.table.getValueAt(i, 2);
			vt+=v;
			
		
		}
		return vt;
	}

	public float mediaVendas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		ModuloComercial mc = new ModuloComercial();
		float vt=0;
		int cont=0;
		
		for (int i=0; i<mc.table.getRowCount();i++){
			float v= (float) mc.table.getValueAt(i, 2);
			vt+=v;
			cont=cont+1;
			
		}
		
		
		float mv=vt/cont;
		return mv;
	}
	
	public void calculaMetricas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		textField_3.setText(percentual.format(luc()));
			
		textField_4.setText(percentual.format(lucvr()));
			
			
		
			
	}
	
	/*public float luc() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioFinanceiro rf = new RelatorioFinanceiro();
		
		float lcv=rf.lucro()/somaVendas();
	
		return lcv;
		
	
		
		
	}*/
	
	public float luc() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		RelatorioFinanceiro rf = new RelatorioFinanceiro();
		
		float lcv=rf.lucro()/150000;
		
		return lcv;
		
		
	}
	
	public float lucvr() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioFinanceiro rf = new RelatorioFinanceiro();
		
		float lcvs=rf.lucro()/rf.receitas();
		
		return lcvs;
		
		
		
	}
	

	 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioComercial frame = new RelatorioComercial();
					frame.setVisible(true);
					frame.exibirRelatorio();
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
	public RelatorioComercial() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setResizable(false);
		setTitle("Relat\u00F3rio - M\u00F3dulo Comercial - Mini-ERP Decis\u00E3o Comercial");
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(190, 122, 769, 639);
		contentPane.add(panel);
		contentPane.setLayout(null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setBounds(291, 22, 86, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(358, 19, 86, 20);
		panel.add(textField);
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);
		textField.setEditable(false);
		textField.setEditable(false);
		textField.setEditable(false);
		textField.setEditable(false);
		textField.setFont(novaFonte);
		
		panel_1 = new JPanel();
		panel_1.setBounds(387, 60, 150, 144);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(20, 61, 86, 20);
		panel_1.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setBackground(Color.WHITE);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		JLabel lblNewLabel_5 = new JLabel("Lucratividade");
		lblNewLabel_5.setBounds(10, 11, 130, 39);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		panel_3 = new JPanel();
		panel_3.setBounds(547, 60, 150, 144);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Rentabilidade");
		lblNewLabel_4.setBounds(10, 11, 130, 39);
		panel_3.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(20, 61, 86, 20);
		panel_3.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setBackground(Color.WHITE);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		panel_5 = new JPanel();
		panel_5.setBounds(67, 60, 150, 144);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Valor total:");
		lblNewLabel_2.setBounds(10, 11, 130, 39);
		panel_5.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(20, 61, 86, 20);
		panel_5.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		panel_6 = new JPanel();
		panel_6.setBounds(227, 60, 150, 144);
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		
		JLabel lblNewLabel_6 = new JLabel("Ticket m\u00E9dio:");
		lblNewLabel_6.setBounds(10, 11, 130, 39);
		panel_6.add(lblNewLabel_6);
		lblNewLabel_6.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(20, 61, 86, 20);
		panel_6.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(novaFonte);
		
		panel_7 = new JPanel();
		panel_7.setBounds(10, 214, 736, 387);
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_7);
		panel_7.setLayout(null);
		
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
		Image img76 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img76));
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
		Image img22 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img22));
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
		Image img7 = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		btnImprimir.setIcon(new ImageIcon(img7));
		btnImprimir.setFont(novaFonte);
		
		label_1 = new JLabel("Vendas");
		label_1.setBounds(480, 97, 157, 14);
		contentPane.add(label_1);
		label_1.setFont(novaFonte);
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(Float.parseFloat(dv.label_2.getText()), "R$", dv.label_1.getText());
		dataset.setValue(Float.parseFloat(dv.label_4.getText()), "R$", dv.label_3.getText());
		dataset.setValue(Float.parseFloat(dv.label_6.getText()), "R$", dv.label_5.getText());
		dataset.setValue(Float.parseFloat(dv.label_8.getText()), "R$", dv.label_7.getText());
		dataset.setValue(Float.parseFloat(dv.label_10.getText()), "R$", dv.label_9.getText());
		
		JFreeChart barChart = ChartFactory.createBarChart(
		        "Faturamento",
		        "",
		        "R$",
		        dataset,
		        PlotOrientation.HORIZONTAL,
		        false, true, false);
		
		
		ChartPanel CP1 = new ChartPanel(barChart);
		CP1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP1.setBounds(376, 45, 350, 288);
		panel_7.add(CP1);
		
		
		
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			dataset2.setValue(Float.parseFloat(dv.lblNewLabel_1.getText()), "R$", dv.lblNewLabel.getText());
			dataset2.setValue(Float.parseFloat(dv.lblNewLabel_3.getText()), "R$", dv.lblNewLabel_2.getText());
			dataset2.setValue(Float.parseFloat(dv.lblNewLabel_5.getText()), "R$", dv.lblNewLabel_4.getText());
			dataset2.setValue(Float.parseFloat(dv.lblNewLabel_7.getText()), "R$", dv.lblNewLabel_6.getText());
			dataset2.setValue(Float.parseFloat(dv.lblNewLabel_9.getText()), "R$", dv.lblNewLabel_8.getText());
			
			JFreeChart barChart2 = ChartFactory.createBarChart(
			        "Quantidade",
			        "",
			        "Unidades",
			        dataset2,
			        PlotOrientation.HORIZONTAL,
			        false, true, false);
			
			
			ChartPanel CP2 = new ChartPanel(barChart2);
			CP2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			CP2.setBounds(10, 45, 350, 288);
			panel_7.add(CP2);
		
	}
}

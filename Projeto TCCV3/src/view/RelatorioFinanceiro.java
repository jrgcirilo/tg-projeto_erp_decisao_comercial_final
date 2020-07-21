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

import javax.swing.ButtonGroup;
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

import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class RelatorioFinanceiro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnImprimir;
	private JButton btnRetornar;
	private JLabel label;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	public JRadioButton radioButton;
	public JRadioButton radioButton_1;
	public JRadioButton radioButton_2;
	public JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField textField_5;
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
	private JPanel panel_8;
	
Date now = new Date(System.currentTimeMillis());
	
	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public void exibirRelatorio() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		calculaRelatorio();
		calculaMetricas();
		radioButton_1.setSelected(true);
		
	}
	
	public void calculaRelatorio() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	
		textField.setText(Integer.toString(mf.table.getRowCount()));
		lblNewLabel_2.setText("Contas a Pagar");
		textField_1.setText(Integer.toString(calculaContaP()));
		lblNewLabel_3.setText("Contas a Receber");
		textField_2.setText(Integer.toString(calculaContaR()));
		
	}

public void calculaRelatorioP() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	textField.setText(Integer.toString(calculaContaP()));
	lblNewLabel_3.setText("Valor total (pagar)");
	textField_2.setText(dinheiro.format(somaContaP()));
	lblNewLabel_2.setText("Média (contas a pagar)");
	textField_1.setText(dinheiro.format(mediaContaP()));

}

public void calculaRelatorioR() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	textField.setText(Integer.toString(calculaContaR()));
	lblNewLabel_3.setText("Valor total (receber)");
	textField_2.setText(dinheiro.format(somaContaR()));
	lblNewLabel_2.setText("Média (contas a receber)");
	textField_1.setText(dinheiro.format(mediaContaR()));
	
}

public int somaContas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	
	int somaContas = mf.table.getRowCount();
	
	return somaContas;
}

public int calculaContaP() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	int cp=0;
	String contap = "Pagar";
	for (int i=0; i<mf.table.getRowCount();i++){
		String str= (String) mf.table.getValueAt(i, 1);
		
	if(str.equals(contap)) {
	cp=cp+1;
		
	}
	}
	return cp;
	}

public int calculaContaR() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	int cr=0;
	String contar = "Receber";
	for (int i=0; i<mf.table.getRowCount();i++){
		String str= (String) mf.table.getValueAt(i, 1);
		
	if(str.equals(contar)) {
	cr=cr+1;
		
	}
	}
	return cr;
	}
	
		
public float somaContaP() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	float vtp=0;
	String contar = "Pagar";
	for (int i=0; i<mf.table.getRowCount();i++){
		String str= (String) mf.table.getValueAt(i, 1);
		
	if(str.equals(contar)) {
		float vp= (float) mf.table.getValueAt(i, 5);
		vtp+=vp;
		
	}
	}
	return vtp;
}

public float mediaContaP() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	float vtp=0;
	int cont=0;
	String contar = "Pagar";
	for (int i=0; i<mf.table.getRowCount();i++){
		String str= (String) mf.table.getValueAt(i, 1);
		
	if(str.equals(contar)) {
		float vp= (float) mf.table.getValueAt(i, 5);
		vtp+=vp;
		cont=cont+1;
	}
	
	}
	float mvp=vtp/cont;
	return mvp;
}

public float somaContaR() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	float vtr=0;
	String contar = "Receber";
	for (int i=0; i<mf.table.getRowCount();i++){
		String str= (String) mf.table.getValueAt(i, 1);
		
	if(str.equals(contar)) {
		float vr= (float) mf.table.getValueAt(i, 5);
		vtr+=vr;
		
	}
	}
	return vtr;
}

public float mediaContaR() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	ModuloFinanceiro mf = new ModuloFinanceiro();
	float vtr=0;
	int cont=0;
	String contar = "Receber";
	for (int i=0; i<mf.table.getRowCount();i++){
		String str= (String) mf.table.getValueAt(i, 1);
		
	if(str.equals(contar)) {
		float vr= (float) mf.table.getValueAt(i, 5);
		vtr+=vr;
		cont=cont+1;
	}
	
	}
	float mvr=vtr/cont;
	return mvr;
}

public void calculaMetricas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
		textField_3.setText(dinheiro.format(receitas()));
		textField_4.setText(dinheiro.format(lucro()));
		

		
	
		
}

public float receitas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	RelatorioComercial rc = new RelatorioComercial();
	
	float rec=somaContaR()+rc.somaVendas();
	
	return rec;
	
}

public float lucro() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
	
	float luc=receitas()-somaContaP();
	
	return luc;

	
}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioFinanceiro frame = new RelatorioFinanceiro();
					frame.setVisible(true);
					frame.exibirRelatorio();
					frame.radioButton_1.setSelected(true);
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
	public RelatorioFinanceiro() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setResizable(false);
		setTitle("Relat\u00F3rio - M\u00F3dulo Financeiro - Mini-ERP Decis\u00E3o Comercial");
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
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(190, 159, 756, 613);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setBounds(291, 22, 68, 14);
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
		panel_1.setBounds(458, 60, 139, 144);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Receitas:");
		lblNewLabel_4.setBounds(10, 11, 119, 39);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(10, 61, 86, 20);
		panel_1.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setBackground(Color.WHITE);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(607, 60, 139, 144);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Lucro:");
		lblNewLabel_5.setBounds(10, 11, 119, 39);
		panel_3.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(20, 61, 86, 20);
		panel_3.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setBackground(Color.WHITE);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 60, 150, 144);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		lblNewLabel_3 = new JLabel("Contas a receber:");
		lblNewLabel_3.setBounds(10, 11, 130, 39);
		panel_5.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(20, 61, 86, 20);
		panel_5.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.WHITE);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(170, 60, 139, 144);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Contas a pagar:");
		lblNewLabel_2.setBounds(10, 11, 119, 39);
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(20, 61, 86, 20);
		panel_6.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		
		
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
		Image img8 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img8));
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();	
			}
		});
		btnRetornar.setFont(novaFonte);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		lblNewLabel = new JLabel("Contas");
		lblNewLabel.setBounds(483, 97, 46, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		
		
		panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_8.setBounds(10, 215, 736, 387);
		panel.add(panel_8);
		panel_8.setLayout(null);
		

		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		dataset.setValue( "Contas a Pagar" , somaContaP() );  
		dataset.setValue( "Contas a Receber" , somaContaR() );    
	          
		JFreeChart chart = ChartFactory.createPieChart(      
		       "",   // chart title 
		       dataset,          // data    
		       true,             // include legend   
		       true, 
		       false);
		
		chart.getTitle().setFont(novaFonte);
		chart.setBackgroundPaint(Color.white);
		
		ChartPanel CP = new ChartPanel(chart);
		CP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP.setBounds(10, 45, 350, 288);
		panel_8.add(CP);
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		dataset2.setValue(somaContaP(), "R$", "Despesas");
		dataset2.setValue(somaContaR(), "R$", "Receitas");
		
		JFreeChart barChart = ChartFactory.createBarChart(
		        "",
		        "",
		        "R$",
		        dataset2,
		        PlotOrientation.VERTICAL,
		        false, true, false);
		
		ChartPanel CP2 = new ChartPanel(barChart);
		CP2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP2.setBounds(376, 45, 350, 288);
		panel_8.add(CP2);
		
		
		 String series1 = "Total";  
		 String series2 = "Média"; 
		
		
DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();  
		
	    dataset3.addValue(0, series1, "0");  
	    dataset3.addValue(somaContaP(), series1, formatador.format(now));   
	  
	    dataset3.addValue(0, series2, "0");  
	    dataset3.addValue(mediaContaP(), series2, formatador.format(now)); 
		
	    JFreeChart chart2 = ChartFactory.createLineChart(  
	            "", // Chart title  
	            "", // X-Axis Label  
	            "Salários", // Y-Axis Label  
	            dataset3  
	            );
		
		ChartPanel CP3 = new ChartPanel(chart2);
		CP3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP3.setBounds(10, 45, 350, 288);
		panel_8.add(CP3);
		CP3.setVisible(false);
		
DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();  
		
	    dataset4.addValue(0, series1, "0");  
	    dataset4.addValue(somaContaR(), series1, formatador.format(now));   
	  
	    dataset4.addValue(0, series2, "0");  
	    dataset4.addValue(mediaContaR(), series2, formatador.format(now)); 
		
	    JFreeChart chart3 = ChartFactory.createLineChart(  
	            "", // Chart title  
	            "", // X-Axis Label  
	            "Salários", // Y-Axis Label  
	            dataset4 
	            );
		
		ChartPanel CP4 = new ChartPanel(chart3);
		CP4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP4.setBounds(10, 45, 350, 288);
		panel_8.add(CP4);
		CP4.setVisible(false);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(319, 60, 130, 144);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Despesas:");
		lblNewLabel_6.setBounds(10, 11, 110, 39);
		panel_7.add(lblNewLabel_6);
		lblNewLabel_6.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBounds(20, 61, 86, 20);
		panel_7.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText(dinheiro.format(somaContaP()));
		textField_5.setFont(novaFonte);
		
ButtonGroup G = new ButtonGroup();
		
		
		radioButton = new JRadioButton("Contas a Pagar");
		radioButton.setBounds(355, 118, 131, 23);
		contentPane.add(radioButton);
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					calculaRelatorioP();
					CP.setVisible(false);
					CP3.setVisible(true);
					CP4.setVisible(false);
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
		radioButton.setFont(novaFonte);
		G.add(radioButton);
		
		radioButton_1 = new JRadioButton("Todas");
		radioButton_1.setBounds(493, 118, 70, 23);
		contentPane.add(radioButton_1);
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					exibirRelatorio();
					CP.setVisible(true);
					CP3.setVisible(false);
					CP4.setVisible(false);
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
		radioButton_1.setFont(novaFonte);
		G.add(radioButton_1);
		
		
		radioButton_2 = new JRadioButton("Contas a Receber");
		radioButton_2.setBounds(586, 118, 131, 23);
		contentPane.add(radioButton_2);
		radioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					calculaRelatorioR();
					CP.setVisible(false);
					CP3.setVisible(false);
					CP4.setVisible(true);
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
		radioButton_2.setFont(novaFonte);
		G.add(radioButton_2);
		
		exibirRelatorio();
		
	}	
}

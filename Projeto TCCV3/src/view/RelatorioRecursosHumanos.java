package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextField;

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
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class RelatorioRecursosHumanos extends JFrame {

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
	private JLabel lblFuncionrios;
	private JPanel panel_4;
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
	
	Date now = new Date(System.currentTimeMillis());
	
	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	
	public void calculaRelatorio() {
		
		textField.setText(Integer.toString(ModuloRecursosHumanos.BindList().size()));
		textField_1.setText(dinheiro.format(somaArrayList()));
		textField_2.setText(dinheiro.format(mediaArrayList()));
	}
	
	public int somaFunc() {
		int somaFunc = ModuloRecursosHumanos.BindList().size();
		
		return somaFunc;
	}
	
	public float somaArrayList() {
		
		float soma=0;
		
		for (int i=0; i<ModuloRecursosHumanos.BindList().size();i++){
		    soma = soma + ModuloRecursosHumanos.BindList().get(i).getSalario();
		}
		
		return soma;
	}
	
	public float mediaArrayList() {
		
		float media=0;
		
		media=somaArrayList()/ModuloRecursosHumanos.BindList().size();
		
		return media;
	}
	
	public void calculaMetricas() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		
		textField_3.setText(dinheiro.format(recFunc()));
			
			
		textField_4.setText(dinheiro.format(lucFunc()));
			
		
			
	}
	
	public float recFunc() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioFinanceiro rf = new RelatorioFinanceiro();
		
		float rec=rf.receitas()/ModuloRecursosHumanos.BindList().size();
		
		return rec;
	}
	
	public float lucFunc() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioFinanceiro rf = new RelatorioFinanceiro();
		
		float luc=rf.lucro()/ModuloRecursosHumanos.BindList().size();
		
		return luc;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioRecursosHumanos frame = new RelatorioRecursosHumanos();
					frame.setVisible(true);
					frame.calculaRelatorio();
					frame.calculaMetricas();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param arrayList 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public RelatorioRecursosHumanos() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setResizable(false);
		setTitle("Relat\u00F3rio - M\u00F3dulo Recursos Humanos - Mini-ERP Decis\u00E3o Comercial");
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
		panel.setBounds(190, 122, 769, 639);
		contentPane.add(panel);
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
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(387, 60, 150, 144);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Receita por funcion\u00E1rios:");
		lblNewLabel_4.setBounds(5, 11, 150, 14);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(20, 61, 86, 20);
		panel_1.add(textField_3);
		textField_3.setFont(new Font("AR CENA", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setBackground(Color.WHITE);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(547, 60, 150, 144);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Lucro por funcion\u00E1rios:");
		lblNewLabel_5.setBounds(10, 11, 130, 14);
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
		panel_5.setBounds(67, 60, 150, 144);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Valor total (sal\u00E1rios):");
		lblNewLabel_2.setBounds(10, 11, 130, 14);
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
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(227, 60, 150, 144);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("M\u00E9dia (sal\u00E1rios):");
		lblNewLabel_3.setBounds(10, 11, 130, 14);
		panel_6.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(20, 61, 86, 20);
		panel_6.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.WHITE);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(10, 210, 736, 387);
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
		Image img6 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		label.setIcon(new ImageIcon(img6));
		panel_2.add(label);
		
		panel_4 = new JPanel();
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
		Image img8 = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		btnImprimir.setIcon(new ImageIcon(img8));
		btnImprimir.setFont(novaFonte);
		
		lblFuncionrios = new JLabel("Funcion\u00E1rios");
		lblFuncionrios.setBounds(479, 97, 157, 14);
		contentPane.add(lblFuncionrios);
		lblFuncionrios.setFont(novaFonte);
		
		 String series1 = "Total";  
		 String series2 = "Média";  
	
	
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();  
		
	    dataset2.addValue(0, series1, "0");  
	    dataset2.addValue(somaArrayList(), series1, formatador.format(now));   
	  
	    dataset2.addValue(0, series2, "0");  
	    dataset2.addValue(mediaArrayList(), series2, formatador.format(now)); 
		
	    JFreeChart chart = ChartFactory.createLineChart(  
	            "", // Chart title  
	            "", // X-Axis Label  
	            "Salários", // Y-Axis Label  
	            dataset2  
	            );
		
		ChartPanel CP3 = new ChartPanel(chart);
		CP3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP3.setBounds(10, 45, 350, 288);
		panel_7.add(CP3);
		CP3.setFont(novaFonte);
		
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      dataset.addValue( recFunc() ,"R$" , "Receita" );
	      dataset.addValue( lucFunc() ,"R$" ,"Lucro" );        
        
	      JFreeChart barChart = ChartFactory.createBarChart(
	    	         "", 
	    	         "", "R$", 
	    	         dataset,PlotOrientation.VERTICAL, 
	    	         false, true, false);
		
		ChartPanel CP2 = new ChartPanel(barChart);
		CP2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		CP2.setBounds(376, 45, 350, 288);
		panel_7.add(CP2);
		
		calculaRelatorio();
		calculaMetricas();
		
		
	}
}

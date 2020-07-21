package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class TelaFolha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRetornar;
	private JButton btnImprimir;
	private JComboBox<String> combobox;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblSalrioLquido;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_14;
	private JTextField textField_12;
	ModuloRecursosHumanos mrh = new ModuloRecursosHumanos();
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
	private JTextField textField_14;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_13;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;
	private JLabel lblNewLabel_17;
	private JTextField textField_15;
	
	
	public void importarFuncionarios() {

		String str = null;  
		 
        for(int i = 0;i<ModuloRecursosHumanos.BindList().size(); i++ ){  
            str = (String) ModuloRecursosHumanos.BindList().get(i).getNome();  
            combobox.addItem(str);
            
       }    

	}
	
	public void calculaSalarios() {
		textField_1.setText(dinheiro.format(calculaINSS()));
		textField_2.setText(dinheiro.format(calculaIRPF()));
		textField_3.setText(dinheiro.format(calculaVT()));
		textField_15.setText(dinheiro.format(somaDescontos()));
		textField_4.setText(dinheiro.format(somaSalarios()));
	}
	
	public float calculaINSS() {
		
		float salario = Float.parseFloat(textField_12.getText()); 
		float INSS=0;
		
		if(salario < 1751) {
			
			INSS=(float) (salario*0.08);
			
		}
		
		else if(salario > 1751 && salario < 2919) {
			
			INSS=(float) (salario*0.09);
	
		}
		
		else if(salario > 2919 && salario < 5839) {
			
			INSS=(float) (salario*0.11);
	
		}
		
		
		else if(salario > 5839) {
			
			INSS=(float) (5.839*0.11);
	
		}
		
		return INSS;
		
	}
	
	public float calculaIRPF() {
		
		float salario = Float.parseFloat(textField_12.getText()); 
		float IRPF=0;
		
		if(salario <1903) {
			IRPF=(float) (salario*0);
		}
		
		else if(salario >= 1903 && salario <= 2826) {
			
			IRPF=(float) (salario*0.075);
			
		}
		
		else if(salario >= 2826 && salario <= 3751) {
			
			IRPF=(float) (salario*0.15);
			
		}
		
		else if(salario >= 3751 && salario <= 4.664) {
			
			IRPF=(float) (salario*0.15);
			
		}
		
		else if(salario >= 4.664) {
			
			IRPF=(float) (salario*0.275);
			
		}
		
		return IRPF;
		
	}
	
	public float calculaVT() {
		
		float salario = Float.parseFloat(textField_12.getText()); 
		float vt=0;
		
		vt=(float) (salario*0.06);
			
		return vt;
		
	}
	
	public float somaDescontos() {
		
		float descontoTotal=0;
		
		descontoTotal=calculaINSS()+calculaIRPF()+calculaVT();
		
		return descontoTotal;
	}
	
	public float somaSalarios() {
		
		float salarioTotal=0;
		
		salarioTotal=Float.parseFloat(textField_12.getText())-(calculaINSS()+calculaIRPF()+calculaVT());
		
		return salarioTotal;
	}
	
	public void calculaEncargos() {
		
		textField_6.setText(dinheiro.format(calculaINSSE()));
		textField_7.setText(dinheiro.format(calculaFGTSE()));
		textField_8.setText(dinheiro.format(calcula13SalarioE()));
		textField_9.setText(dinheiro.format(calculaFeriasE()));
		textField_10.setText(dinheiro.format(calculaVTE()));
		textField_11.setText(dinheiro.format(somaEncargos()));
		textField_13.setText(dinheiro.format(somaSalarioEncargos()));
	}
	
	public float calculaINSSE() {
		
		float salario = Float.parseFloat(textField_14.getText()); 
		float INSS=0;
		
		INSS=(float) (salario*0.29);
		
		return INSS;
		
	}
	
	public float calculaFGTSE() {
		
		float salario = Float.parseFloat(textField_14.getText()); 
		float FGTS=0;
		
		FGTS=(float) (salario*0.08);
			
		return FGTS;
		
	}
	
	public float calcula13SalarioE() {
		
		float salario = Float.parseFloat(textField_14.getText()); 
		float salario13=0;
		
		salario13=(float) (salario*0.0833);
			
		return salario13;
		
	}
	
	public float calculaFeriasE() {
		
		float salario = Float.parseFloat(textField_14.getText()); 
		float ferias=0;
		
		ferias=(float) (salario*0.1111);
			
		return ferias;
		
	}
	
	public float calculaVTE() {
		
		
		float salario = Float.parseFloat(textField_14.getText()); 
		float VTFunc=0,VTEmp=0;
		float VTMes=189;
		
		if(salario==0) {
			
			VTEmp=0;
		}else {
		
		VTFunc=(float) (salario*0.06);
		
		VTEmp=VTMes-VTFunc;}
		
		return VTEmp;
		
	}
	
	public float somaEncargos() {
		
		float salarioTotal=0;
		
		salarioTotal=calculaINSSE()+calculaFGTSE()+calcula13SalarioE()+calculaFeriasE()+calculaVTE();
		
		return salarioTotal;
	}
	
	public float somaSalarioEncargos() {
		
		float salarioMaisEncargo=0;
		
		salarioMaisEncargo=somaEncargos()+Float.parseFloat(textField_14.getText());
		
		return salarioMaisEncargo;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFolha frame = new TelaFolha();
					frame.setVisible(true);
					frame.importarFuncionarios();
					frame.calculaSalarios();
					frame.calculaEncargos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public TelaFolha() throws FontFormatException, IOException {
		setTitle("Folha de Pagamento - M\u00F3dulo de Recursos Humanos - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		Image img2 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
	    
	    Font novaFonteTitulo = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(20f);
	    GraphicsEnvironment geT = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    geT.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		combobox = new JComboBox<String>();
		combobox.setBackground(Color.WHITE);
		combobox.setFont(new Font("AR CENA", Font.PLAIN, 16));
		combobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i = 0;i<ModuloRecursosHumanos.BindList().size(); i++ ){  
					
					if(ModuloRecursosHumanos.BindList().get(i).getNome().contains((CharSequence) combobox.getSelectedItem())) {
						lblNewLabel_13.setText(ModuloRecursosHumanos.BindList().get(i).getNome());
						lblNewLabel_16.setText(ModuloRecursosHumanos.BindList().get(i).getNome());
						textField.setText(dinheiro.format(ModuloRecursosHumanos.BindList().get(i).getSalario()));
						textField_14.setText(Float.toString(ModuloRecursosHumanos.BindList().get(i).getSalario()));
						textField_5.setText(dinheiro.format(ModuloRecursosHumanos.BindList().get(i).getSalario()));
		            	textField_12.setText(Float.toString(ModuloRecursosHumanos.BindList().get(i).getSalario()));
		  
		            }
				}
				calculaSalarios();
				calculaEncargos();
			}
		});
		combobox.setBounds(484, 94, 180, 20);
		contentPane.add(combobox);
		contentPane.setLayout(null);
		combobox.setFont(novaFonte);
		
		lblNewLabel = new JLabel("Funcion\u00E1rio:");
		lblNewLabel.setBounds(374, 97, 100, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(190, 125, 759, 626);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hollerith", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tabbedPane.addTab("Folha de Pagamento", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblNewLabel_8 = new JLabel("SAL\u00C1RIO BRUTO:");
		lblNewLabel_8.setBounds(214, 112, 112, 14);
		panel_1.add(lblNewLabel_8);
		lblNewLabel_8.setFont(novaFonte);
		
		lblNewLabel_9 = new JLabel("SAL\u00C1RIO L\u00CDQUIDO:");
		lblNewLabel_9.setBounds(214, 557, 119, 14);
		panel_1.add(lblNewLabel_9);
		lblNewLabel_9.setFont(novaFonte);
		
		JLabel lblNewLabel_10 = new JLabel("INSS:");
		lblNewLabel_10.setBounds(214, 201, 46, 14);
		panel_1.add(lblNewLabel_10);
		lblNewLabel_10.setFont(novaFonte);
		
		JLabel lblNewLabel_11 = new JLabel("IRPF:");
		lblNewLabel_11.setBounds(214, 290, 46, 14);
		panel_1.add(lblNewLabel_11);
		lblNewLabel_11.setFont(novaFonte);
		
		JLabel lblNewLabel_12 = new JLabel("VT:");
		lblNewLabel_12.setBounds(214, 379, 46, 14);
		panel_1.add(lblNewLabel_12);
		lblNewLabel_12.setFont(novaFonte);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Funcion\u00E1rio:");
		lblNewLabel_1.setBounds(214, 23, 139, 14);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setBounds(416, 23, 139, 14);
		panel_1.add(lblNewLabel_13);
		lblNewLabel_13.setFont(novaFonte);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(510, 109, 86, 20);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		textField_12.setVisible(false);
		textField_12.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(416, 109, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setBounds(416, 198, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setBounds(416, 287, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setBounds(416, 376, 86, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		textField_4.setBounds(416, 557, 86, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setFont(novaFonte);
		
		lblNewLabel_17 = new JLabel("Valor total (descontos):");
		lblNewLabel_17.setBounds(214, 468, 139, 14);
		panel_1.add(lblNewLabel_17);
		lblNewLabel_17.setFont(novaFonte);
		
		textField_15 = new JTextField();
		textField_15.setBackground(Color.WHITE);
		textField_15.setEditable(false);
		textField_15.setBounds(416, 465, 86, 20);
		panel_1.add(textField_15);
		textField_15.setColumns(10);
		textField_15.setFont(novaFonte);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Encargos trabalhistas", null, panel, null);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("SAL\u00C1RIO BRUTO:");
		lblNewLabel_2.setBounds(210, 91, 144, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("INSS:");
		lblNewLabel_3.setBounds(210, 156, 144, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		lblNewLabel_4 = new JLabel("FGTS:");
		lblNewLabel_4.setBounds(210, 221, 144, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		lblNewLabel_5 = new JLabel("13\u00BA Sal\u00E1rio:");
		lblNewLabel_5.setBounds(210, 286, 144, 14);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		lblNewLabel_6 = new JLabel("F\u00E9rias:");
		lblNewLabel_6.setBounds(210, 351, 144, 14);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(novaFonte);
		
		lblNewLabel_7 = new JLabel("VT:");
		lblNewLabel_7.setBounds(210, 416, 144, 14);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(novaFonte);
		
		lblSalrioLquido = new JLabel("Valor total (custos):");
		lblSalrioLquido.setBounds(210, 481, 144, 14);
		panel.add(lblSalrioLquido);
		lblSalrioLquido.setFont(novaFonte);
		
		lblNewLabel_14 = new JLabel("SAL\u00C1RIO + ENCARGOS:");
		lblNewLabel_14.setBounds(210, 546, 144, 14);
		panel.add(lblNewLabel_14);
		lblNewLabel_14.setFont(novaFonte);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setBounds(508, 88, 86, 20);
		panel.add(textField_14);
		textField_14.setColumns(10);
		textField_14.setVisible(false);
		textField_14.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setBackground(Color.WHITE);
		textField_5.setEditable(false);
		textField_5.setBounds(412, 89, 86, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.WHITE);
		textField_6.setEditable(false);
		textField_6.setBounds(412, 154, 86, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setBackground(Color.WHITE);
		textField_7.setEditable(false);
		textField_7.setBounds(412, 219, 86, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setBackground(Color.WHITE);
		textField_8.setEditable(false);
		textField_8.setBounds(412, 284, 86, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		textField_8.setFont(novaFonte);
		
		textField_9 = new JTextField();
		textField_9.setBackground(Color.WHITE);
		textField_9.setEditable(false);
		textField_9.setBounds(412, 349, 86, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setFont(novaFonte);
		
		textField_10 = new JTextField();
		textField_10.setBackground(Color.WHITE);
		textField_10.setEditable(false);
		textField_10.setBounds(412, 414, 86, 20);
		panel.add(textField_10);
		textField_10.setColumns(10);
		textField_10.setFont(novaFonte);
		
		textField_11 = new JTextField();
		textField_11.setBackground(Color.WHITE);
		textField_11.setEditable(false);
		textField_11.setBounds(412, 479, 86, 20);
		panel.add(textField_11);
		textField_11.setColumns(10);
		textField_11.setFont(novaFonte);
		
		textField_13 = new JTextField();
		textField_13.setBackground(Color.WHITE);
		textField_13.setEditable(false);
		textField_13.setBounds(412, 544, 86, 20);
		panel.add(textField_13);
		textField_13.setColumns(10);
		textField_13.setFont(novaFonte);
		
		lblNewLabel_15 = new JLabel("Nome do funcion\u00E1rio:");
		lblNewLabel_15.setBounds(210, 36, 144, 14);
		panel.add(lblNewLabel_15);
		lblNewLabel_15.setFont(novaFonte);
		
		lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setBounds(412, 36, 182, 14);
		panel.add(lblNewLabel_16);
		lblNewLabel_16.setFont(novaFonte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 984, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Folha de Pagamento");
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
		panel_4.setBounds(0, 86, 180, 676);
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
					panel_1.paint(g2);
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
		
		
		
		
	}
}

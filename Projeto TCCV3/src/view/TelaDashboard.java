package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
//import javax.swing.Timer;
import javax.swing.UIManager;

public class TelaDashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnRetornar;
	private JLabel label;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblFuncionrios;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	//private Timer timer;
	public final static int INTERVAL = 30000;
	
	NumberFormat formatter = new DecimalFormat("0.00");
	
	Locale localeBR = new Locale("pt","BR");
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
	NumberFormat percentual = NumberFormat.getPercentInstance(localeBR);
	private JTextField textField_9;
	private JTextField textField_10;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField textField_11;
	private JTextField textField_12;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textField_13;
	private JTextField textField_14;
	private JLabel lblNewLabel_8;
	private JTextField textField_15;
	
	public void vaiDashboard() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		vaiRecursosHumanos();
		vaiComercial();
		vaiFinanceiro();
	}
	
	
	public void vaiRecursosHumanos() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioRecursosHumanos rrh = new RelatorioRecursosHumanos();
	
	    textField_2.setText(Integer.toString(rrh.somaFunc()));  
	    textField.setText(dinheiro.format(rrh.recFunc()));
	    textField_1.setText(dinheiro.format(rrh.lucFunc()));
	    textField_9.setText(dinheiro.format(rrh.somaArrayList()));
	    textField_10.setText(dinheiro.format(rrh.mediaArrayList()));
	    
	}
	
	public void vaiComercial() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioComercial rc = new RelatorioComercial();
	
		 textField_5.setText(Integer.toString(rc.numVendas()));  
		 textField_4.setText(percentual.format(rc.luc())); 
		 textField_12.setText(percentual.format(rc.lucvr())); 
		 textField_11.setText(dinheiro.format(rc.somaVendas())); 
		 textField_3.setText(dinheiro.format(rc.mediaVendas()));
	}
	
	public void vaiFinanceiro() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		RelatorioFinanceiro rf = new RelatorioFinanceiro();
	
		 textField_8.setText(Integer.toString(rf.somaContas()));  
		 textField_7.setText(dinheiro.format(rf.receitas())); 
		 textField_6.setText(dinheiro.format(rf.lucro())); 
		 textField_14.setText(dinheiro.format(rf.somaContaR())); 
		 textField_13.setText(dinheiro.format(rf.mediaContaR())); 
		 textField_15.setText(dinheiro.format(rf.somaContaP())); 
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDashboard frame = new TelaDashboard();
					frame.setVisible(true);
					frame.vaiRecursosHumanos();
					frame.vaiComercial();
					frame.vaiFinanceiro();
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
	public TelaDashboard() throws ClassNotFoundException, SQLException, FontFormatException, IOException {
		setTitle("Dashboard - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1500,880);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
	    
	    Font novaFonteTitulo = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(20f);
	    GraphicsEnvironment geT = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    geT.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Recursos Humanos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(948, 97, 369, 734);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Comercial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(190, 97, 369, 734);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Financeiro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(569, 97, 369, 734);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Receita por funcion\u00E1rios:");
		lblNewLabel_2.setBounds(32, 460, 155, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
		
		lblNewLabel_3 = new JLabel("Lucro por funcion\u00E1rios:");
		lblNewLabel_3.setBounds(32, 580, 155, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(novaFonte);
		
		lblFuncionrios = new JLabel("Funcion\u00E1rios:");
		lblFuncionrios.setBounds(32, 100, 155, 14);
		panel.add(lblFuncionrios);
		lblFuncionrios.setFont(novaFonte);
	
		JLabel lblVendas = new JLabel("Vendas:");
		lblVendas.setBounds(32, 100, 211, 14);
		panel_1.add(lblVendas);
		lblVendas.setFont(novaFonte);
		
		JLabel lblMargemBruta = new JLabel("Rentabilidade:");
		lblMargemBruta.setBounds(32, 460, 211, 14);
		panel_1.add(lblMargemBruta);
		lblMargemBruta.setFont(novaFonte);
		
		JLabel lblMargemDeLucro = new JLabel("Lucratividade :");
		lblMargemDeLucro.setBounds(32, 580, 211, 14);
		panel_1.add(lblMargemDeLucro);
		lblMargemDeLucro.setFont(novaFonte);
	
		JLabel lblContas = new JLabel("Contas:");
		lblContas.setBounds(32, 100, 155, 14);
		panel_2.add(lblContas);
		lblContas.setFont(novaFonte);
		
		JLabel lblRentabilidade = new JLabel("Receitas:");
		lblRentabilidade.setBounds(32, 580, 155, 14);
		panel_2.add(lblRentabilidade);
		lblRentabilidade.setFont(novaFonte);
		
		JLabel lblLucratividade = new JLabel("Lucro:");
		lblLucratividade.setBounds(32, 700, 155, 14);
		panel_2.add(lblLucratividade);
		lblLucratividade.setFont(novaFonte);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(213, 460, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setFont(novaFonte);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setBounds(213, 580, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setFont(novaFonte);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setBounds(213, 100, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setFont(novaFonte);
		
		JLabel lblNewLabel = new JLabel("Valor total (Salários):");
		lblNewLabel.setBounds(32, 220, 155, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(novaFonte);
		
		JLabel lblNewLabel_1 = new JLabel("Média (Salários):");
		lblNewLabel_1.setBounds(32, 340, 155, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setBackground(Color.WHITE);
		textField_9.setEditable(false);
		textField_9.setBounds(213, 220, 86, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setFont(novaFonte);
		
		textField_10 = new JTextField();
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setBackground(Color.WHITE);
		textField_10.setEditable(false);
		textField_10.setBounds(213, 340, 86, 20);
		panel.add(textField_10);
		textField_10.setColumns(10);
		textField_10.setFont(novaFonte);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(253, 340, 86, 20);
		panel_1.add(textField_3);
		textField_3.setFont(novaFonte);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(253, 460, 86, 20);
		panel_1.add(textField_4);
		textField_4.setFont(novaFonte);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText((String) null);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(253, 100, 86, 20);
		panel_1.add(textField_5);
		textField_5.setFont(novaFonte);
		
		lblNewLabel_4 = new JLabel("Valor total (Vendas):");
		lblNewLabel_4.setBounds(32, 220, 211, 14);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(novaFonte);
		
		lblNewLabel_5 = new JLabel("Ticket médio:");
		lblNewLabel_5.setBounds(32, 340, 211, 14);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(novaFonte);
		
		textField_11 = new JTextField();
		textField_11.setBackground(Color.WHITE);
		textField_11.setEditable(false);
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setBounds(253, 220, 86, 20);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		textField_11.setFont(novaFonte);
		
		textField_12 = new JTextField();
		textField_12.setBackground(Color.WHITE);
		textField_12.setEditable(false);
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setBounds(253, 580, 86, 20);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		textField_12.setFont(novaFonte);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(213, 700, 86, 20);
		panel_2.add(textField_6);
		textField_6.setFont(novaFonte);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBackground(Color.WHITE);
		textField_7.setBounds(213, 580, 86, 20);
		panel_2.add(textField_7);
		textField_7.setFont(novaFonte);
		
		textField_8 = new JTextField();
		textField_8.setText((String) null);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBackground(Color.WHITE);
		textField_8.setBounds(213, 100, 86, 20);
		panel_2.add(textField_8);
		textField_8.setFont(novaFonte);
		
		lblNewLabel_6 = new JLabel("Valor total (A receber):");
		lblNewLabel_6.setBounds(32, 220, 188, 14);
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.setFont(novaFonte);
		
		lblNewLabel_7 = new JLabel("M\u00E9dia (A receber):");
		lblNewLabel_7.setBounds(32, 340, 155, 14);
		panel_2.add(lblNewLabel_7);
		lblNewLabel_7.setFont(novaFonte);
		
		textField_13 = new JTextField();
		textField_13.setBackground(Color.WHITE);
		textField_13.setEditable(false);
		textField_13.setBounds(213, 340, 86, 20);
		panel_2.add(textField_13);
		textField_13.setColumns(10);
		textField_13.setFont(novaFonte);
		
		textField_14 = new JTextField();
		textField_14.setBackground(Color.WHITE);
		textField_14.setEditable(false);
		textField_14.setBounds(213, 220, 86, 20);
		panel_2.add(textField_14);
		textField_14.setColumns(10);
		textField_14.setFont(novaFonte);
		
		lblNewLabel_8 = new JLabel("Despesas:");
		lblNewLabel_8.setBounds(32, 460, 155, 14);
		panel_2.add(lblNewLabel_8);
		lblNewLabel_8.setFont(novaFonte);
		
		textField_15 = new JTextField();
		textField_15.setBounds(213, 460, 86, 20);
		panel_2.add(textField_15);
		textField_15.setColumns(10);
		textField_15.setFont(novaFonte);
		
		
		/*timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				try {
					vaiRecursosHumanos();
					vaiComercial();
					vaiFinanceiro();
					
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
	    timer.start();*/
		
	    
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(0, 0, 1444, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMdulos = new JLabel("Dashboard");
		lblMdulos.setForeground(SystemColor.text);
		lblMdulos.setBounds(646, 36, 167, 23);
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
		panel_4.setBounds(0, 86, 180, 756);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		btnRetornar = new JButton("Voltar");
		btnRetornar.setBounds(5, 11, 170, 41);
		panel_4.add(btnRetornar);
		btnRetornar.setToolTipText("");
		Image img11 = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnRetornar.setIcon(new ImageIcon(img11));
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					dispose();
			
			}
		});
		btnRetornar.setFont(novaFonte);
		
		vaiDashboard();
		
	}
}

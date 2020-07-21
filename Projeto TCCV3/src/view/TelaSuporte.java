package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class TelaSuporte extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSuporte frame = new TelaSuporte();
					frame.setVisible(true);
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
	public TelaSuporte() throws FontFormatException, IOException {
		setResizable(false);
		setTitle("Suporte - Mini-ERP Decis\u00E3o Comercial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font novaFonte = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")).deriveFont(16f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ar-cena.ttf")));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(130, 36, 253, 176);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
		Image img6 = new ImageIcon(this.getClass().getResource("/support.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img6));
		
		JLabel lblNewLabel_1 = new JLabel("Fale com o nosso Suporte T\u00E9cnico.");
		lblNewLabel_1.setBounds(130, 234, 197, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(novaFonte);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone: (11)98817-8848");
		lblNewLabel_2.setBounds(130, 259, 197, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(novaFonte);
	}

}

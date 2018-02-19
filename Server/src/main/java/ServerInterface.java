import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ServerInterface {

	private JFrame frmServer;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerInterface window = new ServerInterface();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ServerInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 448, 342);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 412, 196);
		frmServer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 239, 163);
		panel.add(panel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(328, 91, 74, 38);
		textField_3.setColumns(10);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(328, 52, 74, 38);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(328, 11, 74, 38);
		textField_5.setColumns(10);
		panel.add(textField_5);
		
		JTextArea txtrHighestValue = new JTextArea();
		txtrHighestValue.setLineWrap(true);
		txtrHighestValue.setWrapStyleWord(true);
		txtrHighestValue.setText("Highest value:");
		txtrHighestValue.setBounds(252, 11, 73, 38);
		panel.add(txtrHighestValue);
		
		JTextArea txtrLowestValue = new JTextArea();
		txtrLowestValue.setLineWrap(true);
		txtrLowestValue.setWrapStyleWord(true);
		txtrLowestValue.setText("Lowest value:");
		txtrLowestValue.setBounds(252, 52, 73, 38);
		panel.add(txtrLowestValue);
		
		JTextArea txtrFrequency = new JTextArea();
		txtrFrequency.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtrFrequency.setLineWrap(true);
		txtrFrequency.setWrapStyleWord(true);
		txtrFrequency.setText("Frequency(Hz):");
		txtrFrequency.setBounds(252, 92, 73, 38);
		panel.add(txtrFrequency);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 248, 412, 44);
		frmServer.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel(" Console:");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(0, 0, 81, 22);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Start / Stop");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(315, 11, 107, 23);
		frmServer.getContentPane().add(btnNewButton);
	}

	public JFrame getFrmServer() {
		return frmServer;
	}
}

package Student_Result;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static Student_Result.dataBase.*;


public class Login extends JFrame{

	public static String id;
	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JButton btnlogin;
	private JPasswordField passwordField;
	private JComboBox comboBox;

	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Login");
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.white);


		ImageIcon img2 = new ImageIcon(StudentResults.class.getResource("/Student_Result/Classroom.jpg"));

		ImageIcon myImage = img2;
		Image img = myImage.getImage();
		Image newImage = img.getScaledInstance(1044, 511, Image.SCALE_FAST);
		ImageIcon Image2 = new ImageIcon(newImage);

		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"student", "lecturer"}));
		comboBox.setBounds(157, 240, 93, 22);
		frame.getContentPane().add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("ID No.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(157, 93, 93, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(157, 110, 93, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		lblNewLabel_2 = new JLabel("Pin");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(157, 159, 93, 14);
		frame.getContentPane().add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(157, 174, 93, 20);
		frame.getContentPane().add(passwordField);

		btnlogin = new JButton("Login");
		btnlogin.setBounds(157, 313, 93, 23);
		frame.getContentPane().add(btnlogin);
		btnlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				String password = new String(passwordField.getPassword());

				String role = comboBox.getSelectedItem().toString();

				if (id.equals("") || password.equals("")){
					JOptionPane.showMessageDialog(frame,"Fill all records");
				}else{
					try{
						if(login(id,password,role)){
							if(role == "student"){
									new student(id);
									frame.dispose();
							}
							else{
								new StudentResults();
								frame.dispose();
							}

						}
						else{
							JOptionPane.showMessageDialog(frame,"Error Encountered: Wrong Credentials");
						}

					}catch (Exception a){
						JOptionPane.showMessageDialog(frame,"Error Encountered: "+a.getMessage());
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel(Image2);
		lblNewLabel.setBounds(400, 0, 800, 500);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);

	}

}

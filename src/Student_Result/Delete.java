package Student_Result;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static Student_Result.dataBase.*;

public class Delete {
    JFrame myFrame = new JFrame("Delete Student");
    JButton delete = new JButton("Delete");
    JPanel panel = new JPanel();
    JTextField  textField = new JTextField();
    
    ImageIcon img = new ImageIcon(getClass().getResource("Classroom.jpg"));
	
	ImageIcon myImage = img;
	Image img1 = myImage.getImage();
	Image newImage = img1.getScaledInstance(257, 271, Image.SCALE_FAST);
	ImageIcon Image2 = new ImageIcon(newImage);
    
    private final JLabel lblNewLabel = new JLabel(Image2);
    private final JLabel lblNewLabel_2 = new JLabel("Student ID");
    private final JLabel lblNewLabel_3 = new JLabel("Student Name");

    public Delete() throws Exception {
        myFrame.setResizable(false);
        myFrame.setSize(500,300);
        myFrame.getContentPane().setLayout(null);

        panel.setBounds(0,0,494,271);
        panel.setBackground(Color.white);
        panel.setLayout(null);

        JComboBox students = new JComboBox (allStudents().toArray());
        delete.setForeground(Color.RED);

        delete.setEnabled(false);
        students.addActionListener(e -> {
            String s = (String) students.getSelectedItem();//get the selected item
            if (s == ""){
                delete.setEnabled(false);
            }
            try {
                String[] name = getName(students.getSelectedItem().toString());
                textField.setText(name[0] +" "+ name[1]);
                delete.setEnabled(true);
            } catch (Exception exception) {

            }

        });
        students.setBounds(325,54,100,20);
        panel.add(students);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        textField.setBounds(275,104,200,20);
        textField.setForeground(Color.black);
        Font font = new Font("ariel",Font.BOLD,16);
        textField.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField.setEditable(false);
        panel.add(textField);


        delete.addActionListener(e ->{

            String sid = Objects.requireNonNull(students.getSelectedItem()).toString();
            try {
                dltStudent(sid);
                dltCourse(sid);
                myFrame.dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(myFrame,"Error Encounter: "+exception.getMessage());
            }
        });
        delete.setBounds(325,154,100,30);


        
        panel.add(delete);


        JLabel lblNewLabel_1 = new JLabel();
        if(students.getItemCount() == 0){
            lblNewLabel_1.setText("No Students to delete!!");
        }
        lblNewLabel_1.setBounds(275, 210, 200, 50);
        lblNewLabel_1.setFont(new Font("ariel",Font.BOLD,20));
        panel.add(lblNewLabel_1);

        myFrame.getContentPane().add(panel);
        lblNewLabel.setBounds(0, 0, 257, 271);
        
        panel.add(lblNewLabel);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(325, 36, 100, 14);
        
        panel.add(lblNewLabel_2);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(275, 89, 200, 14);
        
        panel.add(lblNewLabel_3);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception{
        new Delete();
    }
}

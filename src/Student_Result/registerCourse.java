package Student_Result;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Student_Result.dataBase.*;

public class registerCourse {
    JFrame myFrame = new JFrame("Register Course");
    JButton register = new JButton("Register");
    JTextField textField = new JTextField();
    JPanel panel = new JPanel();
    private final JLabel lblNewLabel = new JLabel("Course ID");
    private final JLabel lblCourseName = new JLabel("Course Name");
    JLabel lblNewLabel_1 = new JLabel();

    
    ImageIcon img = new ImageIcon(getClass().getResource("Classroom.jpg"));
	
	ImageIcon myImage = img;
	Image img1 = myImage.getImage();
	Image newImage = img1.getScaledInstance(207, 221, Image.SCALE_FAST);
	ImageIcon Image2 = new ImageIcon(newImage);
    private final JLabel lblNewLabel_2 = new JLabel(Image2);

    
    public registerCourse(String id) throws Exception {
        myFrame.setResizable(false);
        myFrame.setSize(416,250);
        myFrame.getContentPane().setLayout(null);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel.setBounds(0,0,410,221);
        panel.setBackground(Color.white);
        panel.setLayout(null);


        register.setEnabled(false);
        String[] course = new String[]{"304","306"};

        JComboBox courses = new JComboBox (course);
        //add actionlistner to listen for change
        courses.addActionListener(e -> {
            String s = (String) courses.getSelectedItem();//get the selected item
            switch (s) {//check for a match
                case "306":
                    textField.setText("Object Oriented Programming");
                    register.setEnabled(true);
                    break;
                case "304":
                    textField.setText("Digital Signals Processing");
                    register.setEnabled(true);
                    break;
            }

        });
        courses.setBounds(52,54,100,20);
        panel.add(courses);

        textField.setBounds(20,100,162,20);
        textField.setForeground(Color.black);
        Font font = new Font("arial",Font.BOLD,10);
        textField.setFont(font);
        textField.setEditable(false);
        panel.add(textField);


        register.addActionListener(e ->{

                String cid = courses.getSelectedItem().toString();
                String title = textField.getText();
                try {
                    if(!isRegistered(id,cid)) {
                        chooseCourse(id, cid, title);
                        myFrame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(myFrame,"Course Already Registered");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(myFrame,"Error Encounter: "+exception.getMessage());
                }

        });
        register.setBounds(52,147,100,30);

        panel.add(register);


//        if(courses.getItemCount() == 0){
//            lblNewLabel_1.setText("No Courses to Register!!");
//        }
        lblNewLabel_1.setBounds(20, 180, 180, 30);
        lblNewLabel_1.setFont(new Font("ariel",Font.BOLD,14));
        panel.add(lblNewLabel_1);

        myFrame.getContentPane().add(panel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(52, 36, 100, 14);
        
        panel.add(lblNewLabel);
        lblCourseName.setHorizontalAlignment(SwingConstants.CENTER);
        lblCourseName.setBounds(20, 85, 162, 14);
        
        panel.add(lblCourseName);
        lblNewLabel_2.setBounds(203, 0, 207, 221);
        
        panel.add(lblNewLabel_2);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }

}

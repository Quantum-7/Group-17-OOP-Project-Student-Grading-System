package Student_Result;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static Student_Result.dataBase.*;

public class Enroll {

    String path;
    JLabel attached;
    JFrame myFrame = new JFrame("Enroll");
    JButton submit, attach;
    JTextField idField, fnameTxt, lnameTxt, emailTxt, contactTxt;
    JPasswordField pintxt;



    public Enroll (){
        myFrame.setSize(900, 400);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.getContentPane().setLayout(null);
        
        
        ImageIcon img = new ImageIcon(getClass().getResource("pencil banner.jpg"));
		
		ImageIcon myImage = img;
		Image img1 = myImage.getImage();
		Image newImage = img1.getScaledInstance(894, 100, Image.SCALE_FAST);
		ImageIcon Image2 = new ImageIcon(newImage);
		
        
        
        JLabel Label1 = new JLabel(Image2);
        Label1.setBounds(0, 0, 894, 40);
        myFrame.getContentPane().add(Label1);



        // Data to be displayed in the JTable


        JPanel panel = new JPanel();
        panel.setBounds(0, 39, 894, 40);
        panel.setBackground(Color.white);
        myFrame.getContentPane().add(panel);

        JLabel label1 = new JLabel("Enroll a Student: ");
        label1.setFont(new Font("sans-serif", Font.BOLD, 20));
        label1.setForeground(Color.black);
        label1.setBounds(0, 0, 900, 50);
        panel.add(label1);

        submit = new JButton("Submit");
        submit.setBounds(780, 0, 110, 40);
        submit.setForeground(new Color(26,140,254,255));
        panel.add(submit);

        attach = new JButton("Attach Image");
        attach.setBounds(651, 320, 150, 40);
        myFrame.getContentPane().add(attach);
        attached = new JLabel("Attach Image Here");
        attached.setBounds(626, 90, 200, 200);
        attached.setFont(new Font(label1.getName(), Font.BOLD, 20));
        attached.setForeground(Color.BLACK);
        myFrame.getContentPane().add(attached);

        attach.addActionListener(arg0 -> attach());


        var studID = new JLabel("Student ID:");
        studID.setBounds(21, 100, 130, 30);
        idField = new JTextField(50);
        idField.setBounds(91, 100, 150, 30);

        var pinNo = new JLabel("Pin No.:");
        pinNo.setBounds(251, 100, 130, 30);
        pintxt = new JPasswordField(50);
        pintxt.setBounds(351, 100, 160, 30);

        var fname = new JLabel("First Name:");
        fname.setBounds(21, 190, 80, 30);
        fnameTxt = new JTextField(50);
        fnameTxt.setBounds(91, 190, 150, 30);

        var lname = new JLabel("SurName:");
        lname.setBounds(251, 190, 130, 30);
        lnameTxt = new JTextField(50);
        lnameTxt.setBounds(351, 190, 160, 30);

        var email = new JLabel("Email:");
        email.setBounds(21, 280, 80, 30);
        emailTxt = new JTextField(50);
        emailTxt.setBounds(91, 280, 150, 30);

        var contact = new JLabel("Contact No:");
        contact.setBounds(251, 280, 130, 30);
        contactTxt = new JTextField(50);
        contactTxt.setBounds(351, 280, 160, 30);


        save();

        myFrame.getContentPane().add(studID);
        myFrame.getContentPane().add(idField);
        myFrame.getContentPane().add(pinNo);
        myFrame.getContentPane().add(pintxt);
        myFrame.getContentPane().add(fname);
        myFrame.getContentPane().add(fnameTxt);
        myFrame.getContentPane().add(lname);
        myFrame.getContentPane().add(lnameTxt);
        myFrame.getContentPane().add(email);
        myFrame.getContentPane().add(emailTxt);
        myFrame.getContentPane().add(contact);
        myFrame.getContentPane().add(contactTxt);


        myFrame.setVisible(true);

    }

    public void attach (){
        // TODO Auto-generated method stub
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image", "jpg", "JPG", "JPEG", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();

            System.out.println(path);
            attached.setIcon(ResizeImage(path));

            setImage("","student",path);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "file not selected");
        }

    }
    public ImageIcon ResizeImage(String Imagepath) {

        ImageIcon myImage = new ImageIcon(Imagepath);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(attached.getWidth(), attached.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon Image = new ImageIcon(newImage);
        return Image;
    }

    public void save() {
            submit.addActionListener(e -> {
                String s1 = idField.getText();
                String s2 = pintxt.getText();
                String s4 = fnameTxt.getText();
                String s5 = lnameTxt.getText();
                String s7 = emailTxt.getText();
                String s8 = contactTxt.getText();

                if (idField.getText().equals("") || pintxt.getText().equals("")
                        || fnameTxt.getText().equals("") || emailTxt.getText().equals("")
                        || contactTxt.getText().equals("")
                ) {
                    JOptionPane.showMessageDialog(myFrame, "All record Must be fill Out");
                } else {
                    try {
                        signUp(s1,s4,s5,s2,s8,s7);
                        setImage(s1,"student",path);
                        myFrame.dispose();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(myFrame,"Error Encountered!");
                    }

                }
            });
    }

    public static void main(String[] args) {
        new Enroll();
    }
}

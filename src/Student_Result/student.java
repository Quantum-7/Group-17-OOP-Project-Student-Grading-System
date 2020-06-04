package Student_Result;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Student_Result.dataBase.*;



public class student {

    JButton getStudents;
    JTable table = new JTable();

    JFrame myFrame = new JFrame("Student Page");
    String userDetails;
    JButton logout;

    private JPanel myPanel = new JPanel(null);
//    private JPanel tablePanel = new JPanel(null);
    Font afont = new Font("ariel", Font.BOLD, 20);

    public student(String id) throws Exception {
        this.userDetails = id;
        String[] identity = getName(id);

        myFrame.setSize(600, 500);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.getContentPane().setLayout(new BorderLayout());

        myPanel.setBackground(new Color(255, 255, 255));


        // Data to be displayed in the JTable


        JLabel myLabel = new JLabel("Welcome, "+identity[1]+" "+identity[0]+".");
        myLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myLabel.setFont(new Font(myLabel.getName(), Font.BOLD, 20));
        myLabel.setBounds(280, 0, 300, 31);
        myPanel.add(myLabel);


        JComboBox courses = new JComboBox ();
        courses.setBounds(76,106,100,20);


        JLabel courseTitle = new JLabel();
        courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        courseTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        courseTitle.setBounds(268,42,300,100);
        myPanel.add(courseTitle);

        getStudents = new JButton("Course");
        getStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
        getStudents.setBounds(76, 56, 100, 20);
        myPanel.add(getStudents);
        getStudents.addActionListener(e -> {
            courses.removeAllItems();
            try {
                String[] array = registered(id);
                for (String s : array) courses.addItem(s);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        myPanel.add(courses);


        logout = new JButton("Logout");
        logout.setBounds(76, 387, 100, 40);
        logout.setFont(new Font("Dialog", Font.BOLD, 18));
        logout.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to exit","Student Result System",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                myFrame.dispose();
                new Login();
            }
        });
        myPanel.add(logout);
        JButton register = new JButton("Register");
        register.setBounds(76,163,100,40);
        myPanel.add(register);
        register.addActionListener(e->{
            try {
                new registerCourse(id);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        JButton results = new JButton("Results");
        results.setBounds(76,235,100,40);
        myPanel.add(results);

        results.addActionListener(e-> {
            if(courses.getSelectedItem() == null){
                JOptionPane.showMessageDialog(myFrame,"Click \"Course\" to select a course");
            }else{
                String[] columnNames = { "Category", "Score" };
                table.setModel(new DefaultTableModel(null,columnNames));

                try {
                    String cid = courses.getSelectedItem().toString();
                    float a = getAttendance(userDetails, cid);
                    float b = getw_quiz(userDetails, cid);
                    float c = getp_exam(userDetails, cid);
                    float d = getProject(userDetails, cid);
                    float ee =getexam(userDetails, cid);

                    String[][] data = {
                            { "Attendance", String.valueOf(a)},
                            { "Written Quiz", String.valueOf(b)},
                            { "Practical Exam", String.valueOf(c)},
                            { "Project", String.valueOf(d)},
                            { "Final Exam", String.valueOf(ee)}
                    };

                    // Initializing the JTable
                    table.setModel(new DefaultTableModel(data,columnNames));
                    table.setFont(new Font("ariel", Font.BOLD, 20));
                    table.setBackground(Color.white);
                    table.setForeground(Color.black);
                    table.setRowHeight(50);
                    table.setShowGrid(false);
                    table.setEnabled(false);
                    table.setBounds(275, 150, 500, 250);

                    myPanel.add(table);

                    courseTitle.setText(courses(courses.getSelectedItem().toString()));

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        JButton transcript = new JButton("Transcript");
        transcript.setBounds(76,314,100,40);
        transcript.addActionListener(e -> {
                try {
                    new transcript(userDetails);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
        });
        myPanel.add(transcript);

        myFrame.getContentPane().add(myPanel, BorderLayout.CENTER);
        
        ImageIcon img = new ImageIcon(getClass().getResource("Classroom section.PNG"));
    	
    	ImageIcon myImage = img;
    	Image img1 = myImage.getImage();
    	Image newImage = img1.getScaledInstance(349, 471, Image.SCALE_FAST);
    	ImageIcon Image2 = new ImageIcon(newImage);
        
        JLabel Label2 = new JLabel(Image2);
        Label2.setBounds(0, 0, 260, 471);
        myPanel.add(Label2);
        myFrame.setVisible(true);

    }
}

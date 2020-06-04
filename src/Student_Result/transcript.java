package Student_Result;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static Student_Result.dataBase.*;

public class transcript {
    JFrame myFrame = new JFrame("Transcript");
    private JTextArea jtxtTranscript;
    JPanel panel = new JPanel();
    private JTextField txtGrade = new JTextField();

    public transcript(String id) throws Exception {
        myFrame.setResizable(false);
        myFrame.setSize(500,300);
        myFrame.setLayout(null);
        myFrame.setLocationRelativeTo(null);

        panel.setBounds(0,0,500,300);
        panel.setBackground(Color.white);
        panel.setLayout(null);



        jtxtTranscript = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(jtxtTranscript);
        scrollPane.setBounds(40, 20, 400, 200);
        jtxtTranscript.setEditable(false);
        panel.add(scrollPane);

        jtxtTranscript.setText(null);
        jtxtTranscript.setVisible(true);
        jtxtTranscript.append("Student Result Recording System\n");
        String[] identity = getName(id);
        jtxtTranscript.append("Name: "+identity[0]+" "+identity[1]+" "+"\n");
        jtxtTranscript.append("Student ID: "+id);
        if(isRegistered(id,"304")){
            float FS = getAttendance(id,"304")+getw_quiz(id,"304")+getp_exam(id,"304")+getProject(id,"304")+getexam(id,"304");
            if (FS >= 80 && FS <= 100) {
                txtGrade.setText("A");

            } else if (FS < 80 && FS >= 70) {
                txtGrade.setText("B");

            } else if (FS < 70 && FS >= 60) {
                txtGrade.setText("C");

            } else if (FS < 60 && FS >= 50) {
                txtGrade.setText("D");

            } else if (FS < 50) {
                txtGrade.setText("F");

            } else
                txtGrade.setText("NULL");

            jtxtTranscript.append(
                     "\n"+courses("304")
                    + "\nAttendance:\t\t\t" + getAttendance(id,"304")
                    + "\nWritten Quiz:\t\t\t" + getw_quiz(id,"304")
                    + "\nPractical Quiz:\t\t\t" + getp_exam(id,"304")
                    + "\nProject:\t\t\t" + getProject(id,"304")
                    + "\nSem Exams:\t\t\t" + getexam(id,"304")
                    + "\n========================"
                    + "\nFinal Score:\t\t\t" + FS
                    + "\nGrade:\t\t\t" + txtGrade.getText()
                    +"\n\n"
            );
        }
        if(isRegistered(id,"306")){
            float FS = getAttendance(id,"306")+getw_quiz(id,"306")+getp_exam(id,"306")+getProject(id,"306")+getexam(id,"306");
            if (FS >= 80 && FS <= 100) {
                txtGrade.setText("A");

            } else if (FS < 80 && FS >= 70) {
                txtGrade.setText("B");

            } else if (FS < 70 && FS >= 60) {
                txtGrade.setText("C");

            } else if (FS < 60 && FS >= 50) {
                txtGrade.setText("D");

            } else if (FS < 50) {
                txtGrade.setText("F");

            } else
                txtGrade.setText("NULL");
            jtxtTranscript.append(
                    "\n"+courses("306")
                    + "\nAttendance:\t\t\t" + getAttendance(id,"306")
                    + "\nWritten Quiz:\t\t\t" + getw_quiz(id,"306")
                    + "\nPractical Quiz:\t\t\t" + getp_exam(id,"306")
                    + "\nProject:\t\t\t" + getProject(id,"306")
                    + "\nSem Exams:\t\t\t" + getexam(id,"306")
                    + "\n========================"
                    + "\nFinal Score:\t\t\t" + FS
                    + "\nGrade:\t\t\t" + txtGrade.getText()
                    +"\n\n"
            );
        }

        JButton save = new JButton("Save");
        save.setBounds(400,230,80,30);
        panel.add(save);
        save.addActionListener(e->{
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transcript.txt"));
                bufferedWriter.write(jtxtTranscript.getText());
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(myFrame,ioException.getMessage());
            }
        });

        myFrame.add(panel);
        myFrame.setVisible(true);
    }

}

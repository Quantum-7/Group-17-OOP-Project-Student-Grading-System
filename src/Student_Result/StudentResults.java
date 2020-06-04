package Student_Result;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static Student_Result.dataBase.*;

public class StudentResults {

	private JFrame frame;
	private JComboBox comboStudentID;
	JComboBox cmbCourseCode = new JComboBox();
	JComboBox student = new JComboBox();
	private JTextField txtFirstName;
	private JTextField txtSurname;
	private JTextField txtFinalScore;
	private JLabel lblNewLabel_5;
	String[] array;
	private JTextField txtRemarks;
	private JButton getStudents;
	private JLabel lblNewLabel_6;
	private JTextField txtGrade;
	private JTextField txtAttendance;
	private JLabel lblNewLabel_8;
	private JTextField txtWrittenQuiz;
	private JLabel lblNewLabel_9;
	private JTextField txtPracticalQuiz;
	private JLabel lblNewLabel_10;
	private JTextField txtProject;
	private JLabel lblNewLabel_11;
	private JTextField txtSemExams;
	private JSeparator separator;
	private JPanel panel_1;
	private JTextArea jtxtTranscript;
	private JTable table;
	private JTable table_1;
	private JTextField txtscAttendance;
	private JTextField txtscWrittenQuiz;
	private JTextField txtscPracticalQuiz;
	private JTextField txtscProject;
	private JTextField txtscSemExams;
	private ImageIcon img1, img2;
	private JLabel label1_1;
	//private JSpinner spinner_At_total;
	//private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public StudentResults() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frame = new JFrame("STUDENT GRADING SYSTEM");
//		frame.getContentPane().setBackground(new Color(152, 251, 152));
		frame.setSize(1060, 550);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

//		if (comboStudentID.getSelectedItem().toString()!=""){
//			String[] name = getName(comboStudentID.getSelectedItem().toString());
//		}




		JPanel panel = new JPanel();
//		panel.setBackground(new Color(0, 250, 154));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 4), "Student Result Recording System", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 608, 289);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSpinner spinner_SE_total = new JSpinner();
		spinner_SE_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_SE_total.setModel(new SpinnerNumberModel(100, 5, 100, 5));
		spinner_SE_total.setBounds(482, 247, 42, 20);
		panel.add(spinner_SE_total);
		
		JSpinner spinner_P_total = new JSpinner();
		spinner_P_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_P_total.setModel(new SpinnerNumberModel(100, 5, 100, 5));
		spinner_P_total.setBounds(482, 189, 42, 20);
		panel.add(spinner_P_total);
		
		JSpinner spinner_PQ_total = new JSpinner();
		spinner_PQ_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_PQ_total.setModel(new SpinnerNumberModel(50, 5, 99, 5));
		spinner_PQ_total.setBounds(482, 129, 42, 20);
		panel.add(spinner_PQ_total);
		
		JSpinner spinner_At_total = new JSpinner();
		spinner_At_total.setToolTipText("");
		spinner_At_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_At_total.setModel(new SpinnerNumberModel(20, 5, 99, 5));
		spinner_At_total.setBounds(482, 22, 42, 20);
		panel.add(spinner_At_total);
		
		JSpinner spinner_WQ_total = new JSpinner();
		spinner_WQ_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_WQ_total.setModel(new SpinnerNumberModel(50, 5, 99, 5));
		spinner_WQ_total.setBounds(482, 73, 42, 20);
		panel.add(spinner_WQ_total);


		JLabel lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(20, 115, 123, 20);
		panel.add(lblNewLabel);
		
		comboStudentID = new JComboBox();
		comboStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboStudentID.setBounds(159, 115, 133, 20);
		panel.add(comboStudentID);

		JLabel lblNewLabel_3 = new JLabel("Course Code");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_3.setBounds(20, 22, 123, 20);
		panel.add(lblNewLabel_3);

		getStudents = new JButton("Students");
		getStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getStudents.setBounds(90, 62, 100, 20);
		panel.add(getStudents);
		getStudents.addActionListener(e -> {
		comboStudentID.removeAllItems();
			String cid = cmbCourseCode.getSelectedItem().toString();
			try {
				array = students(cid);
				for (int i = 0;i<=size;i++)
					comboStudentID.addItem(array[i]);
			} catch (Exception exception) {
//				exception.printStackTrace();
			}
			for (Component C:panel.getComponents()){
				if (C instanceof JTextField || C instanceof JTextArea){
					((JTextComponent) C).setText("");
				}
			}
		});
		
		lblNewLabel_5 = new JLabel("Grade");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_5.setBounds(20, 210, 123, 20);
		panel.add(lblNewLabel_5);

		txtFinalScore = new JTextField();
		
		txtRemarks = new JTextField();
		txtRemarks.setEditable(false);
		txtRemarks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtRemarks.setColumns(10);
		txtRemarks.setBounds(159, 247, 133, 20);
		panel.add(txtRemarks);
		
		lblNewLabel_6 = new JLabel("Remarks");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_6.setBounds(20, 247, 123, 20);
		panel.add(lblNewLabel_6);
		
		txtGrade = new JTextField();
		txtGrade.setEditable(false);
		txtGrade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtGrade.setColumns(10);
		txtGrade.setBounds(159, 210, 133, 20);
		panel.add(txtGrade);
		


		cmbCourseCode.setModel(new DefaultComboBoxModel(new String[] {"304", "306"}));
		cmbCourseCode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbCourseCode.setBounds(159, 22, 133, 22);
		panel.add(cmbCourseCode);
		
		JLabel lblNewLabel_7 = new JLabel("Attendance");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_7.setBounds(314, 22, 123, 20);
		panel.add(lblNewLabel_7);
		
		txtAttendance = new JTextField();
		txtAttendance.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				
				char iNumber = evt.getKeyChar();
				if(!(Character.isDigit(iNumber))
						||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
				evt.consume();
		}
						
			}
		});
		txtAttendance.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtAttendance.setColumns(10);
		txtAttendance.setBounds(450, 22, 30, 20);
		panel.add(txtAttendance);
		
		lblNewLabel_8 = new JLabel("Written Quiz");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_8.setBounds(314, 69, 123, 32);
		panel.add(lblNewLabel_8);
		
		txtWrittenQuiz = new JTextField();
		txtWrittenQuiz.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				
				char iNumber = evt.getKeyChar();
				if(!(Character.isDigit(iNumber))
						||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
				evt.consume();
		}
						
			}
		});
		txtWrittenQuiz.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtWrittenQuiz.setColumns(10);
		txtWrittenQuiz.setBounds(450, 73, 30, 20);
		panel.add(txtWrittenQuiz);
		
		lblNewLabel_9 = new JLabel("Practical Quiz");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_9.setBounds(314, 123, 139, 32);
		panel.add(lblNewLabel_9);
		
		txtPracticalQuiz = new JTextField();
		txtPracticalQuiz.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				
				char iNumber = evt.getKeyChar();
				if(!(Character.isDigit(iNumber))
						||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
				evt.consume();
		}
						
			}
		});
		txtPracticalQuiz.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtPracticalQuiz.setColumns(10);
		txtPracticalQuiz.setBounds(450, 129, 30, 20);
		panel.add(txtPracticalQuiz);
		
		lblNewLabel_10 = new JLabel("Project");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_10.setBounds(314, 189, 123, 20);
		panel.add(lblNewLabel_10);
		
		txtProject = new JTextField();
		txtProject.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				
				char iNumber = evt.getKeyChar();
				if(!(Character.isDigit(iNumber))
						||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
				evt.consume();
		}
						
			}
		});
		txtProject.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtProject.setColumns(10);
		txtProject.setBounds(450, 189, 30, 20);
		panel.add(txtProject);
		
		lblNewLabel_11 = new JLabel("Sem Exams");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_11.setBounds(314, 247, 123, 20);
		panel.add(lblNewLabel_11);
		
		txtSemExams = new JTextField();
		txtSemExams.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {

				char iNumber = evt.getKeyChar();
				if(!(Character.isDigit(iNumber))
						||(iNumber == KeyEvent.VK_BACK_SPACE)
						||(iNumber == KeyEvent.VK_DELETE)){
				evt.consume();
		}

			}
		});
		txtSemExams.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSemExams.setColumns(10);
		txtSemExams.setBounds(450, 247, 32, 20);
		panel.add(txtSemExams);
		
		separator = new JSeparator();
		separator.setBounds(20, 160, 272, 2);
		panel.add(separator);
		
		txtscAttendance = new JTextField();
		txtscAttendance.setEditable(false);
		txtscAttendance.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtscAttendance.setColumns(10);
		txtscAttendance.setBounds(527, 22, 42, 20);
		panel.add(txtscAttendance);
		
		txtscWrittenQuiz = new JTextField();
		txtscWrittenQuiz.setEditable(false);
		txtscWrittenQuiz.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtscWrittenQuiz.setColumns(10);
		txtscWrittenQuiz.setBounds(527, 73, 42, 20);
		panel.add(txtscWrittenQuiz);
		
		txtscPracticalQuiz = new JTextField();
		txtscPracticalQuiz.setEditable(false);
		txtscPracticalQuiz.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtscPracticalQuiz.setColumns(10);
		txtscPracticalQuiz.setBounds(527, 129, 42, 20);
		panel.add(txtscPracticalQuiz);
		
		txtscProject = new JTextField();
		txtscProject.setEditable(false);
		txtscProject.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtscProject.setColumns(10);
		txtscProject.setBounds(527, 189, 42, 20);
		panel.add(txtscProject);
		
		txtscSemExams = new JTextField();
		txtscSemExams.setEditable(false);
		txtscSemExams.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtscSemExams.setColumns(10);
		txtscSemExams.setBounds(527, 247, 42, 20);
		panel.add(txtscSemExams);
		
		JSpinner spinner_At_percent = new JSpinner();
		spinner_At_percent.setModel(new SpinnerNumberModel(10, 0, 99, 5));
		spinner_At_percent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_At_percent.setBounds(568, 22, 32, 20);
		panel.add(spinner_At_percent);
		
		JSpinner spinner_WQ_percent = new JSpinner();
		spinner_WQ_percent.setModel(new SpinnerNumberModel(10, 0, 99, 5));
		spinner_WQ_percent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_WQ_percent.setBounds(568, 73, 32, 20);
		panel.add(spinner_WQ_percent);
		
		JSpinner spinner_PQ_percent = new JSpinner();
		spinner_PQ_percent.setModel(new SpinnerNumberModel(20, 0, 99, 5));
		spinner_PQ_percent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_PQ_percent.setBounds(568, 129, 32, 20);
		panel.add(spinner_PQ_percent);
		
		JSpinner spinner_P_percent = new JSpinner();
		spinner_P_percent.setModel(new SpinnerNumberModel(30, 0, 99, 5));
		spinner_P_percent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_P_percent.setBounds(568, 189, 32, 20);
		panel.add(spinner_P_percent);
		
		JSpinner spinner_SE_percent = new JSpinner();
		spinner_SE_percent.setModel(new SpinnerNumberModel(30, 0, 99, 5));
		spinner_SE_percent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinner_SE_percent.setBounds(568, 247, 30, 20);
		panel.add(spinner_SE_percent);
		
		img2 = new ImageIcon(StudentResults.class.getResource("/Student_Result/Classroom.jpg"));
		
		ImageIcon myImage = img2;
		Image img = myImage.getImage();
		Image newImage = img.getScaledInstance(1044, 511, Image.SCALE_FAST);
		ImageIcon Image2 = new ImageIcon(newImage);


		label1_1 = new JLabel();
		label1_1.setBounds(0, 0, 1044, 511);
		panel.add(label1_1);
		
		panel_1 = new JPanel();
//		panel_1.setBackground(new Color(153, 255, 204));
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 4), "Student Transcript", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(628, 11, 405, 289);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		jtxtTranscript = new JTextArea();
		jtxtTranscript.setBounds(0, 0, 385, 256);
		JScrollPane scrollPane = new JScrollPane(jtxtTranscript);
		scrollPane.setBounds(10, 22, 385, 256);
		jtxtTranscript.setEditable(false);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "Last Name", "First Name", "Attendance", "Written Quiz", "Pratical Quiz", "Project", "Sem Exams"
			}
		));
		table.setBounds(10, 336, 1023, 118);
		frame.getContentPane().add(table);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Student ID", "First Name", "Last Name", "Attendance", "Written Quiz", "Practical Quiz", "Project", "Sem Exams"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(6).setResizable(false);
		table_1.setBounds(10, 320, 1023, 17);
		frame.getContentPane().add(table_1);
		
		
		
		
		JButton btnNewButton = new JButton("Add Result");
		btnNewButton.addActionListener(e -> {
			jtxtTranscript.setText(null);
			if (comboStudentID.getItemCount()==0||txtAttendance.getText().equals("") || txtWrittenQuiz.getText().equals("") || txtPracticalQuiz.getText().equals("") ||
					txtProject.getText().equals("") ||
					txtSemExams.getText().equals("")) {
				JOptionPane.showMessageDialog(frame, "All fields must be filled");
			} else {
					//Array to get score inputs from user (E.g. 25/30 assigns a value for 25)
					double[] R = new double[5];
					R[0] = Double.parseDouble(txtAttendance.getText());
					R[1] = Double.parseDouble(txtWrittenQuiz.getText());
					R[2] = Double.parseDouble(txtPracticalQuiz.getText());
					R[3] = Double.parseDouble(txtProject.getText());
					R[4] = Double.parseDouble(txtSemExams.getText());


					//Array to Expected Total Score from user (E.g. 25/30 assigns a value for 30)
					int[] ST = new int[5];
					ST[0] = (Integer) spinner_At_total.getValue();
					ST[1] = (Integer) spinner_WQ_total.getValue();
					ST[2] = (Integer) spinner_PQ_total.getValue();
					ST[3] = (Integer) spinner_P_total.getValue();
					ST[4] = (Integer) spinner_SE_total.getValue();


					//Array to get percent values
					/*(E.g. If 25/30 holds 10% of the final score, 10 is stored.
					 * 		If   it  holds 20% of the final score, 20 is stored.
					 */
					int[] SP = new int[5];
					SP[0] = (Integer) spinner_At_percent.getValue();
					SP[1] = (Integer) spinner_WQ_percent.getValue();
					SP[2] = (Integer) spinner_PQ_percent.getValue();
					SP[3] = (Integer) spinner_P_percent.getValue();
					SP[4] = (Integer) spinner_SE_percent.getValue();


					double[] SPT = new double[5];
					//Array to hold the ratio i.e (25/30)*10% = 25*(10/30) = Actual score for that activity
					//The  ratio   in   this   example   becomes   (10/30)

					double[] tR = new double[5];
					for (int i = 0; i < 5; i++) {
						SPT[i] = (double) SP[i] / ST[i];//Ratio   =   (10/30)
						tR[i] = R[i] * SPT[i];//Multiplying 25  with (10/30) to get Actual score that activity
					}


					String[] scS = new String[5];
					//Turning these values into Strings to be displayed.
					for (int i = 0; i < 5; i++) {
						scS[i] = String.format("%.1f", tR[i]);
					}


				try {
					setAttendance(tR[0], comboStudentID.getSelectedItem().toString(), cmbCourseCode.getSelectedItem().toString());
					setw_quiz(tR[1], comboStudentID.getSelectedItem().toString(), cmbCourseCode.getSelectedItem().toString());
					setp_exam(tR[2], comboStudentID.getSelectedItem().toString(), cmbCourseCode.getSelectedItem().toString());
					setProject(tR[3], comboStudentID.getSelectedItem().toString(), cmbCourseCode.getSelectedItem().toString());
					setExam(tR[4], comboStudentID.getSelectedItem().toString(), cmbCourseCode.getSelectedItem().toString());
				} catch (Exception exception) {
//						exception.printStackTrace();
				}

				//Finally Outputting values
					txtscAttendance.setText(scS[0]);
					txtscWrittenQuiz.setText(scS[1]);
					txtscPracticalQuiz.setText(scS[2]);
					txtscProject.setText(scS[3]);
					txtscSemExams.setText(scS[4]);

					//Finding the FinalScore by adding each individual actual scores together
					double FS = tR[0] + tR[1] + tR[2] + tR[3] + tR[4];

					//Turning the FinalScore into a string
					String FSS = String.format("%.2f", FS);

					//Outputting the FinalScore
					txtFinalScore.setText(FSS);


					//Grading Criteria
					if (FS >= 80 && FS <= 100) {
						txtGrade.setText("A");
						txtRemarks.setText("Distinction");
					} else if (FS < 80 && FS >= 70) {
						txtGrade.setText("B");
						txtRemarks.setText("Good");
					} else if (FS < 70 && FS >= 60) {
						txtGrade.setText("C");
						txtRemarks.setText("Average");
					} else if (FS < 60 && FS >= 50) {
						txtGrade.setText("D");
						txtRemarks.setText("Pass");
					} else if (FS < 50) {
						txtGrade.setText("F");
						txtRemarks.setText("Fail");
					} else
						txtGrade.setText("NULL");


					//End of Computational Codes

					//Filling of Table
					DefaultTableModel model = (DefaultTableModel) table.getModel();
				try {
					model.addRow(new Object[]{

							comboStudentID.getSelectedItem().toString(),
							getName(comboStudentID.getSelectedItem().toString())[0],
							getName(comboStudentID.getSelectedItem().toString())[1],
							txtAttendance.getText(),
							txtWrittenQuiz.getText(),
							txtPracticalQuiz.getText(),
							txtProject.getText(),
							txtSemExams.getText(),

					});
				} catch (Exception exception) {
					exception.printStackTrace();
				}


//					spinner_At_total.setEnabled(false);
//						spinner_PQ_total.setEnabled(false);
//						spinner_WQ_total.setEnabled(false);
//						spinner_P_total.setEnabled(false);
//						spinner_SE_total.setEnabled(false);
//
//						spinner_At_percent.setEnabled(false);
//						spinner_PQ_percent.setEnabled(false);
//						spinner_WQ_percent.setEnabled(false);
//						spinner_P_percent.setEnabled(false);
//						spinner_SE_percent.setEnabled(false);
				}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(86, 465, 147, 41);
		frame.getContentPane().add(btnNewButton);


		JButton enroll = new JButton("Enroll");
		enroll.addActionListener(e -> {

			try {
				if(studentNumber() > 5){
					JOptionPane.showMessageDialog(frame, "Maximum of 6 students Allowed\nPlease Remove some Students!");
				}
				else {
					new Enroll();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		enroll.setFont(new Font("Tahoma", Font.BOLD, 18));
		enroll.setBounds(254, 465, 141, 41);
		frame.getContentPane().add(enroll);


		
		JButton btnTranscipt = new JButton("Transcipt");
		btnTranscipt.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTranscipt.setBounds(443, 465, 141, 41);
		frame.getContentPane().add(btnTranscipt);
		btnTranscipt.addActionListener(e -> {
			jtxtTranscript.setText("");
			String s = (String) comboStudentID.getSelectedItem();
			if(comboStudentID.getItemCount()!=0 && s!=null) {
				String id = comboStudentID.getSelectedItem().toString();

				String[] identity = new String[0];
				try {
					identity = getName(id);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				jtxtTranscript.append("Student Result Recording System\n");
				jtxtTranscript.append("Name: "+identity[0]+" "+identity[1]+" "+"\n");
				jtxtTranscript.append("Student ID: "+id+"\n\n");
				generateTranscript(id,cmbCourseCode.getSelectedItem().toString());
			}else{
				JOptionPane.showMessageDialog(frame,"Please Select a Student");
			}

		});


		JButton delete = new JButton("Delete");
		delete.addActionListener(e -> {
			try {
				new Delete();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(frame,"Error Encountered");
			}
		});
		delete.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete.setBounds(631, 465, 141, 41);
		frame.getContentPane().add(delete);
		
		JButton btnNewButton_4 = new JButton("Logout");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_4.setBounds(830, 465, 141, 41);
		frame.getContentPane().add(btnNewButton_4);
		
		img1 = new ImageIcon(getClass().getResource("Classroom.jpg"));
		JLabel label1 = new JLabel(img1);
		label1.setBounds(0, 0, 1044, 511);
		frame.getContentPane().add(label1);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit","Student Result System",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					frame.dispose();
					new Login();


				}

			}
		});

		frame.setVisible(true);
	}
	
	
	
	public ImageIcon ResizeImage(String Imagepath, JLabel JL) {

		ImageIcon myImage = new ImageIcon(Imagepath);
		Image img = myImage.getImage();
		Image newImage = img.getScaledInstance(JL.getWidth(), JL.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon Image = new ImageIcon(newImage);
		return Image;
	}

	public static void main(String[] args) throws Exception{
		new StudentResults();
	}
	public void generateTranscript(String id,String cid){
		try {
				float a =		getAttendance(id, cid);
				float b =		getw_quiz(id, cid);
				float c =		getp_exam(id, cid);
				float d =		getProject(id, cid);
				float e =		getexam(id, cid);
				float FS = a+b+c+d+e;
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
						"\n" + courses(cid)
								+ "\nAttendance:\t\t\t" + a
								+ "\nWritten Quiz:\t\t\t" + b
								+ "\nPractical Quiz:\t\t\t" + c
								+ "\nProject:\t\t\t" + d
								+ "\nSem Exams:\t\t\t" + e
								+ "\n========================"
								+ "\nFinal Score:\t\t\t" + FS
								+ "\n\n"
				);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}

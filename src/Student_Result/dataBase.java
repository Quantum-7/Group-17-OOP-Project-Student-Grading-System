package Student_Result;

import javax.swing.*;
import java.io.File;
import java.io.*;
import java.security.*;
import java.sql.*;
import java.util.ArrayList;

/*
 *   Daafuor Leslie Oppong
 *   Group 17
 *   Database

 */
public class dataBase {
    public final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static final String algorithm = "SHA-256";
    public static int size;

    private static Connection getconnection() throws SQLException {
        Connection conn;
        try {
            String url = "jdbc:sqlite:database\\SqliteDatabase.sqlite";
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),"Error Encountered: " + e.getMessage());
            return null;
        }
    }
    public static boolean login(String id, String password, String role) throws Exception {
        Connection conn = getconnection();
        Statement myStmt = conn.createStatement();
        password = generateHash(password, algorithm);
        boolean condition = false;
        try {
            String sql = "Select id, password from '"+role+"' where id = '"+id+"' and password = '"+password+"';";
            ResultSet rs = myStmt.executeQuery(sql);
            if(rs.next()){
                condition = true;
            }
            myStmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
        }
        return condition;
    }
    public static String[] getName(String id) throws Exception{
        Connection conn = getconnection();
        String[] details = new String[2];
        Statement stmt = conn.createStatement();
        try {

            String sql = String.format("select * from student where id = %s;", id);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                details[0] = rs.getString("fName");
                details[1] = rs.getString("lName");
            }
            stmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Error Encountered: "+e.getMessage());
        }
        return details;
    }
    public static String[] students(String cid) throws Exception{
        Connection conn = getconnection();
        String[] students = new String[6];
        Statement stmt = conn.createStatement();
        try {

            String sql = String.format("select studentID from course where id = %s;", cid);
            ResultSet rs = stmt.executeQuery(sql);
            size = 0;
            while (rs.next()) {
                students[size] = rs.getString("studentID");
                size++;
                if(size == 5)
                    break;
            }
            stmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Error Encountered: "+e.getMessage());
        }
        return students;
    }
    public static int studentNumber() throws Exception{
        Connection conn = getconnection();
        String[] students = new String[6];
        Statement stmt = conn.createStatement();
        try {

            String sql = "select * from student;";
            ResultSet rs = stmt.executeQuery(sql);
            size = 0;
            while (rs.next()) {
                students[size] = rs.getString("id");
                size++;
            }
            stmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Error Encountered: "+e.getMessage());
        }
        return size;
    }
    public static ArrayList<String> allStudents() throws Exception{
        Connection conn = getconnection();
        ArrayList<String> students = new ArrayList<>();
        Statement stmt = conn.createStatement();
        try {
            String sql = String.format("select * from student;");
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                students.add(rs.getString("id"));
                i++;
            }
            stmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Error Encountered: "+e.getMessage());
        }
        return students;
    }
    public static void dltStudent(String id) throws Exception {
        Connection conn = getconnection();
        try {
            String sql = String.format("delete from student where id = %s;", id);
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Server Busy\nTry Closing the Application and Try again");
        }
    }
    public static void dltCourse(String id) throws Exception {
        Connection conn = getconnection();
        try {
            String sql2 = String.format("delete from course where StudentID = %s;", id);
            PreparedStatement preparedStmt2 = conn.prepareStatement(sql2);
            preparedStmt2.executeUpdate();
            preparedStmt2.close();
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Server Busy\nTry Closing the Application and Try again");
        }
    }
    public static String[] registered(String id) throws Exception {
        Connection conn = getconnection();
        Statement stmt = conn.createStatement();
        String[] course = new String[2];
        int i = 0;
        try {
            String sql = String.format("select id from course where studentID = %s;", id);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                course[i] = rs.getString("id");
                i++;
            }
            stmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Error Encountered: "+ e.getMessage());
        }
        return course;
    }
    public static boolean isRegistered(String id,String cid) throws Exception {
        Connection conn = getconnection();
        String sql = String.format("select id from course where studentID = %s and id=%s;",id,cid);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            return true;
        }
        stmt.close();
        conn.close();
        rs.close();
        return false;
    }
    public static ArrayList<String> Registered(String id) throws Exception {
        Connection conn = getconnection();
        ArrayList<String> stringing = null;
        String sql = String.format("select id from course where studentID = %s ",id);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            stringing.add(rs.getString("id"));
        }
        stmt.close();
        conn.close();
        rs.close();
        return stringing;
    }
    public static String courses(String cid) throws Exception{
        Connection conn = getconnection();
        String title = null;
        Statement stmt = conn.createStatement();
        try {

            String sql = String.format("select name from course where id = %s;", cid);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                title = rs.getString("name");
            }
            stmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Error Encountered: "+e.getMessage());
        }
        return title;
    }
    public static void signUp(String id,String fName,String lName,String password,String phone,String email) throws Exception {
        Connection conn = getconnection();
        try {
            String query = " insert into student (id, fName, lName, password, phone, email)"
                    + " values (?, ?, ?, ?, ?, ?);";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, id);
            preparedStmt.setString(2, fName);
            preparedStmt.setString(3, lName);
            preparedStmt.setString(4, generateHash(password, "SHA-256"));
            preparedStmt.setString(5, phone);
            preparedStmt.setString(6, email);

            preparedStmt.execute();
            preparedStmt.close();
            conn.close();
            JOptionPane.showMessageDialog(new JFrame(),"Student Enrolled");
        }catch (Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Error Encountered: "+e.getMessage());
        }
    }
    public static void chooseCourse(String sid, String cid, String name) throws Exception{
        Connection conn = getconnection();
        try {
            String query = "Insert into course (id, studentID, name) values (?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cid);
            stmt.setString(2, sid);
            stmt.setString(3, name);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(new JFrame(), "Registration Successful");
        }catch (Exception e){
            JOptionPane.showMessageDialog(new JFrame(),
                    e.getMessage()
            );
        }

    }
    public static void setAttendance(double attendance, String sid, String cid) throws Exception{
        Connection conn = getconnection();
        try {
            String query = "Update course set attendance = ? where studentID = ? and id = ?;";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setDouble(1, attendance);
            preparedStmt.setString(2, sid);
            preparedStmt.setString(3, cid);
            preparedStmt.execute();

            preparedStmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Error Ecountered: "+e.getMessage());
        }
    }
    public static void setw_quiz(double w_quiz, String sid, String cid) throws Exception{
        Connection conn = getconnection();
        try {
            String query = "Update course set w_quiz = ? where studentID = ? and id = ?;";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setDouble(1, w_quiz);
            preparedStmt.setString(2, sid);
            preparedStmt.setString(3, cid);
            preparedStmt.execute();
//            System.out.println("Scoring Successful!");
            preparedStmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Error Ecountered: "+e.getMessage());
        }
    }
    public static void setp_exam(double p_exam, String sid, String cid) throws Exception{
        Connection conn = getconnection();
        try {
            String query = "Update course set p_exam = ? where studentID = ? and id = ?;";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setDouble(1, p_exam);
            preparedStmt.setString(2, sid);
            preparedStmt.setString(3, cid);
            preparedStmt.execute();
//            System.out.println("Scoring Successful!");
            preparedStmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Error Ecountered: "+e.getMessage());
        }
    }
    public static void setExam(double exam, String sid, String cid) throws Exception{
        Connection conn = getconnection();
        try {
            String query = "Update course set f_score = ? where studentID = ? and id = ?;";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setDouble(1, exam);
            preparedStmt.setString(2, sid);
            preparedStmt.setString(3, cid);
            preparedStmt.execute();
//            System.out.println("Scoring Successful!");
            preparedStmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Error Ecountered: "+e.getMessage());
        }
    }
    public static void setProject(double exam, String sid, String cid) throws Exception{
        Connection conn = getconnection();
        try {
            String query = "Update course set project = ? where studentID = ? and id = ?;";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setDouble(1, exam);
            preparedStmt.setString(2, sid);
            preparedStmt.setString(3, cid);
            preparedStmt.execute();
//            System.out.println("Scoring Successful!");
            preparedStmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Error Ecountered: "+e.getMessage());
        }
    }
    public static float getAttendance(String sid, String cid) throws Exception{
        Connection conn = getconnection();
        float attendance = 0;
        try {
            Statement mystmt = conn.createStatement();
            String sql = String.format("select attendance from course where id= %s and studentID = %s;", cid, sid);
            ResultSet rs = mystmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("No course registered");
            }
            else{

                attendance = rs.getFloat("attendance");

            }
            conn.close();
            rs.close();
            mystmt.close();

        }catch (Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
        return attendance;
    }
    public static float getp_exam(String sid, String cid) throws Exception{
        Connection conn = getconnection();
        float p_exam = 0;
        try {
            Statement mystmt = conn.createStatement();
            String sql = String.format("select p_exam from course where id= %s and studentID = %s;", cid, sid);
            ResultSet rs = mystmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("No course registered");
            }
            else{
                p_exam = rs.getFloat("p_exam");
            }
            mystmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
        return p_exam;
    }
    public static float getProject(String sid, String cid) throws Exception{
        Connection conn = getconnection();
        float w_quiz = 0;
        try {
            Statement mystmt = conn.createStatement();
            String sql = String.format("select project from course where id= %s and studentID = %s;", cid, sid);
            ResultSet rs = mystmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("No course registered");
            }
            else{
                w_quiz = rs.getFloat("project");
            }
            mystmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
        return w_quiz;
    }
    public static float getw_quiz(String sid, String cid) throws Exception{
        Connection conn = getconnection();
        float w_quiz = 0;
        try {
            Statement mystmt = conn.createStatement();
            String sql = String.format("select w_quiz from course where id= %s and studentID = %s;", cid, sid);
            ResultSet rs = mystmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("No course registered");
            }
            else{
                w_quiz = rs.getFloat("w_quiz");
            }
            mystmt.close();
            rs.close();
            conn.close();
        }catch (Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
        return w_quiz;
    }
    public static float getexam(String sid, String cid) throws Exception{
        Connection conn = getconnection();
        float exam = 0;
        try {
            Statement mystmt = conn.createStatement();
            String sql = String.format("select f_score from course where id= %s and studentID = %s;", cid, sid);
            ResultSet rs = mystmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("No course registered");
            }
            else{
                exam = rs.getFloat("f_score");
            }
            mystmt.close();
            conn.close();
            rs.close();
        }catch (Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
        return exam;
    }
    public static void setImage(String id, String role, String path){
        try{
            Connection conn = getconnection();
            File file=new File(path);
            FileInputStream fis=new FileInputStream(file);
            PreparedStatement ps = null;
            if(role.equals("student")){
                ps=conn.prepareStatement("update student set img_data = ?  where id= ?");
            }
            else if(role.equals("lecturer")){
                ps=conn.prepareStatement("update lecturer set img_data = ?  where id= ?");
            }
//            ps.setString(1,role);
            ps.setBinaryStream(1,fis,(int)file.length());
            ps.setString(2,id);
            ps.executeUpdate();

            ps.close();
            fis.close();
            conn.close();
        }catch(Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
    }
    public static void getImage(String id, String role){
        try{
            Connection conn = getconnection();
            File file=new File("img/profile.png");
            FileOutputStream fos=new FileOutputStream(file);
            byte[] b;
            Blob blob;

            PreparedStatement ps;
            if(role.equals("student")){
                ps=conn.prepareStatement("select img_data from student where id=?");
            }
            else{
                ps=conn.prepareStatement("select img_data from lecturer where id=?");
            }
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                blob=rs.getBlob("img_data");
                b=blob.getBytes(1,(int)blob.length());
                fos.write(b);
            }

            fos.close();
            ps.close();
            conn.close();
        }catch(Exception e){
//            System.out.println("Error Encountered: "+e.getMessage());
        }
    }
    public static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        byte[] hash = digest.digest(data.getBytes());
        return byteConvert(hash);
    }
    public static String byteConvert(byte[] bytes){
        char[] hexChars = new char[bytes.length * 2];
        for(int i=0;i<bytes.length;i++){
            int v = bytes[i] & 0xFF;
            hexChars[i*2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}

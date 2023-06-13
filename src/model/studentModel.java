/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EZIO AUDITORY
 */
public class studentModel {

    private Connection conn;

    public studentModel() {
        conn = db.dbConnection.getConnection();
    }

    public String registerStudent(String studentName, String address, Date dateOfBirth) {
        String message = null;
        String query = "INSERT INTO student(name,address,dob)VALUE(?,?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, studentName);
            psm.setString(2, address);
            psm.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String updateStudent(String studentNo, String studentName, String address, Date dateOfBirth) {
        String message = null;
        String query = "UPDATE student SET name = ?,address = ?,dob = ? WHERE sno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, studentName);
            psm.setString(2, address);
            psm.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
            psm.setString(4, studentNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String deleteStudent(String studentNo) {
        String message = null;
        String query = "DELETE FROM student WHERE sno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, studentNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public Object[] getSelectedStudentData(int studentNo) {
        String query = "SELECT * FROM student WHERE sno = ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, studentNo);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                int studentNum = rs.getInt("sno");
                String studentName = rs.getString("name");
                String address = rs.getString("address");
                Date dateOfBirth = rs.getDate("dob");
                rowData = new Object[]{studentNum, studentName, address, dateOfBirth};
            }

            return rowData;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void loadStudentTable(String keyword, DefaultTableModel studentTableModel) {
        String query = "SELECT * FROM student WHERE name LIKE ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, "%" + keyword + "%");
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            studentTableModel.setRowCount(0);
            while (rs.next()) {
                int studentNum = rs.getInt("sno");
                String studentName = rs.getString("name");
                String address = rs.getString("address");
                Date dateOfBirth = rs.getDate("dob");
                rowData = new Object[]{studentNum, studentName, address, dateOfBirth};
                studentTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}

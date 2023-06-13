/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EZIO AUDITORY
 */
public class teacherModel {

    private Connection conn;

    public teacherModel() {
        conn = db.dbConnection.getConnection();
    }

    public String registerTeacher(String teacherName, String address, int subject) {
        String message = null;
        String query = "INSERT INTO teacher(name,address,subjects)VALUE(?,?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, teacherName);
            psm.setString(2, address);
            psm.setInt(3, subject);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String updateTeacher(String teacherNo, String teacherName, String address, int subject) {
        String message = null;
        String query = "UPDATE teacher SET name = ?,address = ?,subjects = ? WHERE tno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, teacherName);
            psm.setString(2, address);
            psm.setInt(3, subject);
            psm.setString(4, teacherNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String deleteTeacher(String teacherNo) {
        String message = null;
        String query = "DELETE FROM teacher WHERE tno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, teacherNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public Object[] getSelectedTeacherData(int teacherNo) {
        String query = "SELECT * FROM teacher WHERE tno = ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, teacherNo);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                int teacherNum = rs.getInt("tno");
                String teacherName = rs.getString("name");
                String address = rs.getString("address");
                int subject = rs.getInt("subjects");
                rowData = new Object[]{teacherNum, teacherName, address, subject};
            }

            return rowData;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void loadTeacherTable(String keyword, DefaultTableModel teacherTableModel) {
        String query = "SELECT * FROM teacher INNER JOIN subject ON  teacher.subjects = subject.subno WHERE teacher.name LIKE ? or subject.subjectname LIKE ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, "%" + keyword + "%");
            psm.setString(2, "%" + keyword + "%");
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            teacherTableModel.setRowCount(0);
            while (rs.next()) {
                int teacherNum = rs.getInt("tno");
                String teacherName = rs.getString("name");
                String address = rs.getString("address");
                String subject = rs.getString("subjectname");
                rowData = new Object[]{teacherNum, teacherName, address, subject};
                teacherTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Vector comboBoxLoad() {
        String query = "SELECT * FROM subject";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            ResultSet rs = psm.executeQuery();

            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("subjectname"));
            }
            return v;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}

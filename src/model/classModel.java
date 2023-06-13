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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EZIO AUDITORY
 */
public class classModel {

    private Connection conn;

    public classModel() {
        conn = db.dbConnection.getConnection();
    }

    public String registerclass(int subject, int teacher, String time) {
        String message = null;
        String query = "INSERT INTO class(subno,tno,timeslot)VALUE(?,?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, subject);
            psm.setInt(2, teacher);
            psm.setString(3, time);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String updateclass(String classNo, int subject, int teacher, String time) {
        String message = null;
        String query = "UPDATE class SET subno = ?,tno = ?,timeslot = ? WHERE classno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, subject);
            psm.setInt(2, teacher);
            psm.setString(3, time);
            psm.setString(4, classNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String deleteclass(String classNo) {
        String message = null;
        String query = "DELETE FROM class WHERE classno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, classNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public Object[] getSelectedclassData(int classNo) {
        String query = "SELECT * FROM class WHERE classno = ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, classNo);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                int classNum = rs.getInt("classno");
                int subject = rs.getInt("subno");
                int teacher = rs.getInt("tno");
                String time = rs.getString("timeslot");
                rowData = new Object[]{classNum, subject, teacher, time};
            }

            return rowData;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void loadClassTable(String keyword, DefaultTableModel classTableModel) {
        String query = "SELECT * FROM class INNER JOIN subject INNER JOIN  teacher ON class.subno = subject.subno AND class.tno = teacher.tno WHERE teacher.name LIKE ? or subject.subjectname LIKE ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, "%" + keyword + "%");
            psm.setString(2, "%" + keyword + "%");
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            classTableModel.setRowCount(0);
            while (rs.next()) {
                int classNum = rs.getInt("classno");
                String subject = rs.getString("subjectname");
                String teacher = rs.getString("name");
                String time = rs.getString("timeslot");
                rowData = new Object[]{classNum, subject, teacher, time};
                classTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Vector subjectNamecomboBoxLoad() {
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

    public Vector teacherNamecomboBoxLoad() {
        String query = "SELECT * FROM teacher";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            ResultSet rs = psm.executeQuery();

            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("name"));
            }
            return v;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}

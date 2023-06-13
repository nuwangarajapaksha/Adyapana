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
public class paymentModel {

    private Connection conn;

    public paymentModel() {
        conn = db.dbConnection.getConnection();
    }

    public String insertpayment(int studentNo, int teacher, int subject, String month, String value) {
        String message = null;
        String query = "INSERT INTO invoice(sno,tno,subno,month,value)VALUE(?,?,?,?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, studentNo);
            psm.setInt(2, teacher);
            psm.setInt(3, subject);
            psm.setString(4, month);
            psm.setString(5, value);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String updatepayment(String invoiceNo, int studentNo, int teacher, int subject, String month, String value) {
        String message = null;
        String query = "UPDATE invoice SET sno = ?,tno = ?,subno = ?,month = ?,value = ? WHERE iNo = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, studentNo);
            psm.setInt(2, teacher);
            psm.setInt(3, subject);
            psm.setString(4, month);
            psm.setString(5, value);
            psm.setString(6, invoiceNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String deletepayment(String invoiceNo) {
        String message = null;
        String query = "DELETE FROM invoice WHERE iNo = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, invoiceNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public Object[] getSelectedpaymentData(int invoiceNo) {
        String query = "SELECT * FROM invoice INNER JOIN student ON invoice.sno = student.sno WHERE invoice.iNo = ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, invoiceNo);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                int invoiceNum = rs.getInt("iNo");
                int studentNo = rs.getInt("sno");
                String studentName = rs.getString("student.name");
                int teacher = rs.getInt("tno");
                int subject = rs.getInt("subno");
                String month = rs.getString("month");
                double value = rs.getDouble("value");
                rowData = new Object[]{invoiceNum, studentNo,studentName, teacher, subject, month, value};
            }

            return rowData;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void loadpaymentTable(String keyword, DefaultTableModel paymentTableModel) {
        String query = "SELECT * FROM invoice INNER JOIN student INNER JOIN  teacher INNER JOIN subject "
                + "ON invoice.sno = student.sno AND invoice.tno = teacher.tno AND invoice.subno = subject.subno "
                + "WHERE student.name LIKE ? or teacher.name LIKE ? or subject.subjectname LIKE ? or invoice.month LIKE ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, "%" + keyword + "%");
            psm.setString(2, "%" + keyword + "%");
            psm.setString(3, "%" + keyword + "%");
            psm.setString(4, "%" + keyword + "%");
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            paymentTableModel.setRowCount(0);
            while (rs.next()) {
                int invoiceNum = rs.getInt("iNo");
                int studentNo = rs.getInt("sno");
                String studentName = rs.getString("student.name");
                String teacher = rs.getString("teacher.name");
                String subject = rs.getString("subjectname");
                String month = rs.getString("month");
                double value = rs.getDouble("value");
                rowData = new Object[]{invoiceNum, studentNo ,studentName, teacher, subject, month, value};
                paymentTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Vector studentNamecomboBoxLoad() {
        String query = "SELECT * FROM student";
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EZIO AUDITORY
 */
public class subjectModel {
    
    private Connection conn;
    
    public subjectModel(){
        conn = db.dbConnection.getConnection();
    }

    public String registersubject(String subjectName, String description, String price) {
        String message = null;
        String query = "INSERT INTO subject(subjectname,description,price)VALUE(?,?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, subjectName);
            psm.setString(2, description);
            psm.setString(3, price);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String updatesubject(String subjectNo, String subjectName, String description, String price) {
        String message = null;
        String query = "UPDATE subject SET subjectname = ?,description = ?,price = ? WHERE subno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, subjectName);
            psm.setString(2, description);
            psm.setString(3, price);
            psm.setString(4, subjectNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public String deletesubject(String subjectNo) {
        String message = null;
        String query = "DELETE FROM subject WHERE subno = ?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, subjectNo);
            psm.execute();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public Object[] getSelectedsubjectData(int subjectNo) {
        String query = "SELECT * FROM subject WHERE subno = ?";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setInt(1, subjectNo);
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;

            while (rs.next()) {
                int subjectNum = rs.getInt("subno");
                String subjectName = rs.getString("subjectname");
                String description = rs.getString("description");
                double price  = rs.getDouble("price");
                rowData = new Object[]{subjectNum, subjectName, description, price};
            }

            return rowData;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void loadSubjectTable(String keyword, DefaultTableModel subjectTableModel) {
        String query = "SELECT * FROM subject WHERE subjectname LIKE ? ";
        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, "%" + keyword + "%");
            ResultSet rs = psm.executeQuery();
            Object[] rowData = null;
            subjectTableModel.setRowCount(0);
            while (rs.next()) {
                int subjectNum = rs.getInt("subno");
                String subjectName = rs.getString("subjectname");
                String description = rs.getString("description");
                double price  = rs.getDouble("price");
                rowData = new Object[]{subjectNum, subjectName, description, price};
                subjectTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
}

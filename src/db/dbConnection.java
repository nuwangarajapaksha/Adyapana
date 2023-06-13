/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author EZIO AUDITORY
 */
public class dbConnection {

    private static Connection conn;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/adyapana_db?useSSL=false", "root", "ass34");
        } catch (Exception e) {
            conn = null;
        }

        return conn;

    }
    
//    public static void main(String[] args) {
//        try {
//            System.out.println(getConnection().getCatalog());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

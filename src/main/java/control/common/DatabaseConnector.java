package control.common;

import javax.naming.CommunicationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
    //mysql://b8f424d93bf6a2:193e1b25@us-cdbr-iron-east-05.cleardb.net/heroku_fc4b6c5c2746fe7?reconnect=true
    private static String dbUrl = "jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_fc4b6c5c2746fe7?";
    private static String unicode = "useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
    private static String username = "b8f424d93bf6a2";
    private static String password = "193e1b25";

    private static Connection instance;

    public static Connection getInstance() {
        if (instance == null)
            instance = create();
        return instance;
    }

    private static Connection create() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl + unicode, username, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static void keepConnection() {
//        String sql = "SELECT 1 FROM tbl_admin";
//
//        try {
//            if (instance != null) {
//                PreparedStatement pst = instance.prepareStatement(sql);
//                pst.executeQuery();
//            } else {
//                instance = create();
//                keepConnection();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        DatabaseConnector.instance = create();
    }
}

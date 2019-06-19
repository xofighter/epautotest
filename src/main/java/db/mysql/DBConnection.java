package db.mysql;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 数据库连接层MYSQL
 *
 * @author Administrator
 */
public class DBConnection {


    /**
     * 连接数据库
     *
     * @return
     */
    public static Connection getDBConnection() {
        // 1. 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 获取数据库的连接
        try {

//            "jdbc:mysql://192.168.30.13:3306/eports_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false",
//            "root",
//            "123456"
            Connection conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://192.168.30.13:3306/eports_dev?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false",
                    "root",
                    "123456");
            return conn;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
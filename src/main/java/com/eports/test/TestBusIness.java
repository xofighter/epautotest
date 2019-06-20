package com.eports.test;

import db.mysql.DBManager;
import db.mysql.DataTable;

import java.sql.SQLException;
import java.sql.Types;

public class TestBusIness {

    static String searchSql = "select * from order_enum";
    static String insertSql = "insert into score(name, age, score)values(?,?,?)";
//  static String deleteSql = "delete from score where id = ?";
//  static String updateSql = "update score set name = ? where id = ?";

    public static void main(String[] args) {
//      intsertData();
        searchData();
    }

    private static void intsertData() {
        DBManager dm = new DBManager();
        String[] coulmn = new String[]{"wyf2", "23", "89.5"};
        int[] type = new int[]{Types.CHAR, Types.INTEGER, Types.DOUBLE};

        try {
            boolean flag = dm.updateOrAdd(coulmn, type, insertSql);
            if (flag)
                System.out.println("插入成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void searchData() {
        DBManager dm = new DBManager();
        String[] coulmn = null;
        int[] type = null;

        try {
            DataTable dt = dm.getResultData(coulmn, type, searchSql);
            if (dt != null && dt.getRowCount() > 0) {
                for (int i = 0; i < dt.getRowCount(); i++) {
                    for (int j = 0; j < dt.getColCoun(); j++)
                        System.out.printf(dt.getRow()[i][j] + "\t");
                    System.out.println();
                }
            } else
                System.out.println("查询失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




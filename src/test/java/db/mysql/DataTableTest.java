package db.mysql;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class DataTableTest {

    @BeforeMethod
    public void setUp() {


    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testSetDataTable() {
        String searchSql = "select * from order_enum;";
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

//    @Test
//    public void testGetColumn() {
//    }
//
//    @Test
//    public void testSetColumn() {
//    }
//
//    @Test
//    public void testGetRow() {
//    }
//
//    @Test
//    public void testSetRow() {
//    }
//
//    @Test
//    public void testGetRowCount() {
//    }
//
//    @Test
//    public void testSetRowCount() {
//    }
//
//    @Test
//    public void testGetColCoun() {
//    }
//
//    @Test
//    public void testSetColCoun() {
//    }
}
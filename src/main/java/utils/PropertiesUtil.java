package utils;

import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 */
public class PropertiesUtil {


    private String filePath;

    public PropertiesUtil(String filePath) {
        super();
        this.filePath = filePath;
    }

    /**
     * 获取key对应的值
     * @param key
     * @return
     */
    public Object getProperty(String key) {
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pro.get(key);
    }


    /**
     * 更新键值对，有就更新，没有就新增
     * @param key
     * @param value
     */
    public void setProperty(String key, Object value) {
        // 1.读取原来的数据
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            File f = new File(filePath);
            if(!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            in = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            pro.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 2.更新数据
        pro.setProperty(key, (String) value);
        try {
            pro.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String filePath = PropertiesUtil.class.getResource("").getPath() + "\\a.properties";
        PropertiesUtil property = new PropertiesUtil(filePath);
        // 读取属性
        property.setProperty("age", "20");
        System.out.println(property.getProperty("age"));
        property.setProperty("name", "dadadd");

    }
}

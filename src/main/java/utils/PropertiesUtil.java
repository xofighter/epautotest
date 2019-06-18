package utils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * @author hushuang
 */
public class PropertiesUtil {

//   private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class.getName());
//
//    /**
//     * 根据key读取value
//     * @param filePath
//     * @param key
//     * @return
//     */
//    public static String readValue(String filePath,String key) {
//        Properties prop = new Properties();
//        InputStream in = null;
//        try {
//            in = new BufferedInputStream(new FileInputStream(filePath));
//            if (in != null) {
//                prop.load(in);
//            }
//            return prop.getProperty(key);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return null;
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
//    }
//
//
//
//    /**
//     * 获取配置文件
//     * @param filePath
//     * @return
//     */
//    public static Properties getProperties(String filePath) {
//        Properties prop = new Properties();
//        InputStream in = null;
//        try {
//            File file = new File(filePath);
////          直接读取文件
//            if (file.canRead()) {
//                in = new BufferedInputStream(new FileInputStream(file));
////          从当前路径中获取文件流
//            } else {
//                in = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
//            }
//            if (in != null) {
//                prop.load(in);
//            }
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
//        return prop;
//    }


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

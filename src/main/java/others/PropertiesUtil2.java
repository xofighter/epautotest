package others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil2 {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil2.class.getName());

    /**
     * 根据key读取value
     * @param filePath  文件路径
     * @param key       键
     * @return
     */
    public static <property> String readValue(String filePath, String key) {
        Properties prop = new Properties();
        InputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            if (in != null) {
                prop.load(in);
            }
            return prop.getProperty(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    /**
     * 获取配置文件
     * @param filePath
     * @return
     */
    public static Properties getProperties(String filePath) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            File file = new File(filePath);
//          直接读取文件
            if (file.canRead()) {
                in = new BufferedInputStream(new FileInputStream(file));
//          从当前路径中获取文件流
            } else {
                in = PropertiesUtil2.class.getClassLoader().getResourceAsStream(filePath);
            }
            if (in != null) {
                prop.load(in);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return prop;
    }

}

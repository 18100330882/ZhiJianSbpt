package com.yongjie.ZhiJianSbpt.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebPropsInfo {
    private static Properties properties = new Properties();

    /**
     * 静态代码区只执行一次，用于初始化必要信息。
     */
    static {
        InputStream inputStream = WebPropsInfo.class.getClassLoader().getResourceAsStream("web.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 避免外部生成此类对象。
     */
    private WebPropsInfo() {

    }

    /**
     * 获得web.properties文本中systemFlag信息。
     *
     * @return 返回系统默认flag信息
     */
    public static String getSystemFlag() {
        System.out.println(properties.getProperty("systemFlag"));
        return properties.getProperty("systemFlag");
    }
}

/**
 * @Author: 黄煜豪
 * @Createtime: 2016年12月26日 上午9:24:01
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnSqlite {
    @SuppressWarnings("finally")
    public static Connection getConn(String filePath) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //建立一个数据库连接，如果不存在就在当前目录下创建之
            conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }

}

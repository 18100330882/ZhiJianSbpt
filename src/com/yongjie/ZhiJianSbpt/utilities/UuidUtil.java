/**
 * @Author: 朱卫士
 * @Createtime: 2020年6月18日 上午9:01:47
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.utilities;

import java.util.UUID;

public class UuidUtil {

    /**
     * 获取uuid字符串
     * 	类似于 : b17f24ff026d40949c85a24f4f375d42
     * @return
     */
    public static String getSimpleUUID() {
        return getRandomUUID().replace("-", "");
    }

    /**
     * 获取uuid字符串
     * 	类似于:a5c8a5e8-df2b-4706-bea4-08d0939410e3
     * @return
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
}

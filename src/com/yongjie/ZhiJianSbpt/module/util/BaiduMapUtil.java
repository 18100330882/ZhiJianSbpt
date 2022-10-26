/**
 * @Author: 朱卫士
 * @Createtime: 2020年11月4日 上午9:02:47
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.module.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class BaiduMapUtil {

    public static String MAP_AK = "74IcfwXiFhCw5fRVxj1kGSo6DLefFWe2";

    public static String MAP_URL = "http://api.map.baidu.com/reverse_geocoding/v3/?output=json&extensions_town=true&ak=" + MAP_AK;

    /**
     * 将经纬度获取解析成详细地址
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @return
     */
    public static String getAddress(String lng, String lat) {
        String address = "";
        String location = lat + "," + lng;
        BufferedReader in = null;
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(MAP_URL + "&location=" + location);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
                String town = result.getJSONObject("result").getJSONObject("addressComponent").getString("town");
                address = result.getJSONObject("result").getString("formatted_address") + town;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }


    public static void main(String[] args) throws Exception {
        getAddress("1", "2");
    }
}

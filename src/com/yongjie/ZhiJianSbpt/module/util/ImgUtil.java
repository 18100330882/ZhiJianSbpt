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
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;


public class ImgUtil {

    /**
     * 读取照片里面的信息
     */
    public static JSONObject printImageTags(File file) throws ImageProcessingException, Exception {
        JSONObject json = new JSONObject();

        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                //System.out.println(tag.getTagName() + ", " + tag.getDescription());

                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息
                if (tagName.equals("Image Height")) {
                    //System.out.println("图片高度: "+desc);
                } else if (tagName.equals("Image Width")) {
                    //System.out.println("图片宽度: "+desc);
                } else if (tagName.equals("Date/Time Original")) {
                    //System.out.println("拍摄时间: "+desc);
                    json.put("imgTime", desc);
                } else if (tagName.equals("GPS Latitude")) {
                    //System.err.println("纬度 : "+pointToLatlong(desc));
                    json.put("lat", pointToLatlong(desc));
//                    System.err.println("纬度(度分秒格式) : "+pointToLatlong(desc));
                } else if (tagName.equals("GPS Longitude")) {
                    //System.err.println("经度: "+pointToLatlong(desc));
                    json.put("lng", pointToLatlong(desc));
//                    System.err.println("经度(度分秒格式): "+pointToLatlong(desc));
                }
            }
        }

        return json;
    }

    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     * @param point 坐标点
     * @return
     */
    public static String pointToLatlong(String point) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60;
        return duStr.toString();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\1.jpg");
        JSONObject s = printImageTags(file);

        String add = BaiduMapUtil.getAddress(s.getString("lng"), s.getString("lat"));
        System.out.println(add);
    }
}

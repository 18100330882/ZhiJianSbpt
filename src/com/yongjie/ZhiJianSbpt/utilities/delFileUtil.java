package com.yongjie.ZhiJianSbpt.utilities;

import java.io.File;

/**
 * 删除文件工具类
 *
 * @author zws
 * 2020年4月10日 星期五
 */
public class delFileUtil {

    /**
     * 删除文件
     *
     * @param filePath : 文件路径
     * @return 删除成功 返回 true 失败或文件不存在返回false
     */
    public static Boolean delFile(String filePath) throws Exception {
        File file = getFile(filePath);
        if (file != null) {
            return file.delete();
        }
        return false;
    }

    /**
     * 获取文件的全路径
     *
     * @param filePath 传入的文件路径
     * @return 返回当前 文件
     */
    public static File getFile(String filePath) throws Exception {
        File file = getFile(filePath, 1);
        if (file == null) {
            return getFile(filePath, 2);
        }
        return file;
    }

    /**
     * 获取文件
     *
     * @param filePath 传入的文件名称
     * @param num      判断文件的路径在那个盘符
     */
    public static File getFile(String filePath, int num) throws Exception {
        String fileAllPath = "";
        if (num == 1) {
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
        }
        if (num == 2) {
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY_W + filePath;
        }
        File file = new File(fileAllPath);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    /**
     * 获取文件的全路径,<前提是文件存在,否则返回null>
     *
     * @param filePath 传入的文件名称
     * @param num      判断文件的全路径在那个盘符
     */
    public static String getFileAllPath(String filePath) throws Exception {
        File file = getFile(filePath);
        if (file != null) {
            return file.getPath();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        // 获取文件真实路径
        String filePath = "2020-07-03/1.jpg";
        String fileAllPath = getFileAllPath(filePath);
        System.out.println(fileAllPath);

        // 获取文件
        File file = getFile(filePath);
        System.out.println(file != null ? "文件存在" : "文件被UFO抓走了~~~");


        // 删除文件
        Boolean delFile = delFile(filePath);
        System.out.println(delFile ? "删除成功!" : "文件不存在!");
    }
}

/**
 * @Author: 朱卫士
 * @Createtime: 2020年6月21日 下午9:41:56
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.utilities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    /**
     * 加密数据和秘钥的编码方式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * appid value 由管理员进行听哦过
     */
    public static final String APPID_KEY = "appid";
    public static final String APPID_VALUE = "auditfeedback";
    public static final String PARAM_DATA_KEY = "data";
    public static final String TIMESTAMPE_KEY = "timestampe";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PARAM_BUSLIST_KEY = "buslist";

    /**
     * 公钥由接口方提供
     */
    // public static final String PUBLIC_KEY_STR =
    // "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2NWKYEovmNJWWZwdygSOsgpfw98e9ae2tIOxw8H6cL3ZgZB2UDWrL3UgDFhqrRpv89xE/YYxqDXanJBLKpo5TzF8qv67Q0ZVTGc75vdAuqS/+02EMNXPBWBjc/BFZZtyfT6AyK9vM0l7Pgwm2Tq9Ey7E5XM3khgvYkPZ2QtIovjpPNyUkFVgKNyaXKEBKDdk/9xuBSeVtn0A/i19lyiQ3By8C0QlNqIItffDN1HVG2dkhFIieeNxI91FAT5TvgdUM7cDzEtQ5JgVQqTCTkJPGQZnx91mMHL1Ry65mRrcI9hW0tnUrBt9rTCc8qZfqo0Wk28V3G5rEB6fGlTqJMm2LwIDAQAB";
    public static final String PUBLIC_KEY_STR = "yHBC7ZbzGm8iAqcFv80+fUNDCvy19LIeStrkCHdCpjQ=";

    /**
     * `* 根据公钥和原始内容产生加密内容
     */

    // public static final String AES_ALGORITHM = "RSA";
    public static final String AES_ALGORITHM = "AES";

    public static final String RSA_ALGORITHM = "AES";
    // public static final String RSA_ALGORITHM = "RSA";

    /**
     * 对数据进行加密
     *
     * @param publicKeyStr 加密公钥 由管理端进行提供
     * @param paramsText   传递参数
     */
    public static String encryptRSA(String publicKeyStr, String paramsText)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.encodeBase64String(cipher.doFinal(paramsText.getBytes(UTF_8)));
    }

    /**
     * 根据公钥和原始内容产生加密内容
     */
    public static String encryptAES(String publicKeyStr, String plainText)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(publicKeyStr), AES_ALGORITHM);

        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(UTF_8)));
    }

    /**
     * 根据公钥和原始内容产生加密内容
     */
    public static String encryptAES(String plainText)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(PUBLIC_KEY_STR), AES_ALGORITHM);

        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(UTF_8)));
    }

    /**
     * 对数据进行加密
     *
     * @param publicKeyStr 加密公钥 由管理端进行提供
     * @param paramsText   传递参数
     */
    public static String encryptRSA(String paramsText)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        return encryptRSA(PUBLIC_KEY_STR, paramsText);
    }

    // 转json
    public static String encryptJsonRSA(Object object) throws Exception {
        try {
            String jsonString = JSON.toJSONString(object);
            try {
                return encryptRSA(jsonString);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // 转json
    public static String encryptJsonAES(Object object) throws Exception {
        try {
            String jsonString = JSON.toJSONString(object);
            try {
                return encryptRSA(jsonString);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String post(String urlPath, Object object) throws Exception {
        try {
            String paramToStringJson = getParamToStringJson(object);
            try {
                URL url = new URL(urlPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty(APPID_KEY, APPID_VALUE);
                // 设置超时时间
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                // 建立连接
                conn.connect();
                // 传入参数
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                // 转化并填充参数
                Map<String, Object> map = new HashMap<>();
                map.put("plainText", paramToStringJson);
                JSONObject jo = new JSONObject(map);
                String content = jo.toString();
                dos.writeBytes(content);
                // 关闭流
                dos.flush();
                dos.close();
                // 获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                conn.disconnect();
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String post(String urlPath, Object object, int total, int num, int pageIndex) throws Exception {
        try {
            String paramToStringJson = getParamToStringJson(object, total, num, pageIndex);
            try {
                URL url = new URL(urlPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty(APPID_KEY, APPID_VALUE);
                // 设置超时时间
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                // 建立连接
                conn.connect();
                // 传入参数
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                // 转化并填充参数
                Map<String, Object> map = new HashMap<>();
                map.put("plainText", paramToStringJson);
                JSONObject jo = new JSONObject(map);
                String content = jo.toString();
                dos.writeBytes(content);
                // 关闭流
                dos.flush();
                dos.close();
                // 获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                conn.disconnect();
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String get(String httpurl) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    /**
     * 对参数进行加密
     *
     * @param obj 传递参数集 》 可以要对象，集合（具体参考api接口参数规范）
     * @return
     */
    public static String getParamToStringJson(Object obj) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(PARAM_DATA_KEY, obj);
        paramMap.put(APPID_KEY, APPID_VALUE);
        paramMap.put(TIMESTAMPE_KEY, DateUtils.format(new Date(), YYYYMMDDHHMMSS));
        String plainTextStr = JSON.toJSONString(paramMap);
        try {
            // return encryptJsonAES(plainTextStr);
            return TestUnicode.gbEncoding(plainTextStr);
        } catch (Exception e) {
            throw new RuntimeException("[" + AES_ALGORITHM + "]加密出错！请检查公钥是否正确！");
        }
    }

    /**
     * 对参数进行加密
     *
     * @param obj 传递参数集 》 可以要对象，集合（具体参考api接口参数规范）
     * @return
     */
    public static String getParamToStringJson(Object obj, int total, int num, int pageIndex) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(PARAM_DATA_KEY, obj);
        paramMap.put("taotalnum", total);
        paramMap.put("num", num);
        paramMap.put("pageindex", pageIndex);
        paramMap.put(APPID_KEY, APPID_VALUE);
        paramMap.put(TIMESTAMPE_KEY, DateUtils.format(new Date(), YYYYMMDDHHMMSS));
        String plainTextStr = JSON.toJSONString(paramMap);
        try {
            // return encryptJsonAES(plainTextStr);
            return TestUnicode.gbEncoding(plainTextStr);
        } catch (Exception e) {
            throw new RuntimeException("[" + AES_ALGORITHM + "]加密出错！请检查公钥是否正确！");
        }
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> unidMap = new HashMap<>();
        unidMap.put("uuid", "853C780BB5A24B6BA9AD8DFA91258654");
        unidMap.put("filePath", "2020-07-03/5c3b9a421d494949b308cad8f2ea795d.xls");
        unidMap.put("fileKey", "b5b4e489aa084bd8a5d1d3c751b45796");

        // String url = "http://183.129.215.106:11119/api/upload/downLoadFileByUuid";

        String url = "http://192.168.1.36:8110/ZhiJianXzsp/api/util/file_download.action?filePath=2020-7-1/1.png&fileKey=b5b4e489aa084bd8a5d1d3c751b45796";
        fileWritePost(url, unidMap, "png");
    }

    /**
     * 调用第三方下载文件接口,用于下载文件到本地服务器,如果成功,则返回成功路径名称
     *
     * @param urlPath
     * @param object
     * @throws IOException
     */
    public static String fileWritePost(String urlPath, Object object, String ext) throws IOException {
        try {
            String paramToStringJson = getParamToStringJson(object);
            try {
                URL url = new URL(urlPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty(APPID_KEY, APPID_VALUE);
                // 设置超时时间
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                // 建立连接
                conn.connect();
                // 传入参数
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                // 转化并填充参数
                Map<String, Object> map = new HashMap<>();
                map.put("plainText", paramToStringJson);
                JSONObject jo = new JSONObject(map);
                String content = jo.toString();
                dos.writeBytes(content);
                // 关闭流
                dos.flush();
                dos.close();
                InputStream inputStream = conn.getInputStream();
                String localFilePath = DateUtils.format() + "/" + UuidUtil.getSimpleUUID() + "." + ext;
                String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + localFilePath;
                java.io.File fileOut = new java.io.File(fileAllPath);
                FileUtils.copyInputStreamToFile(inputStream, fileOut);
                conn.disconnect();
                return localFilePath;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 对参数进行加密
     *
     * @param obj 传递参数集 》 可以要对象，集合（具体参考api接口参数规范）
     * @return
     */
    public static String getParamToStringJson2(Object obj) {
        Map<String, Object> paramMap = new HashMap<>();
        ApiQywj entity = (ApiQywj) obj;
        paramMap.put(PARAM_DATA_KEY, entity.getJsonDate());
        paramMap.put(PARAM_BUSLIST_KEY, entity.getBuslist());
        paramMap.put(APPID_KEY, APPID_VALUE);
        paramMap.put(TIMESTAMPE_KEY, DateUtils.format(new Date(), YYYYMMDDHHMMSS));
        String plainTextStr = JSON.toJSONString(paramMap);
        try {
            // return encryptJsonAES(plainTextStr);
            return TestUnicode.gbEncoding(plainTextStr);
        } catch (Exception e) {
            throw new RuntimeException("[" + AES_ALGORITHM + "]加密出错！请检查公钥是否正确！");
        }
    }

    public static String post2(String urlPath, Object object) throws Exception {
        try {
            String paramToStringJson = getParamToStringJson2(object);
            try {
                URL url = new URL(urlPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty(APPID_KEY, APPID_VALUE);
                // 设置超时时间
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                // 建立连接
                conn.connect();
                // 传入参数
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                // 转化并填充参数
                Map<String, Object> map = new HashMap<>();
                map.put("plainText", paramToStringJson);
                JSONObject jo = new JSONObject(map);
                String content = jo.toString();
                dos.writeBytes(content);
                // 关闭流
                dos.flush();
                dos.close();
                // 获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                conn.disconnect();
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

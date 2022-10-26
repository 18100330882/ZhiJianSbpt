package com.yongjie.ZhiJianSbpt.utilities;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import java.sql.Timestamp;
import java.util.Date;

public class JSON {
    public static String Encode(Object obj) {
        if (obj == null || obj.toString().equals("null")) return null;
        if (obj != null && obj.getClass() == String.class) {
            return obj.toString();
        }
        JSONSerializer serializer = new JSONSerializer();
        //为json解析器设置固定日期（date，timestamp）格式
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
        serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
        return serializer.deepSerialize(obj);
    }

    public static Object Decode(String json) {
        if (StringUtil.isNullOrEmpty(json)) return "";
        JSONDeserializer deserializer = new JSONDeserializer();
        deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));
//		deserializer.use(String.class, new DateTransformer("yyyy-MM-dd"));//hyh modified
        //得到源对象
        Object obj = deserializer.deserialize(json);
        //如果源对象不为空，且是string类型。String.class得到String的ClassType
        if (obj != null && obj.getClass() == String.class) {
            return Decode(obj.toString());
        }
        return obj;
    }
}


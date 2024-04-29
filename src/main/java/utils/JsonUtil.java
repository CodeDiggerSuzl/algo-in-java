package utils;

import com.alibaba.fastjson2.JSON;

public class JsonUtil {
    public static String toJson(Object o) {
        return JSON.toJSONString(o);
    }

    public static <T> T toBean(String s, Class<T> tClass) {
        return JSON.parseObject(s, tClass);
    }


}

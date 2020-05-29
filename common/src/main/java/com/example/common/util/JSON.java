package com.example.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Json序列化反序列化工具类,基于Jackson
 */
@Slf4j
public class JSON {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转Json格式字符串
     *
     * @param obj 对象
     * @return Json格式字符串
     */
    public static <T> String stringify(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Parse Object to String error : ", e);
            return null;
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     *
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public static <T> String stringifyPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Parse Object to String error : ", e);
            return null;
        }
    }

    /**
     * 字符串转换为自定义对象
     *
     * @param str   要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    public static <T> T parse(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.error("Parse String to Object error : ", e);
            return null;
        }
    }


    /**
     * 进行复杂类型反序列化工作 （自定义类型的集合类型）
     * 使用示例:
     * List<User> userList = JSON.parseCollection(userListJson, new TypeReference<List<User>>() {});
     *
     * @param str           源字符串
     * @param typeReference 包含elementType与CollectionType的typeReference
     */
    public static <T> T parseCollection(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, typeReference);
        } catch (Exception e) {
            log.error("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * 进行复杂类型反序列化工作（可变类型数量的）
     * 使用示例：
     * List<User> userList = JSON.parseCollection(userListJson, List.class, User.class);
     *
     * @param str             需要进行反序列化的字符串
     * @param collectionClazz 需要反序列化的集合类型 由于这里的类型未定，且为了防止与返回值类型T冲突，故采用<?>表示泛型
     * @param elementClasses  集合中的元素类型（可多个）   此处同上通过<?>...表示多个未知泛型
     * @param <T>             返回值的泛型类型是由{@link JavaType}获取的
     * @return 反序列化对象集合
     */
    public static <T> T parseCollection(String str, Class<?> collectionClazz, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.error("Parse String to Object error : ", e);
            return null;
        }
    }

    /**
     * List反序列化,简化调用
     *
     * @param str          json字符串
     * @param elementClass 列表内元素类型
     * @return 反序列化对象集合
     */
    public static <T> List<T> parseList(String str, Class<T> elementClass) {
        return parseCollection(str, List.class, elementClass);
    }
}

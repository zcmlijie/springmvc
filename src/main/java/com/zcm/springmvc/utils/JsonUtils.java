/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zcm.springmvc.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONException;
/*import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;*/
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class JsonUtils {

 private static ObjectMapper mapper = new ObjectMapper();

 private JsonUtils() {
 }

 public static String toJson(Object value) {
  try {
   return mapper.writeValueAsString(value);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return null;
 }

 public static <T> T toObject(String json, Class<T> valueType) {
  Assert.hasText(json);
  Assert.notNull(valueType);
  try {
   return mapper.readValue(json, valueType);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return null;
 }

 public static <T> T toObject(String json, TypeReference<?> typeReference) {
  Assert.hasText(json);
  Assert.notNull(typeReference);
  try {
   return mapper.readValue(json, typeReference);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return null;
 }

 public static <T> T toObject(String json, JavaType javaType) {
  Assert.hasText(json);
  Assert.notNull(javaType);
  try {
   return mapper.readValue(json, javaType);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return null;
 }

 public static void writeValue(Writer writer, Object value) {
  try {
   mapper.writeValue(writer, value);
  } catch (JsonGenerationException e) {
   e.printStackTrace();
  } catch (JsonMappingException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 public static String readJson(String path) {
  //从给定位置获取文件
  File file = new File(path);
  BufferedReader reader = null;
  //返回值,使用StringBuffer
  StringBuffer data = new StringBuffer();
  //
  try {
   reader = new BufferedReader(new FileReader(file));
   //每次读取文件的缓存
   String temp = null;
   while ((temp = reader.readLine()) != null) {
    data.append(temp);
   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   //关闭文件流
   if (reader != null) {
    try {
     reader.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
  }
  return data.toString();
 }

 /**
  * 将Json对象转换成Map
  *
  * @param jsonObject json对象
  * @return Map对象
  * @throws JSONException
  */
 /*public static Map<String, Object> toMap(String jsonString) throws JSONException {

  JSONObject jsonObject = new JSONObject(jsonString);

  Map<String, Object> result = new HashMap<String, Object>();
  Iterator iterator = jsonObject.keys();
  String key = null;
  String value = null;

  while (iterator.hasNext()) {

   key = (String) iterator.next();
   value = jsonObject.getString(key);
   result.put(key, value);

  }
  return result;

 }*/

 /**
  * 获取请求Content-Type: application/json 类型的二进制数据，转换为JSON数据格式
  *
  * @param request 客户端请求
  * @return JSON字符串
  * @author Mbox
  */
 public static String toJSON(HttpServletRequest request) {
  InputStreamReader input = null;
  BufferedReader reader = null;
  try {
   HttpEntity data = new InputStreamEntity(request.getInputStream(), request.getContentLength());
   input = new InputStreamReader(data.getContent(), "utf-8");
   reader = new BufferedReader(input);
   String line = null;
   StringBuilder sb = new StringBuilder();
   while ((line = reader.readLine()) != null) {
    sb.append(line);
   }

   return sb.toString();

  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   try {
    input.close();
    reader.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }

  return null;
 }
 /**
  * 检查数据中是否包含所要求的Key
  *
  * @param json JSON字符串
  * @param keys 需要测试的key（可变长度参数）
  * @return 包含全部key返回true; 如果 json 不是一个正确的JSON格式字符串，返回false;
  * @author Mbox
  */
 public static boolean hasKeys(String json, String... args) {
  try {
   return hasKeys(com.alibaba.fastjson.JSONObject.parseObject(json), args);
  } catch (Exception e) {
   return false;
  }
 }

 /**
  * 检查JSON数据中是否包含所要求的Key
  *
  * @param json JSON对象
  * @param keys 需要测试的key（可变长度参数）
  * @return 包含全部key返回true
  * @author Mbox
  */
 public static boolean hasKeys(com.alibaba.fastjson.JSONObject json, String... keys) {
  try {
   if (json == null) {
    return false;
   }
   if (keys == null || keys.length == 0) {
    return false;
   }
   if (json.isEmpty()) {
    return false;
   }
   Set<String> keySet = json.keySet();
   for (String key : keys) {
    if (!json.containsKey(key)) {
     return false;
    }
   }
   return true;
  } catch (Exception e) {
   return false;
  }
 }
}
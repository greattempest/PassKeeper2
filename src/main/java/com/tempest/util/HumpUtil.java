package com.tempest.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;

import java.io.BufferedReader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 顾乘瑞
 */
public class HumpUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern linePatternAfterNoValue = Pattern.compile("_");

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String strLineToHump(String str) {
        // 不包含下划线时并且不以_结尾时不处理。例如:user
        boolean isEndOfGlideLine = str.endsWith(linePatternAfterNoValue.toString());
        if (!str.contains(linePatternAfterNoValue.toString()) && !isEndOfGlideLine) {
            return str;
        }
        // 如果已下划线结尾，则截取下划线之前的字符
        str = isEndOfGlideLine ? str.substring(0, str.length() - 1).toLowerCase() : str.toLowerCase();
        Matcher matcher = isEndOfGlideLine ? linePatternAfterNoValue.matcher(str) : linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String strLineToLowerCaseHump(String str) {
        str = str.toLowerCase();
        // 不包含下划线时并且不以_结尾时不处理。例如:user
        boolean isEndOfGlideLine = str.endsWith(linePatternAfterNoValue.toString());
        if (!str.contains(linePatternAfterNoValue.toString()) && !isEndOfGlideLine) {
            return str;
        }
        // 如果已下划线结尾，则截取下划线之前的字符
        str = isEndOfGlideLine ? str.substring(0, str.length() - 1).toLowerCase() : str.toLowerCase();
        Matcher matcher = isEndOfGlideLine ? linePatternAfterNoValue.matcher(str) : linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String initialsUpperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 表名转类名
     * 首字母大写 + 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String tableName2ClassName(String str) {
        //去除表名开头的 t_ 和 T_
        if (str.startsWith("t_") || str.startsWith("T_")) {
            str = str.replace("t_", "").replace("T_", "");
        }
        return initialsUpperCase(lineToHump(str));
    }

    /**
     * 将map中key的下滑线写法转化为驼峰
     * 如：{item_type:'111'}转化后则变为{itemType:'111'}
     *
     * @param source 原始map对象
     * @return
     */
    public static Map mapKeysLineToHump(Map source) {
        if (MapUtil.isEmpty(source)) {
            return null;
        }
        Map<String, Object> target = new HashMap<>();
        for (Object o : source.keySet()) {
            if (source.get(o) instanceof Clob) {
                Clob clob = (Clob) source.get(o);
                target.put(strLineToLowerCaseHump(o.toString()), ClobToString(clob));
                continue;
            }
            target.put(strLineToHump(o.toString()), source.get(o));
        }
        return target;
    }

    /**
     * 将map中key的下滑线写法转化为驼峰
     * 如：{ITEM_TYPE:'111'}转化后则变为{itemType:'111'}
     *
     * @param source 原始map对象
     * @return
     */
    public static Map mapKeysLineToLowerCaseHump(Map source) {
        if (MapUtil.isEmpty(source)) {
            return null;
        }
        Map<String, Object> target = new HashMap<>();
        for (Object o : source.keySet()) {
            if (source.get(o) instanceof Clob) {
                Clob clob = (Clob) source.get(o);
                target.put(strLineToLowerCaseHump(o.toString()), ClobToString(clob));
                continue;
            }
            target.put(strLineToLowerCaseHump(o.toString()), source.get(o));
        }
        return target;
    }

    /**
     * 将List<Map>中将map中key的下滑线写法转化为驼峰
     * 如：[{item_type:'111'}]转化后则变为[{itemType:'111'}]
     *
     * @param source
     * @return
     */
    public static List<Map> listMapKeysLineToHump(List<Map> source) {
        List target = new ArrayList();
        if (CollUtil.isEmpty(source)) {
            return target;
        }

        source.forEach(item -> {
            target.add(mapKeysLineToHump(item));
        });

        return target;
    }

    /**
     * 将List<Map>中将map中key的下滑线写法转化为驼峰
     * 如：[{ITEM_TYPE:'111'}]转化后则变为[{itemType:'111'}]
     *
     * @param source
     * @return
     */
    public static List<Map> listMapKeysLineToLowerCaseHump(List<Map> source) {
        List target = new ArrayList();
        if (CollUtil.isEmpty(source)) {
            return target;
        }

        source.forEach(item -> {
            target.add(mapKeysLineToLowerCaseHump(item));
        });

        return target;
    }

    /**
     * clob类型的数据转为String
     * @param clob
     * @return
     */
    public static String ClobToString(Clob clob) {

        String reString = "";
        try {
            java.io.Reader is = clob.getCharacterStream();// 得到流
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();
            StringBuffer sb = new StringBuffer();
            while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                sb.append(s);
                s = br.readLine();
            }
            reString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reString;
    }
}

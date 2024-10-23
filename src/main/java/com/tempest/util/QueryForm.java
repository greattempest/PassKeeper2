package com.tempest.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @Author 顾乘瑞
 * @Description 通用查询参数
 * @Date 2019/3/18
 */
@Data
public class QueryForm {
    //查询参数
    private Map<String, Object> params;
    //可传入多个排序字段，用【,】分开
    private List<Sorts> sorts;
    private Integer pageNum;

    private Integer pageSize;

    public QueryForm (){
        params = new HashMap<String, Object>();
        pageNum = 1;
        pageSize = 10;
    }
}

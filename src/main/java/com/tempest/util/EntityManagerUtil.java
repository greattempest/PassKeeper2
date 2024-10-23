package com.tempest.util;

import java.math.BigInteger;
import java.text.Normalizer;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.map.MapUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * 功能描述:
 * 自定义sql工具类
 *
 * @author Wzq
 * @date 2022/5/17 16:55
 */
@Component
@Transactional(readOnly = true)
public class EntityManagerUtil<T> {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 通过sql 返回List<Map> 结构的数据
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> getListMap(String sql) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = nativeQuery.getResultList();
        return resultList;
    }
    /**
     * 通过sql 返回List<Map> 结构的数据
     *
     * @param sql
     * @return
     */
    public Integer UpDateMap(String sql) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        List resultList = nativeQuery.getResultList();
        return nativeQuery.executeUpdate();
    }
    /**
     * 通过sql 返回List<Map> 结构的数据
     *
     * @param sql
     * @param params 查询参数 如{name:111}
     * @return
     */
    public List<Map<String, Object>> getListMap(String sql, Map<String, Object> params) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        if (MapUtil.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                nativeQuery.setParameter(key, params.get(key));
            }
        }
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = nativeQuery.getResultList();
        return resultList;
    }

    /**
     * 通过sql 返回List<Map> 结构的数据,且map的可以为驼峰
     *
     * @param sql
     * @param params 查询参数 如{name:111}
     * @return
     */
    public List<Map<String, Object>> getHumpListMap(String sql, Map<String, Object> params) {
    	Long timemark = new Date().getTime();
        //System.out.println("&3:"+timemark);
        //System.out.println("3:"+(new Date().getTime()-timemark));
       Query nativeQuery = entityManager.createNativeQuery(sql);
        if (MapUtil.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                nativeQuery.setParameter(key, params.get(key));
            }
        }
        //System.out.println("4:"+(new Date().getTime()-timemark));
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = nativeQuery.getResultList();
        //System.out.println("5:"+(new Date().getTime()-timemark));
        return HumpUtil.listMapKeysLineToHump(resultList);
    }




    /**
     * 返回自定义类型的结果
     *
     * @param sql
     * @param clazz 类型
     * @return
     */
    public List getListBean(String sql, Class clazz) {
        sql = Normalizer.normalize(sql, Normalizer.Form.NFKC);
        Query query = entityManager.createNativeQuery(sql);
        List queryList = query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return queryList;
    }

    public List<T> getListBean(String sql, Class clazz, Map<String, Object> params) {
        sql = Normalizer.normalize(sql, Normalizer.Form.NFKC);
        Query query = entityManager.createNativeQuery(sql);
        if (MapUtil.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        List<T> queryList = query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return queryList;
    }

    /**
     * 获取分页数据
     *
     * @param sql
     * @param form
     * @return
     */
    public Page getListMapPageInfo(String sql, QueryForm form) {
        return PageUtil.converListMapToPage(getListMap(sql), form);
    }

    /**
     * 获取分页数据
     *
     * @param sql
     * @param params 查询参数 如{name:111}
     * @param form
     * @return
     */
    public Page getListMapPageInfo(String sql, Map<String, Object> params, QueryForm form) {
        Integer totalCount = totalCount(sql, params);
        Query nativeQuery = entityManager.createNativeQuery(sql);
        // 分页
        Pageable pageRequest = PageRequest.of(form.getPageNum() - 1, form.getPageSize());
        if (MapUtil.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                nativeQuery.setParameter(key, params.get(key));
            }
        }
        int start = (int) pageRequest.getOffset();
        int end = pageRequest.getPageSize();
        Query query = nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(start);
        query.setMaxResults(end);
        List<Map> list = query.getResultList();
        return new PageImpl(HumpUtil.listMapKeysLineToHump(list), pageRequest, totalCount);
    }

    /**
     * 获取条数
     *
     * @param sql
     * @param params
     * @return
     */
    public Integer totalCount(String sql, Map<String, Object> params) {
        sql = "select count(1) from (" + sql + ") countAlias";
        Query query = entityManager.createNativeQuery(sql);
        if (MapUtil.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.TO_LIST);
        List list = (List) query.getResultList().get(0);
        BigInteger bigInteger = (BigInteger) list.get(0);
        return bigInteger.intValue();
    }
}

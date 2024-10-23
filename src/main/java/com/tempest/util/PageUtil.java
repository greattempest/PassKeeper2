package com.tempest.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author qiu
 * @date 2020/8/12 11:23 上午
 */
public class PageUtil {

    /**
     * 获取分页数据
     *
     * @param listInfo
     * @param form
     * @return
     */
    public static Page converListToPage(List listInfo, QueryForm form) {
        // 分页
        Pageable pageRequest = PageRequest.of(form.getPageNum() - 1, form.getPageSize());

        int start = (int) pageRequest.getOffset();
        int end = (start + pageRequest.getPageSize()) > listInfo.size() ? listInfo.size() : (start + pageRequest.getPageSize());


        return new PageImpl(listInfo.subList(start, end), pageRequest, listInfo.size());

    }

    /**
     * 获取分页数据
     *
     * @param listInfo
     * @param form
     * @return
     */
    public static Page converListMapToPage(List listInfo, QueryForm form) {
        // 分页
        Pageable pageRequest = PageRequest.of(form.getPageNum() - 1, form.getPageSize());

        int start = (int) pageRequest.getOffset();
        int end = (start + pageRequest.getPageSize()) > listInfo.size() ? listInfo.size() : (start + pageRequest.getPageSize());


        return new PageImpl(HumpUtil.listMapKeysLineToHump(listInfo.subList(start, end)), pageRequest, listInfo.size());

    }

}

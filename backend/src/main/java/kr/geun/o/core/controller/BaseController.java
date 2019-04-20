package kr.geun.o.core.controller;

import kr.geun.o.core.pagination.PaginationInfo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 기본 컨트롤러
 *
 * @author akageun
 */
public class BaseController {

    /**
     * Pagination Setup
     *
     * @param rtnMap
     * @param page
     * @param pageBlockSize
     * @param <T>
     */
    protected <T> void setPagination(Map<String, Object> rtnMap, Page<T> page, int pageBlockSize) {
        //@formatter:off
        PaginationInfo paginationInfo = new PaginationInfo(
            page.getNumber(),
            page.getNumberOfElements(),
            page.getTotalElements(),
			page.getTotalPages(),
            pageBlockSize);
        //@formatter:on

        rtnMap.put("pagination", paginationInfo);
    }
}

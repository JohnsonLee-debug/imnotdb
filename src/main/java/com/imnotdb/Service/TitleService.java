package com.imnotdb.Service;

import org.nutz.dao.QueryResult;

import java.util.Map;

public interface TitleService {
    QueryResult getTitleByCnds(Map<String, Object> conditions, int pageNumber, int pageSize, boolean setTotal);
}

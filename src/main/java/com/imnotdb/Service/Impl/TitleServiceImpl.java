package com.imnotdb.Service.Impl;

import com.imnotdb.Mapper.TitleMapper;
import com.imnotdb.Service.TitleService;
import org.nutz.dao.QueryResult;

import java.util.Map;

public class TitleServiceImpl implements TitleService {
    @Override
    public QueryResult getTitleByCnds(Map<String, Object> conditions, int pageNumber, int pageSize, boolean setTotal) {
        return new TitleMapper().getTitleByCnds(conditions, pageNumber, pageSize, setTotal);
    }
}

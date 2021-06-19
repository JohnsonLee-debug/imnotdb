package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Title;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TitleFullService {
    List<Title> searchByCond(Map<String, Object> cond, int pageNo, int size, Integer fetchAll) throws Exception;
}

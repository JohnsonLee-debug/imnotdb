package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.mapper.TitleFullMapper;
import com.imnotdb.imnotdb.mapper.TitleMapper;
import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.service.TitleFullService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TitleFullServiceImpl implements TitleFullService {
    @Autowired
    private TitleFullMapper titleFullMapper;
    @Autowired
    private TitleMapper titleMapper;
    @Override
    public List<Title> searchByCond(Map<String, Object> conditions, int pageNo, int size, Integer fetchAll) throws ClassCastException{
        if (conditions.containsKey(SymbolTable.ISADULT)){
            Integer isAdult = Integer.valueOf((String) conditions.get(SymbolTable.ISADULT));
            if(isAdult != 0 && isAdult != 1){
                conditions.put(SymbolTable.ISADULT, 0);
            }else{
                conditions.put(SymbolTable.ISADULT, isAdult);
            }
        }
        if (conditions.containsKey(SymbolTable.YEAR_GTE)){
            Integer yearGTE = Integer.valueOf((String) conditions.get(SymbolTable.YEAR_GTE));
            conditions.put(SymbolTable.YEAR_GTE, yearGTE);
        }
        if (conditions.containsKey(SymbolTable.YEAR_LTE)){
            Integer yearLTE = Integer.valueOf((String) conditions.get(SymbolTable.YEAR_LTE));
            conditions.put(SymbolTable.YEAR_LTE, yearLTE);
        }
        if (conditions.containsKey(SymbolTable.RATING_GTE)){
            Double ratingGTE = Double.valueOf((String) conditions.get(SymbolTable.RATING_GTE));
            conditions.put(SymbolTable.RATING_GTE, ratingGTE);
        }
        if (conditions.containsKey(SymbolTable.RATING_LTE)){
            Double ratingLTE = Double.valueOf((String) conditions.get(SymbolTable.RATING_LTE));
            conditions.put(SymbolTable.RATING_LTE, ratingLTE);
        }
        if (conditions.containsKey(SymbolTable.LENGTH_GTE)){
            Integer lengthGTE = Integer.valueOf((String) conditions.get(SymbolTable.LENGTH_GTE));
            conditions.put(SymbolTable.LENGTH_GTE, lengthGTE);
        }
        if (conditions.containsKey(SymbolTable.LENGTH_LTE)){
            Integer lengthLTE = Integer.valueOf((String) conditions.get(SymbolTable.LENGTH_LTE));
            conditions.put(SymbolTable.LENGTH_LTE, lengthLTE);
        }
        String[] tconstArr = titleFullMapper.getTitleByCnds(conditions, pageNo, size);
        Stream<Title> titleStream = Arrays.stream(tconstArr)
                .map((x -> titleMapper.getTitleByTconst(x)));
        List<Title> titleList = titleStream.collect(Collectors.toList());
        if(fetchAll == 1){
            for (Title title : titleList) {
                titleMapper.fetchAllLinks(title);
            }
        }
        return titleList;
    }
}

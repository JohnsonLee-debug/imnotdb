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
        if (conditions.containsKey(SymbolTable.AKASTITLES)){
            if(!(conditions.get(SymbolTable.AKASTITLES) instanceof String)){
                throw new ClassCastException("Title name should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.DIRECTOR)){
            if(!(conditions.get(SymbolTable.DIRECTOR) instanceof String)){
                throw new ClassCastException("Director name should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.WRITER)){
            if(!(conditions.get(SymbolTable.WRITER) instanceof String)){
                throw new ClassCastException("Writer name should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.ACTOR)){
            if(!(conditions.get(SymbolTable.ACTOR) instanceof String)){
                throw new ClassCastException("Actor name should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.TITLETYPE)){
            if(!(conditions.get(SymbolTable.TITLETYPE) instanceof String)){
                throw new ClassCastException("Title type should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.GENRES)){
            if(!(conditions.get(SymbolTable.GENRES) instanceof String)){
                throw new ClassCastException("Title genres should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.ISADULT)){
            if(!((conditions.get(SymbolTable.ISADULT) instanceof Integer)
            && ((Integer) conditions.get(SymbolTable.ISADULT) == 1
            || (Integer) conditions.get(SymbolTable.ISADULT) == 0))){
                conditions.put(SymbolTable.ISADULT, 0);
            }
        }
        if (conditions.containsKey(SymbolTable.YEAR_GTE)){
            if(!(conditions.get(SymbolTable.YEAR_GTE) instanceof Integer)){
                throw new ClassCastException("Year lower bound should be Integer");
            }
        }
        if (conditions.containsKey(SymbolTable.YEAR_LTE)){
            if(!(conditions.get(SymbolTable.YEAR_LTE) instanceof Integer)){
                throw new ClassCastException("Year upper bound should be Integer");
            }
        }
        if (conditions.containsKey(SymbolTable.RATING_GTE)){
            if(!(conditions.get(SymbolTable.RATING_GTE) instanceof Double)){
                throw new ClassCastException("Rating lower bound should be Double");
            }
        }
        if (conditions.containsKey(SymbolTable.RATING_LTE)){
            if(!(conditions.get(SymbolTable.RATING_LTE) instanceof Double)){
                throw new ClassCastException("Rating upper bound should be Double");
            }
        }
        if (conditions.containsKey(SymbolTable.LENGTH_GTE)){
            if(!(conditions.get(SymbolTable.LENGTH_GTE) instanceof Integer)){
                throw new ClassCastException("Title name should be String");
            }
        }
        if (conditions.containsKey(SymbolTable.LENGTH_LTE)){
            if(!(conditions.get(SymbolTable.LENGTH_LTE) instanceof Integer)){
                throw new ClassCastException("RuntimeMinute upper bound should be Integer");
            }
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

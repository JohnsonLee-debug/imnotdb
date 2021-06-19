package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.Crew;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CrewMapper {
    @Autowired
    @Qualifier(SymbolTable.NUTDAO)
    private NutDao dao;
}

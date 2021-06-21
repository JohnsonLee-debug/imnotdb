package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.mapper.TitleMapper;
import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.service.TitleService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    @Qualifier(SymbolTable.NUTDAO)
    private NutDao dao;
    @Autowired
    private TitleMapper titleMapper;
    @Override
    public Title getTitleByTconst(String tconst, Integer fetchAll) {
        Title fetch = dao.fetch(Title.class, tconst);
        if(fetchAll == 1){
            titleMapper.fetchAllLinks(fetch);
        }
        return fetch;
    }

    @Override
    public void insertTitle(Title title) {
        titleMapper.insertTitle(title);
    }

    @Override
    public void updateTitle(Title title) {
        titleMapper.updateTitle(title);
    }

    @Override
    public void deleteTitle(String tconst) {
        titleMapper.deleteTitle(tconst);
    }
}

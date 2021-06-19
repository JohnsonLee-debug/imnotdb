package com.imnotdb.imnotdb.service.impl;

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
    @Override
    public Title getTitleByTconst(String tconst) {
        return dao.fetch(Title.class, tconst);
    }

    @Override
    public void updateTitle(Title title) {
        dao.update(title);
    }
}

package com.imnotdb.Mapper;

import com.imnotdb.Entity.Title;
import com.imnotdb.Mapper.TitleMapper;
import com.imnotdb.Utils.NutDaoUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;

@Slf4j
class TitleMapperTest {
    static private final NutDao nutDao = NutDaoUtils.getNutDao();
    @Test
    void getTitleByTconst() {
        TitleMapper titleMapper = new TitleMapper();
        String tconst = "tt0000006";
        Title title = titleMapper.getTitleByTconst(tconst);
        log.info("{}:{}", tconst, nutDao.fetchLinks(title, "ratings"));
    }

    @Test
    void getTitleByAdult() {
    }

    @Test
    void getTitleByGenres() {
    }

    @Test
    void getTitleByType() {
    }

    @Test
    void getTitleByLen() {
        TitleMapper titleMapper = new TitleMapper();
        QueryResult titleByLen = titleMapper.getTitleByLen(null, null, 1, 20, true);
        for (Title title : titleByLen.getList(Title.class)) {
            log.info(title.toString());
        }
    }

}
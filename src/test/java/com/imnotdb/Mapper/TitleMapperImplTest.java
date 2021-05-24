package com.imnotdb.Mapper;

import com.imnotdb.Entity.Title;
import com.imnotdb.Mapper.TitleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TitleMapperTest {

    @Test
    void getTitleByTconst() {
        TitleMapper titleMapper = new TitleMapper();
        String tconst = "tt0000006";
        Title title = titleMapper.getTitleByTconst(tconst);
        log.info("{}:{}", tconst, title);
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
    }

    @Test
    void getTitleByRating() {
    }
}
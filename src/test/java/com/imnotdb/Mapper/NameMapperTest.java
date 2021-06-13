package com.imnotdb.Mapper;

import com.imnotdb.Entity.Name;
import com.imnotdb.Entity.Title;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.dao.QueryResult;

import java.util.List;

@Slf4j
class NameMapperTest {

    @Test
    void getNameByNconst() {
        NameMapper nameMapper = new NameMapper();
        Name name = nameMapper.getNameByNconst("nm0241054");
        log.info(name.toString());
    }

    @Test
    void getNameByJobAndName() {
        NameMapper nameMapper = new NameMapper();
        String name = "Tom";
        String job = "writer";
        QueryResult nameByJobAndName = nameMapper.getNameByJobAndName(name, job, 1, 20, false);
        for (Name name1 : nameByJobAndName.getList(Name.class)) {
            log.info(name1.toString());
        }
    }

    @Test
    void getDirectorByName() {
        NameMapper nameMapper = new NameMapper();
        String name = "Tom";
        QueryResult nameByJobAndName = nameMapper.getDirectorByName(name, 1, 20, false);
        for (Name name1 : nameByJobAndName.getList(Name.class)) {
            log.info(name1.toString());
        }
    }

    @Test
    void getActorByName() {
        NameMapper nameMapper = new NameMapper();
        String name = "Tom";
        QueryResult nameByJobAndName = nameMapper.getActorByName(name, 1, 20, false);
        for (Name name1 : nameByJobAndName.getList(Name.class)) {
            log.info(name1.toString());
        }
    }

    @Test
    void getWriterByName() {
        NameMapper nameMapper = new NameMapper();
        String name = "Tom";
        QueryResult nameByJobAndName = nameMapper.getWriterByName(name, 1, 20, false);
        for (Name name1 : nameByJobAndName.getList(Name.class)) {
            log.info(name1.toString());
        }
    }

    @Test
    void getKnownForTitlesOfAPerson() {
        NameMapper nameMapper = new NameMapper();
        List<Title> nm0000001 = nameMapper.getKnownForTitlesOfAPerson("nm0000002");
        for (Title title : nm0000001) {
            log.info(title.toString());
        }
    }
}
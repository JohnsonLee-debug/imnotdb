package com.imnotdb.Mapper;

import com.imnotdb.Entity.Name;
import com.imnotdb.Entity.Title;
import com.imnotdb.Utils.MybatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class NameMapperTest {

    @Test
    void getNameByNconst() {

    }

    @Test
    void getNameByJobAndName() {
    }

    @Test
    void getDirectorByName() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            NameMapper mapper = sqlSession.getMapper(NameMapper.class);
//            List<Name> nameList = mapper("bergman");
//            for (Name name :  nameList) {
//                System.out.println(name);
//            }
//        }
    }

    @Test
    void getActorByName() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            NameMapper mapper = new NameMapper();
//            List<Name> nameList = mapper.getNameByNconst();
//            for (Name name : nameList) {
//                System.out.println(name);
//            }
//        }
    }

    @Test
    void getWriterByName() {
    }

    @Test
    void testGetNameByNconst() {
    }

    @Test
    void testGetNameByJobAndName() {
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
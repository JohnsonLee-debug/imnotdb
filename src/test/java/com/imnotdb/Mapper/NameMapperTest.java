package com.imnotdb.Mapper;

import com.imnotdb.Entity.Name;
import com.imnotdb.Utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
package com.imnotdb.Mapper;

import com.imnotdb.Entity.Name;
import com.imnotdb.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NameMapperTest {

    @Test
    void getNameByNconst() {

    }

    @Test
    void getNameByJobAndName() {
    }

    @Test
    void getDirectorByName() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
            NameMapper mapper = sqlSession.getMapper(NameMapper.class);
            List<Name> nameList = mapper.getDirectorByName("bergman");
            for (Name name :  nameList) {
                System.out.println(name);
            }
        }
    }

    @Test
    void getActorByName() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
            NameMapper mapper = sqlSession.getMapper(NameMapper.class);
            List<Name> nameList = mapper.getActorByName("kelly");
            for (Name name : nameList) {
                System.out.println(name);
            }
        }
    }

    @Test
    void getWriterByName() {
    }
}
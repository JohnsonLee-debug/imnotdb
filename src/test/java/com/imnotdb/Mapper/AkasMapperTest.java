package com.imnotdb.Mapper;

import com.imnotdb.Entity.Akas;
import com.imnotdb.Mapper.Impl.AkasMapperImpl;
import com.imnotdb.utils.MybatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.nutz.dao.QueryResult;

@Slf4j
public class AkasMapperTest {

    @Test
    public void getAkasByNameTest(){
        AkasMapper akasMapper = new AkasMapperImpl();
        String name = "want";
        QueryResult queryResult = akasMapper.getAkasByName(name, 1, 25);
        for (Akas akas : queryResult.getList(Akas.class)) {
            System.out.println(akas);
        }
        for(int i = 0; i < 10 && queryResult.getPager().hasNext();i++){
            queryResult = akasMapper.getAkasByName(name, queryResult.getPager().getPageNumber() + 1, queryResult.getPager().getPageSize());
            for (Akas akas : queryResult.getList(Akas.class)) {
                System.out.println(akas);
            }
        }
        System.(queryResult.getPager().getPageCount());
//            List<Akas> akasList = akasMapper.getAkasByName("Carmencita");
//            for (Akas akas : akasList) {
//                System.out.println(akas);
//            }
    }
    @Test
    public void getAkasByTconstTest(){
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            AkasMapper akasMapper = sqlSession.getMapper(AkasMapper.class);
//            List<Akas> akasList = akasMapper.getAkasByTconst("tt0000001");
//            for (Akas akas : akasList) {
//                System.out.println(akas);
//            }
//        }
    }
}

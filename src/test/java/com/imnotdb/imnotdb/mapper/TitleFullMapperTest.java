package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.TitleFull;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

@SpringBootTest
class TitleFullMapperTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TitleFullMapper titleFullMapper;
    @Test
    void testGetTitleByCnds(){
        HashMap<String, Object> cnds = new HashMap<>();
        cnds.put(SymbolTable.AKASTITLES, "你好");
        cnds.put(SymbolTable.RATING_GTE, 5.4);
        cnds.put(SymbolTable.RATING_LTE, 8.5);
        titleFullMapper.getTitleByCnds(cnds, 0, 0);
    }

    @Test
    void getTitleByCnds() {
        HashMap<String, Object> cnds = new HashMap<>();
        titleFullMapper.getTitleByCnds(cnds, 0, 0);
    }

    @Test
    void getTitleFullByTconst() {
        TitleFull titleFull = titleFullMapper.getTitleFullByTconst("tt0000001");
        System.out.println(titleFull);
    }

    @Test
    void testGetTitleByCnds1() {
    }

    @Test
    void syncDataWithMySQL() {
    }
}
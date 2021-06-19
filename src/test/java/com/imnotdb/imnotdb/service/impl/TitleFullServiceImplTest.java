package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.service.TitleFullService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class TitleFullServiceImplTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TitleFullService titleFullService;
    @Test
    void searchByCond() {
        HashMap<String, Object> cnds = new HashMap<>();
        cnds.put(SymbolTable.AKASTITLES, "你好");
        cnds.put(SymbolTable.RATING_GTE, 5.4);
        cnds.put(SymbolTable.RATING_LTE, 8.5);
        try {
            List<Title> searchByConds = titleFullService.searchByCond(cnds, 1, 10, 0);
            for (Title searchByCond : searchByConds) {
                System.out.println(searchByCond);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
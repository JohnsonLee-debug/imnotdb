package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.pojo.Name;
import com.imnotdb.imnotdb.service.NameService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.junit.jupiter.api.Test;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameServiceImplTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private NameService nameService;
    @Autowired
    private NutDao nutDao;
    @Test
    void getNameByNconst() {
    }

    @Test
    void getNameByNconstList() {
        System.out.println(nameService.getNameByNconstList("nm0000001,nm0000002,nm0000003,nm0000004,nm0000005,nm0000006,nm0000007,"));
        System.out.println(nameService.getNameByNconstList("nm"));
    }
}
package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.Akas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nutz.dao.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AkasMapperTest {

    @Autowired
    private AkasMapper akasMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void getAkasByTconst() {
        List<Akas> akasByTconst = akasMapper.getAkasByTconst("tt0000001");
        for (Akas akas : akasByTconst) {
            System.out.println(akas);
        }
    }

    @Test
    void getAkasByRegion() {
    }

    @Test
    void getAkasByRegionAndName() {
    }
}
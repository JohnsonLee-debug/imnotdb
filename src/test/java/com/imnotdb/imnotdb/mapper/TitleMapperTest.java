package com.imnotdb.imnotdb.mapper;

import com.imnotdb.imnotdb.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitleMapperTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TitleMapper titleMapper;

    @Test
    void insertTitle() {
        titleMapper.insertTitle(null);
    }

    @Test
    void getTitleByTconst() {
    }

    @Test
    void getAllTitle() {
    }

    @Test
    void fetchAllLinks() {
    }

    @Test
    void testInsertTitle() {
        Title title = new Title();
        title.setPrimaryTitle("测试123");
        LinkedList<Akas> akasLinkedList = new LinkedList<>();
        Akas akas = new Akas();
        akas.setTitle("测试123");
        akasLinkedList.add(akas);
        LinkedList<Principals> principalsLinkedList = new LinkedList<>();
        Principals principals = new Principals();
        principals.setNconst("nm0000002");
        principals.setCategory("actor");
        Rating rating = new Rating();
        rating.setAverageRating(5.9);
        rating.setNumVotes(1024);
        Crew crew = new Crew();
        crew.setDirectors("nm0000001");
        crew.setWriters("nm0000001");
        title.setAkas(akasLinkedList);
        title.setPrincipals(principalsLinkedList);
        title.setRating(rating);
        title.setCrew(crew);
        titleMapper.insertTitle(title);
    }

    @Test
    void updateTitle() {
        Title title = titleMapper.getTitleByTconst("603713813610631168");
        titleMapper.fetchAllLinks(title);
        List<Akas> akas = title.getAkas();
        Akas aka = akas.get(0);
        aka.setTitle("换个名字");
        titleMapper.updateTitle(title);

    }

    @Test
    void deleteTitle() {
        titleMapper.deleteTitle("603711554709491712");
    }
}
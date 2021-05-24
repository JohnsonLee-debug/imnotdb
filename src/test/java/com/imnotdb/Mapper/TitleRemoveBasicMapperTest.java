package com.imnotdb.Mapper;

import org.junit.jupiter.api.Test;

class TitleRemoveBasicMapperTest {

    @Test
    void getMovieByAdult() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<Title> listOfMovie = mapper.getTitleByAdult(true, pageNumber, pageSize, setTotal);
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<Title> listOfMovie = mapper.getTitleByAdult(false, pageNumber, pageSize, setTotal);
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
    }

    @Test
    void getMovieByGenres() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<Title> listOfMovie = mapper.getTitleByGenres("short");
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
    }

    @Test
    void testGetMovieByGenres() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<String> genres = new LinkedList<>();
//            genres.add("comedy");
//            genres.add("documentary");
//            List<Title> listOfMovie = mapper.getTitleByGenres(genres);
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
    }

    @Test
    void getTitleBasicByType() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<Title> listOfMovie = mapper.getTitleByType("short");
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
    }

    @Test
    void getBasicByTconst() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            System.out.println(mapper.getTitleByTconst("tt0000001"));
//        }
    }

    @Test
    void getTitleBasicByLen() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<Title> listOfMovie = mapper.getTitleByLen(0,5);
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
    }

    @Test
    void getTitleBasicByRating() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()){
//            TitleMapper mapper = sqlSession.getMapper(TitleMapper.class);
//            List<Title> listOfMovie = mapper.getTitleByRating(3.2, 4.5);
//            for (Title title : listOfMovie) {
//                System.out.println(title);
//            }
//        }
    }
}
package com.imnotdb.utils;

import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

public class NutDaoUtils {
    private static final SimpleDataSource dataSource = new SimpleDataSource();
    static {
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/imnotdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
    }
    public static NutDao getNutDao() {
        return new NutDao(dataSource);
    }
    public static void tearDown() throws Exception {
        dataSource.close();
    }
}

package com.imnotdb.Utils;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;

public class NutDaoUtils {
    private static final SimpleDataSource dataSource = new SimpleDataSource();
    private static final NutDao dao;
    static {
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/imnotdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        dao = new NutDao(dataSource);
    }
    public static NutDao getNutDao() {
        return dao;
    }
    public static void tearDown() {
        dataSource.close();
    }
    // count代表是否是计数查询
    public static <T> Sql matchAgainst(Class<T> classOfT, String columns, String words, boolean count){
        String sqlstr = "";
        SqlExpressionGroup sqlExpressionGroup = new SqlExpressionGroup();
        Sql sql = null;
        var entity = dao.getEntity(classOfT);
        if (count){
            sql = Sqls.create("select count(*) from $table_name where Match($columns) AGAINST(@words)");
            sql.setCallback(Sqls.callback.integer());
        }else {
            sql = Sqls.create("select * from $table_name where Match($columns) AGAINST(@words)");
            sql.setCallback(Sqls.callback.entities());
            sql.setEntity(entity);
        }
        sql.vars().set("table_name", entity.getTableName());
        sql.vars().set("columns", columns);
        sql.params().set("words", words);
        return sql;
    }
    public static <T> Sql matchAgainstBoolean(Class<T> classOfT, String columns, String words, boolean count){
        String sqlstr = "";
        if (count){
            sqlstr = "select count(*) from $table_name where Match($columns) AGAINST(@words) in boolean mode";
        }else {
            sqlstr = "select * from $table_name where Match($columns) AGAINST(@words) in boolean mode";
        }
        Sql sql = Sqls.create(sqlstr);
        var entity = dao.getEntity(classOfT);
        sql.vars().set("table_name", entity.getTableName());
        sql.vars().set("columns", columns);
        sql.params().set("words", words);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(entity);
        return sql;
    }
}

package com.imnotdb.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.DaoUp;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class NutDaoUtils {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;
    private static final NutDao dao;
    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/imnotdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true");
        config.setUsername("root");
        config.setPassword("password");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", true);
        config.addDataSourceProperty("useLocalSessionState", true);
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        config.addDataSourceProperty("cacheServerConfiguration", true);
        config.addDataSourceProperty("elideSetAutoCommits", true);
        config.addDataSourceProperty("maintainTimeStats", false);
        ds = new HikariDataSource(config);
        dao = new NutDao(ds);
    }
    public static NutDao getNutDao() {
        return dao;
    }
    public static void tearDown() {
        ds.close();
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

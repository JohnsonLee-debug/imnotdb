package com.imnotdb.imnotdb.utils;

import org.nutz.dao.impl.NutDao;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Match {
//    final static private Dao dao = NutDaoUtils.getNutDao();
    @Autowired
    @Qualifier(SymbolTable.NUTDAO)
    private static NutDao dao;
    public static <T> SqlExpression match(Class<T> classOfT, String columns, String words){
        var entity = dao.getEntity(classOfT);
        return new Static(String.format("MATCH(%s) AGAINST('%s')",
                entity.getColumn(columns).getColumnName(),
                words));
    }
    public static <T> SqlExpression matchBoolean(Class<T> classOfT, String columns, String words){
        var entity = dao.getEntity(classOfT);
        return new Static(String.format("MATCH(%s) AGAINST('%s' IN BOOLEAN MODE)",
                entity.getColumn(columns).getColumnName(),
                words));
    }
}

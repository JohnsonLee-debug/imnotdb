package com.imnotdb.Mapper;

import com.imnotdb.Entity.Ratings;
import com.imnotdb.Entity.Title;
import com.imnotdb.Utils.NutDaoUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;

import java.util.List;

public class RatingsMapper {
    private static final NutDao dao = NutDaoUtils.getNutDao();

    public QueryResult getRatingByAverage(Double from, Double to, int pageNumber, int pageSize,boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Criteria cri = Cnd.cri();
        if (from != null) {
            cri.where().and("averageRating",">=", from);
        }
        if (to != null) {
            cri.where().and("averageRating","<=", to);
        }
        List<Ratings> ratingsList = dao.query(Ratings.class, cri, pager);
        return new QueryResult(ratingsList, pager);
    }

    public QueryResult getRatingByNumVotes(Long from, Long to, int pageNumber, int pageSize,boolean setTotal) {
        Pager pager = dao.createPager(pageNumber, pageSize);
        Criteria cri = Cnd.cri();
        if (from != null) {
            cri.where().and("averageRating",">=", from);
        }
        if (to != null) {
            cri.where().and("averageRating","<=", to);
        }
        List<Ratings> ratingsList = dao.query(Ratings.class, cri, pager);
        if (setTotal) {
            pager.setRecordCount(dao.count(Ratings.class, cri));
        }
        return new QueryResult(ratingsList, pager);
    }
}

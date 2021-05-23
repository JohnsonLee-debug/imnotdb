package com.imnotdb.Mapper;

import com.imnotdb.Entity.Akas;
import org.nutz.dao.QueryResult;

import java.util.List;

public interface AkasMapper {
    QueryResult getAkasByName(String name, int pageNumber, int pageSize);
    List<Akas> getAkasByTconst(String tconst);
}

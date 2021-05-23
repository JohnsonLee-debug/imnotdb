package com.imnotdb.Mapper;

import com.imnotdb.Entity.Title;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public interface TitleMapper {
    Title getTitleByTconst(@Param("tconst") String tconst);

    List<Title> getTitleByAdult(@Param("adult") Boolean adult);

    List<Title> getTitleByGenres(@Param("genres") String genres);
    List<Title> getTitleByType(@Param("type") String type);
    List<Title> getTitleByLen(@Param("from")Integer from, @Param("to") Integer to);
    List<Title> getTitleByRating(@Param("from")Double from, @Param("to") Double to);
    default List<Title> getTitleByGenres(List<String> genres){
        return getTitleByGenres(Strings.join(genres,' '));
    }
}

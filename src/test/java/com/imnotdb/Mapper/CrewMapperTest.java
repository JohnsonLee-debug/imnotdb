package com.imnotdb.Mapper;

import com.imnotdb.Entity.Akas;
import com.imnotdb.Entity.Crew;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.dao.QueryResult;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CrewMapperTest {

    @Test
    void getCrewByDirectorId() {
        CrewMapper crewMapper = new CrewMapper();
        String nconst = "nm0666972";
        QueryResult queryResult = crewMapper.getCrewByDirectorId(nconst, 0, 0, true);
        for (Crew crew : queryResult.getList(Crew.class)) {
            log.info(crew.toString());
        }
    }

    @Test
    void getCrewByWriterId() {
        CrewMapper crewMapper = new CrewMapper();
        String nconst = "nm0666972";
        QueryResult queryResult = crewMapper.getCrewByWriterId(nconst, 1, 0, true);
        for (Crew crew : queryResult.getList(Crew.class)) {
            log.info(crew.toString());
        }
    }

    @Test
    void getCrewByTconst() {
        CrewMapper crewMapper = new CrewMapper();
        String tconst = "tt9418812";
        QueryResult queryResult = crewMapper.getCrewByTconst(tconst, 0, 0, true);
        for (Crew crew : queryResult.getList(Crew.class)) {
            log.info(crew.toString());
        }
    }
}
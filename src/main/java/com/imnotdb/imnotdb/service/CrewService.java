package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Crew;
import org.springframework.stereotype.Service;

@Service
public interface CrewService {
    public Crew getCrewByTconst(String tconst);
}

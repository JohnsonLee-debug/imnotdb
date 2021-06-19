package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Akas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AkasService {
    public List<Akas> getAkasByTconst(String tconst);
}

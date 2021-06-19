package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Principals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrincipalsService {
    public List<Principals> getPrincipalsByTconst(String tconst);
}

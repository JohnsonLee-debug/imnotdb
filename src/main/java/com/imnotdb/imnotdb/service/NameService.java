package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Name;
import org.springframework.stereotype.Service;

@Service
public interface NameService {
    public Name getNameByNconst(String tconst);
    public String getNameByNconstList(String nconstLst);
    public void deleteDeleteNameByNconst(String nconst);
    public void insertName(Name name);

    void updateName(Name name);
}

package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Name;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NameService {
    public Name getNameByNconst(String tconst);

    List<Name> getNameByName(String name);

    public String getNameByNconstList(String nconstLst);

    public void deleteDeleteNameByNconst(String nconst);

    public void insertName(Name name);

    public String getKnownForTitleOf(String nconst);

    void updateName(Name name);
}

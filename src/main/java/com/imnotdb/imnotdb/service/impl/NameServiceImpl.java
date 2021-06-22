package com.imnotdb.imnotdb.service.impl;

import com.imnotdb.imnotdb.mapper.NameMapper;
import com.imnotdb.imnotdb.pojo.Name;
import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.service.NameService;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NameServiceImpl implements NameService {
    @Autowired
    private NutDao nutDao;
    @Autowired
    private NameMapper nameMapper;

    @Override
    public Name getNameByNconst(String nconst) {
        return nutDao.fetch(Name.class, nconst);
    }

    @Override
    public List<Name> getNameByName(String name) {
        return nutDao.query(Name.class, Cnd.where("primaryName", "=", name));
    }

    @Override
    public String getNameByNconstList(String nconstLst) {
        if (nconstLst == null || nconstLst.equals("")) {
            return "";
        }
        String[] split = nconstLst.split(",");
        return Arrays.stream(split)
                .map(String::trim)
                .map(x -> nutDao.fetch(Name.class, x))
                .filter(Objects::nonNull)
                .map(Name::getPrimaryName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public void deleteDeleteNameByNconst(String nconst) {
        nameMapper.deleteNameByNconst(nconst);
    }

    @Override
    public void insertName(Name name) {
        nameMapper.insertName(name);
    }

    @Override
    public String getKnownForTitleOf(String nconst) {
        List<Title> knownForTitlesOfAPerson = nameMapper.getKnownForTitlesOfAPerson(nconst);
        String knownFor = knownForTitlesOfAPerson.stream().map(x -> x.getPrimaryTitle()).collect(Collectors.joining("<br/>"));
        return knownFor;
    }

    @Override
    public void updateName(Name name) {
        nameMapper.updateName(name);
    }
}

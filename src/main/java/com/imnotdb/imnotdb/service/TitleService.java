package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Title;
import org.springframework.stereotype.Service;

@Service
public interface TitleService {
    Title getTitleByTconst(String tconst, Integer fetchAll);
    void insertTitle(Title title);
    void updateTitle(Title title);

    void deleteTitle(String tconst);
}

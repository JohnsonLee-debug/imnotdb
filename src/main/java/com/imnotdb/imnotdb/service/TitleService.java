package com.imnotdb.imnotdb.service;

import com.imnotdb.imnotdb.pojo.Title;
import org.springframework.stereotype.Service;

@Service
public interface TitleService {
    Title getTitleByTconst(String tconst);
    void updateTitle(Title title);
}

package com.imnotdb.imnotdb.controller;

import com.imnotdb.imnotdb.commons.PageJson;
import com.imnotdb.imnotdb.pojo.Principals;
import com.imnotdb.imnotdb.service.PrincipalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/principals")
public class PrincipalsController {
    @Autowired
    private PrincipalsService principalsService;
    public PageJson<Principals> getPrincipalsByTconst(String tconst){
        PageJson<Principals> pageJson = new PageJson<>();
        List<Principals> principalsList = principalsService.getPrincipalsByTconst(tconst);
        pageJson.setData(principalsList);
        return pageJson;
    }
}

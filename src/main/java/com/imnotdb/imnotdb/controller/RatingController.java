package com.imnotdb.imnotdb.controller;

import com.imnotdb.imnotdb.commons.PageJson;
import com.imnotdb.imnotdb.pojo.Rating;
import com.imnotdb.imnotdb.service.RatingService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;

@Controller
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @ResponseBody
    @RequestMapping(value = "/getByTconst",method = RequestMethod.GET)
    public PageJson<Rating> getRatingByTconst(@RequestParam(SymbolTable.TCONST) String tconst){
        Rating rating = ratingService.getRatingsByTconst(tconst);
        PageJson<Rating> pageJson = new PageJson<>();
        LinkedList<Rating> ratings = new LinkedList<>();
        ratings.add(rating);
        pageJson.setData(ratings);
        return pageJson;
    }
}

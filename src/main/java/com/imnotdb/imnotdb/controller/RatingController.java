package com.imnotdb.imnotdb.controller;

import com.imnotdb.imnotdb.commons.PageJson;
import com.imnotdb.imnotdb.pojo.Rating;
import com.imnotdb.imnotdb.service.RatingService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
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

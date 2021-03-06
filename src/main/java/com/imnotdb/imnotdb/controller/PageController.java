package com.imnotdb.imnotdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "search";
    }

    @RequestMapping(value = "/movieDetails")
    public String details(@RequestParam String tconst, @RequestParam Integer fetchAll) {
        return "movieDetails";
    }

    @RequestMapping(value = "/backstage")
    public String backstage() {
        return "select";
    }
    @RequestMapping(value = "/insertMovie")
    public String insert(){
        return "insertMovie";
    }
    @RequestMapping(value = "updateMovie")
    public String update(@RequestParam String tconst){
        return  "updateMovie";
    }
}

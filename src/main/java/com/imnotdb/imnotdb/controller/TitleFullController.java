package com.imnotdb.imnotdb.controller;

import com.alibaba.fastjson.JSONObject;
import com.imnotdb.imnotdb.commons.PageJson;
import com.imnotdb.imnotdb.pojo.Title;
import com.imnotdb.imnotdb.service.TitleFullService;
import com.imnotdb.imnotdb.utils.SymbolTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/titleFull")
@Slf4j
public class TitleFullController {
    @Autowired
    private TitleFullService titleFullService;
    @PostMapping("/searchWithJson")
    @ResponseBody
    public PageJson<Title> getTitleByCond(@RequestBody JSONObject param){
        Integer pageNo = 0;
        if(param.containsKey(SymbolTable.PAGENO)){
            pageNo = (Integer) param.get(SymbolTable.PAGENO);
        }else {
            PageJson<Title> pageJson = new PageJson<Title>();
            pageJson.setCode(-1);
            pageJson.setMsg("Without page number");
            return pageJson;
        }
        Integer size = 0;
        if(param.containsKey(SymbolTable.SIZE)){
            size = (Integer) param.get(SymbolTable.SIZE);
        }else {
            PageJson<Title> pageJson = new PageJson<Title>();
            pageJson.setCode(-1);
            pageJson.setMsg("Without page size.");
            return pageJson;
        }
        Integer fetchAll = 0;
        if(param.containsKey(SymbolTable.FETCHALL)){
            fetchAll = (Integer) param.get(SymbolTable.FETCHALL);
        }
        try {
            List<Title> titles = titleFullService.searchByCond(param, pageNo, size, fetchAll);
            PageJson<Title> pageJson = new PageJson<>();
            pageJson.setData(titles);
            pageJson.setCount(titles.size());
            return pageJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageJson<>();
    }
    @GetMapping("/search")
    @ResponseBody
    public PageJson<Title> getTitleByCond(HttpServletRequest request){
        HashMap<String, Object> param = new HashMap<>();
        constructParam(request, param);
        Integer pageNo = 0;
        if(param.containsKey(SymbolTable.PAGENO)){
            pageNo = (Integer) param.get(SymbolTable.PAGENO);
        }else {
            PageJson<Title> pageJson = new PageJson<Title>();
            pageJson.setCode(-1);
            pageJson.setMsg("Without page number");
            return pageJson;
        }
        Integer size = 0;
        if(param.containsKey(SymbolTable.SIZE)){
            size = (Integer) param.get(SymbolTable.SIZE);
        }else {
            PageJson<Title> pageJson = new PageJson<Title>();
            pageJson.setCode(-1);
            pageJson.setMsg("Without page size.");
            return pageJson;
        }
        Integer fetchAll = 0;
        if(param.containsKey(SymbolTable.FETCHALL)){
            fetchAll = (Integer) param.get(SymbolTable.FETCHALL);
        }
        try {
            List<Title> titles = titleFullService.searchByCond(param, pageNo, size, fetchAll);
            PageJson<Title> pageJson = new PageJson<Title>();
            pageJson.setData(titles);
            pageJson.setCount(titles.size());
            return pageJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageJson<>();
    }
    private void constructParam(HttpServletRequest request, HashMap<String, Object> param) {
        Map<String, String[]> conditions = request.getParameterMap();
        if (conditions.containsKey(SymbolTable.SIZE)){
            String[] strings = conditions.get(SymbolTable.SIZE);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.SIZE, Integer.valueOf(s));
            }
        }
        if (conditions.containsKey(SymbolTable.FETCHALL)){
            String[] strings = conditions.get(SymbolTable.FETCHALL);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.FETCHALL, Integer.valueOf(s));
            }
        }if (conditions.containsKey(SymbolTable.PAGENO)){
            String[] strings = conditions.get(SymbolTable.PAGENO);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.PAGENO, Integer.valueOf(s));
            }
        }
        if (conditions.containsKey(SymbolTable.AKASTITLES)){
            String[] strings = conditions.get(SymbolTable.AKASTITLES);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.AKASTITLES, s);
            }
        }
        if (conditions.containsKey(SymbolTable.DIRECTOR)){
            String[] strings = conditions.get(SymbolTable.DIRECTOR);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.DIRECTOR, s);
            }
        }
        if (conditions.containsKey(SymbolTable.WRITER)){
            String[] strings = conditions.get(SymbolTable.WRITER);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.WRITER, s);
            }
        }
        if (conditions.containsKey(SymbolTable.ACTOR)){
            String[] strings = conditions.get(SymbolTable.ACTOR);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.ACTOR, s);
            }
        }
        if (conditions.containsKey(SymbolTable.TITLETYPE)){
            String[] strings = conditions.get(SymbolTable.TITLETYPE);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.TITLETYPE, s);
            }
        }
        if (conditions.containsKey(SymbolTable.GENRES)){
            String[] strings = conditions.get(SymbolTable.GENRES);
            String s = strings[0];
            if(s != null){
                param.put(SymbolTable.GENRES, s);
            }
        }
        if (conditions.containsKey(SymbolTable.ISADULT)){
            String[] strings = conditions.get(SymbolTable.ISADULT);
            String s = strings[0];
            if(s != null){
                int i = Integer.parseInt(s);
                if(i != 0 && i != 1){
                    param.put(SymbolTable.ISADULT, 0);
                }else {
                    param.put(SymbolTable.ISADULT, i);
                }
            }
        }
        if (conditions.containsKey(SymbolTable.YEAR_GTE)){
            String[] strings = conditions.get(SymbolTable.YEAR_GTE);
            String s = strings[0];
            if(s != null){
                int i = Integer.parseInt(s);
                param.put(SymbolTable.YEAR_GTE, i);
            }
        }
        if (conditions.containsKey(SymbolTable.YEAR_LTE)){
            String[] strings = conditions.get(SymbolTable.YEAR_LTE);
            String s = strings[0];
            if(s != null){
                Integer i = Integer.parseInt(s);
                param.put(SymbolTable.YEAR_LTE, i);
            }
        }
        if (conditions.containsKey(SymbolTable.RATING_GTE)){
            String[] strings = conditions.get(SymbolTable.RATING_GTE);
            String s = strings[0];
            if(s != null){
                Double d = Double.valueOf(s);
                param.put(SymbolTable.RATING_GTE, d);
            }
        }
        if (conditions.containsKey(SymbolTable.RATING_LTE)){
            String[] strings = conditions.get(SymbolTable.RATING_LTE);
            String s = strings[0];
            if(s != null){
                Double d = Double.valueOf(s);
                param.put(SymbolTable.RATING_LTE, d);
            }
        }
        if (conditions.containsKey(SymbolTable.LENGTH_GTE)){
            String[] strings = conditions.get(SymbolTable.LENGTH_GTE);
            String s = strings[0];
            if(s != null){
                Integer i = Integer.parseInt(s);
                param.put(SymbolTable.LENGTH_GTE, i);
            }
        }
        if (conditions.containsKey(SymbolTable.LENGTH_LTE)){
            String[] strings = conditions.get(SymbolTable.LENGTH_LTE);
            String s = strings[0];
            if(s != null){
                Integer i = Integer.parseInt(s);
                param.put(SymbolTable.LENGTH_LTE, i);
            }
        }
    }
}

package com.imnotdb.imnotdb.controller;

import com.imnotdb.imnotdb.commons.JsonResult;
import com.imnotdb.imnotdb.commons.PageJson;
import com.imnotdb.imnotdb.pojo.Name;
import com.imnotdb.imnotdb.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/name")
public class NameController {
    @Autowired
    private NameService nameService;
    @ResponseBody
    @GetMapping("/getByNconstList")
    public JsonResult getByNconstList(@RequestParam String nconstList){
        String names = nameService.getNameByNconstList(nconstList);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setOk();
        jsonResult.put("msg", names);
        return jsonResult;
    }
    @ResponseBody
    @GetMapping("/getNameByNconst")
    public PageJson getNameByNconst(@RequestParam String nconst){
        PageJson<Name> pageJson = new PageJson<>();
        Name name = nameService.getNameByNconst(nconst);
        ArrayList<Name> names = new ArrayList<>();
        names.add(name);
        pageJson.setData(names);
        pageJson.setCode(0);
        pageJson.setMsg("");
        return pageJson;
    }
    @ResponseBody
    @GetMapping("/delete")
    public JsonResult deleteByNconst(@RequestParam String nconst){
        nameService.deleteDeleteNameByNconst(nconst);
        return new JsonResult().setOk();
    }
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody Name name){
        nameService.updateName(name);
        return new JsonResult().setOk();
    }
    @ResponseBody
    @PostMapping("/insert")
    public JsonResult insertName(@RequestBody Name name){
        nameService.insertName(name);
        return new JsonResult().setOk();
    }
}

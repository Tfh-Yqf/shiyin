package com.example.shiyin.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class jdbc_shichang {

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @GetMapping("get_shichang")
    public List<Map<String,Object>> get_tingyin(@RequestParam("type") String type){
        List<Map<String,Object>> res = new ArrayList<>();
        String sql = "select * from shichang where type='"+type+"'";
        res = jdbcTemplate.queryForList(sql);
        return res;
    }
}

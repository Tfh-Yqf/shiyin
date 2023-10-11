package com.example.shiyin.tingyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class tingyin {

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @GetMapping("/get_tingyin")
    public Map<String,Object> get_tingyin(@RequestParam("user") String user){
        Map<String,Object> res = new HashMap<>();
        String sql = "select * from tingyin";
        List<Map<String,Object>> data = jdbcTemplate.queryForList(sql);
        sql = "select first_accuracy,second_accuracy,third_accuracy,first_cnt,second_cnt,third_cnt from account where user='"+user+"'";
        res.put("accuracy",jdbcTemplate.queryForList(sql).get(0));
        res.put("data",data);
        return res;
    }
}

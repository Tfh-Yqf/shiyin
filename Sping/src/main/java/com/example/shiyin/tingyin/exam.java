package com.example.shiyin.tingyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class exam {
    listen lis = new listen();
    listen2 lis2 = new listen2();
    listen3 lis3 = new listen3();

    jdbc jd = new jdbc();

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @PostMapping ("/exam_upload")
    public Map<String,String> exam_upload(@RequestBody Exam_data exam_data){
        Map<String,String> res = new HashMap<>();
        exam_data.print();
        jdbcTemplate.execute(exam_data.charu());
        if(true)
            res.put("result","succ");
        else
            res.put("result","error");
        return res;
    }

    @GetMapping("/get_tingyin_exam")
    public List<Map<String,Object>> get_tingyin_exam(@RequestParam("user") String user){
        String sql = "select * from tingyin_exam  where user='"+user+"'";
        List<Map<String,Object>> res = jdbcTemplate.queryForList(sql);
        return res;
    }

    @GetMapping("/exam")
    public Map<String,Object> exam(@RequestParam("difficulty") int diff){
        Map<String,Object> res = new HashMap<>();
        List<Integer> array = new ArrayList<>();
        List<Integer> array_one = new ArrayList<>();
        List<Integer> array_two = new ArrayList<>();
        List<Integer> array_three = new ArrayList<>();
//        random make array
        if(diff==0){
            array_one =  lis.onedy();
            array_two = lis.oneyc();
            array_three = lis.onehx();
        }else if(diff==1){
            array_one =  lis2.onedy();
            array_two = lis2.oneyc();
            array_three = lis2.onehx();
        }else{
            array_one =  lis3.thrdy();
            array_two = lis3.oneyc();
            array_three = lis3.onehx();
        }
//        merge array (5,10,15)
        for(int i=0;i<30;i++){
            if(i<5){
                array.add(array_one.get(i - 0));
            }else if(i<15){
                array.add(array_two.get(i-5));
            }else{
                array.add(array_three.get(i-15));
            }
        }
//        make res
        res.put("array",array) ;
        res.put("data", jd.pinjie_exam(array).substring(30) ) ;
        res.put("diff",diff) ;
        return res;
    }
}

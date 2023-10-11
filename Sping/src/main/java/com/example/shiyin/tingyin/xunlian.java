package com.example.shiyin.tingyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class xunlian {

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @PostMapping("/tingyin_xunlian")
    public Map<String,String> tingyin_xunlian(@RequestBody Xunlian_data xunlian_data){
        Map<String,String> res = new HashMap<>();
        System.out.println( xunlian_data.print() );
        jdbcTemplate.execute(xunlian_data.charu());
        String sql = "";
        double accuracy=-1.0;
        double cnt=0;

        if(xunlian_data.getAccuracy()==-1.0){
            accuracy = xunlian_data.getRight_per();
            cnt = xunlian_data.getAll_cnt();
        }else{

            accuracy = (xunlian_data.getAccuracy()*xunlian_data.getCnt() + xunlian_data.getRight_cnt())/(xunlian_data.getAll_cnt()+xunlian_data.getCnt());
            cnt = xunlian_data.getCnt()+xunlian_data.getAll_cnt();
        }
        System.out.println(accuracy+" "+cnt);

        if(xunlian_data.getDiff().equals("简单")){
            sql = "update account set first_accuracy = " + accuracy +" , first_cnt = "+cnt+" where user = '"+xunlian_data.getUser()+"'";
        }else if(xunlian_data.getDiff().equals("中等")){
            sql = "update account set second_accuracy = "+ accuracy +" , second_cnt = "+cnt+" where user = '"+xunlian_data.getUser()+"'";
        }else{
            sql = "update account set third_accuracy = "+ accuracy +" , third_cnt = "+cnt+" where user = '"+xunlian_data.getUser()+"'";
        }
        jdbcTemplate.execute(sql);

        if(true)
            res.put("result","succ");
        else
            res.put("result","error");
        return res;
    }

    @GetMapping("/get_tingyin_xunlian")
    public List<Map<String,Object>> get_tingyin_xunlian(@RequestParam("user") String user){
        String sql = "select * from tingyin_xunlian  where user='"+user+"'";
        List<Map<String,Object>> res = jdbcTemplate.queryForList(sql);
        return res;
    }

}

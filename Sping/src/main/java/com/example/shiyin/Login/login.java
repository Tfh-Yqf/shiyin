package com.example.shiyin.Login;

import com.example.shiyin.lanjie.MakeToken;
import com.example.shiyin.sms.SendSms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class login{

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    public String login_success(int id,String user,String password,HttpSession session){
        //添加登录日志
        String sql="insert into login_log(id,login_type,result)  values(" +id+",'密码登录','登录成功')";
        jdbcTemplate.execute(sql);
        //将登录失败次数清0
        sql="update account set login_fail_count=0 where user='"+user+"'";
        jdbcTemplate.execute(sql);
        //token生成
        MakeToken makeToken = new MakeToken();
        String token=makeToken.token(user,password);
//            System.out.println(token);
        //更新token
        sql="update account set token ='"+token+"' where user='" +user +"'"  ;
        jdbcTemplate.execute(sql);
        //以seesion存储token
        session.setAttribute("token",token);
        return token;
    }

    public void login_fail(String user){
        String sql="update account set login_fail_count=login_fail_count+1 where user='"+user+"'";
        jdbcTemplate.execute(sql);
        sql="update account set user_status='锁定' where user='"+user+"' and login_fail_count>=3";
        jdbcTemplate.execute(sql);
        return;
    }



    @GetMapping("/hello")
    public String hello(){
        return "hello ShiYin";
    }

    @GetMapping("/loginByAdmin")
    public String loginByAdmin(@RequestParam("user") String user,
                               @RequestParam("password") String password,
                               HttpSession session){
        String sql = "select * from account where user='"+user+"' and password = '"+password+"' and quanxain = 'admin'";
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql);
        if(maps.size()==0){
            return "登录失败";
        }else{
            String token = login_success(Integer.parseInt(maps.get(0).get("id").toString()),user,password,session);
            return token;
        }
    }

    @RequestMapping("/loginbyPassword")
    public Map<String,String> login(@RequestParam("user") String user,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        String sql="select id,login_fail_count,user_status,password,gender from account where user= '" + user +"'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//        System.out.println(jdbcTemplate.queryForObject(sql,Integer.class));
        Map<String,String> res = new HashMap<String,String>();
        Map<String, Object> temp = null;
        if(maps.size()!=0){
            temp = maps.get(0);
            if(temp.get("user_status").toString().equals("锁定")){
                res.put("result","账户锁定");
            }else if(!temp.get("password").toString().equals(password)){
                login_fail(user);
                res.put("result","登录失败");
            }
            else if(temp.get("password").toString().equals(password)){
                //数据密码正确
                String token = login_success(Integer.parseInt(temp.get("id").toString()),user,password,session);
                res.put("result","登录成功");
                res.put("token",token);
                res.put("gender",temp.get("gender").toString());
            }
        }else{
            res.put("result","登录失败");
        }
        return res;
    }

    @RequestMapping("/loginbySms")
    public Map<String,String> login_sms(@RequestParam("user") String user,
                                        @RequestParam("code") String code,
                                        HttpSession session){
        String sql="select account.id,account.login_fail_count,account.user_status,account.gender from vercode,account where vercode.user= '"+user+"' and vercode.code='"+code+"' and vercode.user=account.user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        Map<String,String> res = new HashMap<String,String>();
        Map<String, Object> temp=null;
        if(maps.size()!=0){
            temp = maps.get(0);
        }
        if(maps.size()!=0&&temp.get("user_status").toString().equals("锁定")){
            res.put("result","账户锁定");
            //delete code
            sql="update vercode set code='' where user='"+user+"'";
            jdbcTemplate.execute(sql);
        }else if(maps.size()==0){
            login_fail(user);
            res.put("result","登录失败");
        }
        else if(maps.size()!=0){
            //数据密码正确
            String token = login_success(Integer.parseInt(temp.get("id").toString()),user,code,session);
            res.put("result","登录成功");
            res.put("token",token);
            res.put("gender",temp.get("gender").toString());
            //delete code
            sql="update vercode set code='' where user='"+user+"'";
            jdbcTemplate.execute(sql);
        }

        return res;

    }

    @RequestMapping("/loginbyUniver")
    public Map<String,Object> login_uni(@RequestParam("user") String user,
                                        @RequestParam("secc") String secc,
                                        HttpSession session){
        Map<String,Object> res = new HashMap<String,Object>();
        if(secc.equals("kfsdgjbvhdskdgfleskhg123fkaf234")==false){
            res.put("code",2);
            res.put("msg","强行突破失败！");
            return res;
        }
        String sql="select id from account where  user='"+user+"'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        Map<String, Object> temp=null;
        if(maps.size()!=0){
            temp = maps.get(0);
            //数据密码正确
            String token = login_success(Integer.parseInt(temp.get("id").toString()),user,"kfsdgjbvhdskdgfleskhg123fkaf234",session);
            res.put("msg","登录成功");
            res.put("token",token);
            res.put("code",0);
            res.put("user",user);
            res.put("gender",temp.get("gender").toString());
        }else{
            res.put("msg","未注册");
            res.put("code",1);
        }
        return res;
    }

    @GetMapping("/loginByauto")
    public Boolean loginByauto(@RequestParam("token") String token,
                               HttpSession session) throws ParseException {
        String sql = "select * from account where token ='"+token+"'";
        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);
        if(maps==null){
            return false;
        }else if(maps.size()==0){
            return false;
        }else{
            session.setAttribute("token",token);
            return true;
        }
    }

    @GetMapping("/register")
    public Map<String,String> register(@RequestParam("user")String user,
                                       @RequestParam("password")String password,
                                       @RequestParam("code") String code,
                                       @RequestParam("gender") String gender){
        Map<String,String> res= new HashMap<String,String>();
        String sql="";

        //user是否已存在
        sql="select id from account where user ='"+user+"'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        if(maps.size()==0){
            //未注册
            //看验证码对不对
            sql="select code from vercode where user='"+user+"'";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
            System.out.println(map.get(0));
            if(map.size()==0){
                res.put("result","验证码发送失败");
            }else if(map.get(0).get("code").equals(code)){
                //正确
                //生成token
                java.util.Date day=new Date();
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String token=user+"<"+sdf.format(day);

                //add数据
                sql="insert into account(user,password,Token,gender) values('"+user+"','"+password+"','"+token+"','"+gender+"')";
                jdbcTemplate.execute(sql);
                //删除code
                sql="update vercode set code='' where user='"+user+"'";
                jdbcTemplate.execute(sql);
                //结果
                res.put("result","注册成功");
                res.put("token",token);
            }else{
                res.put("result","验证码错误");
            }
        }else{
            res.put("result","账户已注册");
        }
        return res;
    }


    @GetMapping("/sendsms")
    public void sendsms(@RequestParam("user") String user){
//        em.contextLoads();
        SendSms sendSms = new SendSms();
        String verifyCode = String.valueOf((int)(Math.random()*900000 + 100000));
        sendSms.send(user,verifyCode,"1417015");
        String sql="select * from vercode where user='"+user+"'";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        if(map.size()==0){
            sql = "insert into vercode(user,code) values('"+user+"','"+verifyCode+"')";
            jdbcTemplate.execute(sql);
        }else{
            sql="update vercode set code ='"+verifyCode+"'where user='"+user+"'";
            jdbcTemplate.execute(sql);
        }
    }

    @GetMapping("/forget")
    public Map<String,String> forgetpassword(@RequestParam("user")String user,@RequestParam("password")String password,@RequestParam("code") String code){
        //首先判断账户是否存在
        String sql="select id from account where user ='"+user+"'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        Map<String,String> res = new HashMap<String,String>();
        if(maps.size()!=0){
            //存在账户
            sql="select code from vercode where user='"+user+"'";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
            System.out.println(map.get(0));
            if(map.size()==0){
                res.put("result","验证码发送失败");
            }else if(map.get(0).get("code").equals(code)) {
                sql = "update account set password='" + password + "'where user='" + user + "'";
                jdbcTemplate.execute(sql);
                res.put("result", "修改成功");
                sql ="update account set user_status='在线',``.account.login_fail_count=0 where user='"+user+"'";
                jdbcTemplate.execute(sql);
                //删除code
                sql = "update vercode set code='' where user='" + user + "'";
                jdbcTemplate.execute(sql);
            }else{
                res.put("result","验证码错误");
            }
        }else{
            res.put("result","账户未注册");
        }
        return res;
    }
}


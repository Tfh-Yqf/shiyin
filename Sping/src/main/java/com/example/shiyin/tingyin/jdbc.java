package com.example.shiyin.tingyin;

import com.example.shiyin.music.mp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.example.shiyin.music.mp3.nowTime;

@RestController
public class jdbc {
    //调用源音乐的路径
//    private String path = new File(this.getClass().getResource("/").getPath()).toString().replace("\\target\\classes","") + "\\src\\main\\java\\com\\example\\shiyin\\music\\srx\\";
    private int count = 0;
    private String path = "/music/";
//    private String path = ""
    private static String temp_path = "/www/wwwroot/www.120.24.238.88/data/User/admin/home/temp/";
    //存放结果的path
    private static String res_path = "/www/wwwroot/www.120.24.238.88/data/User/admin/home/music/";
    private static String txt_path = "/root/ffmpeg-git-20220722-amd64-static/txt/";
    private static String quku_path_two = "/www/wwwroot/www.120.24.238.88/data/User/admin/home/quku_two/";

    private static String quku_path_three = "/www/wwwroot/www.120.24.238.88/data/User/admin/home/quku_three/data/";
    listen lis = new listen();
    listen2 lis2 = new listen2();
    listen3 lis3 = new listen3();
    mp3 Mp3 = new mp3();
    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @GetMapping("/listen")
    public Map<String,Object> random_listen(@RequestParam("type") String type,
                                            @RequestParam("difficulty") int diff) throws IOException, InterruptedException {
        Map<String,Object> res = new HashMap<>();
        List<Integer> array = new ArrayList<>();
        if(type.equals("单音")){
            if(diff==0){
                array =  lis.onedy();
            }else if(diff==1){
                array =  lis2.onedy();
            }else{
                array =  lis3.thrdy();
            }
            res.put("array",array) ;
            res.put("data",pinjie_one(array).substring(30)) ;
            res.put("diff",diff) ;
        }
        else if (type.equals("音程")) {
            if(diff==0){
                array = lis.oneyc();
            }else if(diff==1){
                array = lis2.oneyc();
            }else{
                array = lis3.oneyc();
            }
            res.put("array",array) ;
            res.put("diff",diff) ;
            res.put("data",pinjie_two(array).substring(30)) ;
        } else if (type.equals("和弦")) {
            if(diff==0){
                array = lis.onehx();
            }else if(diff==1){
                array = lis2.onehx();
            }else{
                array = lis3.onehx();
            }
            res.put("array",array) ;
            res.put("diff",diff) ;
            res.put("data",pinjie_three(array).substring(30)) ;
        }


        return res;
    }

    @GetMapping("/hebing")
    public String hebing(int fir,int sec) throws InterruptedException {

        String getdatatime = nowTime();
        String url1 = path + fir +".mp3";
        String url2 = path + sec +".mp3";
        String path_out = temp_path +"合并" + getdatatime + ".mp3";
        Mp3.mergeAudiosSoundtrack(url1,url2,path_out);
        Thread.currentThread().sleep(500);
        return path_out;
    }

    public String pinjie_one(List<Integer> a) throws IOException {
        //获取当前时间
        String getdatatime = nowTime();
        String path_out = res_path + getdatatime+count + ".mp3";
        List<String> temp = new LinkedList<>();
        for(int i=0;i<a.size();i++){
            temp.add(path+"15.mp3");
            temp.add(path+a.get(i).toString()+".mp3");
        }
        String txt = wirtre_txt(temp);
        mp3.mergeAudios_pinjie(txt,path_out);
        return path_out;
    }

    public String pinjie_two(List<Integer> a) throws IOException, InterruptedException {
        String getdatatime = nowTime();
        String path_out = res_path  + getdatatime +count+ ".mp3";
        List<String> temp = new LinkedList<>();
        for(int i=0;i<a.size();i+=2){
            temp.add(path+"15.mp3");
            String path_temp = quku_path_two + (char)('A'+a.get(i))+(char)('A'+a.get(i+1))+".mp3";
            temp.add(path_temp);
        }
        String txt = wirtre_txt(temp);
        mp3.mergeAudios_pinjie(txt,path_out);
        return path_out;
    }

    public String pinjie_three(List<Integer> a){
        String getdatatime = nowTime();
        String path_out = res_path + getdatatime +count+ ".mp3";
        List<String> temp = new LinkedList<>();
        for(int i=0;i<a.size();i+=3){
            temp.add(path+"15.mp3");
            String path_temp = quku_path_three + (char)('A'+a.get(i))+(char)('A'+a.get(i+1))+(char)('A'+a.get(i+2))+".mp3";
            temp.add(path_temp);
        }
        String txt = wirtre_txt(temp);
        mp3.mergeAudios_pinjie(txt,path_out);
        return path_out;
    }

    public String pinjie_exam(List<Integer> a){
        String getdatatime = nowTime();
        String path_out = res_path + getdatatime +count+ ".mp3";
        List<String> temp = new LinkedList<>();

        for(int i=0;i<5;i++){
            temp.add(path+"15.mp3");
            temp.add(path+a.get(i).toString()+".mp3");
        }

        for(int i=5;i<15;i+=2){
            temp.add(path+"15.mp3");
            String path_temp = quku_path_two + (char)('A'+a.get(i))+(char)('A'+a.get(i+1))+".mp3";
            temp.add(path_temp);
        }

        for(int i=15;i<30;i+=3){
            temp.add(path+"15.mp3");
            String path_temp = quku_path_three + (char)('A'+a.get(i))+(char)('A'+a.get(i+1))+(char)('A'+a.get(i+2))+".mp3";
            temp.add(path_temp);
        }

        String txt = wirtre_txt(temp);
        mp3.mergeAudios_pinjie(txt,path_out);
        return path_out;
    }

    public String wirtre_txt(List<String> data)
    {
        String filePath = txt_path + count+".txt";
        count++;
        FileWriter fw = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                file.createNewFile();
            }
            fw = new FileWriter(filePath);
            BufferedWriter bw=new BufferedWriter(fw);
            for(int i=0;i<data.size();i++){
                bw.write("file '"+data.get(i).toString()+"'\n" );
            }
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return filePath;
    }


//    @GetMapping("/quku")
//    public void shengchengquku(){
//        for(int i=1;i<=25;i++){
//            for(int j=1;j<=25;j++){
//                for(int k=1;k<=25;k++){
//                    System.out.println("I:"+i+"  J:"+j +" k:"+k);
//                    String url1 = "D:\\data\\two\\" + (char)('A'+i)+(char)('A'+j) +".mp3";
//                    String url2 = "C:\\Users\\Lenovo\\Desktop\\springboot\\ShiYin\\src\\main\\resources\\static\\srx\\" + k +".mp3";
//                    String path_out = "D:\\data\\" +(char)('A'+i)+(char)('A'+j)+(char)('A'+k)+".mp3";
//                    Mp3.mergeAudiosSoundtrack(url1,url2,path_out);
//                }
//
//            }
//        }
//        return;
//    }
}

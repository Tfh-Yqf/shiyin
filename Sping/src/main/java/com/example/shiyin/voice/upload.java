package com.example.shiyin.voice;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import com.example.shiyin.voice.MP3ToWav;

@RestController
public class upload {
    private static String voice_path = "/www/wwwroot/www.120.24.238.88/data/User/admin/home/voice";
    @PostMapping("/uploadFile")
    public Map<String,Object> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req){
        String path = MultipartFileToFile.saveMultipartFile(file, voice_path).toString();
        String respath = path.replace(".mp3",".wav");
        MP3ToWav.start(path,path);
        Map<String,Object> res = new HashMap<>();
        System.out.println(respath.substring(30));
        res.put("code",0);
        res.put("msg","succ");
        res.put("Mp3Path",path.substring(30));
        res.put("path",respath.substring(30));
        return res;
    }

}
package com.example.shiyin.music;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.*;

/**
 * 利用ffmpeg进行音频视频操作，需先下载安装ffmpeg
 */
public class mp3 {
    /**
     * 下载的ffmpeg解压后的bin目录路径，可配置到环境变量通过配置文件读取
     */
    private static String ffplay = "D:\\ffmpeg\\ffmpeg-5.1-full_build\\bin\\ffplay.exe";
//    private static String ffmpeg = "D:\\ffmpeg\\ffmpeg-5.1-full_build\\bin\\ffmpeg.exe";
//    private static String ffprobe = "D:\\ffmpeg\\ffmpeg-5.1-full_build\\bin\\ffprobe.exe";

    private static String ffmpeg = "/root/ffmpeg-git-20220722-amd64-static/ffmpeg";
    private static String ffprobe = "/root/ffmpeg-git-20220722-amd64-static/ffprobe";
    /**
     * 提取的音频、合成的视频存放路径，不存在会自动创建
     */
    private static String saveMediaPath = "/root/ffmpeg-git-20220722-amd64-static/";

    /**
     * 保存音频、视频的临时文件夹，不存在会自动创建
     */
    private static String tempMediaPath = "/root/ffmpeg-git-20220722-amd64-static/temp/";
    /**
     * 保存图片截图的文件夹，不存在会自动创建
     */
    private static String picturMediaPath = "/root/ffmpeg-git-20220722-amd64-static/";


    static {
        //如果没有文件夹，则创建
        File saveMediaFile = new File(saveMediaPath);
        if (!saveMediaFile.exists() && !saveMediaFile.isDirectory()) {
            saveMediaFile.mkdirs();
        }
        File tempMediaFile = new File(tempMediaPath);
        if (!tempMediaFile.exists() && !tempMediaFile.isDirectory()) {
            tempMediaFile.mkdirs();
        }
        File picturMediaFile = new File(picturMediaPath);
        if (!picturMediaFile.exists() && !picturMediaFile.isDirectory()) {
            picturMediaFile.mkdirs();
        }
    }

    /**
     * 播放音频和视频
     *
     * @param resourcesPath 文件的路径
     */
    public static void playVideoAudio(String resourcesPath) {
        List<String> command = new ArrayList<>();
        command.add(ffplay);
        command.add("-window_title");
        String fileName = resourcesPath.substring(resourcesPath.lastIndexOf("\\") + 1);
        command.add(fileName);
        command.add(resourcesPath);
        //播放完后自动退出
        //command.add("-autoexit");
        commandStart(command);
    }

    /**
     * 播放音频和视频并指定循环次数
     *
     * @param resourcesPath 文件的路径
     * @param loop          循环播放次数
     */
    public static void playVideoAudio(String resourcesPath, int loop) {
        List<String> command = new ArrayList<>();
        command.add(ffplay);
        command.add("-window_title");
        String fileName = resourcesPath.substring(resourcesPath.lastIndexOf("\\") + 1);
        command.add(fileName);
        command.add(resourcesPath);
        command.add("-loop");
        command.add(String.valueOf(loop));
        //播放完后自动退出
        //command.add("-autoexit");
        commandStart(command);
    }

    /**
     * 播放音频和视频并指定宽、高、循环次数
     *
     * @param resourcesPath 文件的路径
     * @param weight        宽度
     * @param height        高度
     * @param loop          循环播放次数
     */
    public static void playVideoAudio(String resourcesPath, int weight, int height, int loop) {
        List<String> command = new ArrayList<>();
        command.add(ffplay);
        command.add("-window_title");
        String fileName = resourcesPath.substring(resourcesPath.lastIndexOf("\\") + 1);
        command.add(fileName);
        command.add(resourcesPath);
        command.add("-x");
        command.add(String.valueOf(weight));
        command.add("-y");
        command.add(String.valueOf(height));
        command.add("-loop");
        command.add(String.valueOf(loop));
        //播放完后自动退出
        //command.add("-autoexit");
        commandStart(command);
    }

    /**
     * 调用命令行执行
     *
     * @param command 命令行参数
     */
    public static void commandStart(List<String> command) {
        command.forEach(v -> System.out.print(v + " "));
        System.out.println();
        System.out.println();
        ProcessBuilder builder = new ProcessBuilder(command);
        //正常信息和错误信息合并输出
        builder.redirectErrorStream(true);

        //开始执行命令
        Process process = null;
        try {
            process =  builder.start();
            //如果你想获取到执行完后的信息，那么下面的代码也是需要的
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 多音频拼接合并为一个音频。
     *
     *
     */
    public static void mergeAudios_pinjie(String txt,String path) {
        //时间作为合并后的音频名
        List<String> command = new ArrayList<>();
        command.add(ffmpeg);
        command.add("-f");
        command.add("concat");
        command.add("-safe");
        command.add("0");
        command.add("-i");
        command.add(txt);
        command.add("-c");
        command.add("copy");
        command.add(path);

        commandStart(command);

    }

    /**
     * 两个音频混缩合并为一个音频。
     * 音量参考：@link:https://blog.csdn.net/sinat_14826983/article/details/82975561
     *
     * @param audioResourcesPath1 音频1文件路径
     * @param audioResourcesPath1 音频2文件路径的
     */
    public static void mergeAudios(String audioResourcesPath1, String audioResourcesPath2) {
        //时间作为混缩后的音频名
        String getdatatime = nowTime();
        List<String> command = new ArrayList<>();
        command.add(ffmpeg);
        command.add("-i");
        command.add(audioResourcesPath1);
        command.add("-i");
        command.add(audioResourcesPath2);
        command.add("-filter_complex");
        command.add("amix=inputs=2:duration=longest");
        command.add(saveMediaPath + "音频混缩" + getdatatime + ".mp3");
        commandStart(command);
    }


    /**
     * 两个音频混缩合并为一个音频的不同声道（即一只耳机播放一个音频）。
     * 声道参考：@link:https://www.itranslater.com/qa/details/2583879740000044032
     *
     * @param audioResourcesPath1 音频1文件路径
     * @param audioResourcesPath1 音频2文件路径的
     */
    public static void mergeAudiosSoundtrack(String audioResourcesPath1, String audioResourcesPath2,String path) {
        //时间作为混缩后的音频名
        List<String> command = new ArrayList<>();
        command.add(ffmpeg);
        command.add("-i");
        command.add(audioResourcesPath1);
        command.add("-i");
        command.add(audioResourcesPath2);
        command.add("-filter_complex");
//        command.add("\"amerge=inputs=2,pan=stereo|c0<c0+c1|c1<c2+c3\"");
        command.add(" amerge=inputs=2");
        command.add(path);
        commandStart(command);
    }

    public static void mergeAudiosSoundtrack3(String audioResourcesPath1, String audioResourcesPath2,String audioResourcesPath3,String path) {
        //时间作为混缩后的音频名
        List<String> command = new ArrayList<>();
        command.add(ffmpeg);
        command.add("-i");
        command.add(audioResourcesPath1);
        command.add("-i");
        command.add(audioResourcesPath2);
        command.add("-i");
        command.add(audioResourcesPath3);
        command.add("-filter_complex");
//        command.add("\"amerge=inputs=2,pan=stereo|c0<c0+c1|c1<c2+c3\"");
        command.add("amerge=inputs=2");
        command.add(path);
        commandStart(command);
    }

    /**
     * 获取当前时间，用于作为文件名
     */
    public static String nowTime() {
        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDate nowdata = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        return nowdata.atTime(nowTime).format(f3);
    }
}



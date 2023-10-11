package com.example.shiyin.voice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3ToWav {
    /**
     * mp3的字节数组生成wav文件
     * @param sourceBytes
     * @param targetPath
     */
    public static boolean byteToWav(byte[] sourceBytes, String targetPath) {
        if (sourceBytes == null || sourceBytes.length == 0) {
            System.out.println("Illegal Argument passed to this method");
            return false;
        }

        try (
                final ByteArrayInputStream bais = new ByteArrayInputStream(sourceBytes);
                final AudioInputStream sourceAIS = AudioSystem.getAudioInputStream(bais)
        ) {
            AudioFormat sourceFormat = sourceAIS.getFormat();
            // 设置MP3的语音格式,并设置16bit
            AudioFormat mp3tFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(),
                    16, sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false);
            try (
                    final AudioInputStream mp3AIS = AudioSystem.getAudioInputStream(mp3tFormat, sourceAIS)){
                AudioSystem.write(mp3AIS, AudioFileFormat.Type.WAVE, new File(targetPath));
            }

            return true;
        } catch (IOException e) {
            System.out.println("文件转换异常：" + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (UnsupportedAudioFileException e) {
            System.out.println("文件转换异常：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将文件转成字节流
     * @param filePath
     * @return
     */
    private static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void start(String filePath, String targetPath) {
//        String filePath = "C:/Users/Lenovo/Desktop/springboot/test.mp3";
//        String targetPath = "C:/Users/Lenovo/Desktop/springboot/test.wav";
        byteToWav(getBytes(filePath), targetPath);
        return;
    }

    public static void main(String[] args) {
        String filePath = "C:/Users/Lenovo/Desktop/springboot/test.mp3";
        String targetPath = "C:/Users/Lenovo/Desktop/springboot/test.wav";
        byteToWav(getBytes(filePath), targetPath);
        return;
    }


}

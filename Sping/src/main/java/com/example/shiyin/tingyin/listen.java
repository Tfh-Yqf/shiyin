package com.example.shiyin.tingyin;
import java.util.*;

public class listen {

//一级单音，每个数单独播放
    public List<Integer> onedy(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12 || yf == 14
                || yf == 16 || yf == 19 || yf == 21 || yf == 24 ||yf ==0)
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        while(list.size()<5) {
            int yf2 = random.nextInt(26);
            while(yf2 == 2 || yf2 == 4 || yf2 == 7 || yf2 == 9 || yf2 == 12
                    || yf2 == 14 || yf2 == 16 || yf2 == 19 || yf2 == 21 || yf2 == 24
                    || yf2 - yf > 5 || yf - yf2 > 5 ||yf2 == 0)
            {
                yf2 = random.nextInt(26);
            }
            int judge=0;
            for( int i:list){
                if (yf2 == i){
                    judge=1;
                    break;
                }
            }
            if(judge == 0){
                list.add(yf2);
                yf=yf2;
            }

        }
        return list;
    }

    //一级音程，每两个数同时播放
    public static List<Integer> oneyc(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        while(list.size()<10){
            int yc = random.nextInt(4);
            switch (yc){
                case 0:
                    List<Integer> yf = oneycds();
                    int judge=0;
                    for(int i = 0; i <= list.size() - 2; i=i+2){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int dyy = yf.get(0);
                        int dey = yf.get(1);
                        if (dy == dyy && de == dey) {
                            judge = 1;
                            break;
                        }
                    }
                    if(judge == 0){
                        list.addAll(yf);
                    }
                    break;

                case 1:
                    List<Integer> yf1 = oneycxs();
                    int judge1=0;
                    for(int i = 0; i <= list.size() - 2; i=i+2){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int dyy = yf1.get(0);
                        int dey = yf1.get(1);
                        if (dy == dyy && de == dey) {
                            judge1 = 1;
                            break;
                        }
                    }
                    if(judge1 == 0){
                        list.addAll(yf1);
                    }
                    break;
                case 2:
                    List<Integer> yf2 = oneyccs();
                    int judge2=0;
                    for(int i = 0; i <= list.size() - 2; i=i+2){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int dyy = yf2.get(0);
                        int dey = yf2.get(1);
                        if (dy == dyy && de == dey) {
                            judge2 = 1;
                            break;
                        }
                    }
                    if(judge2 == 0){
                        list.addAll(yf2);
                    }
                    break;
                case 3:
                    List<Integer> yf3 = oneyccw();
                    int judge3=0;
                    for(int i = 0; i <= list.size() - 2; i=i+2){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int dyy = yf3.get(0);
                        int dey = yf3.get(1);
                        if (dy == dyy && de == dey) {
                            judge3 = 1;
                            break;
                        }
                    }
                    if(judge3 == 0){
                        list.addAll(yf3);
                    }
                    break;
                case 4:
                    List<Integer> yf4 = oneyccb();
                    int judge4=0;
                    for(int i = 0; i <= list.size() - 2; i=i+2){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int dyy = yf4.get(0);
                        int dey = yf4.get(1);
                        if (dy == dyy && de == dey) {
                            judge4 = 1;
                            break;
                        }
                    }
                    if(judge4 == 0){
                        list.addAll(yf4);
                    }
                    break;
            }

        }

        return list;
    }



    //一级音程大三，每两个数同时播放
    public static List<Integer> oneycds(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 1 && yf != 6 && yf != 11 && yf != 13 && yf != 18)
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+4);

        return list;
    }

    //一级音程小三，每两个数同时播放
    public static List<Integer> oneycxs(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
            int yf = random.nextInt(26);
            while(yf != 3 && yf != 5 && yf != 8 && yf != 10 && yf != 15 && yf != 17 && yf != 20 && yf != 22)
            {
                yf = random.nextInt(26);
            }
        list.add(yf);
        list.add(yf+3);

        return list;
    }

    //一级音程纯四，每两个数同时播放
    public static List<Integer> oneyccs(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf == 11 || yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12
                || yf == 14 || yf == 16 || yf == 19 || yf == 21 || yf == 24 ||  yf == 22
                || yf == 23 || yf == 25 ||yf == 0)
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+5);

        return list;
    }

    //一级音程纯五，每两个数同时播放
    public static List<Integer> oneyccw(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf == 5 || yf == 17 || yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12
                || yf == 14 || yf == 16 || yf == 19 ||yf == 20 || yf == 21 || yf == 24 ||  yf == 22
                || yf == 23 || yf == 25 ||yf == 0)
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+7);

        return list;
    }

    //一级音程纯八，每两个数同时播放
    public static List<Integer> oneyccb(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 1 && yf != 3 && yf != 5 && yf != 6 && yf != 8 && yf != 10 && yf != 11 && yf != 13 )
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+12);
        return list;
    }

    //一级和弦，每三个数同时播放
    public List<Integer> onehx(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        while(list.size()<15){
            int yc = random.nextInt(2);
            switch (yc){
                case 0:
                    List<Integer> yf = onehxds();
                    int judge=0;
                    for(int i = 0; i <= list.size() - 3; i=i+3){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int ds = list.get(i+2);
                        int dyy = yf.get(0);
                        int dey = yf.get(1);
                        int dsy = yf.get(2);
                        if (dy == dyy && de == dey && ds == dsy) {
                            judge = 1;
                            break;
                        }
                    }
                    if(judge == 0){
                        list.addAll(yf);
                    }
                    break;
                case 1:
                    List<Integer> yf1 = onehxxs();
                    int judge1=0;
                    for(int i = 0; i <= list.size() - 3; i=i+3){
                        int dy = list.get(i);
                        int de = list.get(i+1);
                        int ds = list.get(i+2);
                        int dyy = yf1.get(0);
                        int dey = yf1.get(1);
                        int dsy = yf1.get(2);
                        if (dy == dyy && de == dey && ds == dsy) {
                            judge1 = 1;
                            break;
                        }
                    }
                    if(judge1 == 0){
                        list.addAll(yf1);
                    }
                    break;

            }

        }

        return list;
    }

    //一级和弦大三，每三个数同时播放
    public List<Integer> onehxds(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 1 && yf != 6 && yf != 11 && yf != 13 && yf != 18 )
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+4);
        list.add(yf+7);
        return list;
    }

    //一级和弦小三，每三个数同时播放
    public List<Integer> onehxxs(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 3 && yf != 8 && yf != 10 && yf != 15 )
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+3);
        list.add(yf+7);
        return list;
    }


}

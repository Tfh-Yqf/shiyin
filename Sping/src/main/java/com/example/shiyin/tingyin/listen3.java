package com.example.shiyin.tingyin;
import java.util.*;

public class listen3 {

//三级单音，每个数单独播放
    public List<Integer> thrdy(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        int yf2 ;
        list.add(yf);
        while(list.size()<5) {
            if(yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12
                || yf == 14 || yf == 16 || yf == 19 || yf == 21 || yf == 24)
            {
                while(true)
                {
                    yf2 = random.nextInt(26);
                    if((yf2 - yf < 4 && yf2 - yf > 0)||(yf - yf2 < 4 && yf - yf2 > 0))
                        break;
                }
                list.add(yf2);
                yf = yf2;
            }
            else {
                while(true){
                    yf2 = random.nextInt(26);
                    if((yf2 == 2 || yf2 == 4 || yf2 == 7 || yf2 == 9 || yf2 == 12
                            || yf2 == 14 || yf2 == 16 || yf2 == 19 || yf2 == 21 || yf2 == 24)
                            &&(yf2 - yf < 4 && yf2 - yf > 0)||(yf - yf2 < 4 && yf - yf2 > 0))
                        break;
                    else if((yf2 == 1 || yf2 == 3 || yf2 == 5 || yf2 == 6 || yf2 == 8 || yf2 == 10
                            || yf2 == 11 || yf2 == 13 || yf2 == 15 || yf2 == 17 || yf2 == 18 ||
                            yf2 == 20 || yf2 == 22 || yf2 == 23 || yf2 == 25 )&&
                            ((yf2 - yf < 10 && yf2 - yf > 0)||(yf - yf2 < 10 && yf - yf2 > 0)||
                                    (yf2 - yf < 11 && yf2 - yf > 0)||(yf - yf2 < 11 && yf - yf2 > 0)))
                        break;
                }
                list.add(yf2);
                yf = yf2;
            }
        }
        return list;
    }

    //三级音程
    public List<Integer> oneyc(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        while(list.size()<10){
            int yc = random.nextInt(8);
            switch (yc){
                case 0:
                    List<Integer> yf = oneycds();
                    list.addAll(yf);
                    break;
                case 1:
                    List<Integer> yf1 = oneycxs();
                    list.addAll(yf1);
                    break;
                case 2:
                    List<Integer> yf2 = oneyccs();
                    list.addAll(yf2);
                    break;
                case 3:
                    List<Integer> yf3 = oneyccw();
                    list.addAll(yf3);
                    break;
                case 4:
                    List<Integer> yf4 = oneyccb();
                    list.addAll(yf4);
                    break;
                case 5:
                    List<Integer> yf5=oneycde();
                    list.addAll(yf5);
                    break;
                case 6:
                    List<Integer> yf6=oneycdl();
                    list.addAll(yf6);
                    break;
                case 7:
                    List<Integer> yf7=oneycdq();
                    list.addAll(yf7);
                    break;
//                case 8:
//                    List<Integer> yf8=oneycxe();
//                    list.addAll(yf8);
//                    break;
//                case 9:
//                    List<Integer> yf9=oneyczs();
//                    list.addAll(yf9);
//                    break;
//                case 10:
//                    List<Integer> yf10=oneycxl();
//                    list.addAll(yf10);
//                    break;
//                case 11:
//                    List<Integer> yf11=oneycxq();
//                    list.addAll(yf11);
//                    break;
            }

        }

        return list;
    }
    //三级音程大三，每两个数同时播放
    public List<Integer> oneycds(){
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
    //三级音程纯四，每两个数同时播放
    public List<Integer> oneyccs(){
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

    //三级音程纯五，每两个数同时播放
    public List<Integer> oneyccw(){
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

    //三级音程纯八，每两个数同时播放
    public List<Integer> oneyccb(){
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
    //三级音程小三，每两个数同时播放
    public List<Integer> oneycxs(){
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
    //三级音程大二，每两个数同时播放
    public List<Integer> oneycde(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12 || yf == 14
                || yf == 16 || yf == 19 || yf == 21 || yf == 24 ||yf ==0)
        {
            yf = random.nextInt(26);
        }
        list.add(yf);
        list.add(yf+1);
        return list;
    }
    //三级音程大六，每两个数同时播放
    public List<Integer> oneycdl(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(true) {
            while (yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12 || yf == 14
                    || yf == 16 || yf == 19 || yf == 21 || yf == 24 || yf == 0) {
                yf = random.nextInt(26);
            }
            if(yf>=5||yf+5<=6||yf>=10||yf+5<=11||yf>=17||yf+5<=18||yf>=22||yf+5<=23) {
                list.add(yf);
                list.add(yf + 5);
                break;
            }
        }
        return list;
    }
    //三级音程大七，每两个数同时播放
    public List<Integer> oneycdq() {
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while (true) {
            while (yf == 2 || yf == 4 || yf == 7 || yf == 9 || yf == 12 || yf == 14
                    || yf == 16 || yf == 19 || yf == 21 || yf == 24 || yf == 0) {
                yf = random.nextInt(26);
            }
            if (yf >= 5 || yf + 6 <= 6 || yf >= 10 || yf + 6 <= 11 || yf >= 17 || yf + 6 <= 18 || yf >= 22 || yf + 6 <= 23) {
                list.add(yf);
                list.add(yf + 6);
                break;
            }
        }
        return list;
    }


    //三级和弦，每三个数同时播放
    public List<Integer> onehx(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        while(list.size()<15){
            int yc = random.nextInt(4);
            switch (yc){
                case 0:
                    List<Integer> yf = onehxdsdy();
                    list.addAll(yf);
                    break;
                case 1:
                    List<Integer> yf1 = onehxxsdy();
                    list.addAll(yf1);
                    break;
                case 2:
                    List<Integer> yf2 = onehxdsde();
                    list.addAll(yf2);
                    break;
                case 3:
                    List<Integer> yf3 = onehxxsde();
                    list.addAll(yf3);
                    break;
//                case 4:
//                    List<Integer> yf4 = onehxzsyw();
//                    list.addAll(yf4);
//                    break;
//                case 5:
//                    List<Integer> yf5 = onehxjsyw();
//                    list.addAll(yf5);
//                    break;
            }

        }

        return list;
    }

    //三级和弦大三第一转位，每三个数同时播放
    public List<Integer> onehxdsdy(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 1 && yf != 6 && yf != 11 && yf != 13 && yf != 18 )
        {
            yf = random.nextInt(26);
        }
        list.add(yf+4);
        list.add(yf+7);
        list.add(yf+12);
        return list;
    }

    //三级和弦小三第一转位，每三个数同时播放
    public List<Integer> onehxxsdy(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 3 && yf != 8 && yf != 10 && yf != 15 )
        {
            yf = random.nextInt(26);
        }
        list.add(yf+7);
        list.add(yf+4);
        list.add(yf+12);
        return list;
    }
    //三级和弦大三第二转位，每三个数同时播放
    public List<Integer> onehxdsde(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 1 && yf != 6 && yf != 11 && yf != 13 && yf != 18 )
        {
            yf = random.nextInt(26);
        }

        list.add(yf+7);
        list.add(yf+12);
        list.add(yf+16);
        return list;
    }

    //三级和弦小三第二转位，每三个数同时播放
    public List<Integer> onehxxsde(){
        Random random=new Random();
        List<Integer> list=new ArrayList<Integer>();
        int yf = random.nextInt(26);
        while(yf != 3 && yf != 8 && yf != 10 && yf != 15 )
        {
            yf = random.nextInt(26);
        }

        list.add(yf+4);
        list.add(yf+12);
        list.add(yf+15);
        return list;
    }
    //三级和弦增三和弦原位，每三个数同时播放

    //三级和弦减三和弦原位，每三个数同时播放
}

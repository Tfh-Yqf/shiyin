package com.example.shiyin.personalise;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.shiyin.tingyin.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.ceil;

@RestController
public class operation {

    jdbc voic_operation;

    @PostMapping("/get_endless_init")
    public Map<String,Object> get_endless_init(@RequestBody accuracy acc){
        System.out.println("start");
        Map<String,Object> res = new HashMap<>();
        acc.print();
        List<Integer> array = endless_init(acc);
        System.out.println(array);
        res.put("code",0);
        res.put("msg","succ");
        res.put("array",array);
        return res;
    }

    @PostMapping("/get_endless_load")
    public Map<String,Object> get_endless_load(@RequestBody accuracy acc){
        Map<String,Object> res = new HashMap<>();

        List<Integer> array = endless_load(acc);
        System.out.println(array);

        res.put("array",array);
        return res;
    }

    public List<Integer> endless_init(accuracy acc){
        double rate_l1, rate_l2, rate_l3, rate_l4,rate_l5;
        rate_l1=acc.getFirst_accuracy();
        rate_l2=acc.getSecond_accuracy();
        rate_l3=acc.getThird_accuracy();
        rate_l4=rate_l3;
        rate_l5=rate_l3;
        double[] temp=new double[5];
        temp[0]=rate_l1;
        temp[1]=rate_l2;
        temp[2]=rate_l3;
        temp[3]=rate_l4;
        temp[4]=rate_l5;
        boolean[] st=new boolean[5];
        double[] ans=new double[5];
        for(int i=0;i<5;i++) {
            st[i]=false;
            ans[i]=0.0;
        }

        if(rate_l1==-1&&rate_l2==-1&&rate_l3==-1&&rate_l4==-1&&rate_l5==-1){
            ans[0]=1.0;

        }
        else if(rate_l1==-1||rate_l2==-1||rate_l3==-1||rate_l4==-1||rate_l5==-1){
            double mmax=0.0;
            int tar=0;
            for(int i=0;i<5;i++){
                if(temp[i]>mmax){
                    mmax=temp[i];
                    tar=i;
                }
            }
            if(tar==0){
                ans[0]=1.0;
            }
            else if(tar==1){
                ans[0]=0.35;
                ans[1]=0.65;
            }
            else if(tar==2){
                ans[0]=0.16;
                ans[1]=0.32;
                ans[2]=0.52;
            }
            else if(tar==3){
                ans[0]=0.1;
                ans[1]=0.2;
                ans[2]=0.3;
                ans[3]=0.4;
            }
            else{
                ans[0]=.1;
                ans[1]=.15;
                ans[2]=.2;
                ans[3]=.25;
                ans[4]=.3;
            }
        }
        if(rate_l1<=0.7){
            ans[0]=0.6;
            ans[1]=0.4*rate_l2;
            ans[2]=0.4*rate_l3;
            ans[3]=0.4*rate_l4;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l2<=0.6){
            ans[0]=0.4*(1-rate_l1);
            ans[1]=0.6;
            ans[2]=0.4*rate_l3;
            ans[3]=0.4*rate_l4;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l3<=0.6){
            ans[0]=0.4*(1-rate_l1-rate_l2);
            ans[1]=0.4*(1-rate_l2);
            ans[2]=0.6;
            ans[3]=0.4*rate_l4;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l4<=0.6){
            ans[0]=0.4*(1-rate_l1-rate_l2-rate_l3);
            ans[1]=0.4*(1-rate_l2-rate_l3);
            ans[2]=0.4*(1-rate_l3);
            ans[3]=0.6;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l5<=0.5){
            ans[0]=0.4*(1-rate_l1-rate_l2-rate_l3-rate_l4);
            ans[1]=0.4*(1-rate_l2-rate_l3-rate_l4);
            ans[2]=0.4*(1-rate_l3-rate_l4);
            ans[3]=0.4*(1-rate_l4);
            ans[4]=0.6;
        }
        else{
            ans[4]=1.0;
        }
        for(int i=0;i<5;i++){
            if(ans[i]<=0.1)
                ans[i]=0;
        }
        int[] res=new int[5];
        for(int i=0;i<5;i++){
            res[i]= (int) ceil(ans[i]*100);
        }
        List<Integer> myans=new ArrayList<>();
        for(int i=0;i<5;i++){
            myans.add(res[i]);
        }
        return myans;
    }

    public List<Integer> endless_load(accuracy acc){
        double rate_l1, rate_l2, rate_l3, rate_l4,rate_l5;
        rate_l1=acc.getFirst_accuracy();
        rate_l2=acc.getSecond_accuracy();
        rate_l3=acc.getThird_accuracy();
        rate_l4=rate_l3;
        rate_l5=rate_l3;
        double[] temp=new double[5];
        temp[0]=rate_l1;
        temp[1]=rate_l2;
        temp[2]=rate_l3;
        temp[3]=rate_l4;
        temp[4]=rate_l5;
        boolean[] st=new boolean[5];
        double[] ans=new double[5];
        for(int i=0;i<5;i++) {
            st[i]=false;
            ans[i]=0.0;
        }

        if(rate_l1==-1&&rate_l2==-1&&rate_l3==-1&&rate_l4==-1&&rate_l5==-1){
            ans[0]=1.0;

        }
        else if(rate_l1==-1||rate_l2==-1||rate_l3==-1||rate_l4==-1||rate_l5==-1){
            double mmax=0.0;
            int tar=0;
            for(int i=0;i<5;i++){
                if(temp[i]>mmax){
                    mmax=temp[i];
                    tar=i;
                }
            }
            if(tar==0){
                ans[0]=1.0;
            }
            else if(tar==1){
                ans[0]=0.35;
                ans[1]=0.65;
            }
            else if(tar==2){
                ans[0]=0.16;
                ans[1]=0.32;
                ans[2]=0.52;
            }
            else if(tar==3){
                ans[0]=0.1;
                ans[1]=0.2;
                ans[2]=0.3;
                ans[3]=0.4;
            }
            else{
                ans[0]=.1;
                ans[1]=.15;
                ans[2]=.2;
                ans[3]=.25;
                ans[4]=.3;
            }
        }
        if(rate_l1<=0.7){
            ans[0]=0.6;
            ans[1]=0.4*rate_l2;
            ans[2]=0.4*rate_l3;
            ans[3]=0.4*rate_l4;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l2<=0.6){
            ans[0]=0.4*(1-rate_l1);
            ans[1]=0.6;
            ans[2]=0.4*rate_l3;
            ans[3]=0.4*rate_l4;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l3<=0.6){
            ans[0]=0.4*(1-rate_l1-rate_l2);
            ans[1]=0.4*(1-rate_l2);
            ans[2]=0.6;
            ans[3]=0.4*rate_l4;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l4<=0.6){
            ans[0]=0.4*(1-rate_l1-rate_l2-rate_l3);
            ans[1]=0.4*(1-rate_l2-rate_l3);
            ans[2]=0.4*(1-rate_l3);
            ans[3]=0.6;
            ans[4]=0.4*rate_l5;
        }
        else if(rate_l5<=0.5){
            ans[0]=0.4*(1-rate_l1-rate_l2-rate_l3-rate_l4);
            ans[1]=0.4*(1-rate_l2-rate_l3-rate_l4);
            ans[2]=0.4*(1-rate_l3-rate_l4);
            ans[3]=0.4*(1-rate_l4);
            ans[4]=0.6;
        }
        else{
            ans[4]=1.0;
        }
        for(int i=0;i<5;i++){
            if(ans[i]<=0.1)
                ans[i]=0;
        }
        int[] res=new int[5];
        for(int i=0;i<5;i++){
            res[i]= (int) ceil(ans[i]*100);
        }
        List<Integer> myans=new ArrayList<>();
        for(int i=0;i<5;i++){
            myans.add(res[i]);
        }
        return myans;
    }

}

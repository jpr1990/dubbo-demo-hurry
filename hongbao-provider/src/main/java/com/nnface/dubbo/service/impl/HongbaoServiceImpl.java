package com.nnface.dubbo.service.impl;

import com.nnface.dubbo.service.HongbaoService;
import com.nnface.dubbo.utils.HongBaoAlgorithm;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;

/**
 * hongbao service implement class
 * Created by Administrator on 2016/2/29.
 */
public class HongbaoServiceImpl implements HongbaoService {

    private static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();

    public HongbaoServiceImpl(){

    }

    public String  hi(String name){
        return "Hi,"+name;
    }

    public String hongbaoAlgorithm(long money, int number) {
        System.out.println("Hongbao Service Provider::started....");
//        DecimalFormat   fnum  =   new DecimalFormat("##0.00");
//        String  dd=fnum.format(money);
        long max = money/2+ money%2;
        int min = 0;
        long[] data = HongBaoAlgorithm.generate(money,number,max,min);
        String result = convertObjectToString(data);
        return result;
    }

    public static String convertObjectToString(Object obj){
        StringWriter w=new StringWriter();
        String jsonValue=null;
        try{
            JACKSON_MAPPER.writeValue(w,obj);
            jsonValue=w.toString();
            }catch(JsonParseException e){
            //异常时，记录日志，不中断程序
            e.printStackTrace();
            }catch(JsonMappingException e){
            //异常时，记录日志，不中断程序
            e.printStackTrace();
            }catch(IOException e){
            //异常时，记录日志，不中断程序
            e.printStackTrace();
            }
            return "hurry response:"+jsonValue;
        }

     public static void main(String[] args){
         long[] data = HongBaoAlgorithm.generate(288,20,100,3);
         String result = convertObjectToString(data);
         System.out.println(result);
     }
}

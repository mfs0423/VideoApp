/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Fansheng Meng
 */
public class Tool {
    private static SimpleDateFormat sdf = new SimpleDateFormat("M d y");
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static Calendar cal = Calendar.getInstance();
    public static String path ="";
    
    public static String getDate(Date date){
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        
        String formatDate = "";
        switch(month+1){
            case 1: formatDate+="January "; break;
            case 2: formatDate+="February "; break;
            case 3: formatDate+="March "; break;
            case 4: formatDate+="April "; break;
            case 5: formatDate+="May "; break;
            case 6: formatDate+="June "; break;
            case 7: formatDate+="July "; break;
            case 8: formatDate+="August "; break;
            case 9: formatDate+="September "; break;
            case 10: formatDate+="October "; break;
            case 11: formatDate+="November "; break;
            case 12: formatDate+="December ";     
        }
        formatDate += day+" "+year;
        return formatDate;
    }
    public static Date getDate(String date){
        
            String[] str = date.split(" ");
            String month = str[0];
            String formatDate = "";
            Date realDate = null;
            switch(month){
                case "January" : formatDate+=0; break;
                case "February": formatDate+=1; break;
                case "March": formatDate+=2; break;
                case "April": formatDate+=3; break;
                case "May": formatDate+=4; break;
                case "June": formatDate+=5; break;
                case "July": formatDate+=6; break;
                case "August": formatDate+=7; break;
                case "September": formatDate+=8; break;
                case "October": formatDate+=9; break;
                case "November": formatDate+=10; break;    
                case "December": formatDate+=11;
            }
            formatDate += " "+str[1]+" "+str[2];
        try {
            realDate = sdf.parse(formatDate);            
        } catch (ParseException ex) {
            writeLog(logSdf.format(new Date())+": Tool.java format date error!");
        }       
        return realDate;
    }
    public static BufferedReader getReader(String path){
        File file = new File(path);
        if(!file.exists()){
            writeLog(logSdf.format(new Date())+": Tool.java file does not exist!");
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (RuntimeException e) {
            writeLog(logSdf.format(new Date())+": Tool.java failed to create reader!");
        } catch (FileNotFoundException e){
            writeLog(logSdf.format(new Date())+": Tool.java Can not find file!");
        }
        return reader;
    }
        
    public static boolean write(String content){
        File file = new File(path);
        if(!file.exists()){
            writeLog(logSdf.format(new Date())+": Tool.java file does not exist!");
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
            writer.println(content);
        } catch (RuntimeException e) {          
            writeLog(logSdf.format(new Date())+": Tool.java failed to create reader!");
            return false;
        }catch (IOException e){
             writeLog(logSdf.format(new Date())+": Tool.java has error for writting file!");
             return false;
        }          
        closeWriter(writer);   
        return true;
    }
    public static boolean replace(String content){
        File file = new File(path);
        if(!file.exists()){
            writeLog(logSdf.format(new Date())+": Tool.java file does not exist!");
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            writer.print(content);
        } catch (RuntimeException e) {          
            writeLog(logSdf.format(new Date())+": Tool.java failed to create reader!");
            return false;
        }catch (IOException e){
             writeLog(logSdf.format(new Date())+": Tool.java has error for writting file!");
             return false;
        }          
        closeWriter(writer);   
        return true;
    }
    public static void closeReader(BufferedReader reader){
        if(reader!=null){
            try {
                reader.close();
            } catch (IOException e) {
                writeLog(logSdf.format(new Date())+": Tool.java has error with closing reader.");
            }
        }
        
    }
    public static void closeWriter(PrintWriter writer){        
        if(writer!=null){
            writer.close();
        }
    }
    
    public static void writeLog(String error){
        PrintWriter writer = null;
        try {
            String fileName = "src/log/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".txt";
            File log = new File(fileName);
            writer = new PrintWriter(new BufferedWriter(new FileWriter(log,true)));
            writer.println(error);
        } catch (IOException ex) {
            throw new RuntimeException("Log Error");
        }
        closeWriter(writer);
    }
    
    
    public static boolean copy(String path1, String path2) {
        long startTime = System.currentTimeMillis();
        File fromFile = new File(path1);
        File toFile = new File(path2);
        InputStream is = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
                is = new FileInputStream(fromFile);
                os = new FileOutputStream(toFile);
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(os);
                int content = bis.read();
                while (content != -1) {
                        bos.write(content);
                        bos.flush();
                        content = bis.read();
                }
        } catch (FileNotFoundException e) {
                writeLog(logSdf.format(new Date())+": Tool.java can not find file.");
                return false;
        } catch (IOException e) {
                writeLog(logSdf.format(new Date())+": Tool.java has IO exception");
                return false;
        } finally {
                try {
                        is.close();
                        os.close();
                } catch (IOException e) {
                        writeLog(logSdf.format(new Date())+": Tool.java has error with closing is/os.");
                        return false;
                }
        }
        return true;
    }
    
//    public static void main(String[] args) {
//        copy("src/demo.mp4","src/videos/demo.mp4");
//    }
}

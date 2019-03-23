package fr.romain120105.gomax.utils;

import java.io.*;

public class FileUtils {

    public static String readFile(File file){
        try {

            FileInputStream is = new FileInputStream(file);

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int length;

            while((length = is.read(buffer)) != -1){
                result.write(buffer, 0, length);
            }

            return result.toString("UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void saveFile(File file, String input){
        try{
        PrintWriter writer = new PrintWriter(new FileWriter(file));
            writer.println(input);
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void clearFile(File file){
        try{
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


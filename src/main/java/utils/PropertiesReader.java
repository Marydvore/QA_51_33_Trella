package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String gerProperty(String fileName, String key){
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream
                ("src/test/java/properties"+ File.separator+fileName)){
            properties.load(fileInputStream);
            return properties.getProperty(key);
        }catch (IOException e){
            System.out.println("created exception");
            e.printStackTrace();
            return null;
        }
    }
}

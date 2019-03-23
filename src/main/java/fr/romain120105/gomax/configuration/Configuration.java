package fr.romain120105.gomax.configuration;

import fr.romain120105.gomax.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private HashMap<String, String> values;
    private File file;

    public Configuration(HashMap<String, String> values, File file){
        this.values = values;
        this.file = file;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public String get(String key){

        return values.get(key);
    }

    public void set(String key, String value){
        if(this.values.containsKey(key)){
            this.values.remove(key);
        }

        this.values.put(key, value);
    }

    public void save(){
        FileUtils.clearFile(file);
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, String> entry: values.entrySet()){
            System.out.println("[" + entry.getKey() + "]=\"" +entry.getValue() + "\" \r");
            builder.append("[" + entry.getKey() + "]=\"" +entry.getValue() + "\" \r" );
        }

        FileUtils.saveFile(this.file, builder.toString());
    }
}

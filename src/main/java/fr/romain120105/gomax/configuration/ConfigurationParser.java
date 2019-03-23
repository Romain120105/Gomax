package fr.romain120105.gomax.configuration;

import fr.romain120105.gomax.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ConfigurationParser {

    private String input;
    private File file;

    public ConfigurationParser(String input){
        this.input = input;

    }

    public Configuration parse() throws ConfigurationSyntaxException{

        HashMap<String,String> keys = new HashMap<>();

        int lineNumber = 0;
        for(String line : input.split("\r")){
            if(line.replaceAll(" ", "").length() != 1){
                if(line.contains("=")){
                    String[] splitedLine = line.split("=");
                    String key = splitedLine[0].replaceAll("\n", "");

                    String value= splitedLine[1];

                    if(!key.startsWith("[") || !key.endsWith("]")){
                        throw new ConfigurationSyntaxException("Error at line " + lineNumber + (this.file != null ? " in " + file.getAbsolutePath() : "") + " : the key " + key + " must be surrounded with \"[\" and \"]\"");

                    }

                    if(!value.startsWith("\"") || !value.startsWith("\"")){
                        throw new ConfigurationSyntaxException("Error at line " + lineNumber + (this.file != null ? " in " + file.getAbsolutePath() : "") + " : the key " + key + " must be surrounded with '\"'");

                    }

                    key = key.substring(1);

                    key = key.substring(0, key.length() -1);

                    value = value.substring(1);


                    value = value.substring(0, value.length() -1);

                    keys.put(key, value);



                }else{
                    throw new ConfigurationSyntaxException("Error at line " + lineNumber + (this.file != null ? " in " + file.getAbsolutePath() : "") + " : line must contains \"=\" to work");
                }

                lineNumber += 1;
            }


        }

        return new Configuration(keys, this.file);


    }

    public ConfigurationParser setFile(File file) {
        this.file = file;
        return this;
    }

    public static ConfigurationParser createParser(File file){
        try {
            if (!file.exists()) {
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                file.createNewFile();
            }
            String input = FileUtils.readFile(file);

            return new ConfigurationParser(input).setFile(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

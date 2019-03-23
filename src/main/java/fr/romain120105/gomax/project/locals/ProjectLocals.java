package fr.romain120105.gomax.project.locals;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import fr.romain120105.gomax.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ProjectLocals {

    /**
     * This field represents the locals folder root of a project
     */
    private static final File LOCALS_FOLDER_ROOT = new File(".locals/");

    private String name;

    private File file;

    private Gson gson = new Gson();


    private HashMap<String, Object> datas = new HashMap<>();

    public ProjectLocals(String name) {
        this.name = name;
        this.file = new File(LOCALS_FOLDER_ROOT, name + ".goconf");
        this.createFile();
    }

    public void createFile(){
        try {
            if (!LOCALS_FOLDER_ROOT.exists()) {
                LOCALS_FOLDER_ROOT.mkdirs();
            }

            if(!this.file.exists()){
                this.file.createNewFile();
                FileUtils.saveFile(file, gson.toJson(datas));
            }

            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();

            datas = gson.fromJson(FileUtils.readFile(file), type);

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public Object getData(String name){
        return this.datas.get(name);
    }

    public void setData(String name, Object object){
        this.datas.put(name, object);
        save();
    }

    public void save(){

        FileUtils.saveFile(file, gson.toJson(this.datas));
    }

    public String getName() {
        return name;
    }
}

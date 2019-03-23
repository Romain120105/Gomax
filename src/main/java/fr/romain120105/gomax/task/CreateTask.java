package fr.romain120105.gomax.task;

import com.google.gson.GsonBuilder;
import fr.romain120105.gomax.ConsoleColors;
import fr.romain120105.gomax.Gomax;
import fr.romain120105.gomax.Printer;
import fr.romain120105.gomax.project.Project;
import fr.romain120105.gomax.project.ProjectType;
import fr.romain120105.gomax.utils.FileUtils;
import fr.romain120105.gomax.utils.JsonManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CreateTask extends Task{

    private Printer out = Printer.out();

    private Gomax gomax = Gomax.get();

    @Override
    public void execute(String... args) {

        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String defaultName = new File(s).getName();


            String projectName = askGlobalValue("name", defaultName);

            String projectVersion = askGlobalValue("version", "1.0.0");

            String projectAuthor = askGlobalValue("author", null);



            ProjectType projectLanguage = gomax.getProjectRegistry().getProjectType(askGlobalValue("type", "JAVA"));

            String license = askGlobalValue("license", "MIT");

            while (projectLanguage == null) {
                projectLanguage = gomax.getProjectRegistry().getProjectType(askGlobalValue("type", "JAVA"));
            }

            Project project = new Project(projectLanguage, projectName, projectAuthor,projectVersion, license, new ArrayList<>(), new HashMap<>());

            File projectFile = new File("project.json");
            if (!projectFile.exists()) {
                projectFile.createNewFile();
            }

            FileUtils.saveFile(projectFile, JsonManager.get().getGson().toJson(project));

            out.println();
            out.println(ConsoleColors.GREEN + "success " + ConsoleColors.WHITE+ "Saved project.json");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String askGlobalValue(String valueName, String valueDefault){
        if(valueDefault != null) {
            out.print("question " +  valueName + " (" + valueDefault + "): ");
        }else{
            out.print("question " + valueName + ": ");
        }

        String result = askValue(null);



        while(result == null){
            if(valueDefault != null) {
                return valueDefault;
            }else{
                out.print("question " + valueName + ": ");
            }
            result = askValue(null);
        }



        return result;
    }

    public String askValue(String defaultValue){
        Scanner scanner = new Scanner(System.in);

        String awnser = scanner.nextLine();

        if(awnser != null && !awnser.isEmpty()){
            return awnser;
        }


        return defaultValue;


    }

    @Override
    public String getName() {
        return "create";
    }
}

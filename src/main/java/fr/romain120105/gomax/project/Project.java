package fr.romain120105.gomax.project;

import fr.romain120105.gomax.project.locals.ProjectLocals;
import fr.romain120105.gomax.utils.Exclude;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {

    private ProjectType type;
    private String name;
    private String version;
    private String author;
    private String license;
    private HashMap<String, String> scripts;

    @Exclude
    private ArrayList<ProjectLocals> locals = new ArrayList<>();

    public Project(ProjectType type, String name, String author, String version, String license,ArrayList<ProjectLocals> locals, HashMap<String, String> scripts) {
        this.type = type;
        this.name = name;
        this.locals = locals;
        this.scripts = scripts;
        this.version = version;
        this.author = author;
        this.license = license;
    }

    public ProjectType getProjectType() {
        return type;
    }

    public String getProjectName() {
        return name;
    }

    public ArrayList<ProjectLocals> getProjectLocals() {
        return locals;
    }

    public void createProjectLocals(ProjectLocals projectLocals){
        if(this.locals == null){
            this.locals = new ArrayList<>();
        }
        this.locals.add(projectLocals);
    }

    public ProjectLocals getProjectLocals(String name){
        for(ProjectLocals projectLocals: locals){
            if(projectLocals.getName().equals(name)){
                return projectLocals;
            }
        }
        return null;
    }

    public HashMap<String, String> getScripts() {
        return scripts;
    }

    public String getAuthor() {
        return author;
    }

    public String getLicense() {
        return license;
    }
}

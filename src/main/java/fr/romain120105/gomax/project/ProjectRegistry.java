package fr.romain120105.gomax.project;

import fr.romain120105.gomax.Gomax;

import java.util.ArrayList;

public class ProjectRegistry {

    private ArrayList<ProjectType> projectTypes = new ArrayList<>();

    private Gomax gomax;

    public ProjectRegistry(Gomax gomax) {
        this.gomax = gomax;
    }

    public void registerProjectType(ProjectType type){
        this.projectTypes.add(type);
    }

    public ProjectType getProjectType(String id){
        if(projectTypes.isEmpty()){
            registerProjectType(new ProjectTypeBuilder().withName("Java").withID("JAVA").build());
        }
        for(ProjectType type: projectTypes){
            if(type.getId().equals(id)){
                return type;
            }
        }
        return null;
    }

    public ArrayList<ProjectType> getProjectTypes() {
        return projectTypes;
    }

    public Gomax getGomax() {
        return gomax;
    }
}

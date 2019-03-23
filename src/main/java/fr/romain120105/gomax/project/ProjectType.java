package fr.romain120105.gomax.project;

import fr.romain120105.gomax.utils.Exclude;

public class ProjectType {

    @Exclude
    private String name;

    private String id;


    public ProjectType(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}

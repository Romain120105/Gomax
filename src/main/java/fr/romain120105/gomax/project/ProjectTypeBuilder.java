package fr.romain120105.gomax.project;

import fr.romain120105.gomax.utils.Builder;

public class ProjectTypeBuilder extends Builder<ProjectType> {

    private String name;

    private String id;

    private ProjectHandler handler;

    public ProjectTypeBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ProjectTypeBuilder withID(String id){
        this.id= id.toUpperCase().replaceAll(" ", "_");
        return this;
    }

    public ProjectTypeBuilder withHandler(ProjectHandler handler){
        this.handler = handler;
        return this;
    }



    @Override
    public ProjectType build() {
        return new ProjectType(this.name, this.id);
    }
}

package fr.romain120105.gomax;

import com.google.gson.Gson;
import fr.romain120105.gomax.project.Project;
import fr.romain120105.gomax.project.ProjectRegistry;
import fr.romain120105.gomax.project.TaskRegistry;
import fr.romain120105.gomax.project.locals.ProjectLocals;
import fr.romain120105.gomax.task.Task;
import fr.romain120105.gomax.utils.FileUtils;
import fr.romain120105.gomax.utils.JsonManager;

import java.io.File;

public class Gomax {

    /**
     * Represents the singleton
     */
    private static Gomax singleton= new Gomax();

    private ProjectRegistry projectRegistry = new ProjectRegistry(this);

    private TaskRegistry taskRegistry;


    private Project project;

    public Gomax(){
       singleton = this;
       this.taskRegistry = new TaskRegistry(this);

       this.loadProject();
    }

     /**
     * Return the project registry, used for adding project type (Well, thank youCaptain Obvious!)
     */
    public ProjectRegistry getProjectRegistry() {
        return projectRegistry;
    }

    /**
     * Return the task registry, used for adding task (Well, thank youCaptain Obvious!)
     */
    public TaskRegistry getTaskRegistry() {
        return taskRegistry;
    }

    /**
     * Execute a task
     * @param task the task
     * @param args the args
     */
    public void executeTask(Task task, String... args){
        task.execute(args);
    }


    /**
     * Load the project.json and convert it into a Project object
     * Return null if the project.json is invalid
     */
    public Project loadProject(){
        try {
            File project = new File("project.json");
            if (!project.exists()) {
                return null;
            }


            Project p = JsonManager.get().getGson().fromJson(FileUtils.readFile(project), Project.class);

            this.project = p;

            return p;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return the current project instance. Return null if project is not created
     */
    public Project getProject() {
        return project;
    }

    /**
     * Return the singleton instance
     */
    public static Gomax get(){
        return singleton;
    }

}

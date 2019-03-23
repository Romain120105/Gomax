package fr.romain120105.gomax.project;

import fr.romain120105.gomax.Gomax;
import fr.romain120105.gomax.task.Task;
import fr.romain120105.gomax.task.Tasks;

import java.util.ArrayList;

public class TaskRegistry {

    private ArrayList<Task> tasks = new ArrayList<>();

    private Gomax gomax;

    public TaskRegistry(Gomax gomax) {
        this.gomax = gomax;

        Tasks.registerTasks(this);
    }

    public void registerTask(Task task){
        this.tasks.add(task);
    }

    public Task getTask(String name){
        for(Task task : tasks){
            if(task.getName().equals(name)){
                return task;
            }
        }

        return null;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Gomax getGomax() {
        return gomax;
    }
}

package fr.romain120105.gomax.task;

import fr.romain120105.gomax.project.TaskRegistry;

public class Tasks {

    public static void registerTasks(TaskRegistry registry){
        registry.registerTask(new CreateTask());
        registry.registerTask(new RunTask());
        registry.registerTask(new HelpTask(registry));
    }

}

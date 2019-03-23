package fr.romain120105.gomax;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.romain120105.gomax.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArgumentsParser {

    private static Printer out = Printer.out();
    private static Gomax gomax = Gomax.get();


    /**
     * Contains the flags added from the command line
     */
    private HashMap<String, Boolean> flags = new HashMap<>();

    /**
     * The args received from the main method
     */
    private String[] args;

    /**
     * The task arguments
     */
    private String[] taskArgs;

    /**
     * The task name who's started
     * Return null if the user don't precise a task
     */
    private String taskName;


    /**
     * @param args The args received from the main method
     */
    public ArgumentsParser(String[] args) {
        this.args = args;
    }

    /**
     * Parse the command line arguments
     */
    public void parse(){
        ArrayList<String> taskArgsArray = new ArrayList<>();
        int index =0;
        for(String arg: args){
            if(isFlag(arg)){
                flags.put(arg, true);
            }else{

                if(index == 0){
                    taskName = arg;
                }else{

                    if(!arg.startsWith("-")) {
                        taskArgsArray.add(arg);
                    }
                }
                index += 1;
            }
        }

        if(this.taskName == null){
            this.taskName = "install";
        }

        this.taskArgs = taskArgsArray.toArray(new String[taskArgsArray.size()]);


    }

    public Task getTask(){
        Task task = null;

        if(this.taskName != null && !taskName.isEmpty()){

            task = gomax.getTaskRegistry().getTask(taskName);

            if(task == null){
                throwError("Unknow task name: " + taskName);

            }else{

                out.println(ConsoleColors.CYAN + "Executing task: " + task.getName());
                out.println("Using flags: "+ getFlagsString());
                out.println("Using args: "+ getArgsString()+ ConsoleColors.WHITE);

                out.println();

            }

        }else{
            throwError("You may specify the task than you want to execute");
        }

        return task;
    }

    private void throwError(String error){
        out.println(ConsoleColors.RED_BOLD+"Error: " + ConsoleColors.RED +  error + ConsoleColors.WHITE);
    }

    private boolean isFlag(String arg){
        if(arg.startsWith("-")){
            return true;
        }
        return false;
    }

    public boolean containsFlag(String name){
        if(this.flags.containsKey(name)){
            if(this.flags.get(name)){
                return true;
            }
        }
        return false;
    }

    public String getFlagsString(){

        if(flags.size() == 0){
            return "any";
        }

        String output = "";


        for(Map.Entry<String, Boolean> entry: flags.entrySet()){
            output += entry.getKey() + " ";
        }

        return output;
    }

    public String getArgsString(){

        if(taskArgs.length == 0){
            return "any";
        }

        String output = "";


        for(String entry: taskArgs){
            output += entry + " ";
        }

        return output;
    }

    public String getTaskName() {
        return taskName;
    }

    public String[] getTaskArgs() {
        return taskArgs;
    }


}

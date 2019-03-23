package fr.romain120105.gomax.task;

import fr.romain120105.gomax.ConsoleColors;
import fr.romain120105.gomax.Gomax;
import fr.romain120105.gomax.Printer;

import java.io.PrintWriter;
import java.util.Map;

public class RunTask extends Task{

    private Gomax gomax = Gomax.get();

    private Printer out = Printer.out();

    @Override
    public void execute(String... args) {
        if(gomax.getProject() != null){
            if(args.length == 1){
                String script = args[0];

                if(gomax.getProject().getScripts() == null){
                    throwError("Unable to find script: " + script);
                    return;
                }

                String command = null;

                for(Map.Entry<String, String> entry : gomax.getProject().getScripts().entrySet()){
                    if(entry.getKey().equals(script)){
                        command = entry.getValue();
                        System.out.println("Run command: " + entry.getValue());
                    }
                }

                if(command == null){
                    String validScripts = "";
                    for(Map.Entry<String, String> entry : gomax.getProject().getScripts().entrySet()){
                        validScripts += entry.getKey() + " ";
                    }

                    if(validScripts.equals("")){
                        throwError("Unable to find script: " + script);

                    }else{
                        throwError("Unable to find script: " + script + ". Valid scripts are: " + validScripts);

                    }


                    return;
                }
            }else{
                throwError("Which script would you like to run ? Specify it in the command! (example: gomax run start)");
            }
        }else{
            throwError("You may create the project before executing this command using: gomax create");
        }
    }

    public void throwError(String error){
        out.println(ConsoleColors.RED_BOLD + "Error: "+ ConsoleColors.RED + error + ConsoleColors.WHITE);

    }

    @Override
    public String getName() {
        return "run";
    }
}

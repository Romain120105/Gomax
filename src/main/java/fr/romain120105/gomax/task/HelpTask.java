package fr.romain120105.gomax.task;

import fr.romain120105.gomax.ConsoleColors;
import fr.romain120105.gomax.Gomax;
import fr.romain120105.gomax.Printer;
import fr.romain120105.gomax.project.TaskRegistry;

import java.io.PrintWriter;
import java.util.Map;

public class HelpTask extends Task{

    private Gomax gomax = Gomax.get();

    private Printer out = Printer.out();

    private TaskRegistry taskRegistry;

    public HelpTask(TaskRegistry taskRegistry) {
        this.taskRegistry = taskRegistry;
    }

    @Override
    public void execute(String... args) {
        out.println(ConsoleColors.GREEN + "Usage: gomax [taskname] [args] [flags]" + ConsoleColors.WHITE);
        out.println();
        out.println(ConsoleColors.CYAN + "Display help information.");
        out.println();
        out.println("All tasks: " + ConsoleColors.WHITE);
        out.println();
        for(Task task : taskRegistry.getTasks()){
            out.println(" - " + task.getName() + "");

        }
    }

    @Override
    public String getName() {
        return "help";
    }
}

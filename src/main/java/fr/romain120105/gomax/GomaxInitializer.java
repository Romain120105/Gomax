package fr.romain120105.gomax;


import fr.romain120105.gomax.project.Project;
import fr.romain120105.gomax.task.Task;
import org.fusesource.jansi.Ansi;


public class GomaxInitializer {

    private static Printer out = Printer.out();
    private static Printer err = Printer.err();


    public static void main(String... args){


        ArgumentsParser parser = new ArgumentsParser(args);
        parser.parse();

        out.println(ConsoleColors.GREEN_BOLD + "Gomax " + parser.getTaskName() + " v1.0" + ConsoleColors.WHITE);

        out.println();

        Task task = parser.getTask();

        if(task != null){
            Gomax.get().executeTask(task, parser.getTaskArgs());
        }








    }


}

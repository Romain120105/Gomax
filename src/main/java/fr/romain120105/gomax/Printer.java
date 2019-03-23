package fr.romain120105.gomax;

import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;
import java.io.PrintWriter;

public class Printer {

    private static Printer out = new Printer("Default", AnsiConsole.out());
    private static Printer err = new Printer("Default", AnsiConsole.err());

    static{
        AnsiConsole.systemInstall();
    }

    private String name;
    private PrintStream stream;

    public Printer(String name, PrintStream stream) {
        this.name = name;
        this.stream = stream;
    }

    public void println(String text){
        stream.println(text);
    }

    public void println()
    {
        stream.println();
    }

    public void println(Object object)
    {
        stream.println(object);
    }


    public void print(String text){
        stream.print(text);
    }

    public static Printer out() {
        return out;
    }

    public static Printer err() {
        return err;
    }
}

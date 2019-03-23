package fr.romain120105.gomax.utils;

public class Utils {

    public static boolean isDevelopmentEnvironment() {
        boolean isEclipse = true;
        if (System.getenv("idea") == null) {
            isEclipse = false;
        }
        return isEclipse;
    }

}

package com.xenojava.tournament.utils;

import com.xenojava.tournament.Setting;

import java.util.logging.Logger;

public class Log {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static final String prefix = "[Tournament] ";

    public static void info(String output) {
        log.info(prefix + output);
    }

    public static void debug(String output) {
        if (Setting.DEBUG.getBoolean()) log.info(prefix + "(Debug) " + output);
    }

    public static void severe(String output) {
        log.severe(prefix + output);
    }

    public static void warning(String output) {
        log.warning(prefix + output);
    }

}

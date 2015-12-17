package amplitude.utils;

import amplitude.system.AmplitudeProperties;

import java.util.Hashtable;
import java.util.prefs.Preferences;

public class CreatePrefs {
    Hashtable<String, String> options;

    public static void main(String[] args) {
        CreatePrefs prefs = new CreatePrefs();
        if (!prefs.parse(args))
            usage();

    }

    public static void usage() {
        System.out.println("Usage [-system | -user] [-name <name> -value <value>] | [ -remove ] | [ -read ]");
    }

    public void execute() {
        Preferences prefs = null;

        if (hasOption("system")) {
            prefs = Preferences.systemNodeForPackage(AmplitudeProperties.class);
        } else {
            prefs = Preferences.userNodeForPackage(AmplitudeProperties.class);
        }

        if (hasOption("remove")) {
            prefs.remove("name");
        } else if (hasOption("read")) {
            System.out.println(prefs.absolutePath() + " = " + prefs.get("name", "undefined"));
        } else {
            prefs.put(getOption("name"), getOption("value"));
        }
    }

    public String getOption(String name) {
        return options.get(name);
    }

    public boolean hasOption(String name) {
        return options.containsKey(name);
    }

    public boolean parse(String[] args) {
        options = new Hashtable<String, String>();
        String key = null;
        String arg = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) != '-') {
                return false;
            }
            key = args[i].substring(1);
            if (args[i + 1].charAt(0) != '-') {
                arg = args[++i];
                while ((i + 1 < args.length) && (args[i + 1].charAt(0) != '-')) {
                    arg = arg + " " + args[++i];
                }
            }
            options.put(key, arg);
        }

        if (!hasOption("system") || !hasOption("user"))
            return false;
        if (!hasOption("name"))
            return false;
        if (!hasOption("value") || !hasOption("remove") || !hasOption("read"))
            return false;

        return true;
    }
}

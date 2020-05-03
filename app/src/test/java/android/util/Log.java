package android.util;

/**
 * Log class emulates the Android studio Log class to be used for JUNIT testing.
 */
public class Log {

    /**
     * Emulates a debug message.
     *
     * @param tag
     * @param msg
     * @return
     */
    public static int d(String tag, String msg) {
        System.out.println("DEBUG: " + tag + ": " + msg);
        return 0;
    }

    /**
     * Emulates an informational message.
     *
     * @param tag
     * @param msg
     * @return
     */
    public static int i(String tag, String msg) {
        System.out.println("INFO: " + tag + ": " + msg);
        return 0;
    }

    /**
     * Emulates a warning message.
     *
     * @param tag
     * @param msg
     * @return
     */
    public static int w(String tag, String msg) {
        System.out.println("WARN: " + tag + ": " + msg);
        return 0;
    }

    /**
     * Emulates an error message.
     *
     * @param tag
     * @param msg
     * @return
     */
    public static int e(String tag, String msg) {
        System.out.println("ERROR: " + tag + ": " + msg);
        return 0;
    }

    /**
     * Emulates a verbose message.
     *
     * @param tag
     * @param msg
     * @return
     */
    public static int v(String tag, String msg) {
        System.out.println("VERBOSE: " + tag + ": " + msg);
        return 0;
    }
}

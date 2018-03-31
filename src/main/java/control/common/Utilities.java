package control.common;

public class Utilities {

    public static String optimizeString(String target) {
        String opt = target;
        opt = opt.replace("\u0000", "");
        opt = opt.replace("\r", "");
        opt = opt.replace("\n", "");
        return opt;
    }
}

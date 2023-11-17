import java.io.PrintWriter;

/*The main class for MP8 where the program and experiments are run */
public class BrailleASCII {

    /* main class that takes the parameters for the toX methods */
    public static void main(String[] args) {
        PrintWriter p = new PrintWriter(System.out, true);
        if (args[0].compareTo("braille") == 0) {// if we are to convert from ASCII chars to Braille
            String result = "";
            for (int i = 0; i < args[1].length(); i++) {
                result += BrailleASCIITables.toBraille(args[1].toCharArray()[i]);
            }
            p.printf("%s\n", result);
        } else if (args[0].compareTo("ascii") == 0) {// if we are to convert from braille to ASCII
            String data = BrailleASCIITables.toASCII(args[1]);
            p.printf("%s\n", data);
        } else if (args[0].compareTo("unicode") == 0) {// if we are to convert from ASCII to unicode
            String data = BrailleASCIITables.toUnicode(args[1]);
            for (int i = 0; i < data.length(); i++) {// printing the braille
                p.printf("%c", data.toCharArray()[i]);
            }
        }
    }
}

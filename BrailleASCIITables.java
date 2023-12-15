import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/*A class to contain and implement my toX methods
* @Author: John Miller
*/
public class BrailleASCIITables {
    BitTree BrailleTree;
    BitTree UnicodeTree;
    BitTree ASCIItree;

    /* Constructor */
    public BrailleASCIITables() {
        this.BrailleTree = new BitTree(8);// converting from ASCII to Braille
        this.UnicodeTree = new BitTree(6);
        this.ASCIItree = new BitTree(6);
    }

    /*
     * toUnicode method that converts a string or ASCII binary string
     * to Braille. it takes bits, which represents a String of ASCII chars,
     * and returns Unicode Braille
     * 
     * @pre: bits is a valid string
     * 
     * @post: returns a string of Braille unicode
     */
    public static String toUnicode(String bits) {
        BrailleASCIITables BAT = new BrailleASCIITables();
        File AtoB = new File("ASCIItoBraille.txt");// making files for data
        File AtoChar = new File("ASCIItoCHAR.txt");// making files for data
        File BtoUni = new File("BrailleToUnicode.txt");// making files for data
        FileInputStream AtoBr = null;
        FileInputStream AtoCh = null;
        FileInputStream BtoU = null;
        try {
            AtoBr = new FileInputStream(AtoB);
            AtoCh = new FileInputStream(AtoChar);
            BtoU = new FileInputStream(BtoUni);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BAT.BrailleTree.load(AtoBr);// loading in from file
        BAT.UnicodeTree.load(BtoU);// loading in from file
        BAT.ASCIItree.load(AtoCh);// loading in from file

        String result = "";
        if (Character.isAlphabetic(bits.toCharArray()[0])) {// if we are given character input
            for (int i = 0; i < bits.length(); i++) {
                int c = (int) bits.toCharArray()[i];// getting the ASCII 7 bit
                String ASCIIbinary = "0" + Integer.toBinaryString(c);
                try {
                    String Braille = BAT.BrailleTree.get(ASCIIbinary);
                    String UnicodeBraille = BAT.UnicodeTree.get(Braille);
                    result += Arrays.toString(Character.toChars(Integer.parseInt(UnicodeBraille, 16)));
                    // prints
                    // the
                    // string
                    // representing the
                    // Unicode braille
                    // character
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (Character.isDigit(bits.toCharArray()[0])) {// if we are given ASCIIbinary input
            for (int i = 0; i < bits.length(); i = i + 7) {
                String ASCIIbinary = bits.substring(i, i + 7);
                try {
                    String Braille = BAT.BrailleTree.get(ASCIIbinary);
                    String UnicodeBraille = BAT.UnicodeTree.get(Braille);
                    result = result + UnicodeBraille;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /*
     * toBraille method takes a char and returns the binary
     * 6-bit braille representation of it
     * 
     * @pre: letter is a valid char
     * 
     * @post: returns a binary string representing it's binary braille representatio
     */
    public static String toBraille(char letter) {
        BrailleASCIITables BAT = new BrailleASCIITables();
        File AtoB = new File("ASCIItoBraille.txt");// making files for data
        File AtoChar = new File("ASCIItoCHAR.txt");// making files for data
        File BtoUni = new File("BrailleToUnicode.txt");// making files for data
        FileInputStream AtoBr = null;
        FileInputStream AtoCh = null;
        FileInputStream BtoU = null;
        try {
            AtoBr = new FileInputStream(AtoB);
            AtoCh = new FileInputStream(AtoChar);
            BtoU = new FileInputStream(BtoUni);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BAT.BrailleTree.load(AtoBr);// loading in from file
        BAT.UnicodeTree.load(BtoU);// loading in from file
        BAT.ASCIItree.load(AtoCh);// loading in from file
        String result = "";
        try {
            int val = (int) letter;
            String fin = "0" + Integer.toBinaryString(val);
            result = BAT.BrailleTree.get(fin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * toASCII method: takes a string of bits representing a binary Braille
     * binary char, and returns the ASCII string,
     * 
     * @pre: bits is a valid string
     * 
     * @post: returns a valid string
     */
    public static String toASCII(String bits) {
        BrailleASCIITables BAT = new BrailleASCIITables();
        File AtoB = new File("ASCIItoBraille.txt");// making files for data
        File AtoChar = new File("ASCIItoCHAR.txt");// making files for data
        File BtoUni = new File("BrailleToUnicode.txt");// making files for data
        FileInputStream AtoBr = null;
        FileInputStream AtoCh = null;
        FileInputStream BtoU = null;
        try {
            AtoBr = new FileInputStream(AtoB);
            AtoCh = new FileInputStream(AtoChar);
            BtoU = new FileInputStream(BtoUni);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BAT.BrailleTree.load(AtoBr);// loading in from file
        BAT.UnicodeTree.load(BtoU);// loading in from file
        BAT.ASCIItree.load(AtoCh);// loading in from file
        String s = "";// empty string to start
        for (int i = 0; i < bits.length(); i = i + 6) {// for each Braille binary substring representation in the string
            try {
                String correspondingASCII = BAT.ASCIItree.get(bits.substring(i, i + 6));// get the char associated with
                                                                                        // it
                s = s + correspondingASCII;// add the ASCII string to the String
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}

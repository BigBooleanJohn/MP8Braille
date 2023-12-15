
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*A class written for the 8th mini project to
 * to store mappings of trees as bits
 */

public class BitTree {
    /* Object fields */
    BitTreeNode root;
    int depth;

    /* Constructor */

    // this constructor takes the number of levels needed in the tree
    public BitTree(int n) {
        this.depth = n;
        this.root = new BitTreeNode(-1, null, null, null, null);
        /*
         * BitTreeNode recursiveNode = this.root;
         * BitTreeHelper(n, recursiveNode);
         */
    }

    /* main methods */

    /*
     * dump is used to print out the values and data at the end of the tree.
     * @Pre: None
     *
     * @Post: None
     */

    public void dump(PrintWriter pen) {
        BitTreeNode BTNode = this.root;
        String s = "";
        if (BTNode.leftNode != null) {
            dumpHelper(pen, BTNode.leftNode, s);
        }
        if (BTNode.rightLeaf != null) {
            dumpHelper(pen, BTNode.rightNode, s);
        }
    }

    /* this is a recursive helper to get through the branches of the trees
     * @Pre: None
     *
     * @Post: None*/
    public void dumpHelper(PrintWriter pen, BitTreeNode node, String s) {
        if (node.rightNode == null && node.leftNode == null) {
            s = s + node.bit;
            if (node.leftLeaf != null) {
                pen.printf("%s, %s\n", s + node.leftLeaf.leafBit, node.leftLeaf.data);
            }
            if (node.rightLeaf != null) {
                pen.printf("%s, %s\n", s + node.rightLeaf.leafBit, node.rightLeaf.data);
            }
        } else {
            s = s + Integer.toString(node.bit);
            if (node.leftNode != null) {
                dumpHelper(pen, node.leftNode, s);
            }
            if (node.rightNode != null) {
                dumpHelper(pen, node.rightNode, s);
            }
        }
    }

    /*
     * Get follows the path through the tree given by bits, returning the value at
     * the end. If there is no such path, or if bits is the incorrect length, get
     * should throw an exception.
     */
    /**
     * @param bits
     * @return
     * @throws Exception
     */
    String get(String bits) throws Exception {
        if (bits.toCharArray().length != this.depth || isValidBits(bits)) {
            throw new Exception("the bit String given is invalid\n");
        }
        BitTreeNode currNode = this.root; // making a copy for looping through
        int i = 0;
        String data = null;
        while (i < bits.length()) {
            if (currNode == null) {
                throw new Exception("the path you gave leads to a null node\n");
            } else if (i == bits.length() - 1 && bits.toCharArray()[i] == '1' && currNode.rightLeaf != null) {
                data = currNode.rightLeaf.data;// if at last node and last index is 1, return val at right leaf
                return data;
            } else if (i == bits.length() - 1 && bits.toCharArray()[i] == '0' && currNode.leftLeaf != null) {
                data = currNode.leftLeaf.data;// if at last node and last index is 0, return val at left leaf
                return data;
            } else if (bits.toCharArray()[i] == '1') {
                currNode = currNode.rightNode;// make the new node the right node
            } else if (bits.toCharArray()[i] == '0') {
                currNode = currNode.leftNode;// make the new node the left node
            }
            i++;
        }
        return data;// return data if nothing happens
    }

    /*
     * Load is a method that takes a source and reads in bit/data values until
     * the end of the file
     * 
     * @pre: source is a valid file of form bitString,data pairs
     * 
     * @post: writes into binary tree
     */
    void load(InputStream source) {
        BufferedReader BFR = new BufferedReader(new InputStreamReader(source));
        String line = null;
        try {
            line = BFR.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            String[] lineArr = line.split(",");
            try {
                this.set(lineArr[0], lineArr[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                line = BFR.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * Set follows the path through the tree given by bits (adding nodes as
     * appropriate) and adds or replaces the value at the end with value. set should
     * throw an exception if bits is the inappropriate length or contains values
     * other than 0 or 1.
     * @Pre: None
     *
     * @Post: None
     */
    public void set(String bits, String value) throws Exception {
        if (bits.toCharArray().length != this.depth || isValidBits(bits)) {
            throw new Exception("the bit String given is invalid\n");
        } else {
            int i = 0;
            BitTreeNode curNode = this.root;
            while (i < bits.length()) {
                if (i == bits.length() - 1 && bits.toCharArray()[i] == '1') {
                    if (curNode.rightLeaf != null) {
                        curNode.rightLeaf.data = value;// set the value in the existing right node to be the value given
                    } else {
                        curNode.rightLeaf = new BitTreeLeaf(1, value);// make a new node with the value
                    }
                } else if (i == bits.length() - 1 && bits.toCharArray()[i] == '0') {
                    if (curNode.leftLeaf != null) {
                        curNode.leftLeaf.data = value;// set the value in the existing right node to be the value given
                    } else {
                        curNode.leftLeaf = new BitTreeLeaf(0, value);// make a new node with the value
                    }
                } else if (bits.toCharArray()[i] == '0') {// if we are at an inner node and the next node has a value of
                                                          // 0
                    if (curNode.leftNode != null) {
                        curNode = curNode.leftNode;
                    } else {
                        curNode.leftNode = new BitTreeNode(0, null, null, null, null);
                        curNode = curNode.leftNode;
                    }
                } else if (bits.toCharArray()[i] == '1') {// if we are at an inner node and the next node has a value of
                                                          // 1
                    if (curNode.rightNode != null) {
                        curNode = curNode.rightNode;
                    } else {
                        curNode.rightNode = new BitTreeNode(1, null, null, null, null);
                        curNode = curNode.rightNode;
                    }
                }
                i++;
            }
        }
    }

    /*
     * isValidBits checks if the bits given have solely 1 and 0 values
     * 
     * @pre: bits is a valid string
     * 
     * @post: true or false returned
     */
    public static boolean isValidBits(String bits) {
        for (int i = 0; i < bits.length(); i++) {
            if (bits.toCharArray()[i] != 0 && bits.toCharArray()[i] != 1) {
                return false;
            }
        }
        return true;
    }
}

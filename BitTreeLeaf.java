/*thus is BitTreeLeaf, a subclass of BitTreeNode, which is used to implement a binary tree.
 * @Author: John Miller
 */
public class BitTreeLeaf extends BitTreeNode {
    /* fields */
    int leafBit;// the final bit at the end of the tree
    String data;// the string associated with the leaf

    /* constructors */
    public BitTreeLeaf(int leafBit, String data) {
        this.data = data;// setting the field to the variable
        this.leafBit = leafBit;// setting the field to the variable
    }
}

/*this is a class to form the nodes for my BitTree.
 * It will contain an inner class that represents the 
 * leaves of the nodes, that is, the final values at the end of the trees.
 * the nodes will be connected either to other interior nodes,
 * or to leaves.
 */

public class BitTreeNode {
    /* fields */
    int bit;// the bit value stored at the Node
    BitTreeNode leftNode;// the next interior left node (if there is one)
    BitTreeNode rightNode;// the next interior right node(if there is one)
    BitTreeLeaf leftLeaf;// the end leaf, containing a final bit and the String data, on the left of the
                         // node
    BitTreeLeaf rightLeaf;// the end leaf, containing a final bit and the String data, on the right of the
                          // node

    /* Constructors */
    public BitTreeNode() {
    }

    public BitTreeNode(int bit, BitTreeNode leftNode, BitTreeNode rightNode, BitTreeLeaf leftLeaf,
            BitTreeLeaf rightLeaf) {
        this.bit = bit;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.leftLeaf = leftLeaf;
        this.rightLeaf = rightLeaf;
    }
}

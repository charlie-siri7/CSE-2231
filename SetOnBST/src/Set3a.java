import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @authors Charles Sirichoktanasup, Dylan Jian
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        /*
         * If t isn't empty, disassembles tree and checks if the label equals x.
         * If this isn't the case, isInTree is called on the left or right tree
         * until x is found (inTree = true) until the tree search is empty
         * (inTree = false). The tree is reassembled at the end of the code, and
         * the inTree boolean is returned.
         */
        boolean inTree = false;
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();
        if (t.size() > 0) {
            T label = t.disassemble(left, right);
            if (label.compareTo(x) == 0) {
                inTree = true;
            } else if (label.compareTo(x) > 0) {
                inTree = isInTree(left, x);
            } else {
                inTree = isInTree(right, x);
            }
            t.assemble(label, left, right);
        }
        return inTree;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        /*
         * t is disassembled, and insertTree is recursively called on either the
         * left or right tree (whichever contains values close to x) until an
         * empty tree has been reached, in which it is replaced with root x and
         * 2 empty subtrees. All calls of insertInTree on a non-empty tree
         * result in re-assembly of that tree.
         */
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();
        if (t.size() > 0) {
            T label = t.disassemble(left, right);
            if (label.compareTo(x) > 0) {
                insertInTree(left, x);
            } else {
                insertInTree(right, x);
            }
            t.assemble(label, left, right);
        } else {
            t.assemble(x, left, right);
        }
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";
        /*
         * The smallest node is assumed to be the current node. However, if the
         * left subtree of t isn't empty, removeSmallest will recursively call
         * on that tree in order to set T smallest to the node of the leftmost
         * node, which is then replaced by its right subtree. t is reassembled
         * if its root has a non-empty left subtree and smallest is returned.
         */
        T smallest = t.root();
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();
        T label = t.disassemble(left, right);
        if (left.size() > 0) {
            smallest = removeSmallest(left);
            t.assemble(label, left, right);
        } else {
            t.transferFrom(right);
        }
        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";
        /*
         * Disassembles tree and checks if the root equals the item to be
         * removed. If this is the case, assemble and replace the root node with
         * the smallest item from the right tree or the left tree if the right
         * tree is empty. Otherwise, call removeFromTree on either the left or
         * right subtree depending on the root's value compared to x, and
         * reassemble the trees as normal since they don't contain the item to
         * be removed.
         */
        T returnValue = t.root();
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();
        T label = t.disassemble(left, right);
        if (label.compareTo(x) == 0) {
            if (right.size() > 0) {
                t.assemble(removeSmallest(right), left, right);
            } else {
                t.transferFrom(left);
            }
        } else {
            if (label.compareTo(x) > 0) {
                returnValue = removeFromTree(left, x);
            } else {
                returnValue = removeFromTree(right, x);
            }
            t.assemble(label, left, right);
        }
        return returnValue;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.tree = new BinaryTree1<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";
        // Inserts x into the BinaryTree, simulating add for Set
        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";
        // Removes x from BinaryTree, simulating remove for Set
        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";
        // Removes the smallest item from Binary Tree, simulating removeAny for Set
        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";
        // Returns whether x is in the BinaryTree, simulating contains for Set
        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        // Returns size of BinaryTree, simulating size for Set
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}

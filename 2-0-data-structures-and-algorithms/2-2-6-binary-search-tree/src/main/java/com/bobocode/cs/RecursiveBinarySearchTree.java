package com.bobocode.cs;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong><a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        private Node(T element) {
            this.element = element;
        }

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> root;

    private int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> recursiveBinarySearchTree = new RecursiveBinarySearchTree<>();
        Stream.of(elements).forEach(recursiveBinarySearchTree::insert);
        return recursiveBinarySearchTree;
    }

    @Override
    public boolean insert(T element) {
        Objects.requireNonNull(element);
        boolean isInserted = insertElement(element);
        if (isInserted) {
            size++;
        }
        return isInserted;
    }

    boolean insertElement(T element) {
        if (root == null) {
            root = Node.valueOf(element);
            return true;
        } else {
            return insertIntoSubTree(root, element);
        }
    }

    private boolean insertIntoSubTree(Node<T> subTreeRoot, T element) {
        if (subTreeRoot.element.compareTo(element) > 0) {
            return insertIntoLeftSubTree(subTreeRoot, element);
        } else if (subTreeRoot.element.compareTo(element) < 0) {
            return insertIntoRightSubTree(subTreeRoot, element);
        } else {
            return false;
        }
    }

    private boolean insertIntoLeftSubTree(Node<T> node, T element) {
        if (node.left != null) {
            return insertIntoSubTree(node.left, element);
        } else {
            node.left = Node.valueOf(element);
            return true;
        }
    }

    private boolean insertIntoRightSubTree(Node<T> node, T element) {
        if (node.right != null) {
            return insertIntoSubTree(node.right, element);
        } else {
            node.right = Node.valueOf(element);
            return true;
        }
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        if (root == null) {
            return false;
        } else {
            return containsInSubTree(root, element);
        }
    }

    private boolean containsInSubTree(Node<T> subTreeRoot, T element) {
        if (subTreeRoot.element.compareTo(element) > 0) {
            return containsInLeftSubTree(subTreeRoot, element);
        } else if (subTreeRoot.element.compareTo(element) < 0) {
            return containsInRightSubTree(subTreeRoot, element);
        } else return subTreeRoot.element.compareTo(element) == 0;
    }

    private boolean containsInLeftSubTree(Node<T> node, T element) {
        if (node.left != null) {
            return containsInSubTree(node.left, element);
        } else {
            return false;
        }
    }

    private boolean containsInRightSubTree(Node<T> node, T element) {
        if (node.right != null) {
            return containsInSubTree(node.right, element);
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        int innerRightDepth = 0;
        int innerLeftDepth = 0;
        innerRightDepth = depthInRightSubTree(root, innerRightDepth);
        innerLeftDepth = depthInLeftSubTree(root, innerLeftDepth);

        return Math.max(innerRightDepth, innerLeftDepth);
    }

    private int depthInRightSubTree(Node<T> subTreeRoot, int innerRightDepth) {
        if (subTreeRoot != null) {
            if (subTreeRoot.right != null) {
                subTreeRoot = subTreeRoot.right;
                innerRightDepth++;
                return depthInRightSubTree(subTreeRoot, innerRightDepth);
            } else if (subTreeRoot.left != null) {
                subTreeRoot = subTreeRoot.left;
                innerRightDepth++;
                return depthInRightSubTree(subTreeRoot, innerRightDepth);
            }
            return innerRightDepth;
        }
        return 0;
    }

    private int depthInLeftSubTree(Node<T> subTreeRoot, int innerLeftDepth) {
        if (subTreeRoot != null) {
            if (subTreeRoot.left != null) {
                subTreeRoot = subTreeRoot.left;
                innerLeftDepth++;
                return depthInRightSubTree(subTreeRoot, innerLeftDepth);
            } else if (subTreeRoot.right != null) {
                subTreeRoot = subTreeRoot.right;
                innerLeftDepth++;
                return depthInRightSubTree(subTreeRoot, innerLeftDepth);
            }
            return innerLeftDepth;
        }
        return 0;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.element);
            inOrderTraversal(node.right, consumer);
        }
    }
}

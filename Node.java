/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocumentWordBinaryTree;

import java.util.LinkedList;

/**
 * This class is constructed to create a node object for a binary tree.
 * @author Md Imran Hossain
 */
public class Node {

    private String key;
    private Long value;
    private Node parent;
    private Node left;
    private Node right;
/**
 * Constructor for the Node Class
 * @param key setting the key for the node
 * @param value setting the value for the node
 */
    public Node(String key, Long value) {
        this.key = key;
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
/**
 * Constructor for the Node Class
 * @param key setting the key for the node
 * @param value setting the value for the node
 * @param left setting the left node of this node
 * @param right setting the right node of this node
 */
    public Node(String key, Long value, Node left, Node right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = null;
    }

    public String getKey() {
        return key;
    }

    public Long getValue() {
        return value;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
/**
 * The getChilds() method will return the left and right node associated 
 * with this node as a collection (LinkedList) of nodes
 */
    public LinkedList<Node> getChilds() {
        LinkedList<Node> nodeList = new LinkedList<>();
        if (this.left != null) {
            nodeList.add(this.left);
        }
        if (this.right != null) {
            nodeList.add(this.right);
        }
        return nodeList;
    }
/**
 * The print() method print the entire binary tree in the console 
 */
    public void print() {
        print("",this, false);
    }

    private void print(String prefix, Node node, boolean isLeft) {
        if (node != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + node.getKey() + "=" + node.getValue());  
            print(prefix + (isLeft ? "|   " : "    "), node.right, true);
            print(prefix + (isLeft ? "|   " : "    "), node.left, false);
        }
    }
 

}

package net.htlgrieskirchen.aud2.list;

public class Node<E> {

    public Node<E> left;

    public Node<E> right;

    public E element;

    public Node() {
    }

    public Node(E element) {
        this.element = element;
    }

    public Node(E element, Node<E> left) {
        this.element = element;
        this.left = left;
    }

    public Node(E element, Node<E> left, Node<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public E getElem() {
        return right.element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "Node{" + "left=" + left + ", right=" + right + ", element=" + element + '}';
    }

}

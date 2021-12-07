package net.htlgrieskirchen.aud2.list;

public class MyList<E> {

    private Node<E> start = null;

    public MyList() {
        start = new Node();
    }

    public boolean add(E obj) {
        if (start == null) {
            start = new Node(obj, null, null);
        } else {
            Node<E> current = start;

            while (current.right != null) {
                current = current.right;
            }

            Node<E> newNode = new Node<>(obj);

            newNode.left = current;
            current.right = newNode;
        }

        return true;
    }

    public void add(int index, E s) {
        Node<E> current = start;
        int count = 0;

        if (start == null) {
            start = new Node(s, null, null);
            return;
        }

        while (current.getRight() != null && count <= index) {

            current = current.getRight();
            count++;
        }
        Node temp = current.getRight();
        Node toAdd = new Node(s);
        toAdd.setRight(temp);
        current.setRight(toAdd);
    }

    public E get(int index) {
        Node<E> current = start;
        int counter = 0;

        while (current != null) {

            if (counter == index) {

                return current.getElem();
            }
            counter++;

            current = current.getRight();

        }

        return null;
    }

    public boolean remove(String s) {
        Node<E> current = start;
        if (start == null || start.element == null) {
            start = new Node(s, null, null);
        } else {
            while (current != null) {
                if (s.equals(current.getRight().getElem())) {
                    current = null;
                    return true;
                }
                current = current.getRight();
            }
        }
        return false;
    }

    public E remove(int index) {
        Node<E> current = start;
        int counter = 0;

        if (start == null || start.element == null) {
            start = new Node(null, null);
        } else {
            while (current != null) {
                if (index == counter) {
                    current.setElement(null);
                    break;
                }
                counter++;
                current = current.getRight();
            }
        }
        return current.getElem();
    }

    public E set(int index, E s) {
        Node<E> current = start;
        int counter = 0;

        while (current != null) {
            if (counter == index) {
                current.setElement(s);
                break;
            }
            current = current.getRight();
            counter++;
        }

        return s;
    }

    public boolean contains(E s) {
        Node<E> current = start;

        if (start == null || start.element == null) {
            start = new Node(s, null, null);
        } else {

        }
        while (current != null) {
            if (s.equals(current.getElem())) {
                return true;
            }
            current = current.getRight();
        }

        return false;
    }

    public int indexOf(E s) {
        Node<E> current = start;
        int counter = 0;

        while (current != null) {
            if (s.equals(current.getElem())) {
                return counter;
            }
            counter++;
            current = current.getRight();
        }

        return -1;
    }

    public int size() {
        Node<E> current = start;
        int counter = 1;

        while (current.getRight() != null) {
            counter++;
            current = current.getRight();
        }

        return counter;
    }

    public boolean isEmpty() {
        Node<E> current = start;

        if (current.getRight() == null) {
            return true;
        }

        return false;

    }

    private Node lastElem() {
        Node<E> current = start;
        if (start == null || start.element == null) {
            start = new Node(null, null);
        } else {
            while (current.getRight() != null) {
                current = current.getRight();

            }
        }
        return current;
    }

}

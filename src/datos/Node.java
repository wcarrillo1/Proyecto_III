package datos;


public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Node<T> up;
    private Node<T> down;

    public Node(T data) {
        this.data = data;
        this.left = this;
        this.right = this;
        this.up = this;
        this.down = this;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getLeft() {
        return this.left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public Node<T> getUp() {
        return this.up;
    }

    public Node<T> getDown() {
        return this.down;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setUp(Node<T> up) {
        this.up = up;
    }

    public void setDown(Node<T> down) {
        this.down = down;
    }

    public void setData(T data) {
        this.data = data;
    }
}

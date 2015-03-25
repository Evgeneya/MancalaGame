/**
 * Created by 1 on 19.03.2015.
 */
public class Main {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(5, null, null);
        System.out.println(t);

    }
}

class TreeNode {
    private int data;
    private TreeNode left, right;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight(){
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Main.TreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        TreeNode t = (TreeNode) o;

        if (t.getData() == this.getData()){
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return data;
    }
}

class MyTree {
    private TreeNode root;

    public MyTree() {

    }

    private void add(TreeNode root, int data) {
        if (root != null) {

            if (data > root.getData()) {
                if (root.getRight() == null) {
                    root.setRight(new TreeNode(data, null, null));
                } else add(root.getRight(), data);
            } else {
                if (root.getLeft() == null) {
                    root.setLeft(new TreeNode(data, null, null));
                } else {
                    add(root.getLeft(), data);
                }
            }
        }
    }

    public void add(int data) {
        if (this.root == null) {
            this.root = new TreeNode(data, null, null);
        } else {
            add(this.root, data);
        }
    }
}

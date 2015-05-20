public class TreeNode {
    private int hole;
    private int mark = 0;
    private TreeNode hole1 = null;
    private TreeNode hole2 = null;
    private TreeNode hole3 = null;
    private TreeNode hole4 = null;
    private TreeNode hole5 = null;
    private TreeNode hole6 = null;

    public TreeNode(int step, int level) {
        this.hole = step;
        if(level <= 5) {
            this.hole1 = new TreeNode(1, level + 1);
            this.hole2 = new TreeNode(2, level + 1);
            this.hole3 = new TreeNode(3, level + 1);
            this.hole4 = new TreeNode(4, level + 1);
            this.hole5 = new TreeNode(5, level + 1);
            this.hole6 = new TreeNode(6, level + 1);
        }

    }

    public int getHole() {
        return this.hole;
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public TreeNode getHole1() {
        return this.hole1;
    }

    public TreeNode getHole2() {
        return this.hole2;
    }

    public TreeNode getHole3() {
        return this.hole3;
    }

    public TreeNode getHole4() {
        return this.hole4;
    }

    public TreeNode getHole5() {
        return this.hole5;
    }

    public TreeNode getHole6() {
        return this.hole6;
    }

    public void printNode(int level) {
        System.out.println(this + "level = " + level);
        System.out.println("Children: level " + (level + 1));
        if(this.getHole1() != null) {
            System.out.println(this.getHole1().toString() + "; " + this.getHole2() + "; " + this.getHole3() + "; " + this.getHole4() + "; " + this.getHole5() + this.getHole6());
        }

        System.out.println();
    }

    public String toString() {
        return "TreeNode{hole=" + this.hole + ", mark=" + this.mark + '}';
    }
}

public class Comp extends Tree {

    private boolean player = false;
    private int best;

    public Comp(int step, Board board) {
        super(step);
        this.AssessTree(this.getRoot(), this.player, null, 0, board);
        this.best = this.BestStep(this.getRoot(), board);
    }

    public int getBest() {

        return this.best;
    }

    int BestStep(TreeNode root, Board board) {
        int[] MasAssess = this.AssessStep(root, board);
        int IndexMaxMark = 0;

        for(int i = 1; i < 6; ++i) {   //цикл можно начать с 1, нет смысла сравнивать одинаковые значения
            if(MasAssess[IndexMaxMark] < MasAssess[i]) {
                IndexMaxMark = i;
            }
        }

        return IndexMaxMark + 1;
    }
}

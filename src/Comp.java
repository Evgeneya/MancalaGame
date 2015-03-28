/**
 * Created by 1 on 18.03.2015.
 */
public class Comp extends Tree{

    private boolean player = false;
    private int best;

    public Comp(int step, Board board) {
        super(step);
        AssessTree(getRoot(), player, null, 0, board);
        this.best = BestStep(getRoot(), board);
    }

    public int getBest() {
        return best;
    }

    int BestStep(TreeNode root, Board board)
    {
        int [] MasAssess = AssessStep(root, board);
        int IndexMaxMark = 0;
        for (int i = 0; i < 6; i++)
        {
            if (MasAssess[IndexMaxMark] < MasAssess[i])
                IndexMaxMark = i;
        }
        return IndexMaxMark;		//Ход, при котором предполагаемое число камней, которые могут быть положены с свою манкалу, больше
    }
}

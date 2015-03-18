/**
 * Created by 1 on 18.03.2015.
 */
public class Comp {
    private boolean player = true;
    public void Move(Board board){
        Tree tree = new Tree();
        int[][] mas = board.getBoard();
        tree.AssessTree(player, mas, (byte) 0);
        byte niche = tree.BestStep();
        board.Step(niche, player);
    }
}

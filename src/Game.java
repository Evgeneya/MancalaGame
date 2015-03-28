/**
 * Created by 1 on 18.03.2015.
 */
public class Game {
    public static void main(String[] args) {
        Board board=new Board();
        board.print();
        board.Step(3,1);
        board.print();
        board.Step(5);
        board.print();
        board.CheckEndGame();
    }
}

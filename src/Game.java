/**
 * Created by 1 on 18.03.2015.
 */
public class Game {
    public static void main(String[] args) {
        Board board=new Board();
        int step;
        board.print();
        while (!board.CheckEndGame())
        {
            System.out.println("Введите номер ниши:");
            step=board.userInput();
            board.Step(step, 1);// для пользователя
            board.print();
            Comp comp=new Comp(step,board);
            //System.out.println(comp.getBest());
            board.Step(3);//для компа
            board.print();
        }
        board.winner();
    }
}

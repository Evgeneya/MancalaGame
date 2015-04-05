/**
 * Created by 1 on 18.03.2015.
 */
public class Game {
    public static void main(String[] args) {
        Board board=new Board();
        int step=0;
        boolean gear=true;
        board.print();
        boolean player=true;
        while (!board.CheckEndGame())
        {
            if (player){
                while (gear) {
                    System.out.println("Введите номер ниши:");
                    step = board.userInput();
                    gear = board.Step(step, 1);
                    board.print();
                }
            }
            else{
                while (gear){
                    Comp comp=new Comp(step,board);
                    gear=board.Step(comp.getBest());//для компа
                    board.print();
                }
            }
            player=!player;
            gear=true;
        }
        board.winner();
    }
}

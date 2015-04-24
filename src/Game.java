import java.io.IOException;

/**
 * Created by 1 on 18.03.2015.
 */
public class Game {
    public static void main(String[] args) throws IOException {
        Board board=new Board();
        int step=0;
        boolean gear=true;
        board.print();
        boolean player=true;
        while (!board.CheckEndGame())
        {
            if (player){
                while (gear && !board.CheckEndGame()) {
                    System.out.println("Введите номер ниши:");
                    do{
                        step=board.userInput();
                    }while(step<0 || step>6);
                    if (board.getSum(step)!=0)
                        gear = board.Step(step, 1);
                    else{
                        System.out.println("В этой нише нет камней. Выберите другую нишу:");
                        do{
                            step=board.userInput();
                        }while(step<0 || step>6);
                    }
                    board.print();
                }
            }
            else{
                while (gear && !board.CheckEndGame()){
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

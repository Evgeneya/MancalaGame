/**
 * Created by 1 on 18.03.2015.
 */

/* true - user, false - comp or 2-user */

public class Board {
    private int[][] board;
    private int MyMancala, YouMancala;          //Не ругайся, начальника, я переименовала их для случая игры двух игроков

    public Board(){
        board = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = 3;
            }
        }
        MyMancala = 0;
        YouMancala = 0;
    }
/* Для компа или второго пользователя */
    public boolean Step(int niche){
        /* niche - номер ниши, с которой начнём раскладывать камни */
        return true;
    }
    //Для пользователя, параметр player для полиморфизма + на случай игры двух игроков
    public boolean Step(int niche, byte player){
        int index=niche-1;
        boolean flag=false;
        int sum=board[player][index];
        boolean end=false;      //индикатор границы своего поля
        board[player][index]=0;
        index++;
        while (sum!=0){
            flag=false;
            if (index<6){
                sum--;
                board[player][index]++;
                index++;
            }
            else
            {
                sum--;
                end=true;

            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }



}

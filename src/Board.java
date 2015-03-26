/**
 * Created by 1 on 18.03.2015.
 */

/* 1 - user, 0 - comp or 2-user */

public class Board {
    private int[][] board;
    private int MyMancala, YouMancala;

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
    //Для пользователя, параметр player для полиморфизма
    public boolean Step(int niche, byte player){
        int index=niche-1;
        boolean flag=false;
        int sum=board[player][index];
        boolean end=false;      //индикатор границы своего поля
        boolean gear=false;
        board[player][index]=0;
        index++;
        while (sum!=0){
            while (sum!=0) {
                flag = false;
                if (index < 6) {
                    sum--;
                    board[player][index]++;
                    index++;
                } else {
                    sum--;
                    end = true;
                    YouMancala++;
                    break;
                }
            }
            if (sum!=0) {
                index=5;
                while(sum!=0){
                    if (index>=0){
                        flag=true;
                        sum--;
                        board[player-1][index]++;
                        index--;
                    }
                    else{
                        sum--;
                        break;
                    }
                }
            }
        }
        if (!flag){
            if (!end){
                if(board[player][index-1]!=1){
                    Step(index,player);
                }
                else{
                    YouMancala+=board[player-1][index-1];
                    board[player-1][index-1]=0;
                    return gear;
                }
            }
            else
                gear=true;
        }
        return gear;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }



}

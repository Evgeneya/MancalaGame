/**
 * Created by 1 on 18.03.2015.
 */

/* true - user, false - comp */

public class Board {
    private int[][] board;
    private int MancalaComp, MancalaUser;

    public Board(){
        board = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = 3;
            }
        }
        MancalaComp = 0;
        MancalaUser = 0;
    }
/* Для компа */
    public boolean Step(int niche, boolean player){
        /* niche - номер ниши, с которой начнём раскладывать камни */
        return true;
    }
    //Для пользователя
    public boolean Step(int niche){
        int index=niche-1;
        boolean flag=false;

        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }



}

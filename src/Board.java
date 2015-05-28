public class Board {
    private int[][] board = new int[2][6];
    private int CompMancala;
    private int YouMancala;

    public Board() {
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 6; ++j) {
                this.board[i][j] = 3;
            }
        }

        this.CompMancala = 0;
        this.YouMancala = 0;
    }

    public int getSum(int i) {
        return this.board[1][i - 1];
    }

    /* Для компа или второго пользователя */
    public boolean Step(int hole){
        /* hole - номер ниши, с которой начнём раскладывать камни */
        int index=hole-1;
        boolean flag=false;   //принимает значение true, если раскладка переходит на поле противника(пользователя)
        int sum=board[0][index];
        if (sum == 0)
            return false;
        boolean end=false;      //индикатор попадания в свою манкалу
        boolean gear=false;
        board[0][index]=0;
        index--;
        //добавить еще 1 цикл, для случая возвращения на свое поле
        if (sum!=0){  // убрать
            while(sum!=0){
                //flag = false //можем вернуться в этот цикл, то есть на свое поле
                if(index>=0){
                    sum--;
                    board[0][index]++; //можно сократить код
                    index--;
                }
                else {
                    sum--;
                    end=true;
                    CompMancala++;
                    break;
                }
            }
            if (sum!=0){ //убрать
                index=0;
                while(sum!=0) {
                    //end = false;  // можем в следующей итерации попасть на свое поле, но не в манкалу, поэтому сбрасываем true
                    if (index<6) {
                        flag=true;
                        sum--;
                        board[1][index]++;  //можно сократить код
                        index++;
                    }
                    else{
                        sum--;  //зачем? в манкалу противника камни не раскладываются
                        //index = 5; // вдруг сумма еще не 0 и нужно продолжить расклад на своем поле
                        break;
                    }
                }
            }
        }
        if (!flag){  //остались на своем поле
            if(!end){   //последний камень не в манкале
                if(board[0][index+1]!=1)
                    Step(index+2);
                else{
                    CompMancala+=board[1][index+1];
                    board[1][index+1]=0;
                    return false;
                }
            }
            else
                gear=true;   //return true
        }
        return gear;  //можно убрать переменную, вместо неё return false
    }
    //Для пользователя, параметр player для полиморфизма
    public boolean Step(int hole, int player){
        int index=hole-1;
        boolean flag=false;  //переход на чужое поле - true
        int sum=board[player][index];
        if (sum == 0)
            return false;
        boolean end=false;      //индикатор границы своего поля //индикатор попадания в свою манкалу
        boolean gear=false; // ненужная!
        board[player][index]=0;
        index++;
        //добавить еще 1 цикл, для случая возвращения на свое поле
        if (sum!=0){ //убрать
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
            if (sum!=0) { //убрать
                index=5;
                while(sum!=0){
                    if (index>=0){
                        flag=true;
                        sum--;
                        board[player-1][index]++;
                        index--;
                    }
                    else{
                        sum--;  //зачем?
                        //index = 0;
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
                    return gear; //return false
                }
            }
            else
                gear=true; //return true
        }
        return gear; //можно убрать переменную, вместо неё return false
    }

    public int[][] getBoard() {
        return this.board;
    }

    public int getYouMancala() {
        return this.YouMancala;
    }

    public int getCompMancala() {
        return this.CompMancala;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public boolean CheckEndGame() {
        short flag = 0;

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 6; ++j) {
                if(this.board[i][j] == 0) {
                    ++flag;
                }
            }

            if(flag == 6) {
                if (i == 0)
                    for (int j = 0; j < 6; j++){
                        YouMancala += board[1][j];
                        board[1][j] = 0;
                    }
                else
                    for (int j = 0; j < 6; j++){
                        CompMancala += board[0][j];
                        board[0][j] = 0;
                    }

                return true;
            }

            flag = 0;
        }

        return false;
    }

    public String winner() {
        return this.CompMancala > this.YouMancala?"You lose!":(this.CompMancala < this.YouMancala?"You win!":"Dead heat");
    }
}
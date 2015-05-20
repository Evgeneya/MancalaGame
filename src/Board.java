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

    public boolean Step(int hole) {
        int index = hole - 1;
        boolean flag = false;
        int sum = this.board[0][index];
        if(sum == 0) {
            return false;
        } else {
            boolean end = false;
            boolean gear = false;
            this.board[0][index] = 0;


            --index;

            label48:
            while(sum != 0) {
                while(true) {
                    if(sum != 0) {
                        if(index >= 0) {
                            --sum;
                            ++this.board[0][index];


                            --index;
                            continue;
                        }

                        --sum;
                        end = true;
                        ++this.CompMancala;

                    }

                    if(sum == 0) {
                        break;
                    }

                    index = 0;

                    while(true) {
                        if(sum == 0) {
                            continue label48;
                        }

                        if(index >= 6) {
                            --sum;
                            continue label48;
                        }

                        flag = true;
                        --sum;
                        ++this.board[1][index];


                        ++index;
                    }
                }
            }

            if(!flag) {
                if(!end) {
                    if(this.board[0][index + 1] == 1) {
                        this.CompMancala += this.board[1][index + 1];
                        this.board[1][index + 1] = 0;
                        return false;
                    }

                    this.Step(index + 2);
                } else {
                    gear = true;
                }
            }

            return gear;
        }
    }

    public boolean Step(int hole, int player) {
        int index = hole - 1;
        boolean flag = false;
        int sum = this.board[player][index];
        if(sum == 0) {
            return false;
        } else {
            boolean end = false;
            boolean gear = false;
            this.board[player][index] = 0;

            ++index;

            label48:
            while(sum != 0) {
                while(true) {
                    if(sum != 0) {
                        flag = false;
                        if(index < 6) {
                            --sum;
                            ++this.board[player][index];
                            ++index;
                            continue;
                        }

                        --sum;
                        end = true;
                        ++this.YouMancala;
                    }

                    if(sum == 0) {
                        break;
                    }

                    index = 5;

                    while(true) {
                        if(sum == 0) {
                            continue label48;
                        }

                        if(index < 0) {
                            --sum;
                            continue label48;
                        }

                        flag = true;
                        --sum;
                        ++this.board[player - 1][index];
                        --index;

                    }
                }
            }

            if(!flag) {
                if(!end) {
                    if(this.board[player][index - 1] == 1) {
                        this.YouMancala += this.board[player - 1][index - 1];



                        this.board[player - 1][index - 1] = 0;



                        return gear;
                    }

                    this.Step(index, player);
                } else {
                    gear = true;
                }
            }

            return gear;
        }
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
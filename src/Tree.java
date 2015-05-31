public class Tree {

    private TreeNode root;
    private int mark;
    private boolean gear;
    private int sum;

    public TreeNode getRoot() {
        return this.root;
    }

    public Tree(int step) {
        this.root = new TreeNode(step, 0);
    }

    //Зачем публично? Она нигде не используется вроде. Елена Кувшинова
    public int AssessVertex(int step, boolean player, int[][] dop) {
        this.gear = false;
        int index = step - 1;
        int pl = player?1:0;
        int sum = dop[pl][index];
        if(sum == 0) {
            return 0;
        } else {
            dop[pl][index] = 0;
            boolean flag = false;
            boolean end = false;
            if(pl == 0) {  // if (!player)
                --index;
                while(sum != 0) {
                    while(sum != 0) {
                        flag = false;
                        if(index < 0) {
                            --sum;
                            end = true;     //Конец поля, попало в манкалу
                            ++this.mark;
                            break;
                        }
                        --sum;
                        ++dop[0][index];
                        --index;
                    }

                    if(sum != 0) {  //убрать        //То же самое что и для Степов в борде. Елена
                        for(index = 0; sum != 0; ++index) {
                            if(index >= 6) {
                                //index = 5; //для след. итерации
                                break;
                            }
                            //end = false;      //Зачем это здесь делать? Елена
                            flag = true;
                            --sum;
                            ++dop[1][index];
                        }
                    }
                }
                if(!flag) {
                    if(!end) {
                        if(dop[0][index + 1] == 1) {  //ровно 1     //Согласна
                            this.mark += dop[1][index + 1];
                            dop[1][index + 1] = 0;
                            return this.mark;
                        }
                        this.AssessVertex(index + 2, false, dop);
                    } else {
                        this.gear = true;           //Также как и в степах не нужна эта переменная
                    }
                } else {
                    --this.mark;
                }
            } else {
                ++index;
                while(sum != 0) {
                    while(sum != 0) {
                        flag = false;
                        if(index >= 6) {
                            --sum;  //почему не влияет на mark      // Потому что как там пользователь чего разложит нам не важно
                            end = true;
                            break;
                        }
                        --sum;
                        ++dop[1][index];
                        ++index;
                    }

                    if(sum != 0) { //убрать     //объяснила
                        for(index = 5; sum != 0; --index) {
                            if(index < 0) {
                                --sum;  //влияние на mark
                                //index = 0;  //для след. цикла
                                break;
                            }

                            //end = false;
                            flag = true;
                            --sum;
                            ++dop[0][index];
                        }
                    }
                }

                if(!flag) {
                    if(!end) {
                        if(dop[1][index - 1] == 1) { //ровно 1              //Согласна
                            dop[0][index - 1] = 0;
                            return this.mark;
                        }

                        this.AssessVertex(index, true, dop);
                    } else {
                        this.gear = true;
                    }
                }
            }

            return this.mark;
        }
    }

    //Зачем в параметрах неиспользуемый mas? Елена
    public void AssessTree(TreeNode cur, boolean player, int[][] mas, int level, Board board) {
        if(level <= 5) {
            this.mark = 0;
            boolean gear = false;
            int[][] dopBoard = board.getBoard();            //Зачем опять копия массива?
            int[][] dop1 = new int[2][6];
            int[][] dop2 = new int[2][6];

            int i;
            int j;
            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop1[i][j] = dopBoard[i][j];
                }
            }

            if(level != 0) {
                if(!player && dop1[0][cur.getHole() - 1] == 0) {
                    return;
                }

                cur.setMark(this.AssessVertex(cur.getHole(), player, dop1));
            }

            if(!gear) {
                player = !player;
            }

            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop2[i][j] = dop1[i][j];
                }
            }

            if(cur.getHole1() != null && dop1[0][cur.getHole1().getHole() - 1] != 0) {
                this.mark = 0;
                this.AssessTree(cur.getHole1(), player, dop1, level + 1, board);
            }

            this.mark = 0;

            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop1[i][j] = dop2[i][j];
                }
            }

            if(cur.getHole2() != null && dop1[0][cur.getHole2().getHole() - 1] != 0) {
                this.AssessTree(cur.getHole2(), player, dop1, level + 1, board);
            }

            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop1[i][j] = dop2[i][j];
                }
            }

            this.mark = 0;
            if(cur.getHole3() != null) {
                this.AssessTree(cur.getHole3(), player, dop1, level + 1, board);
            }

            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop1[i][j] = dop2[i][j];
                }
            }

            this.mark = 0;
            if(cur.getHole4() != null) {
                this.AssessTree(cur.getHole4(), player, dop1, level + 1, board);
            }

            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop1[i][j] = dop2[i][j];
                }
            }

            this.mark = 0;
            if(cur.getHole5() != null) {
                this.AssessTree(cur.getHole5(), player, dop1, level + 1, board);
            }

            for(i = 0; i < 2; ++i) {
                for(j = 0; j < 6; ++j) {
                    dop1[i][j] = dop2[i][j];
                }
            }

            this.mark = 0;
            if(cur.getHole6() != null) {
                this.AssessTree(cur.getHole6(), player, dop1, level + 1, board);
            }
        }
    }

    public int Sum(TreeNode cur, int level) {
        if(level > 4) {
            return this.sum;
        } else {
            this.sum+= cur.getMark();
            this.Sum(cur.getHole1(), level + 1);
            this.Sum(cur.getHole2(), level + 1);
            this.Sum(cur.getHole3(), level + 1);
            this.Sum(cur.getHole4(), level + 1);
            this.Sum(cur.getHole5(), level + 1);
            this.Sum(cur.getHole6(), level + 1);
            return this.sum;
        }
    }

    public int[] AssessStep(TreeNode root, Board board) {
        int[] MasAssess = new int[6];
        int[][] field = board.getBoard();       //Зачем копия массива?
        this.sum = 0;
        if(field[0][root.getHole1().getHole()-1] != 0) {
            MasAssess[0] = this.Sum(root.getHole1(), 1);
        } else {
            MasAssess[0] = -1;
        }

        this.sum = 0;
        if(field[0][root.getHole2().getHole()-1] != 0) {
            MasAssess[1] = this.Sum(root.getHole2(), 1);
        } else {
            MasAssess[1] = -1;
        }

        this.sum = 0;
        if(field[0][root.getHole3().getHole()-1] != 0) {
            MasAssess[2] = this.Sum(root.getHole3(), 1);
        } else {
            MasAssess[2] = -1;
        }

        this.sum = 0;
        if(field[0][root.getHole4().getHole()-1] != 0) {
            MasAssess[3] = this.Sum(root.getHole4(), 1);
        } else {
            MasAssess[3] = -1;
        }

        this.sum = 0;
        if(field[0][root.getHole5().getHole()-1] != 0) {
            MasAssess[4] = this.Sum(root.getHole5(), 1);
        } else {
            MasAssess[4] = -1;
        }

        this.sum = 0;
        if(field[0][root.getHole6().getHole()-1] != 0) {
            MasAssess[5] = this.Sum(root.getHole6(), 1);
        } else {
            MasAssess[5] = -1;
        }
        return MasAssess;
    }
}

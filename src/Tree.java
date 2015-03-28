
/**
 * Created by 1 on 18.03.2015.
 */
public class Tree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public Tree(int step) {    //create tree
        this.root = new TreeNode(step, 0);  //0-level
    }

    public void Delete() {

    }

    public int AssessVertex(int step, boolean player, /*менять?*/Integer mark, int[][] dop, Boolean gear) {
        gear = false;
        int index = step - 1;
        int pl = player ? 1 : 0;  //для удобства использования в дальнейшем
        int sum = dop[pl][index];
        if (sum == 0)
            return 0;
        dop[pl][index] = 0;
        boolean flag = false;        //Перешёл ход на поле противника или нет
        boolean end = false;
        if (pl == 0) {
            index--;
            while (sum != 0) {
                flag = false;
                if (index >= 0) {
                    sum--;
                    dop[0][index]++;
                    index--;
                } else {
                    sum--;
                    end = true;
                    mark++;
                    break;
                }
            }
            if (sum != 0) {
                index = 0;
                while (sum != 0) {
                    if (index < 6) {
                        flag = true;
                        sum--;
                        dop[1][index]++;
                        index++;
                    } else {
                        sum--;
                        break;
                    }
                }
            }
            if (flag == false)        //Ход остался на своём поле
            {
                if (end == false) {
                    //if ((dop[0][index+1]!=0)&&(dop[0][index+1]!=1))
                    if (dop[0][index + 1] > 1) {
                        AssessVertex(index + 2, false, mark, dop, gear);
                    } else {
                        mark += dop[1][index + 1];
                        dop[1][index + 1] = 0;
                        return mark;
                    }
                } else
                    gear = true;        //Последний камень попал в манкалу, значит игрок не передаёт ход ,снова ходит из любой своей вершины
            } else {
                mark--;        //Если ход перешёл к противнику вычитаем 1 из оценки, т.к.выгодне,если ход останется на поле компьютера
            }
        } else {
            index++;
            while (sum != 0)        //Ход на поле игрока
            {
                flag = false;
                if (index < 6) {
                    sum--;
                    dop[1][index]++;
                    index++;
                } else {
                    sum--;
                    end = true;
                    break;
                }
            }
            if (sum != 0) {
                index = 5;
                while (sum != 0)    //Ход на поле компьютера
                {
                    if (index >= 0) {
                        flag = true;        //Ход перешёл на поле противника
                        sum--;
                        dop[0][index]++;
                        index--;
                    } else {
                        sum--;
                        break;
                    }
                }
            }
            if (flag == false)        //Ход остался на своём поле
            {
                if (end == false) {
                    //if ((dop[1][index-1]!=0)&&(dop[1][index-1]!=1))
                    if (dop[1][index - 1] > 1) {
                        AssessVertex(index, true, mark, dop, gear);
                    } else {
                        dop[0][index - 1] = 0;
                        return mark;
                    }
                }
                else
                    gear = true;
            }
        }
        return mark;
    }

    public void AssessTree(TreeNode cur, boolean player, int[][] mas, int level, Board board) {
        if (level > 5) {
            return;
        }
        int mark = 0;
        boolean gear = false;        //Индикатор закончился ход на манкале или нет
        int dop1[][] = board.getBoard();
        int dop2[][] = dop1;  //для инициализации приравняла null
        if (level != 0) {
            if ((player == false) && (dop1[0][cur.getNiche() - 1] == 0))            //Не оцениваем пустую нишу
                return;
            cur.setMark(AssessVertex(cur.getNiche(), player, mark, dop1, gear));
        }
        if (gear != true)    //Если последний камень хода не попал в манкалу, то ход передаётся противнику
        {
            player = !player;
        }

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop2[i][j] = dop1[i][j];
        //dop2 = dop1.clone();

        if ((cur.getNiche1() != null) && (dop1[0][cur.getNiche1().getNiche() - 1] != 0)) {
            mark = 0;
            AssessTree(cur.getNiche1(), player, dop1, level + 1, board);
        }
        mark = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];
        //dop1 = dop2.clone();
        if ((cur.getNiche2() != null) && (dop1[0][cur.getNiche2().getNiche() - 1] != 0)) {
            AssessTree(cur.getNiche2(), player, dop1, level + 1, board);
        }
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];

        mark = 0;
        if (cur.getNiche3() != null) {
            AssessTree(cur.getNiche3(), player, dop1, level + 1, board);
        }

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];

        mark = 0;
        if (cur.getNiche4() != null) {
            AssessTree(cur.getNiche4(), player, dop1, level + 1, board);
        }
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];
        mark = 0;
        if (cur.getNiche5() != null) {
            AssessTree(cur.getNiche5(), player, dop1, level + 1, board);
        }
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];
        mark = 0;
        if (cur.getNiche6() != null) {
            AssessTree(cur.getNiche6(), player, dop1, level + 1, board);
        }
        return;
    }

    int Sum(TreeNode cur, Integer sum, int level) {
        if (level > 4) {
            return sum;
        }
        sum += cur.getMark();            //Сумма всех камней, которые возможно получить при совершении хода
        Sum(cur.getNiche1(), sum, level + 1);
        Sum(cur.getNiche2(), sum, level + 1);
        Sum(cur.getNiche3(), sum, level + 1);
        Sum(cur.getNiche4(), sum, level + 1);
        Sum(cur.getNiche5(), sum, level + 1);
        Sum(cur.getNiche6(), sum, level + 1);
        return sum;
    }

    int[] AssessStep(TreeNode root, Board board)    //Оценка каждого хода
    {
        int[] MasAssess = new int[6];        //Массив оценок ходов
        int[][] field = board.getBoard();
        int sum = 0;
        if (field[0][root.getNiche1().getNiche() - 1] != 0)
            MasAssess[0] = Sum(root.getNiche1(), sum, 1);
        else
            MasAssess[0] = 0;
        sum = 0;
        if (field[0][root.getNiche2().getNiche() - 1] != 0)
            MasAssess[1] = Sum(root.getNiche2(), sum, 1);
        else
            MasAssess[1] = 0;
        sum = 0;
        if (field[0][root.getNiche3().getNiche() - 1] != 0)
            MasAssess[2] = Sum(root.getNiche3(), sum, 1);
        else
            MasAssess[2] = 0;
        sum = 0;
        if (field[0][root.getNiche4().getNiche() - 1] != 0)
            MasAssess[3] = Sum(root.getNiche4(), sum, 1);
        else
            MasAssess[3] = 0;
        sum = 0;
        if (field[0][root.getNiche5().getNiche() - 1] != 0)
            MasAssess[4] = Sum(root.getNiche5(), sum, 1);
        else
            MasAssess[4] = 0;
        sum = 0;
        if (field[0][root.getNiche6().getNiche() - 1] != 0)
            MasAssess[5] = Sum(root.getNiche6(), sum, 1);
        else
            MasAssess[5] = 0;
        return MasAssess;
    }
}

class TreeNode {
    private int niche;
    private int mark = 0;
    private TreeNode niche1 = null, niche2 = null, niche3 = null, niche4 = null, niche5 = null, niche6 = null;

    public TreeNode(int step, int level) {
        this.niche = step;
        if (level <= 5) {
            this.niche1 = new TreeNode(1, level + 1);
            this.niche2 = new TreeNode(2, level + 1);
            this.niche3 = new TreeNode(3, level + 1);
            this.niche4 = new TreeNode(4, level + 1);
            this.niche5 = new TreeNode(5, level + 1);
            this.niche6 = new TreeNode(6, level + 1);
        }
    }

    public int getNiche() {
        return niche;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public TreeNode getNiche1() {
        return niche1;
    }

    public TreeNode getNiche2() {
        return niche2;
    }

    public TreeNode getNiche3() {
        return niche3;
    }

    public TreeNode getNiche4() {
        return niche4;
    }

    public TreeNode getNiche5() {
        return niche5;
    }

    public TreeNode getNiche6() {
        return niche6;
    }
}


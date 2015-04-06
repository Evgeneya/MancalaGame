import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 1 on 18.03.2015.
 */
public class Tree {

    private TreeNode root;



    private int mark;
    private boolean gear;
    private int sum;
    public TreeNode getRoot() {
        return root;
    }

    public Tree(int step) {    //create tree
        this.root = new TreeNode(step, 0);  //0-level
    }

    public int AssessVertex(int step, boolean player, int[][] dop) {
        gear = false;
        int index = step - 1;
        int pl = player ? 1 : 0;  //для удобства использования в дальнейшем
        int sum = dop[pl][index];
        if (sum == 0)
            return 0;
        dop[pl][index] = 0;
        boolean flag = false;        //Перешёл ход на поле противника или нет
        boolean end = false;
        if (pl == 0)
        {
            index--;
            while (sum != 0)
            {
                while (sum != 0)
                {
                    flag = false;
                    if (index >= 0)
                    {
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
                if (sum != 0)
                {
                    index = 0;
                    while (sum != 0)
                    {
                        if (index < 6)
                        {
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
            }

            if (flag == false)        //Ход остался на своём поле
            {
                if (end == false)
                {
                    if (dop[0][index + 1] > 1)
                    {
                        AssessVertex(index + 2, false, dop);
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
                while (sum != 0)    //Ход на поле компьютера
                {
                    flag = false;
                    if (index < 6)
                    {
                        sum--;
                        dop[1][index]++;
                        index++;
                    } else {
                        sum--;
                        end = true;
                        break;
                    }
                }
                if (sum != 0)
                {
                    index = 5;
                    while (sum != 0)    //Ход на поле компьютера
                    {
                        if (index >= 0)
                        {
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
            }
            if (flag == false)        //Ход остался на своём поле
            {
                if (end == false) {
                    //if ((dop[1][index-1]!=0)&&(dop[1][index-1]!=1))
                    if (dop[1][index - 1] > 1) {
                        AssessVertex(index, true, dop);
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
        mark = 0;
        boolean gear = false;        //Индикатор закончился ход на манкале или нет

        int dopBoard[][] = board.getBoard();
        int dop1[][] = new int[2][6];
        int dop2[][] = new int[2][6];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dopBoard[i][j];

        if (level != 0) {
            if ((player == false) && (dop1[0][cur.getHole() - 1] == 0))            //Не оцениваем пустую нишу
                return;
            cur.setMark(AssessVertex(cur.getHole(), player, dop1));
        }
        if (gear != true)    //Если последний камень хода не попал в манкалу, то ход передаётся противнику
        {
            player = !player;
        }

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop2[i][j] = dop1[i][j];

        if ((cur.getHole1() != null) && (dop1[0][cur.getHole1().getHole() - 1] != 0)) {
            mark = 0;
            AssessTree(cur.getHole1(), player, dop1, level + 1, board);
        }
        mark = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];

        if ((cur.getHole2() != null) && (dop1[0][cur.getHole2().getHole() - 1] != 0)) {
            AssessTree(cur.getHole2(), player, dop1, level + 1, board);
        }
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];

        mark = 0;
        if (cur.getHole3() != null) {
            AssessTree(cur.getHole3(), player, dop1, level + 1, board);
        }

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];

        mark = 0;
        if (cur.getHole4() != null) {
            AssessTree(cur.getHole4(), player, dop1, level + 1, board);
        }
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];
        mark = 0;
        if (cur.getHole5() != null) {
            AssessTree(cur.getHole5(), player, dop1, level + 1, board);
        }
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++)
                dop1[i][j] = dop2[i][j];
        mark = 0;
        if (cur.getHole6() != null) {
            AssessTree(cur.getHole6(), player, dop1, level + 1, board);
        }
        return;
    }

    public int Sum(TreeNode cur, int level) {
        if (level > 4) {
            return sum;
        }
        sum += cur.getMark();            //Сумма всех камней, которые возможно получить при совершении хода
        Sum(cur.getHole1(), level + 1);
        Sum(cur.getHole2(), level + 1);
        Sum(cur.getHole3(), level + 1);
        Sum(cur.getHole4(), level + 1);
        Sum(cur.getHole5(), level + 1);
        Sum(cur.getHole6(), level + 1);
        return sum;
    }

    public int[] AssessStep(TreeNode root, Board board)    //Оценка каждого хода
    {
        int[] MasAssess = new int[6];        //Массив оценок ходов
        int[][] field = board.getBoard();
        sum = 0;
        if (field[0][root.getHole1().getHole() - 1] != 0)
            MasAssess[0] = Sum(root.getHole1(), 1);
        else
            MasAssess[0] = 0;
        sum = 0;
        if (field[0][root.getHole2().getHole() - 1] != 0)
            MasAssess[1] = Sum(root.getHole2(), 1);
        else
            MasAssess[1] = 0;
        sum = 0;
        if (field[0][root.getHole3().getHole() - 1] != 0)
            MasAssess[2] = Sum(root.getHole3(), 1);
        else
            MasAssess[2] = 0;
        sum = 0;
        if (field[0][root.getHole4().getHole() - 1] != 0)
            MasAssess[3] = Sum(root.getHole4(), 1);
        else
            MasAssess[3] = 0;
        sum = 0;
        if (field[0][root.getHole5().getHole() - 1] != 0)
            MasAssess[4] = Sum(root.getHole5(), 1);
        else
            MasAssess[4] = 0;
        sum = 0;
        if (field[0][root.getHole6().getHole() - 1] != 0)
            MasAssess[5] = Sum(root.getHole6(), 1);
        else
            MasAssess[5] = 0;
        return MasAssess;
    }
    int summa = 7;  // для тестирования числа листьев в дереве

    public int getSumma() {
        return summa;
    }

    public void printTree(TreeNode cur, int level) throws IOException {
        if (level > 2) return;  //закомментить при вычислении числа листьев
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cur.printNode(level);
        if (cur.getHole1() != null)
        {
            cur.getHole1().printNode(level+1);
            cur.getHole2().printNode(level+1);
            cur.getHole3().printNode(level+1);
            cur.getHole4().printNode(level+1);
            cur.getHole5().printNode(level+1);
            cur.getHole6().printNode(level+1);

            System.out.print("Введи строку для продолжения:");
            reader.readLine();
            System.out.println();

            printTree(cur.getHole1(), level+1);
            printTree(cur.getHole2(), level+1);
            printTree(cur.getHole3(), level+1);
            printTree(cur.getHole4(), level+1);
            printTree(cur.getHole5(), level+1);
            printTree(cur.getHole6(), level+1);
            summa+=36;
        }
    }
}

class TreeNode {
    private int hole;
    private int mark = 0;
    private TreeNode hole1 = null, hole2 = null, hole3 = null, hole4 = null, hole5 = null, hole6 = null;

    public TreeNode(int step, int level) {
        this.hole = step;
        if (level <= 5) {
            this.hole1 = new TreeNode(1, level + 1);
            this.hole2 = new TreeNode(2, level + 1);
            this.hole3 = new TreeNode(3, level + 1);
            this.hole4 = new TreeNode(4, level + 1);
            this.hole5 = new TreeNode(5, level + 1);
            this.hole6 = new TreeNode(6, level + 1);
        }
    }

    public int getHole() {
        return hole;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public TreeNode getHole1() {
        return hole1;
    }

    public TreeNode getHole2() {
        return hole2;
    }

    public TreeNode getHole3() {
        return hole3;
    }

    public TreeNode getHole4() {
        return hole4;
    }

    public TreeNode getHole5() {
        return hole5;
    }

    public TreeNode getHole6() {
        return hole6;
    }

    public void printNode(int level){
        System.out.println(this + "level = " + level);
        System.out.println("Children: level " + (level+1));
        if (this.getHole1() != null) {
            System.out.println(this.getHole1().toString() + "; " +
                    this.getHole2() + "; " +
                    this.getHole3() + "; " +
                    this.getHole4() + "; " +
                    this.getHole5()+ this.getHole6());
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "hole=" + hole +
                ", mark=" + mark +
                '}';
    }
}


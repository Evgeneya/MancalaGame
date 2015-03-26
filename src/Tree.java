
/**
 * Created by 1 on 18.03.2015.
 */
public class Tree{

    public static void main(String[] args) {
        Tree tree = new Tree(1);  //передаем шаг игрока
        //System.out.println(tree.root.getNiche6().getNiche3().getNiche1().getNiche1().getNiche1().getNiche1().getNiche());
        //System.out.println(tree.root.getNiche());

        int mas[][] = null;
        Board board = new Board();
        tree.AssessTree(tree.root, false, mas, 0, board);
        System.out.println(tree.root.getMark());
    }

    private TreeNode root;

    public Tree(int step){    //create tree
        this.root = new TreeNode(step, 0);  //0-level
    }

    public void Delete(){

    }

    public int AssessVertex(int step, boolean player, /*менять?*/Integer mark, int[][] dop, Boolean gear){
        gear = false;
        int index = step - 1;
        int pl = player ? 1 : 0;  //для удобства использования в дальнейшем
        int sum = dop[pl][index];
        if (sum == 0)
            return 0;
        dop[pl][index]=0;
        boolean flag = false;		//Перешёл ход на поле противника или нет
        boolean end = false;
        if (pl == 0)
        {
            index--;
            while (sum!=0)
            {
                flag = false;
                if (index>=0)
                {
                    sum--;
                    dop[0][index]++;
                    index--;
                }
                else
                {
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
                    }
                    else
                    {
                        sum--;
                        break;
                    }
                }
            }
            if (flag == false)		//Ход остался на своём поле
            {
                if (end == false)
                {
                    //if ((dop[0][index+1]!=0)&&(dop[0][index+1]!=1))
                    if (dop[0][index+1] > 1)
                    {
                        AssessVertex(index+2, false, mark, dop, gear);
                    }
                    else
                    {
                        mark += dop[1][index+1];
                        dop[1][index+1] = 0;
                        return mark;
                    }
                }
                else
                    gear = true;		//Последний камень попал в манкалу, значит игрок не передаёт ход ,снова ходит из любой своей вершины
            }
            else
            {
                mark--;		//Если ход перешёл к противнику вычитаем 1 из оценки, т.к.выгодне,если ход останется на поле компьютера
            }
        }
        else
        {
            index++;
            while (sum != 0)		//Ход на поле игрока
            {
                flag = false;
                if (index < 6)
                {
                    sum--;
                    dop[1][index]++;
                    index++;
                }
                else
                {
                    sum--;
                    end = true;
                    break;
                }
            }
            if(sum != 0)
            {
                index = 5;
                while (sum != 0)	//Ход на поле компьютера
                {
                    if (index>=0)
                    {
                        flag = true;		//Ход перешёл на поле противника
                        sum--;
                        dop[0][index]++;
                        index--;
                    }
                    else
                    {
                        sum--;
                        break;
                    }
                }
            }
            if (flag == false)		//Ход остался на своём поле
            {
                if (end == false)
                {
                    //if ((dop[1][index-1]!=0)&&(dop[1][index-1]!=1))
                    if (dop[1][index-1] > 1)
                    {
                        AssessVertex(index, true, mark, dop, gear);
                    }
                    else
                    {
                        dop[0][index-1] = 0;
                        return mark;
                    }
                }
                else
                    gear = true;
            }
        }
        return mark;
    }

    public void AssessTree (TreeNode cur, boolean player, int[][] mas, int level, Board board){
        if (level>5)
        {
            return;
        }
        int mark = 0;
        boolean gear = false;		//Индикатор закончился ход на манкале или нет
        int dop1[][] = board.getBoard();
        int dop2 [][] = dop1;  //для инициализации приравняла null
        if (level != 0)
        {
            if ((player == false)&&(dop1[0][cur.getNiche() - 1] == 0))			//Не оцениваем пустую нишу
            return;
            cur.setMark(AssessVertex(cur.getNiche(), player, mark, dop1, gear));
        }
        if (gear != true)	//Если последний камень хода не попал в манкалу, то ход передаётся противнику
        {
            player = !player;
        }

        for (int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++)
                dop2[i][j] = dop1[i][j];
        //dop2 = dop1.clone();

        if ((cur.getNiche1() != null) && (dop1[0][cur.getNiche1().getNiche() - 1] != 0))
        {
            mark = 0;
            AssessTree(cur.getNiche1(), player, dop1, level+1, board);
        }
        mark=0;
        for (int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++)
                dop1[i][j]=dop2[i][j];
        //dop1 = dop2.clone();
        if ((cur.getNiche2() != null)&&(dop1[0][cur.getNiche2().getNiche() - 1] != 0))
        {
            AssessTree(cur.getNiche2(), player, dop1,level+1, board);
        }
        for (int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++)
                dop1[i][j]=dop2[i][j];

        mark = 0;
        if (cur.getNiche3() != null)
        {
            AssessTree(cur.getNiche3(),player, dop1, level+1, board);
        }

        for (int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++)
                dop1[i][j]=dop2[i][j];

        mark = 0;
        if (cur.getNiche4() != null)
        {
            AssessTree(cur.getNiche4(), player, dop1, level+1, board);
        }
        for (int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++)
                dop1[i][j]=dop2[i][j];
        mark = 0;
        if (cur.getNiche5() != null)
        {
            AssessTree(cur.getNiche5(), player, dop1, level+1, board);
        }
        for (int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++)
                dop1[i][j]=dop2[i][j];
        mark = 0;
        if (cur.getNiche6() != null)
        {
            AssessTree(cur.getNiche6(), player, dop1, level+1, board);
        }
        return;
    }

    public byte BestStep(){
        return 0;
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

    public void setNiche(int niche) {
        this.niche = niche;
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

    public void setNiche1(TreeNode niche1) {
        this.niche1 = niche1;
    }

    public TreeNode getNiche2() {
        return niche2;
    }

    public void setNiche2(TreeNode niche2) {
        this.niche2 = niche2;
    }

    public TreeNode getNiche3() {
        return niche3;
    }

    public void setNiche3(TreeNode niche3) {
        this.niche3 = niche3;
    }

    public TreeNode getNiche4() {
        return niche4;
    }

    public void setNiche4(TreeNode niche4) {
        this.niche4 = niche4;
    }

    public TreeNode getNiche5() {
        return niche5;
    }

    public void setNiche5(TreeNode niche5) {
        this.niche5 = niche5;
    }

    public TreeNode getNiche6() {
        return niche6;
    }

    public void setNiche6(TreeNode niche6) {
        this.niche6 = niche6;
    }
}


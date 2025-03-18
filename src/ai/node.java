package ai;

public class node {     //SE VA IMPLEMANTA ALGORITMULA DJKRSTA PT A FACE POSIBILE URM PERS
    node parinte;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;
    public node(int col, int row){
        this.col = col;
        this.row = row;
    }
}

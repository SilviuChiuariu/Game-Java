package ai;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {       //SE VA IMPLEMANTA ALGORITMULA DJKRSTA PT A FACE POSIBILE URM PERS
    GamePanel gp;
    node[][] node;        //matrice de noduri
    ArrayList<node> openList = new ArrayList<>();      //lista de noduri
    public ArrayList<node> pathList = new ArrayList<>();       //lista de noduri
    node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    public PathFinder(GamePanel gp){      //CONSTRUCTOR DE INITIALIZARE
        this.gp = gp;
        instantiateNodes();
    }
    public void instantiateNodes(){
        //CREEM NODURI PENTRU TOATE TILE-URILE DIN MAPA
        node = new node[gp.maxWorldCol][gp.maxWorldRow];
        int col=0;
        int row=0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] = new node(col,row);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void resetNodes(){
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            //pt a reseta : open checked si solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        //Resetam
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNode(int startCol, int startRow, int goalCol, int goalRow, Entity entity){
        resetNodes();
        //SETAM START AND GOAL node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);     //se adauga nodul curent in openList

        int row = 0;
        int col = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            //SET SOLID NODES
            //CHECK TILES
            int tileNum = gp.tileM.mapTileNr[gp.currentMap][col][row];    //verificam pe layerul 1 care dintre tileuri sunt sau nu soilide
            int tileNum3 = gp.tileM.mapTileNr3[gp.currentMap][col][row];  //verificam pe layerul 3
            int tileNum5 = gp.tileM.mapTileNr5[gp.currentMap][col][row];  //verificam pe layerul 5
            int tileNum6 = gp.tileM.mapTileNr6[gp.currentMap][col][row];  //verificam pe layerul 6
            if (gp.tileM.tile[tileNum].colision==true || gp.tileM.tile[tileNum3].colision==true || gp.tileM.tile[tileNum5].colision==true || gp.tileM.tile[tileNum6].colision==true ) {
                node[col][row].solid = true;                              //adica care are sau nu coliziune facem asta pt ca nps sa le poata evita
            }
            //SET COST
            getCost(node[col][row]);          //metoda pt a afla costul
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void getCost(node node){
        //G cost
        int xDistance = Math.abs(node.col - startNode.col);      //se calculeaza distanta de la node la startNode
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        //H Cost
        xDistance = Math.abs(node.col - goalNode.col);           //se calculeaza distanta de la node la goalNode
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        //F Cost
        node.fCost = node.gCost + node.hCost;       //costul final = suma distantelor
    }

    public boolean search(){
        while(goalReached == false && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            //check the current Node
            currentNode.checked = true;
            openList.remove(currentNode);   //nodurile verificate sunt eleiminate din lista
            //open the Up node
            if(row - 1 >= 0){
                openNode(node[col][row-1]);
            }
            //open the left node
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            //open the down node
            if(row + 1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }
            //open the right node
            if(col + 1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }
            //Find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            for(int i=0; i<openList.size(); i++){
                //verificam daca costul F al nodului ii cel mai bun
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //IF F cost is equal, check the g cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            //cand se goleste open list,inchdem loopul
            if(openList.size()==0){
                break;
            }
            //actualizam current nodul cu bestNodeIndex
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                verificaCalea();
            }
            step++;
        }
        return goalReached;
    }
    public void openNode(node node){
        if(node.open == false && node.checked == false && node.solid == false){
            node.open = true;
            node.parinte = currentNode;
            openList.add(node);
        }
    }
    public void verificaCalea(){
        node current = goalNode;

        while(current != startNode){
            pathList.add(0,current);     //cu aceasta lista npc si monstri te pot urmari
            current = current.parinte;
        }
    }
}

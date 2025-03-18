package tile;

import  main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int [][][]mapTileNr;     //pt layer1
    public int [][][]mapTileNr2;     //pt layer2
    public int [][][]mapTileNr3;     //pt layer3
    public int [][][]mapTileNr5;     //pt layer5
    public int [][][]mapTileNr6;     //pt layer6
    boolean drawPath = false;       //pt a arata traseul mobilor
    public TileManager(GamePanel gp){          //un fel de constructor de initializare
        this.gp=gp;
        tile=new Tile[300];
        mapTileNr=new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];       //map pt layer1
        mapTileNr2=new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];      //map pt layer2
        mapTileNr3=new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];      //map pt layer3
        mapTileNr5=new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];      //map pt layer5
        mapTileNr6=new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];      //map pt layer6

        getTileImage();   //apelam metoda
        //HARTA1
        loadMap("/maps/maptest3.txt",mapTileNr3,0);        //apelam pt layer 3
        loadMap("/maps/maptest2.txt",mapTileNr2,0);        //apelam pt layer 2
        loadMap("/maps/maptest1.txt",mapTileNr,0);         //apelam pt layer 1
        loadMap("/maps/maptest5.txt",mapTileNr5,0);        //apelam pt layer 5
        loadMap("/maps/maptest6.txt",mapTileNr6,0);        //apelam pt layer 6
        //HARTA2
        loadMap("/maps/Harta2Layer1.txt",mapTileNr,1);     //apelam pt layer 1
        loadMap("/maps/Harta2Layer2.txt",mapTileNr5,1);    //apelam pt layer 5
        //HARTA3
        loadMap("/maps/Harta3Layer1.txt",mapTileNr,2);     //apelam pt layer 1
        loadMap("/maps/Harta3Layer2.txt",mapTileNr5,2);     //apelam pt layer 5

    }
    public void getTileImage(){
        setUp(0,"layer1_map1/goala",false);
        setUp(1,"layer1_map1/Water_2",true);
        setUp(2,"layer1_map1/Grass_1",false);
        setUp(3,"layer1_map1/Fan",false);
        setUp(4,"layer1_map1/piatra_kingdom rush",false);
        setUp(5,"layer1_map1/drum_bej_mijloc",false);
        setUp(6,"layer1_map1/margine_stanga",false);
        setUp(7,"layer1_map1/margine_dreapta",false);
        setUp(8,"layer1_map1/margine_sus",false);
        setUp(9,"layer1_map1/Latura_jos",false);
        setUp(10,"layer1_map1/mal",false);
        setUp(11,"layer1_map1/iarba_verde_trista",false);
        setUp(12,"layer1_map1/colt_stanga_sus",false);
        setUp(13,"layer1_map1/colt_dreapta_sus",false);
        setUp(14,"layer1_map1/colt_dreapta_jos",false);
        setUp(15,"layer1_map1/colt_stanga_jos",false);
        //PT LAYERUL 2:
        setUp(16,"layer2_map1/umb_varf",false);
        setUp(17,"layer2_map1/umb_patrat_linii",false);
        setUp(18,"layer2_map1/umb_patrat",false);
        //PT LAYERUL 3:
        setUp(19,"layer3_map1/Tree_varf_stanga",false);
        setUp(20,"layer3_map1/Tree_varf_dreapta",false);
        setUp(21,"layer3_map1/Tree_sus_stanga",false);
        setUp(22,"layer3_map1/Tree_sus_dreapta",false);
        setUp(23,"layer3_map1/Tree_jos_stanga",false);
        setUp(24,"layer3_map1/Tree_jos_dreapta",false);
        setUp(25,"layer3_map1/Tree_baza_stanga",false);
        setUp(26,"layer3_map1/Tree_baza_dreapta",true);
        setUp(27,"layer3_map1/stalp_parteSus",false);
        setUp(28,"layer3_map1/stalp_parteJos",true);
        setUp(29,"layer3_map1/gard de piatra",true);
        setUp(30,"layer3_map1/usa_inchisa",true);
        setUp(31,"layer3_map1/fantana sacara",true);
        setUp(32,"layer3_map1/butoi",true);
        setUp(33,"layer3_map1/mormant_vertical_parteSus",false);
        setUp(34,"layer3_map1/mormant_vertical_parteJos",true);
        setUp(35,"layer3_map1/banca2",true);
        setUp(36,"layer3_map1/statue_femeie_sus",false);
        setUp(37,"layer3_map1/statue_femeie_jos",true);
        setUp(38,"layer3_map1/cruce",true);
        setUp(39,"layer3_map1/banca_1",true);
        setUp(40,"layer3_map1/mormant_RIP",true);
        setUp(41,"layer3_map1/mormant_american",true);
        setUp(42,"layer3_map1/obuzRusesc",true);
        setUp(43,"layer3_map1/chest open",true);
        setUp(44,"layer3_map1/chest",true);
        setUp(45,"layer3_map1/stalp-spart_parteSus",false);
        setUp(46,"layer3_map1/stalp-spart_parteJos",true);
        //PT LAYER5+ceva 6 mod:
        setUp(47,"layer5_map1/zid1",true);
        setUp(48,"layer5_map1/zid2",true);
        setUp(49,"layer5_map1/zid3",true);
        setUp(50,"layer5_map1/zid4",true);
        setUp(51,"layer5_map1/zid5",false);
        setUp(52,"layer5_map1/roca st sus",true);
        setUp(53,"layer5_map1/roca dr sus",true);
        setUp(54,"layer5_map1/roca st jos",true);
        setUp(55,"layer5_map1/roca dr jos",true);
        setUp(56,"layer5_map1/casa sus1",true);
        setUp(57,"layer5_map1/casa sus2",true);
        setUp(58,"layer5_map1/casa sus3",true);
        setUp(59,"layer5_map1/casa sus4",true);
        setUp(60,"layer5_map1/casa jos1",true);
        setUp(61,"layer5_map1/casa jos2",true);
        setUp(62,"layer5_map1/casa jos3",true);
        setUp(63,"layer5_map1/casa jos4",true);
        setUp(64,"layer5_map1/cotet1 rosu1",false);
        setUp(65,"layer5_map1/cotet1 rosu2",false);
        setUp(66,"layer5_map1/cotet1 rosu3",false);
        setUp(67,"layer5_map1/cotet2 rosu1",true);
        setUp(68,"layer5_map1/cotet2 rosu2",true);
        setUp(69,"layer5_map1/cotet2 rosu3",true);
        setUp(70,"layer5_map1/cotet3 rosu1",true);
        setUp(71,"layer5_map1/cotet3 rosu2",true);
        setUp(72,"layer5_map1/cotet3 rosu3",true);
        setUp(73,"layer5_map1/cotet4 rosu1",true);
        setUp(74,"layer5_map1/cotet4 rosu2",true);
        setUp(75,"layer5_map1/cotet4 rosu3",true);
        setUp(76,"layer5_map1/cotet1 alb1",false);
        setUp(77,"layer5_map1/cotet1 alb2",false);
        setUp(78,"layer5_map1/cotet1 alb3",false);
        setUp(79,"layer5_map1/cotet2 alb1",true);
        setUp(80,"layer5_map1/cotet2 alb2",true);
        setUp(81,"layer5_map1/cotet2 alb3",true);
        setUp(82,"layer5_map1/cotet3 alb1",true);
        setUp(83,"layer5_map1/cotet3 alb2",true);
        setUp(84,"layer5_map1/cotet3 alb3",true);
        setUp(85,"layer5_map1/cotet4 alb1",true);
        setUp(86,"layer5_map1/cotet4 alb2",true);
        setUp(87,"layer5_map1/cotet4 alb3",true);
        setUp(88,"layer5_map1/daram1cu1",true);
        setUp(89,"layer5_map1/daram1cu2",true);
        setUp(90,"layer5_map1/daram1cu3",true);
        setUp(91,"layer5_map1/daram2cu1",true);
        setUp(92,"layer5_map1/daram2cu2",true);
        setUp(93,"layer5_map1/daram2cu3",true);
        setUp(94,"layer5_map1/cotet1 vio1",false);
        setUp(95,"layer5_map1/cotet1 vio2",false);
        setUp(96,"layer5_map1/cotet1 vio3",false);
        setUp(97,"layer5_map1/cotet2 vio1",false);
        setUp(98,"layer5_map1/cotet2 vio2",false);
        setUp(99,"layer5_map1/cotet2 vio3",false);
        setUp(100,"layer5_map1/cotet3 vio1",true);
        setUp(101,"layer5_map1/cotet3 vio2",true);
        setUp(102,"layer5_map1/cotet3 vio3",true);
        setUp(103,"layer5_map1/cotet4 vio1",true);
        setUp(104,"layer5_map1/cotet4 vio2",true);
        setUp(105,"layer5_map1/cotet4 vio3",true);
        setUp(106,"layer5_map1/varf clopot dr",false);
        setUp(107,"layer5_map1/varf clopot st",false);
        setUp(108,"layer5_map1/varf acp bis st",false);
        setUp(109,"layer5_map1/varf acp bis mj",false);
        setUp(110,"layer5_map1/varf acp bis dr",false);
        setUp(111,"layer5_map1/l3 1bis",false);
        setUp(112,"layer5_map1/l3 2bis",false);
        setUp(113,"layer5_map1/l3 3bis",false);
        setUp(114,"layer5_map1/l3 4bis",false);
        setUp(115,"layer5_map1/l4 1bis",true);
        setUp(116,"layer5_map1/l4 2bis",true);
        setUp(117,"layer5_map1/l4 3bis",true);
        setUp(118,"layer5_map1/l4 4bis",true);
        setUp(119,"layer5_map1/l5 1bis",true);
        setUp(120,"layer5_map1/l5 2bis",true);
        setUp(121,"layer5_map1/l5 3bis",true);
        setUp(122,"layer5_map1/l5 4bis",true);
        setUp(123,"layer5_map1/l6 1bis",true);
        setUp(124,"layer5_map1/l6 2bis",true);
        setUp(125,"layer5_map1/l6 3bis",true);
        setUp(126,"layer5_map1/l6 4bis",true);
        setUp(127,"layer5_map1/l7 1bis",false);
        setUp(128,"layer5_map1/l7 2bis",false);
        setUp(129,"layer5_map1/ts1",true);
        setUp(130,"layer5_map1/ts2",true);
        setUp(131,"layer5_map1/ts3",true);
        setUp(132,"layer5_map1/ts4",true);
        setUp(133,"layer5_map1/ts5",true);
        setUp(134,"layer5_map1/ts6",true);
        setUp(135,"layer5_map1/ts7",true);
        setUp(136,"layer5_map1/ts8",true);
        setUp(137,"layer5_map1/ts9",true);
        setUp(138,"layer5_map1/ts10",true);
        setUp(139,"layer5_map1/ts11",true);
        setUp(140,"layer5_map1/ts12",true);
        setUp(141,"layer5_map1/ts13",true);
        setUp(142,"layer5_map1/ts14",true);
        setUp(143,"layer5_map1/ts15",true);
        setUp(144,"layer5_map1/ts16",true);
        setUp(145,"layer5_map1/ts17",true);
        setUp(146,"layer5_map1/ts18",true);
        setUp(147,"layer5_map1/ts19",true);
        setUp(148,"layer5_map1/ts20",true);
        setUp(149,"layer5_map1/td1",true);
        setUp(150,"layer5_map1/td2",true);
        setUp(151,"layer5_map1/td3",true);
        setUp(152,"layer5_map1/td4",true);
        setUp(153,"layer5_map1/td5",true);
        setUp(154,"layer5_map1/td6",true);
        setUp(155,"layer5_map1/td7",true);
        setUp(156,"layer5_map1/td8",true);
        setUp(157,"layer5_map1/td9",true);
        setUp(158,"layer5_map1/td10",true);
        setUp(159,"layer5_map1/td11",true);
        setUp(160,"layer5_map1/td12",true);
        setUp(161,"layer5_map1/td13",true);
        setUp(162,"layer5_map1/td14",true);
        setUp(163,"layer5_map1/td15",true);
        setUp(164,"layer5_map1/td16",true);
        setUp(165,"layer5_map1/td17",true);
        setUp(166,"layer5_map1/td18",true);
        setUp(167,"layer5_map1/td19",true);
        setUp(204,"layer5_map1/portal1",false);
        setUp(205,"layer5_map1/portal2",false);
        setUp(206,"layer5_map1/portal3",false);
        setUp(207,"layer5_map1/portal4",false);
        setUp(208,"layer5_map1/portal5",false);
        setUp(209,"layer5_map1/portal6",false);
        //PT LAYER 6:
        setUp(168,"layer6_map1/ac1",false);
        setUp(169,"layer6_map1/ac2",false);
        setUp(170,"layer6_map1/ac3",false);
        setUp(171,"layer6_map1/ac4",false);
        setUp(172,"layer6_map1/ac5",false);
        setUp(173,"layer6_map1/ac6",false);
        setUp(174,"layer6_map1/ac7",false);
        setUp(175,"layer6_map1/ac8",false);
        setUp(176,"layer6_map1/ac9",false);
        setUp(177,"layer6_map1/ac10",false);
        setUp(178,"layer6_map1/ac11",false);
        setUp(179,"layer6_map1/ac12",false);
        setUp(180,"layer6_map1/ac13",true);
        setUp(181,"layer6_map1/ac14",true);
        setUp(182,"layer6_map1/ac15",true);
        setUp(183,"layer6_map1/ac16",true);
        setUp(184,"layer6_map1/ac17",true);
        setUp(185,"layer6_map1/ac18",true);
        setUp(186,"layer6_map1/ac19",true);
        setUp(187,"layer6_map1/ac20",true);
        setUp(188,"layer6_map1/lat ac1",false);
        setUp(189,"layer6_map1/lat ac2",false);
        setUp(190,"layer6_map1/lat ac3",false);
        setUp(191,"layer6_map1/lat ac4",false);
        setUp(192,"layer6_map1/lat ac5",false);
        setUp(193,"layer6_map1/lat ac6",false);
        setUp(194,"layer6_map1/lat ac7",false);
        setUp(195,"layer6_map1/lat ac8",false);
        setUp(196,"layer6_map1/lat ac9",true);
        setUp(197,"layer6_map1/lat ac10",true);
        setUp(198,"layer6_map1/lat ac11",true);
        setUp(199,"layer6_map1/lat ac12",true);
        //PT LAYER1 NIV2
        setUp(200,"layer1_map2/darkGrs",false);
        setUp(201,"layer1_map2/mal2",false);
        setUp(202,"layer1_map2/malSup",false);
        setUp(203,"layer1_map2/malPietris",false);
        //PT LAYER2 NIV2
        setUp(210,"layer2_map2/Cps1",true);
        setUp(211,"layer2_map2/Cps2",true);
        setUp(212,"layer2_map2/Cps3",true);
        setUp(213,"layer2_map2/Cps4",true);
        setUp(214,"layer2_map2/Cps5",true);
        setUp(215,"layer2_map2/Cps6",true);
        setUp(216,"layer2_map2/trS1",true);
        setUp(217,"layer2_map2/trS2",true);
        setUp(218,"layer2_map2/Cpg7",true);
        setUp(219,"layer2_map2/Cpg8",true);
        setUp(220,"layer2_map2/Cpg9",true);
        setUp(221,"layer2_map2/Cpg4",true);
        setUp(222,"layer2_map2/Cpg5",true);
        setUp(223,"layer2_map2/Cpg6",true);
        setUp(224,"layer2_map2/Cpg1",true);
        setUp(225,"layer2_map2/Cpg2",true);
        setUp(226,"layer2_map2/Cpg3",true);
        setUp(227,"layer2_map2/TrG1",true);
        setUp(228,"layer2_map2/TrG2",true);
        setUp(229,"layer2_map2/TrG3",true);
        setUp(230,"layer2_map2/TrG4",true);
        setUp(231,"layer2_map2/TrG5",true);
        setUp(232,"layer2_map2/TrG6",true);
        setUp(233,"layer2_map2/roc",false);
        //Pt LAYER1 NIV3:
        setUp(238,"layer1_map3/piatra",false);
        setUp(239,"layer1_map3/apaVr",true);
        setUp(236,"layer1_map3/apaDr",true);
        setUp(235,"layer1_map3/apaSt",true);
        setUp(234,"layer1_map3/apaGol",true);
        setUp(237,"layer1_map3/apa",true);
        setUp(240,"layer1_map3/apaUmb",false);
        setUp(241,"layer1_map3/piatra alb",false);
        setUp(242,"layer1_map3/spike",true);
        setUp(243,"layer1_map3/spikeCob",false);
        setUp(244,"layer1_map3/piatraCpr",false);
        setUp(245,"layer1_map3/trepte",false);
        //Pt LAYER2 NIV3:
        setUp(246,"layer2_map3/piatraNgr",true);
        setUp(247,"layer2_map3/wall ap1",true);
        setUp(248,"layer2_map3/wall ap2",true);
        setUp(249,"layer2_map3/covor",false);
        setUp(250,"layer2_map3/colt jos st",true);
        setUp(251,"layer2_map3/colt jos dr",true);
        setUp(252,"layer2_map3/cpjos dr",true);
        setUp(253,"layer2_map3/zid st",true);
        setUp(254,"layer2_map3/zid dr",true);
        setUp(255,"layer2_map3/cpjos st",true);
        setUp(256,"layer2_map3/treapta covor",false);
        setUp(257,"layer2_map3/pod sus1",false);
        setUp(258,"layer2_map3/pod sus2",false);
        setUp(259,"layer2_map3/pod jos1",false);
        setUp(260,"layer2_map3/pod jos2",false);
        setUp(261,"layer2_map3/zid oriz st",true);
        setUp(262,"layer2_map3/zid oriz dr",true);
        setUp(263,"layer2_map3/cpsus st",true);
        setUp(264,"layer2_map3/colt sus st",true);
        setUp(265,"layer2_map3/colt sus dr",true);
        setUp(266,"layer2_map3/cpsus dr",true);
    }
    //UTILIZATA PT A APELA METODA DIN UTILITY
    public void setUp(int index , String imageName, boolean colision){
        UtilityTool uTool=new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].colision = colision;
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void loadMap(String file, int[][][]x, int hrt){
        try{
            InputStream is=getClass().getResourceAsStream(file);           //folosit pentru a importa text file
            BufferedReader br=new BufferedReader(new InputStreamReader(is));            //folosit pentru a citi contentul din text file

            int col=0;
            int row=0;

            while(col< gp.maxWorldCol && row < gp.maxWorldRow){
                String line =br.readLine();                        //citeste fiecare caracter de pe linie

                while(col<gp.maxWorldCol){
                    String []numbers=line.split(" ");        //creem un vector de stringuri in care salvam caracterele de pe prima linie
                    int num= Integer.parseInt(numbers[col]);       //trnsformam din string in int
                    x[hrt][col][row] = num;   //nu inteleg??
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void draw(Graphics2D g2,int [][][]x){
        int worldCol=0;
        int worldRow=0;
        while(worldCol< gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum=x[gp.currentMap][worldCol][worldRow];                //extrage numerele din matrice de int-uri
            int worldX=worldCol * gp.tileSize;                //lung hartii pe axa ox
            int worldY=worldRow * gp.tileSize;                //lung hartii pe axa oy
            int screenX=worldX-gp.player.worldX + gp.player.screenX;
            int screenY=worldY-gp.player.worldY + gp.player.screenY;

            //pentru eficenta la randare(iti deseneaza casutele doar din screen)
            if(     worldX+gp.tileSize >gp.player.worldX - gp.player.screenX &&
                    worldX-gp.tileSize <gp.player.worldX + gp.player.screenX &&
                    worldY+gp.tileSize >gp.player.worldY - gp.player.screenY &&
                    worldY-gp.tileSize <gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);

                }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        if(drawPath == true){
            g2.setColor(new Color(255,0,0,70));
            for(int i=0; i < gp.pFinder.pathList.size(); i++){

                int worldX= gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY= gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX= worldX-gp.player.worldX + gp.player.screenX;
                int screenY= worldY-gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}

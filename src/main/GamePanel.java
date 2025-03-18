package main;
import ai.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    //SETARI ECRAN
    final int originalTitleSize=16;    //16x16 pixeli
    final int scale=3;
    public final int tileSize=originalTitleSize * scale;  //48
    public final int maxScreenCol=20;
    public final int maxScreenRow=12;
    public final int ScreenWidth=maxScreenCol*tileSize;    //latime=960
    public final int ScreenHeight=maxScreenRow*tileSize;   //inaltime=576

    //WORLD SETTINGS pentru camera
    public final int maxWorldCol=70;    //limitele mapei totale
    public final int maxWorldRow=60;
    //MAPA2...
    public final int maxMap = 10;
    public int currentMap = 0;  //schimb nivele
    int FPS=30;
    public ColiziuneaFrate cf=new ColiziuneaFrate(this);    //pentru coliziune
    public AssetSetter aSetter=new AssetSetter(this);       //pentru obiecte
    //SYSTEM
    public TileManager tileM=new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui=new UI(this);
    public EventHandler eHandler= new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);      //pt a urmari playerul
    Thread gameThread;

    //ENTITY AND OBJECTS
    public Player player=new Player(this,keyH);
    public Entity [][]obj=new Entity[maxMap][10];         //pt a introduce obiecte
    public Entity [][]npc=new Entity[maxMap][10];        //creem un array pt npc-uri
    public Entity [][]monstrii=new Entity[maxMap][20];   //creem array pt monstry
    ArrayList<Entity> entityList = new ArrayList<>();
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState= 3;
    public final int optionsState= 5;
    public final int gameOverState= 6;

    //FOR FULL SCREEN
    public  int screenWidth2 = ScreenWidth;
    public int screenHeight2 = ScreenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    //BAZE DATE
    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;
    String[] sqlQuery = new String[10];

    //METODE
    public GamePanel(){
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));   //set the size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);        //o calitate mai buna a randarii

        this.addKeyListener(keyH);           //recunoaste inputurile de la tastatura
        this.setFocusable(true);
        //DE AICI EROARE !!!!
        try {
            InitSQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void InitSQL() throws SQLException,ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        System.out.println("JDBC driver for SQLite loaded...");
        connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
        System.out.println("Connected to database ... DONE");
    }
    public void savedb(){
        String sql = "UPDATE Save SET Level = "+ currentMap; // interogare cu valoarea fixă 2
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing record was updated successfully!");
            } else {
                System.out.println("No record was updated.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        gameState = titleState;
    }
    public void loaddb()throws SQLException {
        System.out.println("HHHHH");
        stmt = connection.createStatement();
        rs = stmt.executeQuery("select * from Save;");
        if (rs.next()) {
            currentMap = rs.getInt("Level");
        }
        if (currentMap == 0) {
            drawToTempScreen();
            drawToScreen();
            tileM.getTileImage();
            player.setDefaultValues();
            aSetter.setMonstrii();
            aSetter.setObiect();
            gameState = playState;
        } else if (currentMap == 1) {
            drawToTempScreen();
            drawToScreen();
            tileM.getTileImage();
            player.setDefaultValues();
            aSetter.setMonstrii();
            gameState = playState;
        } else if (currentMap == 2) {
            drawToTempScreen();
            drawToScreen();
            tileM.getTileImage();
            player.setDefaultValues();
            aSetter.setMonstrii();
            gameState =titleState;
        }

        else {
            System.out.println("No data found in LastSave table.");
        }
    }
    //METODA CE FACE POSIBILA MODIFICARE MUZICII ,ETC
    public void setupGame(){
        aSetter.setObiect();                 //pt a pune obiect-mapa
        aSetter.setNpc();                    //pt a pune npc-mapa
        aSetter.setMonstrii();
        gameState=titleState;
        //FOR FULL SCREEN
        tempScreen=new BufferedImage(ScreenWidth, ScreenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        //setFullScreen();
    }
    public void retry(){  //CAND MORI SI DAI RETRY
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setNpc();                    //pt a pune npc-mapa
        aSetter.setMonstrii();
    }
    public void restart(){  //CAND MORI SI DAI RESTART
        player.setDefaultValues();
        player.setDefaultPositions();
        aSetter.setObiect();                 //pt a pune obiect-mapa
        aSetter.setNpc();                    //pt a pune npc-mapa
        aSetter.setMonstrii();
    }
    public void setFullScreen(){
        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();  //aflam informatiile ecranului nostru
        gd.setFullScreenWindow(Main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread() {
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
                                                                 // long currentTime = System.nanoTime();  pentru a afla timpul curent System.out.print(+currentTime)
        double drawInterval=1000000000/FPS;                      // o secunda sau un bilion de nanosecunde
        double nextDrawTime=System.nanoTime() + drawInterval;    //timpul curent plus 0.016666 sec

        while(gameThread!=null) {
            //System.out.println(FPS);
            update();
            try {
                drawToTempScreen();                                   //deseneaza totul in buffered image
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            drawToScreen();                                       //deseneaza din buffered image in screen
            try {
                double remainingTime=nextDrawTime - System.nanoTime();      //timpul ramas pana la urmatorul interval
                remainingTime = remainingTime / 1000000;                    //conversie din nano in milisec
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);                         //pune pauza la gameloop
                nextDrawTime+=drawInterval;                                 //reactualizarea urmat interval
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update() {
        if(gameState==playState){
            //PLAYER
            player.update();
            //NPC
            for(int i=0; i<npc[1].length; i++){
                if(npc[currentMap][i] != null)
                    npc[currentMap][i].update();
            }
            //MONSTRII
            for(int i=0; i<monstrii[1].length; i++){
                if(monstrii[currentMap][i] != null) {
                    if (monstrii[currentMap][i].alive == true && monstrii[currentMap][i].dying == false)
                        monstrii[currentMap][i].update();
                    if (monstrii[currentMap][i].alive == false)    ////pt death efect
                        monstrii[currentMap][i] = null;
                }
            }
        }
        if(gameState==pauseState){
            //NIMICA
        }
    }

    //PENTRU A DESENA TEMPSCREEN IN FULL SCREEN:
    public void drawToTempScreen() throws SQLException {
        //DEBUG (verificare timp pentru optimizare "T")
        long drawStart=0;
        if(keyH.checkDrawTime)
            drawStart=System.nanoTime();
        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);                          //apelam functia de desenare din ui
        }else {
            //OTHERS
            //MAPA1 layer:1, 2, 5

            tileM.draw(g2, tileM.mapTileNr);
            tileM.draw(g2, tileM.mapTileNr2);
            tileM.draw(g2, tileM.mapTileNr5);
            //ADAUG TOATE ENTITATILE IN ENTITY ARRAY

            entityList.add(player);
            for(int i=0; i < npc[1].length; i++){
                if(npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            //ADAUG TOATE OBIECT

            for(int i=0;i< obj[1].length; i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i=0;i< monstrii[1].length; i++){
                if(monstrii[currentMap][i]!=null){
                    entityList.add(monstrii[currentMap][i]);
                }
            }
            //SORT
            Collections.sort(entityList, new Comparator<Entity>(){

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            //DRAW ENTITIES
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();
            //LAYER 3+6 MAPA1

            tileM.draw(g2, tileM.mapTileNr3);
            tileM.draw(g2, tileM.mapTileNr6);

            //NR OBIECTE
            ui.draw(g2);
        }
        //DEBUG(pt obtimizare)
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time:" + passed, 10, 400);
            System.out.println("Draw Time:" + passed);
        }
    }

    //PT A LUA DIN TEMPSCREEN SI A PUNE IN SCREEN
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }


}

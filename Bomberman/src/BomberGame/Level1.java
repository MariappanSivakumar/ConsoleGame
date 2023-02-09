package BomberGame;

public class Level1 {

    char def;
    static char map[][];
    int playerX = -1, playerY = -1;

    public int position(char alpha) {
        return ((int)(alpha - 'A')) + 1;
    }

    public boolean isValidPosition(int i, int j) {
        return map[i][j] == def;
    }

    public void getPlayerPosition() {
        System.out.print("PLAYER POSITION:");
        String pos = new Input().getString();
        int i = position(pos.charAt(0)), j = position(pos.charAt(1));
        if (isValidPosition(i, j)) {
            map[i][j] = 'P';
            playerX = i;
            playerY = j;
        }
    }

    public void getKeyPosition() {
        System.out.print("Key POSITION:");
        String pos = new Input().getString();
        int i = position(pos.charAt(0)), j = position(pos.charAt(1));
        if (isValidPosition(i, j)) {
            map[i][j] = 'K';
        }
    }

    public void getVillanPosition() {
        System.out.print("VILLAN COUNT:");
        int n = Integer.parseInt(new Input().getString());
        for (int iter = 0; iter < n; iter++) {
            System.out.print("V:"+(iter+1)+":");
            String pos = new Input().getString();
            int i = position(pos.charAt(0)), j = position(pos.charAt(1));
            if (isValidPosition(i, j)) {
                map[i][j] = 'V';
            }
        }
    }
    
    public void getBricksPosition() {
        System.out.print("BRICKS COUNT:");
        int n = Integer.parseInt(new Input().getString());
        for (int iter = 0; iter < n; iter++) {
            System.out.print("B:"+(iter+1)+":");
            String pos = new Input().getString();
            int i = position(pos.charAt(0)), j = position(pos.charAt(1));
            if (isValidPosition(i, j)) {
                map[i][j] = 'B';
            }
        }
    }

    public void init(char arr[][]) {
        map = arr;
        getPlayerPosition();
        new Bomber().display(map);
        getKeyPosition();
        new Bomber().display(map);
        getVillanPosition();
        new Bomber().display(map);
        getBricksPosition();  
        new Bomber().display(map);
        
        new Level2().init(map, playerX, playerY);
    }
}

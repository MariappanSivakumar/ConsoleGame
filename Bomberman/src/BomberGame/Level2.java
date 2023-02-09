package BomberGame;

public class Level2 {
    
    char map[][];
    int moveX, moveY;
    int playerX, playerY;
    int boumCount;
    char def;
    int count = 0;
    
    public boolean isValid(int i, int j) {
        return i >= 0 && i < map.length && j >= 0 && j < map.length && map[i][j] == def || map[i][j] == 'V' || map[i][j] == 'K';
    }
    
    public char position(char pos) {
        int x = playerX, y = playerY;
        boolean isexe = false;
        if (pos == 'W') {
            if ((isexe = isValid(playerX - 1, playerY))) {
                playerX--;
            }
        } else if (pos == 'S') {
            if ((isexe = isValid(playerX + 1, playerY))) {
                playerX++;
            }
        } else if (pos == 'A') {
            if ((isexe = isValid(playerX, playerY - 1))) {
                playerY--;
            }
        } else if (pos == 'D') {
            if ((isexe = isValid(playerX, playerY + 1))) {
                playerY++;
            }
        } else if (pos == 'Q') {
            if ((isexe = isValid(playerX - 1, playerY - 1))) {
                playerX--;
                playerY--;
            }
        } else if (pos == 'Z') {
            if ((isexe = isValid(playerX + 1, playerY - 1))) {
                playerX++;
                playerY--;
            }
        } else if (pos == 'E') {
            if (isValid(playerX - 1, playerY + 1)) {
                playerX--;
                playerY++;
            }
        } else if (pos == 'C') {
            if ((isexe = isValid(playerX + 1, playerY + 1))) {
                playerX++;
                playerY++;
            }
        } else if (pos == 'X') {
            if ((isexe = isValid(playerX, playerY))) {
                System.out.print("1 Plant\n"
                        + "2 Detonate\n");
                int plan = Integer.parseInt(new Input().getString());
                if (plan == 1 && boumCount == 0) {
                    count = 0;
                    new Bomber().display(map);
                    boumCount++;
                } else if (plan == 2 && boumCount==1) {
                    boumCount--;
                } else {
                    System.err.print("\n\t\t>>>   SORRY NO BOUM IS AVALIABLE   <<<");
                }
                return 'X';
            }
        }
        
        if (isexe == false) {
            System.err.println("\t\t\t>>>  SORRY WE ARE NOT MOVE THAT POSITION    <<<");
            return ' ';
        } else {
            if (map[playerX][playerY] == 'V') {
                return 'V';
            } else if (map[playerX][playerY] == 'K') {
                return 'K';
            } else {
                map[playerX][playerY] = 'P';
                map[x][y] = ' ';
                return ' ';
            }
        }
    }
    
    public char move() {
//        System.out.print("W - Move up\n"
//                + "S - Move down\n"
//                + "A - Move left\n"
//                + "D - Move right\n"
//                + "Q - Move diagonally up left\n"
//                + "Z - Move diagonally down left\n"
//                + "E - Move diagonally up right\n"
//                + "C - Move diagonally down right\n");
        System.out.print("ENTER MOVE : ");
        return position(new Input().getString().trim().charAt(0));
    }
    
    public void startPlay() {
        while (true) {
            char state = move();
            if (state == 'K') {
                new Bomber().display(map);
                System.err.print("\n\t\t>>> YOU WON THE MATCH  <<<");
                break;
            } else if (state == 'V') {
                System.out.print("\n\t\t>>> YOU LOSE THE MATCH   <<<");
                break;
            } else if (state == 'X') {
                new Bomber().display(map);
            }else  {
                new Bomber().display(map);
            }
        }
    }
    
    public void init(char arr[][], int playerX, int playerY) {
        map = arr;
        this.playerX = playerX;
        this.playerY = playerY;
        startPlay();
    }
}

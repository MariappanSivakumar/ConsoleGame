package BomberGame;

public class Map {

    private void fillIt(char[][] map, int index, int mapSize) {
        for (int i = 3; i < mapSize; i += 2) {
            map[index][i] = '*';
        }
    }

    private char[][] designMap() {
        System.out.print("MAP SIZE : ");
        int mapSize = Integer.parseInt(new Input().getString());
        char map[][] = new char[mapSize][mapSize];

        int count = 65;
        for (int i = 0; i < mapSize; i++) {
            if (i != 0) {
                map[0][i] = (char) count;
                map[i][0] = (char) count++;
            }
            if (i + 1 < mapSize) {
                map[1][i + 1] = '*';
                map[i + 1][1] = '*';
            }
            map[mapSize - 1][i] = '*';
            map[i][mapSize - 1] = '*';
            if (i % 2 != 0 && i != 0 && i!=1 && i!=mapSize-1) {
                fillIt(map, i, mapSize);
            }
        }
        return map;
    }

    public char[][] init() {
        return new Map().designMap();
    }
}

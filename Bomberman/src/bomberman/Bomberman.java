package bomberman;

class Bomberman {

    private String getString() {
        String input = "";
        try {
            input = ((new java.io.BufferedReader(new java.io.InputStreamReader(System.in))).readLine());
        } catch (java.io.IOException e) {
        }
        return input;
    }

    private char getChar() {
        char character = ' ';
        try {
            character = (char) ((new java.io.BufferedReader(new java.io.InputStreamReader(System.in))).read());
        } catch (java.io.IOException e) {
        }
        return character;
    }

    private int[] split(String elements) {

        return new int[]{(elements.charAt(0) - 'A') + 1, (elements.charAt(1) - 'A') + 1};
    }

    private void fillWalls(char bomberMatrix[][]) {
        int character = 65;
        for (int i = 0; i < bomberMatrix.length; i++) {
            if (i != 0) {
                bomberMatrix[0][i] = (char) character;
                bomberMatrix[i][0] = (char) character++;
                bomberMatrix[i][1] = '*';
                bomberMatrix[1][i] = '*';
                bomberMatrix[bomberMatrix.length - 1][i] = '*';
                bomberMatrix[i][bomberMatrix.length - 1] = '*';
            }
        }
        for (int i = 0; i < bomberMatrix.length; i++) {
            for (int j = 0; j < bomberMatrix.length; j++) {
                if ((i + 1) % 2 == 0 && (j + 1) % 2 == 0) {
                    bomberMatrix[i][j] = '*';
                }
            }
        }

    }

    private void display(char bomberMatrix[][]) {
        for (int i = 0; i < bomberMatrix.length; i++) {

            for (int j = 0; j < bomberMatrix.length; j++) {
                System.out.print(bomberMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public java.util.List<String> findBlastPosition(char bomberMatrix[][], int i, int j) {
        java.util.List<String> list = new java.util.ArrayList();
        bomberMatrix[i][j] = ' ';

        // MOVE ONE POSITIONS
        if (i + 1 < bomberMatrix.length) {
            if (bomberMatrix[i + 1][j] != '*') {
                list.add((i + 1) + " " + j);
            }
            if (j + 1 < bomberMatrix.length && bomberMatrix[i + 1][j + 1] != '*') {
                list.add((i + 1) + " " + (j + 1));
            }
        }
        if (i - 1 >= 0) {
            if (bomberMatrix[i - 1][j] != '*') {
                list.add((i - 1) + " " + j);
            }
            if (j - 1 >= 0 && bomberMatrix[i - 1][j - 1] != '*') {
                list.add((i - 1) + " " + (j - 1));
            }
        }
        if (j + 1 < bomberMatrix.length) {
            if (bomberMatrix[i][j + 1] != '*') {
                list.add(i + " " + (j + 1));
            }
            if (i - 1 >= 0 && bomberMatrix[i - 1][j + 1] != '*') {
                list.add((i - 1) + " " + (j + 1));
            }
        }
        if (j - 1 >= 0) {
            if (bomberMatrix[i][j - 1] != '*') {
                list.add(i + " " + (j - 1));
            }
            if (i + 1 < bomberMatrix.length && bomberMatrix[i + 1][j - 1] != '*') {
                list.add((i + 1) + " " + (j - 1));
            }
        }
        // MOVE TWO POSITION
        if (i + 2 < bomberMatrix.length) {
            if (bomberMatrix[i + 2][j] != '*') {
                list.add((i + 2) + " " + j);
            }
            if (j + 2 < bomberMatrix.length && bomberMatrix[i + 2][j + 2] != '*') {
                list.add((i + 2) + " " + (j + 2));
            }
        }
        if (i - 2 >= 0) {
            if (bomberMatrix[i - 2][j] != '*') {
                list.add((i - 2) + " " + j);
            }
            if (j - 2 >= 0 && bomberMatrix[i - 2][j - 2] != '*') {
                list.add((i - 2) + " " + (j - 2));
            }
        }
        if (j + 2 < bomberMatrix.length) {
            if (bomberMatrix[i][j + 2] != '*') {
                list.add(i + " " + (j + 2));
            }
            if (i - 2 >= 0 && bomberMatrix[i - 2][j + 2] != '*') {
                list.add((i - 2) + " " + (j + 2));
            }
        }
        if (j - 2 >= 0) {
            if (bomberMatrix[i][j - 2] != '*') {
                list.add(i + " " + (j - 2));
            }
            if (i + 2 < bomberMatrix.length && bomberMatrix[i + 2][j - 2] != '*') {
                list.add((i + 2) + " " + (j - 2));
            }
        }

        return list;
    }

    public int[] spliter(String str) {
        String s[] = str.split(" ");
        return new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])};
    }

    private boolean boumBlast(java.util.List<String> blastPosition, char[][] bomberMatrix) {
        for (String iter : blastPosition) {
            int index[] = spliter(iter);
            if (bomberMatrix[index[0]][index[1]] == 'M') {
                boumBlast(findBlastPosition(bomberMatrix, index[0], index[1]), bomberMatrix);
            } else if (bomberMatrix[index[0]][index[1]] == 'V' || bomberMatrix[index[0]][index[1]] == 'B') {
                bomberMatrix[index[0]][index[1]] = ' ';
            } else if (bomberMatrix[index[0]][index[1]] == 'P') {
                return true;
            }
        }
        return false;
    }

    private void process(char bomberMatrix[][], int playerPosition[], int i, int j) {
        int boumCount = 0;
        int x = -1, y = -1;
        while (true) {
            x = playerPosition[0];
            y = playerPosition[1];
            System.out.print("ENTER THE NEXT MOVE...:");
            char c = getChar();
            bomberMatrix[playerPosition[0]][playerPosition[1]] = ' ';
            if (c == 'W') {
                playerPosition[0] -= 1;
            } else if (c == 'S') {
                playerPosition[0] += 1;
            } else if (c == 'A') {
                playerPosition[1] -= 1;
            } else if (c == 'D') {
                playerPosition[1] += 1;
            } else if (c == 'Q') {
                playerPosition[0] -= 1;
                playerPosition[1] -= 1;
            } else if (c == 'Z') {
                playerPosition[0] = +1;
                playerPosition[1] -= 1;
            } else if (c == 'E') {
                playerPosition[0] -= 1;
                playerPosition[1] += 1;
            } else if (c == 'C') {
                playerPosition[0] += 1;
                playerPosition[1] += 1;
            } else if (c == 'X') {
                System.out.println("1-->Plant\n2-->Detonate");
                int decition = Integer.parseInt(getString());
                if (decition == 1) {
                    boumCount++;
                    bomberMatrix[playerPosition[0]][playerPosition[1]] = 'P';
                    i = playerPosition[0];
                    j = playerPosition[1];

                } else if (decition == 2) {
                    if (boumCount == 0) {
                        System.out.println("No Boum is Avaliable to Bleast");
                    } else {
                        boumCount--;
                        bomberMatrix[playerPosition[0]][playerPosition[1]] = 'P';
                        java.util.List<String> validBlastPositions = findBlastPosition(bomberMatrix, i, j);
                        if (boumBlast(validBlastPositions, bomberMatrix)) {
                            System.out.println("\t\t>>>Sorry You Lose the Match<<<");
                            break;
                        }
                        i = -1;
                        j = -1;
                    }
                }
            }

            if (i != -1 && j != -1 && c != 'X') {
                bomberMatrix[i][j] = 'X';
            }
            if (bomberMatrix[playerPosition[0]][playerPosition[1]] == 'K') {
                bomberMatrix[playerPosition[0]][playerPosition[1]] = 'P';
                display(bomberMatrix);
                System.out.println("\t\t\t>>>    WON THE MATCH     <<<");
                break;
            } else if (bomberMatrix[playerPosition[0]][playerPosition[1]] == 'V') {
                bomberMatrix[playerPosition[0]][playerPosition[1]] = 'V';
                display(bomberMatrix);
                System.out.println("\t\t\t>>>     YOU LOSE THE MATCH    <<<");
                break;
            } else if (bomberMatrix[playerPosition[0]][playerPosition[1]] == '*' || bomberMatrix[playerPosition[0]][playerPosition[1]] == 'X' || bomberMatrix[playerPosition[0]][playerPosition[1]] == 'B') {
                System.out.println("\t\t\t>>>    YOU CAN'T ABLE TO MOVE THAT POSITION   <<<");
                playerPosition[0] = x;
                playerPosition[1] = y;
            }
            if (bomberMatrix[playerPosition[0]][playerPosition[1]] != '*') {
                bomberMatrix[playerPosition[0]][playerPosition[1]] = 'P';
            }
            display(bomberMatrix);
        }
    }

    private void getData() {
        System.out.print("\nMap size:");
        int size = Integer.parseInt(getString());
        char bomberMatrix[][] = new char[size][size];
        fillWalls(bomberMatrix);
        display(bomberMatrix);

        System.out.print("\nPlayer position:");
        int playerPosition[] = split(getString());
        bomberMatrix[playerPosition[0]][playerPosition[1]] = 'P';
        display(bomberMatrix);

        System.out.print("\nKey position:");
        int keyPosition[] = split(getString());
        bomberMatrix[keyPosition[0]][keyPosition[1]] = 'K';
        display(bomberMatrix);

        System.out.print("\nEnter Count:");
        int dynamiteCount = Integer.parseInt(getString());
        for (int i = 0; i < dynamiteCount; i++) {
            System.out.print("\nDM" + (i + 1) + ":");
            int dynamitePosition[] = split(getString());
            bomberMatrix[dynamitePosition[0]][dynamitePosition[1]] = 'M';
            display(bomberMatrix);
        }

        System.out.print("\nVillan Count:");
        int villanCount = Integer.parseInt(getString());
        for (int i = 0; i < villanCount; i++) {
            System.out.print("\n V" + (i + 1) + ": ");
            int villanPosition[] = split(getString());
            bomberMatrix[villanPosition[0]][villanPosition[1]] = 'V';
            display(bomberMatrix);
        }
        System.out.print("\nBricks Count:");
        int bricksCount = Integer.parseInt(getString());
        for (int i = 0; i < bricksCount; i++) {
            System.out.print("\n B" + (i + 1) + ": ");
            int bricksPosition[] = split(getString());
            bomberMatrix[bricksPosition[0]][bricksPosition[1]] = 'B';
            display(bomberMatrix);
        }
        process(bomberMatrix, playerPosition, -1, -1);
    }

    public static void main(String ar[]) {
        new Bomberman().getData();
    }
}

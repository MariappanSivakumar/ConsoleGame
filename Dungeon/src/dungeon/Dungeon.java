package dungeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Dungeon {

    private int move = 0;

    private void moves(char dungeonMatrix[][], int iA, int jA, int iG, int jG, int count, char person, String index) {
        if (iA == iG && jA == jG) {
            System.out.println(index);
            move = count;
            return;
        }

        if (iA < 0 || jA < 0 || iA >= dungeonMatrix.length || jA >= dungeonMatrix[0].length || person != 'M' && dungeonMatrix[iA][jA] == 'P') return;

        if (iG < iA) moves(dungeonMatrix, iA - 1, jA, iG, jG, count + 1, person, index + "-->(" + (iA - 1) + "," + jA + ")");

        if (iG > iA) moves(dungeonMatrix, iA + 1, jA, iG, jG, count + 1, person, index + "-->(" + (iA + 1) + "," + jA + ")");

        if (jG < jA) moves(dungeonMatrix, iA, jA - 1, iG, jG, count + 1, person, index + "-->(" + iA + "," + (jA - 1) + ")");

        if (jG > jA) moves(dungeonMatrix, iA, jA + 1, iG, jG, count + 1, person, index + "-->(" + iA + "," + (jA + 1) + ")");
    }
    
    public void disp(char dungeon[][]){
        for(char matrix[] : dungeon){
            for(char i : matrix){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
    private void getStartPlay() {
        System.out.print("Enter the Row and Column:");
        int rowAndcolumn[] = split(getString());
        char dungeonMatrix[][] = new char[rowAndcolumn[0] + 1][rowAndcolumn[1] + 1];

        System.out.print("\nPosition of adventurer:");
        int adventurer[] = split(getString());
        if (adventurer.length == 2) dungeonMatrix[adventurer[0]][adventurer[1]] = 'A';

        System.out.print("\nPosition of monster:");
        final int monster[] = split(getString());
        if (monster.length == 2) dungeonMatrix[monster[0]][monster[1]] = 'M';

        System.out.print("\nPosition of trigger:");
        final int trigger[] = split(getString());
        if (trigger.length == 2) dungeonMatrix[trigger[0]][trigger[1]] = 'T';

        System.out.print("\nPosition of gold:");
        final int gold[] = split(getString());
        if (gold.length == 2) dungeonMatrix[gold[0]][gold[1]] = 'G';

        System.out.print("\nEnter number of pits:");
        final int noOfPits = Integer.parseInt(getString());
        for (int i = 0; i < noOfPits; i++) {
            System.out.print("\nEnter the pit" + (i + 1) + ":");
            int pits[] = split(getString());

            dungeonMatrix[pits[0]][pits[1]] = 'P';
        }

        moves(dungeonMatrix, adventurer[0], adventurer[1], gold[0], gold[1], 0, 'A', "");
        int adventurerMove = move;
        move = 0;

        if (monster.length == 2)moves(dungeonMatrix, monster[0], monster[1], gold[0], gold[1], 0, 'M', "");
        int monsterMove = move;
        move = 0;

        if (monsterMove < adventurerMove) {
            if (trigger.length == 2) {
                moves(dungeonMatrix, adventurer[0], adventurer[1], trigger[0], trigger[1], 0, 'A', "");
                int advenTotriggerMove = move;
                move = 0;

                moves(dungeonMatrix, trigger[0], trigger[1], gold[0], gold[1], 0, 'T', "");
                int triggerToadvenMode = move;
                move = 0;

                adventurerMove = advenTotriggerMove + triggerToadvenMode;
                System.out.println("\t\t\t>>>     MOVE : " + adventurerMove + "    <<<");

            } else if (monster.length != 2) {
                if (adventurerMove != 0) {
                    System.out.println("\t\t\t>>>     MOVE : " + adventurerMove + "    <<<");
                } else {
                    System.out.println("\t\t\t>>> NO POSSIBLE  <<<");
                }
            } else {
                System.out.println("\t\t\t>>> NO POSSIBLE  <<<");
            }
        } else {
            if (monsterMove >= adventurerMove && monsterMove != 0) {
                System.out.println("\t\t\t>>>     MOVE : " + adventurerMove + "    <<<");
            } else {
                System.out.println("\t\t\t>>> NO POSSIBLE  <<<");
            }
        }
        disp(dungeonMatrix);
    }

    public static void main(String ar[]) {
        new Dungeon().getStartPlay();
    }

    private String getString() {
        String input = "";
        try {
            input = ((new BufferedReader(new InputStreamReader(System.in))).readLine());
        } catch (IOException e) {}
        return input;
    }

    private int[] split(String str) {
        str = str.trim();
        String positions[] = str.split(" ");
        if (positions.length == 2) {
            return new int[]{Integer.parseInt(positions[0]) - 1, Integer.parseInt(positions[1]) - 1};
        } else {
            return new int[]{};
        }
    }
}

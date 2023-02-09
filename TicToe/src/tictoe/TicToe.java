
package tictoe;

public class TicToe {

    char c;
    public void disp(char board[][]){
        System.out.println("----------");
        System.out.print("  ");
        for(int i=1;i<=board.length;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(j==0)System.out.print((i+1)+" ");
                if(board[i][j]!=c)
                System.out.print(board[i][j]+" ");
                else System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println("----------");
    }
    public char whoWin(char board[][]){
        
        int count = 0;
        for(int i=0;i<board.length;i++){
            int rightX = 0;
            int rightO = 0;
            
            int downX = 0;
            int downO = 0;
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==c)count++;
                if(board[i][j]=='X'){
                    rightX++;
                } else if(board[i][j]=='O') {
                    rightO++;
                }
                
                if(board[j][i] == 'O'){
                    downO++;
                } else if(board[j][i]=='X') {
                    downX++;
                }
            }
            if(rightX == board.length || downX == board.length)return 'X';
            if(rightO == board.length || downO == board.length)return 'O';
        }
        for(int i=0;i<board.length;i++){
            int dioLX = 0, dioLO = 0;
            int dioRX = 0, dioRO = 0;
            for(int j=0;j<board[i].length;j++){
                if(board[i][i] == 'X'){
                    dioLX++;
                } else if(board[i][i] == 'O') {
                    dioLO++;
                }
                if(board[i][board.length-i-1] == 'X'){
                    dioRX++;
                } else if(board[i][board.length-i-1] == 'O'){
                    dioRO++;
                }
            }
            if(dioLX == board.length || dioRX == board.length)return 'X';
            if(dioLO == board.length || dioRO == board.length)return 'O';
        }
        if(count==0)return 'E';
        return ' ';
    }
    public char[][] computerMove(char board[][], char computer, char player){
        
        int x =-1, y = -1;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == c){
                    x = i;
                    y = j;
                    board[i][j] = player;
                    if(whoWin(board) == player){
                        board[i][j] = computer;
                        return board;
                    }
                    board[i][j] = c;
                    
                }
                if(board[j][i]==c){
                    x = i;
                    y = j;
                    board[j][i] = computer;
                    if(whoWin(board) == computer){
                        board[j][i] = computer;
                        return board;
                    }
                    board[j][i] = c;
                }
            }
        }
        if(board[1][1]==c)
        board[1][1] = computer;
        else if(x!=-1) board[x][y] = computer;
        return board;
    }
    public void startPlay(char board[][], char player, char computer){
        int count = 0;
        while(true){
            System.out.println("Pos LIKE (X Y): ");
            String pos = new java.util.Scanner(System.in).nextLine();
            int i = Integer.parseInt(pos.split(" ")[0])-1;
            int j = Integer.parseInt(pos.split(" ")[1])-1;
            if(board[i][j] == c){
                board[i][j] = player;
                computerMove(board, computer, player);
                disp(board);
            } else {
                System.out.println("\t\t\t>>>>    Sorry Wrong Position   <<<<");
                count--;
            }
            
            char winner = whoWin(board);
            if(winner == 'E')break;
            if(winner == 'X'){
                if(player == winner){
                    System.out.println(">>>      YOU WON     <<<");
                } else {
                    System.out.println(">>>      COMPUTER WON    <<<");
                }
                break;
            } else if(winner == 'O'){
                if(player == winner){
                    System.out.println(">>>     YOU WON       <<<<");
                } else {
                    System.out.println(">>>  COMPUTER WON    <<<");
                }
                break;
            }
            if(count>7)break;
            count++;
        }
        
    }
    public void init(){
        char board[][] = new char[3][3];
        char computer = ' '; 
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.println("SELECT ( X (or) O ) ...:");
        char player = scan.nextLine().charAt(0);
        if(player == 'X')computer = 'O';
        else computer = 'X';
        disp(board);
        startPlay(board, player, computer);
    }
    public static void main(String[] args) {
        new TicToe().init();
    }
    
}

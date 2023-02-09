
package BomberGame;

public class Bomber {
    public void display(char arr[][])
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void init()
    {
        char map[][] = new Map().init();
        display(map);
        new Level1().init(map);
    }
    public static void main(String ar[])
    {
        new Bomber().init();
    }
}

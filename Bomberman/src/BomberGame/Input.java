package BomberGame;

public class Input {

    public String getString() {
        String userinput = "";
        try {
            userinput = ((new java.io.BufferedReader(new java.io.InputStreamReader(System.in))).readLine());
        } catch (java.io.IOException e) {
        }
        return userinput;
    }
}

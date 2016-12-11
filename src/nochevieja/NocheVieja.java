
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class NocheVieja {
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input;
        while (true) {
            try {
                input = BR.readLine();
                if (input.equals("00:00")) {
                    break;
                }
                System.out.println(getMinutesFromInput(input));
            } catch (java.lang.NumberFormatException e) {
                //System.out.println(e);
            }
        }
    }

    private static int getMinutesFromInput(String input) {
        String[] inputArray = input.split(":");
        int hours =  Integer.parseInt(inputArray[0]);
        int minutes = Integer.parseInt(inputArray[1]);
        if (hours >= 24 || hours < 0 || minutes >= 60 || minutes < 0){
            throw new NumberFormatException();
        }
        return (23-hours)*60 + (60-minutes);
    }
}

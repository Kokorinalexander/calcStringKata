import java.util.Scanner;
public class Main {

    private static String[] data;

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        if (!exp.matches("\".*\" (\\+|\\-|\\*|\\/) (\".*\"|\\d+)")){

            throw new Exception ("Неверный формат выражения");
        }

        exp = exp.replace("\"","");
        char action = getAction(exp);

        if(action == '*' || action == '/') {
            if(data[1].contains ("\"")) throw new Exception ("Строчку можно делить или умножать только на число");
        }
        for(int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }
        if(action == '+') {
            printInQuotes(data[0] + data[1]);
        } else if(action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            if (multiplier <= 0 || multiplier > 10) throw new Exception ("Неверное выражение");
            String result = "";
            for(int i = 0; i < multiplier; i++) {
                result += data[0];
            }
            printInQuotes(result);
        } else if(action == '-') {
            int index = data[0].indexOf(data[1]);
            if(index == -1) {
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
                printInQuotes(result);
            }
        } else {
            if (Integer.parseInt(data[1]) <= 0 || Integer.parseInt(data[1]) > 10) throw new Exception ("no");
            int newLen = data[0].length()/Integer.parseInt(data[1]);
            String result = data[0].substring(0, newLen);
            printInQuotes(result);
        }
    }

    private static char getAction(String exp) throws Exception{
        char action;

        if(exp.contains (" + ")) {
            data = exp.split(" \\+ ");
            action = '+';
        }
        else if(exp.contains (" - ")) {
            data = exp.split(" - ");
            action = '-';
        }
        else if(exp.contains (" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        } else if(exp.contains (" / ")) {
            data = exp.split(" / ");
            action = '/';
        } else {
            throw new Exception ("Некорректный знак действия");
        }

        return action;
    }

    static void printInQuotes(String text) throws IllegalAccessException {
        if(text.length() > 40){
            text = text.substring(0,40) + "...";
        }
        System.out.println("\"" + text + "\"");
    }
}

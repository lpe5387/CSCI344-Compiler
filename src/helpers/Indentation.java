package helpers;

public class Indentation {
    public static final String indent = "    ";
    public static int count = 0;

    public static String addIndent(){
        String result = "";
        for(int i = 0; i < count; i++){
            result += indent;
        }
        return result;
    }

    public static void shiftIndentForward(){
        count++;
    }

    public static void shiftIndentBackward(){
        count--;
    }
}

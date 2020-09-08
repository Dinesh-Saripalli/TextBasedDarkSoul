import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static Scanner scan2 = new Scanner(System.in);
    private static int pos= 0;
    private static int hour = 0;
    private boolean isLive = true;
    private static int localPos = 0;
    private static int windowProgress = 0;

    private static String[][] text = {
            {"List of basic commands: \nExplore \nInvestigate \nGoTo(Positon) \nUse(Object) \nTake(Object)", "*********\nYou awake in a prison cell"}
    };
    private static String[] explore = {
            "You are sitting sitting on a bed in the far left corner. \nIn the opposite corner is a toilet. \nA metal door with a slit opening is the only entrance. \nOn the back wall is a window covered with bars."
    };
    private static String[][] locations = {
            {"Bed", "Toilet", "Window", "Wall", "Ceiling"}
    };
    private static String[][] descriptions = {
            {"bed desc", "The seat and body are covered in rust. \nInside are several loose lead pipes", "window desc", "wall desc", "ceiling desc"}
    };
    private static String[][] objects = {
            {"File", "Lead Pipe", "", "", ""}
    };
    
    private static ArrayList<String> inventory = new ArrayList<String>();


    public static void main (String args[]) {
        pt(text[0][0]);
        pt(text[0][1]);
        while(hour < 32){
            input();
        }
    }

    //Universal methods
    public static void pt(String word) {
        System.out.println(word);
    }

    public static void input() {
        String line = scan.nextLine();
        String pt1 = line;
        String pt2 = " ";
        int num = line.indexOf(" ");
        if(num != -1){
            pt1 = line.substring(0, num);
            pt2 = line.substring(num + 1, line.length());
        }
        switch(pt1) {
            case("Explore"): Explore(); break;
            case("GoTo"): GoTo(pt2); break;
            case("Investigate"): Investigate(); break;
            case("Take"): Take(pt2); break;
            case("Use"): Use(pt2); break;
            default: invalid();
        }

    }

    public static void invalid(){
        pt("Enter in a valid input");
        input();
    }

    public static void Explore() {
        pt(explore[pos]);
    }

    public static void Investigate() {
        pt(descriptions[pos][localPos]);
    }

    public static void GoTo(String word) {
        boolean beans = true;
        for(int i = 0; i < locations[pos].length; i++){
            if(locations[pos][i].equals(word)) {
                localPos = i;
                beans = false;
            }
        }
        if(beans) {
            invalid();
        }
    }

    public static void Take(String word){
        boolean beans = true;
        for(int i = 0; i < objects[pos].length; i++){
            if(objects[pos][i].equals(word) && i == localPos) {
                inventory.add(word);
                beans = false;
            }
        }
        if(beans) {
            invalid();
        }
    }

    public static void Use(String word){
        if(!inventory.contains(word)){
            invalid();
        }
        switch(localPos) {
            case(2):
                switch(word) {
                    case("File"): pt("You begin to file down one of the bars"); break;
                    case("Lead Pipe"): pt("You begin banging the pipe against the window bars. \n It has no effect"); break;
                    default: pt("It has no effect");
                }
        }
    }


}
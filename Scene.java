public class Scene {
    public Location[] contents;  //Array containing Location instances
    public String description;   //Description of Room/Scene

    //Constructor
    public Scene (Location[] locations, String desc) {
        contents = locations;
        description = desc;
    }

    //Universal methods
    public static void pt(String word) {
        System.out.println(word);
    }

    //Checks if Scene contains Locations with matching name to entered String
    public boolean contains(String location){
        for(int i = 0; i < contents.length; i++) {
            if (location.equals(contents[i].name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    //Takes a Location name String and returns the position matching
    public int returnLocalPos(String word) {
        for(int i = 0; i < contents.length; i++) {
            if (word.equals(contents[i].name.toUpperCase())) {
                return i;
            }
        }
        return 0;
    }

}
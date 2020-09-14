public class Scene {
    public Location[] contents;  //Array containing Location instances
    public String description;   //Description of Room/Scene
    public String name;

    //Constructor
    public Scene (Location[] locations, String desc, String word) {
        contents = locations;
        description = desc;
        name = word;
    }

    //Universal methods
    public static void pt(String word) {
        System.out.println(word);
    }

    //Checks if Scene contains Locations with matching name to entered String
    public boolean contains(String location){
        for(int i = 0; i < contents.length; i++) {
            if(contents[i] != null){
                if (location.equals(contents[i].name.toUpperCase())) {
                    return true;
                }
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

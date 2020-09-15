public class KitchenWindow extends Location{

    public KitchenWindow(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            case("LEAD_PIPE"): gameEnd = true; pt("The glass shatters!!! \nYou crawl through the window and escape!!!\nVICTORY"); break;
            case("WEIGHT"): gameEnd = true; pt("The glass shatters!!! \nYou crawl through the window and escape!!!\nVICTORY"); break;
            case("BAT"): gameEnd = true; pt("The glass shatters!!! \nYou crawl through the window and escape!!!\nVICTORY"); break;
            case("File"): pt("You make some scratches on the glass."); break;
            case("Shank"): pt("You make some scratches on the glass."); break;
            default: pt("It has no effect");
        }
    }

}

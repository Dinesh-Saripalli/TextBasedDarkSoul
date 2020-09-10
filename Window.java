public class Window extends Location{

    public int progress = 10;      //how close the player is to filing down the bars, down to 0
    public boolean alarm = false;

    public Window(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public String itemUsed(String item){
        switch(item) {
            case("LEAD_PIPE"): alarm = true; return "You bang the pipe on the bars loudly. \nIt does not seem to affect them.";
            case("FILE"): progress -= 1; return "You chisel down the bars";
            default: return "It has no effect";
        }
    }



}

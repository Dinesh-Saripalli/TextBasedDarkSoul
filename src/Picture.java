public class Picture extends Location{

    public Picture(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            case("KEY"): pt("You unlock the safe. Inside is stacks of cash."); break;
            default: pt("It has no effect");
        }
    }

}

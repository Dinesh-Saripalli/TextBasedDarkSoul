public class Desk extends Location{

    public Desk(String Name, String desc, String invest, String Item){
        name = Name;
        description = desc;
        investigation = invest;
        item = Item;
    }

    //Based on item used, will return different result
    public void itemUsed(String item){
        switch(item) {
            default: pt("It has no effect");
        }
    }

}

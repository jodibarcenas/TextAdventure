import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
public class Player {
private HashMap<String, Item> inventory;
private Item carKeys;
Player(){
    inventory = new HashMap<>();
}
public String getItemString(){
    String returnString = "Player Inv:";
    Set<String> keys = inventory.keySet();
    for(String items: keys){
        returnString += " " + items;
    }
    return returnString;
}
    public void setItem(String name,Item item){
        inventory.put(name,item);
    }
    public Item getItem(String name){
        return inventory.remove(name);
    }
    public HashMap getInv(){
    return inventory;
    }
    public Item getCarKeys(){
        return carKeys;
    }
    public boolean hasCarKeys(){
        return (carKeys != null);
    }

}

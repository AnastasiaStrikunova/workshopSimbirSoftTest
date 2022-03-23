import java.util.Iterator;
import java.util.LinkedHashMap;

public class Statistics {
    LinkedHashMap <String, Integer> map;

    public Statistics() {
        map = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, Integer> map) {
        this.map = map;
    }

    void addRegisterOn(String word){
        if (map.containsKey(word)){
            map.put(word, map.get(word)+1);
        }
        else
            map.put(word, 1);
    }
    void addRegisterOff(String word){
        word = word.toLowerCase();
        if (map.containsKey(word)){
            map.put(word, map.get(word)+1);
        }
        else
            map.put(word, 1);
    }

    String printStr() {
        Iterator <String> iterator = map.keySet().iterator();
        StringBuilder str= new StringBuilder();
        String key;
        while (iterator.hasNext()){
            key = iterator.next();
            str.append(key).append(" - ").append(map.get(key)).append("\n");
        }
        return str.toString();
    }
}

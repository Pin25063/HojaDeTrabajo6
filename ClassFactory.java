import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClassFactory {
    public Map<String, List<String>> mapFactory(int option){
        switch (option) {
            case 1:
                return new HashMap<String,List<String>>();     
            case 2:
                return new TreeMap<String,List<String>>();   
            case 3:
                return new LinkedHashMap<String,List<String>>();
            default:
                return null;
        }
    }
}

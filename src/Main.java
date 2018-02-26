import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Map hashMap = new HashMap();

        hashMap.put("hola", 123);
        hashMap.put("sap", 50);
        hashMap.put(101, "zo");
        hashMap.put(202, "zi");
        hashMap.put("789", 7894);

        hashMap.forEach((k, v) ->
                System.out.println("key: " +k+ " value: " +v)
        );
        System.out.println(hashMap.values());
        System.out.println(hashMap.entrySet());
        System.out.println(hashMap.keySet());



        RafaMap MyMap = new RafaMap();


/*
        System.out.println(1%100);
        System.out.println(2%100);
        System.out.println(3%100);
        System.out.println(98%100);
        System.out.println(100%100);
*/


//        MyMap.put("hola", 500);
//        MyMap.put("adios", ":D");
        MyMap.put(2, "pepito");
        MyMap.put(2, "otra cosa ");
        MyMap.put(false, true);


        RafaMap.Node[] fulanito =  MyMap.getTable();

        System.out.println(MyMap.get(2));

        System.out.println(MyMap.containsKey("hola"));
        System.out.println(MyMap.get("hola"));
        MyMap.remove("hola");

        System.out.println(MyMap.containsKey(2));
        MyMap.remove(2);
        System.out.println(MyMap.containsKey(2));

        System.out.println(MyMap.size());


    }
}

package es.uned.lsi.eped.DataStructures;

import java.util.ArrayList;

public class Test1 {

    static boolean a = true;

    public static void main(String[] args) throws Exception {
        /*ListIF<String> strings = new List();
              strings.insert("s", 1);
              IteratorIF it = strings.iterator();
              System.out.println(it.hasNext());*/

        /*System.out.println(textCompareTo("a", "b"));
        System.out.println(textCompareTo("b", "c"));
        System.out.println(textCompareTo("d", "c"));
        System.out.println(textCompareTo("z", "x"));
        System.out.println(textCompareTo("luz", "abecedario"));
        System.out.println(textCompareTo("avestruz", "b"));*/
        ArrayList<String> list = new ArrayList<String>();
        list.add("Luz");
        list.add("Luz ama");
        list.add("Luz amarilla");
        list.add("Luz abierta que entra de par en par");
        //test();
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("---------");
        list = testQuicksort(list, 0, list.size() - 1);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static ArrayList<String> testQuicksort(ArrayList<String> l, int left, int right) {
        String pivot = l.get(left);
        int i = left;
        int j = right;
        String aux;
        while (i < j) {
            while (l.get(i).compareTo(pivot) <= 0 && i < j) {
                i++;
            }
            while (l.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i < j) {          
                aux = l.get(i);
                l.set(i, l.get(j));
                l.set(j, aux);
            }
        }
        l.set(left, l.get(j));
        l.set(j, pivot);
        if (left < j - 1) {
            testQuicksort(l, left, j - 1);
        }
        if (j + 1 < right) {
            testQuicksort(l, j + 1, right);
        }
        return l;
    }
    
    private static String textCompareTo(String a, String b) {
        return "\"" + a + "\".compareTo(\"" + b + "\") -> " + a.compareTo(b);
    }

    public static void test() {
        System.out.println("testing");
        if (a) {
            return;
        }
        System.out.println("asd");
    }

}

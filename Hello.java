
package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author diana
 */
public class Hello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map<String, List<String>> steps = new HashMap<String, List<String>>();
        Map<String, List<String>> step4 = new HashMap<String, List<String>>();

        int count = 0;
        List<String> lines = new ArrayList<String>();
        try (Scanner input = new Scanner(System.in)) {
            String line;
            while (!(line = input.nextLine()).isEmpty()) {
                lines.add(line);
            }
        }
        //First

        for (int i = 0; i < lines.size(); i++) {

            List<String> values = new ArrayList<String>();

            String[] part = lines.get(i).split("->|/");

            for (int J = 0; J < part.length; J++) {

                if (J != 0) {
                    values.add(part[J]);
                    if (i == 0) {
                        if (part[J].contains(part[0])) {
                            List<String> myList = new ArrayList<String>(Arrays.asList(part[0]));
                            steps.put("S0", myList);

                        }
                    }

                }

            }
            steps.put(part[0], values);

        }
        //Done

        if (!steps.containsKey("S0")) {
            System.out.println("First step skipped");
        } else {
            System.out.println("First step:" + steps);
        }

        ////Second 
        List<String> list0 = new ArrayList<String>();
        int counts = 1;
        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {

            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (int k = 0; k < values.size(); k++) {
                if (values.get(k).equals("e")) {
                    values.remove(k);
                    list0.add(key);

                }

            }

            if (list0.size() == 0) {
                System.out.println("Second step skipped for line" + counts);
            }
            counts++;
        }

        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (int k = 0; k < values.size(); k++) {
                for (int i = 0; i < list0.size(); i++) {
                    int count1 = 0;

                    if (values.get(k).contains(list0.get(i))) {//Если у value есть какой то empty key
                        List<Integer> indexes = new ArrayList<Integer>();
                        String[] part = values.get(k).split("");
                        for (int j = 0; j < part.length; j++) {
                            if (part[j].equals(list0.get(i))) {
                                count1++;
                                indexes.add(j);
                            }

                        }//сколько key empty

                        count1 = (int) Math.pow(2, count1);

                        String e_pos = null;
                        for (int m = count1 - 2; m >= 0; m--) {
                            String valuech = values.get(k);

                            e_pos = Integer.toBinaryString(m);
                            if (e_pos.length() != indexes.size()) {
                                int c = indexes.size() - e_pos.length();
                                for (int o = 1; o <= c; o++) {
                                    e_pos = "0" + e_pos;

                                }

                            }
                            String[] part1 = e_pos.split("");
                            int mlength = valuech.length();
                            int mind = 0;
                            for (int p = 0; p < part1.length; p++) {

                                if (part1[p].equals("0")) {
//                                    System.out.println(indexes);
//                                    System.out.print("mind ");
//                                    System.out.println(indexes.get(p) - mind);
//                                    System.out.println("Length "+mlength);
                                    if (indexes.get(p) - mind == mlength) {
                                        valuech = valuech.substring(0, indexes.get(p) - mind) + valuech.charAt(indexes.get(p) - mind);
                                    } else {
                                        valuech = valuech.substring(0, indexes.get(p) - mind) + valuech.substring(indexes.get(p) - mind + 1, mlength);
                                    }
                                    mlength = mlength - 1;
                                    mind = mind + 1;

                                }
                            }

//                            System.out.println("E" + e_pos);
//                            System.out.println("Valuech: " + valuech);
//                            //values.add(valuech);
//                        //values.add(valuech);
//                        // System.out.println("VAluech "+valuech);
//                        System.out.println(values);
                            int dup = 0;
                            for (int r = 0; r < values.size(); r++) {
                                if (values.get(r).equals(valuech)) {
                                    dup = 1;
                                }

                            }
                            if (dup == 0) {
                                values.add(valuech);
//                            System.out.println(values);

                            }

                        }

                    }

                }
            }

        }
        System.out.println(steps);
        List<String> list0key = new ArrayList<String>();
        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            if (values.size() == 0 || values.size() == 1 && values.get(0).equals(key)) {

                list0key.add(key);

            }

            for (int k = 0; k < values.size(); k++) {

                if (values.get(k).equals("")) {
                    values.set(k, "e");
                }
                if (values.get(k).equals(key)) {
                    values.remove(k);
                }

            }
        }

        for (int k = 0; k < list0key.size(); k++) {
            steps.remove(list0key.get(k));

        }
        System.out.println("Second step:" + steps);

        //Done
        ///Third
        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (int k = 0; k < values.size(); k++) {

                if (values.get(k).equals(values.get(k).toUpperCase()) && values.get(k).length() == 1) {

                    for (Map.Entry<String, List<String>> entry2 : steps.entrySet()) {
                        String key2 = entry2.getKey();
                        List<String> values2 = entry2.getValue();
                        if (key2.equals(values.get(k))) {

                            values.addAll(values2);

                            values.remove(k);

                        }
                    }

                }
            }

        }
        System.out.println("Third step:" + steps);
        //Done 
        //Fourth
        char state0 = 'z';
        int num;

        Set<String> hash_Set1 = new HashSet<String>();
        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {

            List<String> values = entry.getValue();

            for (int k = 0; k < values.size(); k++) {

                if (values.get(k).length() > 2) {
                    num = values.get(k).length() - 1;
                    int n = 0;

                    while (!(num <= 2)) {
                        int m = n + 2;

                        String newItem = values.get(k).substring(n, m);//два элемента

                        hash_Set1.add(newItem);
                        n = m;

                        num--;
                    }

                }

            }

        }

        for (String s : hash_Set1) {
            List<String> list = new ArrayList<String>();
            String state = Character.toString(state0).toUpperCase();
            for (Map.Entry<String, List<String>> entry : steps.entrySet()) {

                List<String> values = entry.getValue();

                for (int k = 0; k < values.size(); k++) {
                    String[] listn;
                    values.set(k, values.get(k).replaceAll(s, state));
//                    listn=values.get(k).split("(?<=\\G.{2})");
//                    System.out.println(Arrays.asList(listn));
//                    values.remove(k);
//                    values.addAll(Arrays.asList(listn));
                    System.out.println(values);

                }

            }
            list.add(s);

            steps.put(state, list);
            state0--;

        }
        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {

            List<String> values = entry.getValue();

            for (int k = 0; k < values.size(); k++) {
//                String[] listn;
//                values.set(k, values.get(k).replaceAll(s, state));
////                    listn=values.get(k).split("(?<=\\G.{2})");
////                    System.out.println(Arrays.asList(listn));
////                    values.remove(k);
////                    values.addAll(Arrays.asList(listn));
//                System.out.println(values);
                int o = 0;
                int num1 = values.get(k).length();
                while (!(num1 <= 2)) {
                    int m = o + 2;
                    String state = Character.toString(state0).toUpperCase();
                    String newItem = values.get(k).substring(o, m);//два элемента
                    values.set(k, values.get(k).replaceAll(newItem, state));
                    o = m;
                    num1--;
                    List<String> list = new ArrayList<String>();
                    list.add(newItem);
                    step4.put(state, list);
                    state0--;
                }
            }

        }
        steps.putAll(step4);
        System.out.println("Fourth step:" + steps);

//        // fifth
//        
        Set<Character> hash_Set = new HashSet<Character>();
        for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            for (int k = 0; k < values.size(); k++) {
                if (!values.get(k).equals("e")) {
                    for (int j = 0; j < values.get(k).length(); j++) {
                        if (Character.isLowerCase(values.get(k).charAt(j))) {
                            hash_Set.add(values.get(k).charAt(j));

                        }
                    }
                }
            }

        }

        int countA = 1;

        String word;

        for (Character s : hash_Set) {
            List<String> list = new ArrayList<String>();
            StringBuilder n = new StringBuilder("A");
            n.append(countA);
            for (Map.Entry<String, List<String>> entry : steps.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();

                for (int k = 0; k < values.size(); k++) {

                    word = s.toString();
                    values.set(k, values.get(k).replaceAll(s.toString(), n.toString()));

                }

            }
            list.add(s.toString());
            steps.put(n.toString(), list);
            countA++;
        }

        System.out.println("Fifth step" + steps);
    }
}

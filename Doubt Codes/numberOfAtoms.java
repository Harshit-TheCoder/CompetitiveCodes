import java.util.*;
class Solution {
    void parser(String str, Map<String, Integer> map) {
        int i = 0, n = str.length();
        while (i < n) {
            // Step 1: Parse element name
            StringBuilder element = new StringBuilder();
            element.append(str.charAt(i)); // uppercase
            i++;
            while (i < n && Character.isLowerCase(str.charAt(i))) {
                element.append(str.charAt(i));
                i++;
            }

            // Step 2: Parse frequency (if any)
            StringBuilder freqStr = new StringBuilder();
            while (i < n && Character.isDigit(str.charAt(i))) {
                freqStr.append(str.charAt(i));
                i++;
            }

            int freq = freqStr.length() > 0 ? Integer.parseInt(freqStr.toString()) : 1;
            map.put(element.toString(), map.getOrDefault(element.toString(), 0) + freq);
        }
    }

    public String countOfAtoms(String formula) {
        StringBuilder sb = new StringBuilder();
        int n = formula.length();
        for(int i=0;i<n;i++){
            char ch = formula.charAt(i);
            if(ch != ')') sb.append(ch);
            else{
                int idx = sb.lastIndexOf("(");
                Map<String, Integer> map = new HashMap<>();
                String str = sb.toString().substring(idx+1);
                parser(str, map);
                int freq = 0;
                int j = i + 1;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    freq = freq * 10 + (formula.charAt(j) - '0');
                    j++;
                }
                if (freq == 0) freq = 1;
                i = j - 1;
                StringBuilder hash = new StringBuilder();
                for(Map.Entry<String, Integer> entry: map.entrySet()){
                    hash.append(entry.getKey());
                    hash.append(Integer.toString(entry.getValue()*freq));
                }
                sb.delete(idx, sb.length());
                sb.insert(idx, hash.toString());
                // System.out.println(sb.toString());
            }
        }

        StringBuilder answer = new StringBuilder();
        TreeMap<String, Integer> mpp = new TreeMap<>();
        parser(sb.toString(), mpp);
        for(Map.Entry<String, Integer> entry: mpp.entrySet()){
            answer.append(entry.getKey());
            if(entry.getValue() > 1) answer.append(Integer.toString(entry.getValue()));
        }

        return answer.toString();
    }
}


// This is a typical pattern of problem which involves USAGE OF STRINGBUILDER TO STIMULATE STACK LIKE BEHAVIOUR IN AN EASIER AN MORE CONVENIENT WAY


public class numberOfAtoms {
    public static void main(String[] args) {
        
    }
}

import java.util.*;
public class subStringConcatWords {
    
}

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            if(!map.containsKey(word)) map.put(word, 0);
            map.put(word, map.get(word) + 1);
        }
        int l = words[0].length(), len = words.length * l, n = s.length(), i=0;
        List<Integer> list = new ArrayList<>();
        while(i<=(n-len)){
            Map<String, Integer> map2 = new HashMap<>();
            int start = i, end = i + len;
            boolean flag = true;
            while(start<end){
                StringBuilder sb = new StringBuilder();
                sb.append(s.substring(start, start+l));
                start += l;
                String word = sb.toString();
                if(!map.containsKey(word)){
                    flag = false;
                    break;
                }
                if(!map2.containsKey(word)) map2.put(word, 0);
                map2.put(word, map2.get(word) + 1);
                if(map2.get(word) > map.get(word)){
                    flag = false;
                    break;
                }
            }  
            if(flag == true) list.add(i);
            i++;
        }
        return list;
    }
}



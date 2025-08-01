package Strings;

import java.util.HashMap;
import java.util.Map;

public class Isomorphic {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);

            if (mapST.containsKey(cs)) {
                if (mapST.get(cs) != ct)
                    return false;
            } else {
                mapST.put(cs, ct);
            }

            if (mapTS.containsKey(ct)) {
                if (mapTS.get(ct) != cs)
                    return false;
            } else {
                mapTS.put(ct, cs);
            }
        }

        return true;
    }
}

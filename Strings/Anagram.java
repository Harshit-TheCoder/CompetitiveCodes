package Strings;

public class Anagram {
    public static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int[] count = new int[256]; // ASCII characters

        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }

        for (int c : count) {
            if (c != 0)
                return false;
        }

        return true;
    }
}

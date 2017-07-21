package billmortam.util;

/**
 * Created by pp00344204 on 09/07/17.
 * KMP Algorithm (Knuth-Morris-Pratt) for pattern matching
 */
public class TextSearch {

    public static boolean search(String term, String inText) {
        return match(term,inText);
    }

    public static int[] getPatternSubstringPositions(String pattern) {
        int[] positions = new int[pattern.length()];
        int j =0;
        for(int i=1; i< positions.length; i++){
            if(pattern.charAt(j) == pattern.charAt(i)){
                positions[i] = ++j;
            }else{
                while(j!=0 && pattern.charAt(j) != pattern.charAt(i)){
                    j = positions[j-1];
                }

                if(j!=0 || pattern.charAt(j) == pattern.charAt(i) ){
                    j++;
                }
                positions[i] = j;
            }
        }
        return positions;
    }

    public static boolean match(String pattern, String text) {
        int[] subStringPositions = getPatternSubstringPositions(pattern);
        int i=0;
        int j=0;
        while(j < pattern.length() && i < text.length()){
            if(text.charAt(i) == pattern.charAt(j)){
                j++;
            }else{
                while (j!=0 && pattern.charAt(j) != text.charAt(i)){
                    j = subStringPositions[j-1];
                }
                if(pattern.charAt(j) == text.charAt(i)){
                    j++;
                }
            }

            i++;
        }
        return j == pattern.length();
    }
}

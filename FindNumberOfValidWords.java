import java.util.*;
import java.util.stream.*;

//https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/

class FindNumberOfValidWords {

    public static void main(String args[]) {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        System.out.println(new FindNumberOfValidWords().findNumOfValidWords(words, puzzles));
    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        int[] wordBinRep = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordBinRep[i] = getBinRep(words[i]);
        }

        int[] results = new int[puzzles.length];
        for (int i = 0; i < puzzles.length; i++) {
            int pChar1Rep = 1 << puzzles[i].charAt(0) - 'a';
            int puzzleBinRep = getBinRep(puzzles[i]);

            for (int j = 0; j < wordBinRep.length; j++) {
                if ((pChar1Rep & wordBinRep[j]) == 0) {
                    continue;
                }
                if (wordBinRep[j] == (puzzleBinRep & wordBinRep[j])) {
                    results[i] += 1;
                }
            }
        }
        return Arrays.stream(results).boxed().collect(Collectors.toList());
    }

    private int getBinRep(String word) {
        int binRep = 0;
        for(int j = 0; j < word.length(); j++) {
            binRep |= 1 << word.charAt(j) - 'a';
        }
        System.out.print(String.format("Word:%20s -> ", word));
        System.out.println(String.format("Bin:%26s", Integer.toBinaryString(binRep)).replace(' ','0'));
        return binRep;
    }
}


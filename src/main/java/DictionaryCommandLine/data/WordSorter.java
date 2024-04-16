package DictionaryCommandLine.data;

import DictionaryCommandLine.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordSorter {
    public static void sortedWordList (ArrayList<Word> wordList) {
        sortByWordTarget(wordList);
    }

    private static void sortByWordTarget(ArrayList<Word> wordList) {
        Collections.sort(wordList, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
    }
}

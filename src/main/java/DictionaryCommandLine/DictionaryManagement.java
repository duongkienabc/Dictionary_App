package DictionaryCommandLine;

import DictionaryCommandLine.Dictionary;
import DictionaryCommandLine.Word;
import DictionaryCommandLine.data.DictionaryFileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandLine(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter the number of words: ");
            int numberOfWords = sc.nextInt();
            sc.nextLine(); // Đọc ký tự newline sau khi đọc số nguyên

            for (int i = 0; i < numberOfWords; i++) {
                System.out.print("Enter English word #" + (i + 1) + ": ");
                String wordTarget = sc.nextLine();

                System.out.print("Enter Vietnamese meaning #" + (i + 1) + ": ");
                String wordExplain = sc.nextLine();

                if (wordTarget.trim().isEmpty() || wordExplain.trim().isEmpty()) {
                    System.out.println("Invalid input, word not added.");
                    continue;
                }

                Word word = new Word(wordTarget, wordExplain);
                dictionary.addWord(word);
            }
        } finally {
            //sc.close(); // Đảm bảo rằng Scanner được đóng sau khi sử dụng
        }
    }

    public void insertFromFile(Dictionary dictionary, String filePath) {
        DictionaryFileIO dictionaryFileIO = new DictionaryFileIO();
        ArrayList<Word> wordList = dictionaryFileIO.readWordsFromFile("src/data/V_E.txt");
        if (wordList.isEmpty()) {
            System.out.println("No words in the file !");
            return;
        }
        for (Word word : wordList) {
            dictionary.addWord(word);
        }
    }

    public void lookUp(Dictionary dictionary) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Word to look up: ");
        String word_target = sc.nextLine();
        Word wordFound = dictionary.searchWord(word_target);
        if (wordFound != null) {
            System.out.println("Meaning: " + wordFound.getWord_explain());
        } else {
            System.out.println("Can not find this word. ");
        }
    }

    public ArrayList<Word> searchByFirstLetter(Dictionary dictionary, String prefix) {
        ArrayList<Word> results = new ArrayList<>();
        for (Word word : dictionary.getWords()) {
            if (word.getWord_target().toLowerCase().startsWith(prefix.toLowerCase())) {
                results.add(word);
            }
        }
        return results;
    }

    public void dictionarySearcher(Dictionary dictionary, String keyword) {
        String lowerKeyWord = keyword.toLowerCase();
        ArrayList<Word> searchResults = searchByFirstLetter(dictionary,lowerKeyWord);

        if (!searchResults.isEmpty()) {
            for(Word word : searchResults) {
                System.out.println("Word: " + word.getWord_target() + " | Meaning: " + word.getWord_explain());
            }
        } else {
            System.out.println("Found Nothing!!!");
        }

    }
}

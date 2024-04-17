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
                System.out.println("Word added successfully.");
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
        System.out.println("Words inserted from file successfully.");
    }

//    public void lookUp(Dictionary dictionary) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Word to look up: ");
//        String word_target = sc.nextLine();
//        Word wordFound = dictionary.searchWord(word_target);
//        if (wordFound != null) {
//            System.out.println("Meaning: " + wordFound.getWord_explain());
//        } else {
//            System.out.println("Can not find this word. ");
//        }
//    }

    public ArrayList<Word> searchByFirstLetter(Dictionary dictionary, String prefix) {
        // Danh sách để lưu trữ kết quả tìm kiếm
        ArrayList<Word> results = new ArrayList<>();

        // Tìm kiếm từ cụ thể bằng cách sử dụng phương thức searchWord
        Word foundWord = dictionary.searchWord(prefix);
        if (foundWord != null) {
            // Thêm từ cụ thể vào danh sách kết quả nếu không đã tồn tại
            results.add(foundWord);
        }
        // Tìm kiếm tất cả các từ có tiền tố (prefix) và thêm vào danh sách kết quả
        ArrayList<Word> wordsWithPrefix = dictionary.searchAllWords(prefix);
        for (Word word : wordsWithPrefix) {
            // Kiểm tra nếu từ chưa tồn tại trong danh sách kết quả
            if (!results.contains(word)) {
                results.add(word);
            }
        }
        return results; // Trả về danh sách kết quả tổng hợp
    }

    public void dictionarySearcher(Dictionary dictionary, String keyword) {
        String lowerKeyWord = keyword.toLowerCase();
        ArrayList<Word> searchResults = searchByFirstLetter(dictionary, lowerKeyWord);

        if (!searchResults.isEmpty()) {
            for (Word word : searchResults) {
                System.out.println("Word: " + word.getWord_target() + " | Meaning: " + word.getWord_explain());
            }
        } else {
            System.out.println("Found Nothing!!!");
        }
    }

    public void dictionaryUpdate(Dictionary dictionary, String oldWordTarget, Word newWord) {
        // Gọi phương thức updateWord của đối tượng Dictionary để cập nhật từ
        dictionary.updateWord(oldWordTarget, newWord);

        // In thông báo xác nhận cập nhật thành công
        System.out.println("Updated word: " + oldWordTarget + " to " + newWord.getWord_target());
    }

    public void wordDelete(Dictionary dictionary, String wordTarget) {
        // Gọi phương thức removeWord của đối tượng Dictionary để xóa từ
        dictionary.removeWord(wordTarget);

        // In thông báo xác nhận xóa thành công
        System.out.println("Removed word: " + wordTarget);
    }

}

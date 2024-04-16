package DictionaryCommandLine.data;

import DictionaryCommandLine.Word;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    public static List<Word> getAllWords() {
        List<Word> wordList = new ArrayList<>();
        ResultSet resultSet = SQLDatabase.executeQuery("SELECT * FROM words");
        try {
            while (resultSet.next()) {
                String word_target = resultSet.getString("word_target");
                String word_explain = resultSet.getString("word_explain");
                wordList.add(new Word(word_target, word_explain));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wordList;
    }
    public static void addWordToDataBase (String word_target, String word_explain) {
        String query = "INSERT INTO words(word_target, word_explain) VALUES ('" + word_target + "', '" + word_explain + "')";
        SQLDatabase.executeUpdate(query);
    }

    public static void replaceWord(String word_target, String new_word_explain) {
        String query = "UPDATE words SET word_explain = '" + new_word_explain + "' WHERE word_target = '" + word_target + "'";
        SQLDatabase.executeUpdate(query);
    }

    public static void deleteWord(String word_target) {
        String query = "DELETE FROM words WHERE word_target = '" + word_target + "'";
        SQLDatabase.executeUpdate(query);
    }
}


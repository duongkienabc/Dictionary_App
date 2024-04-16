package DictionaryCommandLine.api;

public class SpeechTest {
    public static void main(String[] args) {
        // Test phát âm tiếng Anh
        System.out.println("Test phát âm tiếng Anh:");
        Speech.UsualSpeech("Hello, this is a test.");

        // Test phát âm tiếng Việt
        System.out.println("\nTest phát âm tiếng Việt:");
        try {
            Speech.VietnameseAPISpeech("Xin chào, đây là một bài kiểm tra.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test phát âm sử dụng API tiếng Anh
        System.out.println("\nTest phát âm sử dụng API tiếng Anh:");
        try {
            Speech.EnglishAPISpeech("This is a test using API.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

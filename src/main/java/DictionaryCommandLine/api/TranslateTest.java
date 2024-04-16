package DictionaryCommandLine.api;
import org.testng.annotations.Test;

public class TranslateTest {

    @Test
    public void testTranslateVietnameseToEnglish() {
        String vietnameseText = "Anh đưa em về quê, đưa em ra đồng, có đàn có trắng bay. Chiếc nón lá có quai hồng, ";
        String englishText = Translate.translateText(vietnameseText, "vi", "en");
        System.out.println("Vietnamese Text: " + vietnameseText);
        System.out.println("Vietnamese to English: " + englishText);
    }

    @Test
    public void testTranslateEnglishToVietnamese() {
        String englishText = "Network and Proxy Configuration: Ensure that your network connection is working properly and there are no firewall or proxy issues blocking access to Maven Central Repository.";
        String vietnameseText = Translate.translateText(englishText, "en", "vi");
        System.out.println("English Text: " + englishText);
        System.out.println("English to Vietnamese: " + vietnameseText);
    }
}

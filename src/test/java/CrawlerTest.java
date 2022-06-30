import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrawlerTest {
    final String TESTING_URL = "https://en.wikipedia.org/wiki/Wikipedia";

    @Test
    @Name("First Link Is Valid 1")
    void firstLinkIsValid1() {
        Webpage webpage = new Webpage(TESTING_URL);
        Crawler webCrawler = new Crawler(webpage);

        String expected = "https://en.wikipedia.org/wiki/Multilingualism";
        String actual = webCrawler.getFirstValidLink();

        assertEquals(expected, actual);
    }
}
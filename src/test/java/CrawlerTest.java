import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrawlerTest {
    @Test
    @DisplayName("First Link is Valid without Parentheses")
    void firstLinkIsValid1() {
        String testingURL = "https://en.wikipedia.org/wiki/Wikipedia";

        Webpage webpage = new Webpage(testingURL);
        Crawler webCrawler = new Crawler(webpage);

        String expected = "https://en.wikipedia.org/wiki/Multilingualism";
        String actual = "https://en.wikipedia.org" + webCrawler.getFirstValidLink();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("First Link is Valid with Parentheses")
    void firstLinkIsValid2() {
        String testingURL = "https://en.wikipedia.org/wiki/Point_and_click";

        Webpage webpage = new Webpage(testingURL);
        Crawler webCrawler = new Crawler(webpage);

        String expected = "https://en.wikipedia.org/wiki/User_(computing)";
        String actual = "https://en.wikipedia.org" + webCrawler.getFirstValidLink();

        assertEquals(expected, actual);
    }
}
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    Webpage startingWebpage;

    public Crawler(Webpage webpage) {
        this.startingWebpage = webpage;
    }

    public String getFirstValidLink() {
        Elements bodyContentParagraphTags = startingWebpage.getDocument().getElementsByClass("mw-parser-output").select("p");

        for (Element currentParagraph : bodyContentParagraphTags) {
            System.out.println(getParagraphWithoutParentheses(currentParagraph));
        }
        return "";
    }

    private String getParagraphWithoutParentheses(Element paragraph) {
        String oldParagraph = paragraph.toString();
        StringBuilder newParagraph = new StringBuilder();

        int length = oldParagraph.length();
        int counter = 0;

        for (int i = 0; i < length; i++) {
            char currentCharacter = oldParagraph.charAt(i);

            if (currentCharacter == '(') {
                counter++;
            } else if (currentCharacter == ')') {
                counter--;
            } else if (counter == 0) {
                newParagraph.append(currentCharacter);
            }
        }
        return newParagraph.toString();
    }
}

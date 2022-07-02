import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    Webpage initialWebpage;

    public Crawler(Webpage webpage) {
        this.initialWebpage = webpage;
    }

    public String getFirstHeading() {
        return initialWebpage.getDocument().getElementsByClass("firstHeading mw-first-heading").text();
    }

    public String getFirstValidLink() {
        Elements bodyContentParagraphTags = initialWebpage.getDocument().getElementsByClass("mw-parser-output").select("p");

        for (Element currentParagraph : bodyContentParagraphTags) {
            Elements links = Jsoup.parseBodyFragment(getParagraphWithoutParentheses(currentParagraph)).select("a");

            for (Element currentLink : links) {
                if (!linkIsAFile(currentLink) && !linkIsAReference(currentLink)) {
                    return currentLink.attr("href");
                }
            }
        }
        return "";
    }

    private String getParagraphWithoutParentheses(Element paragraph) {
        String oldParagraph = paragraph.toString();
        StringBuilder newParagraph = new StringBuilder();

        int length = oldParagraph.length();
        int counter = 0;

        boolean isInAnchor = false;

        int index = 0;
        for (; index < length - 1; index++) {
            char currentCharacter = oldParagraph.charAt(index);
            char nextCharacter = oldParagraph.charAt(index + 1);

            if (currentCharacter == '<' && nextCharacter == 'a') {
                isInAnchor = true;
            } else if (currentCharacter == 'a' && nextCharacter == '>') {
                isInAnchor = false;
            }

            if (currentCharacter == '(' && !isInAnchor) {
                counter++;
            } else if (currentCharacter == ')' && !isInAnchor) {
                counter--;
            } else if (counter == 0) {
                newParagraph.append(currentCharacter);
            }
        }
        newParagraph.append(oldParagraph.charAt(index)); // get the last character
        return newParagraph.toString();
    }

    private boolean linkIsAReference(Element anchor) {
        String anchorStr = anchor.toString();
        return anchorStr.contains("#cite_note") || anchorStr.contains("#cnote");
    }

    private boolean linkIsAFile(Element anchor) {
        return anchor.toString().contains("/wiki/File:");
    }
}

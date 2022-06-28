import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Webpage {
    String URL;
    Document document;

    public Webpage(String URL) {
        this.URL = URL;
        setDocument();
    }

    public String getTitle() {
        return document.title();
    }

    public String getURL() {
        return URL;
    }

    public void setDocument() {
        try {
            document = Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Document getDocument() {
        return document;
    }
}

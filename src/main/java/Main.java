public class Main {
    public static void main(String[] args) {
        String URL = "https://en.wikipedia.org/wiki/Wikipedia";
        Webpage initialWebpage = new Webpage(URL);

        Crawler webCrawler = new Crawler(initialWebpage);
        String heading = webCrawler.getFirstHeading();
        System.out.println(heading);

        while (!heading.equals("Philosophy")) {
            String nextLink = "https://en.wikipedia.org" + webCrawler.getFirstValidLink();
            Webpage nextWebpage = new Webpage(nextLink);

            webCrawler = new Crawler(nextWebpage);
            heading = webCrawler.getFirstHeading();

            System.out.println(heading);
        }
    }
}

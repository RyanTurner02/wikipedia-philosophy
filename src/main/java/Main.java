public class Main {
    public static void main(String[] args) {
        String URL = "https://en.wikipedia.org/wiki/Wikipedia";
        Webpage startingWebpage = new Webpage(URL);

//        System.out.println(startingWebpage.getDocument());
//        System.out.println(startingWebpage.getTitle());
//        System.out.println(startingWebpage.getURL());

        Crawler crawler = new Crawler(startingWebpage);
    }
}

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by SretenskyVD on 01.09.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "s:/Let'sEncryptAuthorityX3_BF.crt.jks");
        Document doc1 = Jsoup.connect("https://bfide.ru/catalog/losiny/").get();
        Elements lHref = doc1.select("a.d_block");
        Elements links1 = doc1.getElementsByClass("product_name got_bold");
        Elements prices = doc1.getElementsByClass("current_price got_bold pink");
        int y = 0;
        for (Element link1 : links1) {
            System.out.println(link1.text() + " ; " + prices.get(y).text());
            y = y + 1;
        }
        System.out.println();
        System.out.println();

        for (int x = 0; x < links1.size(); x++) {
            String addressUrl = lHref.get(x).attr("abs:href");
            Document doc2 = Jsoup.connect(addressUrl).get();
            Elements razmeres = doc2.getElementsByClass("skuLabel prop_RAZMER_");
            Elements title = doc2.getElementsByTag("title");
            for (Element razmer : razmeres) {
                System.out.println(title.text() + " ; " + razmer.text());
            }
        }
    }
}



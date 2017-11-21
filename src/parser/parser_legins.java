package parser;

/**
 * Created by SretenskyVD on 21.11.2017.
 */
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by SretenskyVD on 25.09.2017.
 */
public class parser_legins {

    public static void main(String[] args) throws IOException {

        System.setProperty("javax.net.ssl.trustStore", "s:/Let'sEncryptAuthorityX3_BF.crt.jks");
        String Path = "https://bfide.ru/catalog/losiny/";
        int Page =0;
        for (int count = 1 ; count <=3 ; count++  )

        {

            if (Page==1){
                Path = "https://bfide.ru/catalog/losiny/?PAGEN_1=2";
            }
            if (Page==2){
                Path = "https://bfide.ru/catalog/losiny/?PAGEN_1=3";
            }

            Document doc1 = Jsoup.connect(Path).get();
            // Document doc1 = Jsoup.connect("https://bfide.ru/catalog/losiny/").get();
            Elements lHref = doc1.select("a.d_block");
            Elements links1 = doc1.getElementsByClass("product_name got_bold");
            Elements prices = doc1.getElementsByClass("current_price got_bold pink");
            Elements Categorys = doc1.getElementsByClass("title1 got_light mb30");
            Elements Names = doc1.select("a[data-product]");


            int y = 0;
            for (Element link1 : links1) {
                System.out.println();
                System.out.print(Categorys.text() + " ; " + Names.get(y).attr("data-product") + " ; " + link1.text() + " ; " + prices.get(y).text());
                String addressUrl = lHref.get(y).attr("abs:href");


                Document doc2 = Jsoup.connect(addressUrl).get();

                Elements razmeres = doc2.getElementsByClass("skuLabel prop_RAZMER_");
                for (Element razmer : razmeres) {
                    System.out.print(" ; " + razmer.text());
                }
                Elements pictures = doc2.getElementsByClass("zoomer image");

                int z = 0;
                for (Element picture : pictures) {
                    System.out.print(" ; https://bfide.ru" + pictures.get(z).attr("src"));
                    z++;
                }
                y++;

            }

            Page++;

        }


    }
}



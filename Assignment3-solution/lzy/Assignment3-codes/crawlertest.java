
import java.io.IOException;
import org.apache.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawlertest {
    public static void main(String[] args) throws ParseException, IOException {
        String content = Javacrawler.getHtmlByUrl(
                "https://music.163.com/#/song?id=3986017");
        parserHtml(content);
    }

    public static void parserHtml(String content) throws ParseException, IOException {
        Document doc = Jsoup.parse(content);
        Elements links = doc.getElementsByTag("div");
        for (Element e : links) {
            //获取页面链接
            Elements linkHref = e.select("a");
            //获取页面图片链接
            Elements imgSrc = e.select("img");
            
            System.out.println("图片地址：" + imgSrc.attr("arc"));
            System.out.println("链接地址:" + linkHref.attr("href"));
            System.out.println("链接内容:" + linkHref.text());
            System.out.println("              ");
        }
        
    }
}
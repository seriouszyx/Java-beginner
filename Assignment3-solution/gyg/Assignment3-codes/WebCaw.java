import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class HtmlFetcher {
	public static Document httPost(String url) throws IOException{
        Connection res = Jsoup.connect(url);
        res.data("params", "4VXgm2ZJDXc6d56UZW7N4GMaWSEdfgCr+Mt+mbactFdj+3QcfyaF75zKJKwM+vNJz7tm6ogen5ehg7v3LPmAMJFoVaF0y8EfJgf6vtQOQkUwYeHSgNyjMy3ZfqFMaizkckHku9EJDGzoHPdp2Scl7fFicplbA3RuDNp8kKYT4ZlTvMWCXAFnDsSoARJAAJHQ");
        res.data("encSecKey", "8371d42d8204ad218afa1b11d5d6f7b3f6d596647c23d3f802b442b6e95a1b5bfce6b9c65eb484c65696dc3ef746d4e62a156af84bfb2499ac04e8caa41c8719368f450a5579645580d538fc3f5f4f0dd73047d7e8b4d4ae5bd9ceb484156e4bb573cff829f05fe6911ba7e63614d6714f629bf4a184e5d5fa5433f8981fc003");
        Document html = res.post();
        return html;
	}
}
public class WebCaw {
	public static void main(String[] args) throws IOException {
		HtmlFetcher fetcher = new HtmlFetcher();
		Document doc = fetcher.httPost("https://music.163.com/weapi/v1/resource/comments/R_SO_4_3986017?csrf_token=");
		String html = doc.html();
		String reg = "(?=hotComments)(.*?)(?<=\"code\":200,)";
		Pattern regex = Pattern.compile(reg);
	    Matcher data = regex.matcher(html);
	    String res="";
	    while (data.find()) {
	    	 res=data.group(1);
	    }
	    Pattern WritterID = Pattern.compile("(?=\"nickname\":\")(.*?)(?<=\",)");
	    Pattern HotComments = Pattern.compile("(?=\"content\":\")(.*?)(?<=)}");
	    Matcher WritterIDMatcher = WritterID.matcher(res);
        Matcher HotCommentsMatcher = HotComments.matcher(res);
        ArrayList<String> writterID = new ArrayList<String>();
        while (WritterIDMatcher.find()) {
        	writterID.add(WritterIDMatcher.group(1));
        } 
        ArrayList<String> hotComments = new ArrayList<String>();
        while (HotCommentsMatcher.find()) {
        	hotComments.add(HotCommentsMatcher.group(1));
        }
        for (int i = 0; i < hotComments.size(); i++) {
        	String IDstr = String.valueOf(writterID.get(i));
        	StringBuilder IDbuilder = new StringBuilder(IDstr);
        	String Comstr = String.valueOf(hotComments.get(i));
        	StringBuilder Combuilder = new StringBuilder(Comstr);
        	System.out.println("用户名  " + IDbuilder.delete(0,11) + ": 	"+ Combuilder.delete(0, 9));
        }
	}
	

	
}

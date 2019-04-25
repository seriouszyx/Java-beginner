
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
public class Clawer {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		// 创建一个文本文档
		File file = new File("f://HotView(3).txt");
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://music.163.com/weapi/v1/resource/comments/R_SO_4_3986017?csrf_token=");
        
        
        // 疯狂模仿浏览器
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("params","GsGGdQ01caroHeuiyCzr1N7pckE54T3qRIjybzO5t5HCsBVbzbuFPRkjCTmpT7RD9Er9ZS73+DOFfwZHLWdKNjbxBW1lvEWsrvdY1ZKHWPZs7L+58R6e89ZLhDxHZxHBuuQPtOL9TMgxOEXy9EEJ9RBwGRtoQicAhRwnDX5//ytK5A4ACj3iIp+CzVzJrr43"));
        list.add(new BasicNameValuePair("encSecKey","197356952b46b74aa4baf94c5f4867f7e0180e7ed32ccc6f0f53d4a6530c93114bbdc9ec5a9de2b6cc08f95dea0b57868a4934d4675b2fb50204d6f108f9602b7d8723fa85f431750516b20bd25b78d2e73dfeab875ebff46a4a58cfb65c14d44e67918407b45657369964bec0623c998b4a7d67c231a90bb13a1b78c4bb6ff1"));
        
        httpPost.setEntity(new UrlEncodedFormEntity(list));
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
        httpPost.setHeader("Referer", "https://music.163.com/song?id=3986017");
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        CloseableHttpResponse httpResponse = null;
        
        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null) {
                String content = EntityUtils.toString(entity, "utf-8");
                
                // 发现热评都跟在‘content’，的后边所以用来这样一个正则表达式
                String con = "content(...)(.*?)\"}";
                Pattern pattern = Pattern.compile(con);
                Matcher matcher = pattern.matcher(content);
                // 记录热评条数。
                int i = 0;
                while(matcher.find()) {
                	i++;
                	System.out.print(i + ": ");
                	//  把爬取的热评放进文档中
                    System.out.println(matcher.group(2));
                    out.write(matcher.group(2) + "\n");
                } 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpResponse!=null) {
                httpResponse.close();
            }
            out.flush();
            out.close();
            httpClient.close();
        }

    }

}

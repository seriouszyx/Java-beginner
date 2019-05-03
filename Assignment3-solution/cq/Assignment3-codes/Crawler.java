import java.io.FileNotFoundException;
import java.io.FileReader;
 
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.junit.Test;

//import java.util.List;

/**
 * 	简单的方法不能处理加密的问题，经过查询发现网易云热评放在了动态加载的iframe页面中，而且两个参数经过了三次不同的加密方法，暂时没有学到那么深入，了解到了分布式爬虫处理方式
 *	在网上找到了一个可以直接获取评论的api，走了个捷径
 * 	http://music.163.com/api/v1/resource/comments/R_SO_4_id //(?limit=a&&offset=b)//可以进一步拓展翻页
 */
public class Crawler {
    @Test
    public void test() {
    	
    	//使用代理IP地址:有点不稳定撒：只要访问次数不多，没毛病的
    	HttpHost proxy = new HttpHost("111.177.182.42",9999);
    	
    	//选择目标网址和设置超时时间：https://music.163.com/#/song?id=1311319058
        HttpGet httpGet = new HttpGet("https://music.163.com/api/v1/resource/comments/R_SO_4_1311319058");
        httpGet.setConfig(RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build());

    	//模拟浏览器
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64)  AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");

        //初始化响应
        String responseStr = "";
        
        //创建实例
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        
        //异常处理机制
        try {
        	//创建
            httpClient = HttpClientBuilder.create().build();
            HttpClientContext context = HttpClientContext.create();
            
            //响应获取
            response = httpClient.execute(httpGet, context);
            
            //响应状态
            int state = response.getStatusLine().getStatusCode();
            if (state != 200)
            {
            	responseStr = "";
            	System.out.println("非200");
            }
            
            //执行GET请求
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                responseStr = EntityUtils.toString(entity, "utf-8");
                System.out.println("爬取内容如下：");
            }
            //字符集注意！
            //可以传入想要获取的评论数：注意不要越界了
            new Crawler().ReadJsonStr(responseStr, 14);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (responseStr == null)
        {
            System.out.println("获取内容为空");
            return;
        }

/*====================================JSOUP处理==========================================*/
//这部分是利用Jsoup处理获得的字符串，然鹅直接传json字符串没有用上，可以直接处理没有加密的网站
//        Document document = Jsoup.parse(responseStr);
//        
//        List<Element> elements = document.getAllElements();;
//        
//        elements.forEach(element -> {
//        	//匹配节点信息
//            for (Element e : element.select("div")) {
//            	System.out.print(e.text() + ": ");
//            }
//        });
//    }
/*=============================对JSON字符串的处理=======================================*/
    }
    
    public void ReadJsonStr(String str, int len)
    {
    	//获取所有json对象
    	str = str.replace("null", "\"\"");
    	
    	JsonParser parser = new JsonParser();
    	JsonObject jsonObj = parser.parse(str).getAsJsonObject();
    	
    	//一层一层获取Json属性
    	JsonArray hotCommentsArr = jsonObj.get("hotComments").getAsJsonArray();
    	for(int i = 0; i < len; i++) {
    		JsonObject hotComments = hotCommentsArr.get(i).getAsJsonObject();
    		JsonObject hotUserIp = hotComments.get("user").getAsJsonObject();
    		String UserIp = hotUserIp.get("nickname").getAsString();
    		String hotComment = hotComments.get("content").getAsString();
    		
    		//输出用户名
    		System.out.print("  " + UserIp + ":\n");
    		//输出评论内容
    		System.out.println(hotComment  + "\n");
    	}
/*=================================================获取的JSON格式============================================*/
//    	{
//    		"user": {
//    			"locationInfo": null,
//    			"userId": 44393510,
//    			"remarkName": null,
//    			"expertTags": null,
//    			"nickname": "放肆了啊",
//    			"userType": 0,
//    			"vipRights": {
//    				"associator": {
//    					"vipCode": 100,
//    					"rights": true
//    				},
//    				"musicPackage": null,
//    				"redVipAnnualCount": -1
//    			},
//    			"authStatus": 0,
//    			"avatarUrl": "http://p1.music.126.net/7ystH7U9B--070HBE7zDTQ==/109951163399384130.jpg",
//    			"vipType": 11,
//    			"experts": null
//    		},
//    		"beReplied": [],
//    		"pendantData": {
//    			"id": 5002,
//    			"imageUrl": "http://p1.music.126.net/tFjGw0GRZ-Oyf6r5vH4kFQ==/109951163313123168.jpg"
//    		},
//    		"showFloorComment": null,
//    		"status": 0,
//    		"commentLocationType": 0,
//    		"parentCommentId": 0,
//    		"decoration": null,
//    		"repliedMark": false,
//    		"likedCount": 158945,
//    		"liked": false,
//    		"commentId": 17351419,
//    		"expressionUrl": null,
//    		"time": 1430864801561,
//    		"content": "自从给我们家狗狗听完vlv，它在后街打架再也没输过，鼎盛时期，后面跟随它的大小狗狗不计其数。现在它老了，在夜深时间，它会独自爬上楼顶，哼唱着这首歌“I used to rule the world 大千世界曾由我主宰”"
//    	}
/*======================================================================================================*/
    }
}
package lt_500_599;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 *
 * [535] Encode and Decode TinyURL
 * 系统设计问题
 * https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/
 * http://blog.gainlo.co/index.php/2016/03/08/system-design-interview-question-create-tinyurl-system/
 */
public class LC_535 {
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_HOST = "http://tinyurl.com/";
    private Map<String, String> tinyMap = new HashMap<>();
    private Map<String, String> urlMap = new HashMap<>();
    private Random random = new Random();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (urlMap.containsKey(longUrl)) {
            return BASE_HOST + urlMap.get(longUrl);
        }
        String key = "";
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 6; ++i) {
                char c = CHARSET.charAt(random.nextInt(CHARSET.length()));
                builder.append(c);
            }
            key = builder.toString();
        } while(tinyMap.containsKey(key));
        tinyMap.put(key, longUrl);
        urlMap.put(longUrl, key);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return tinyMap.get(shortUrl.replace(BASE_HOST, ""));
    }
}

import com.myproject.bigdata.spider.RegexUtils;
import org.junit.Test;

public class TestRegex {
    @Test
    public void testRegex(){
        String rs = RegexUtils.paserDaomainnamme("https://www.douyu.com/xxxx");
        System.out.println(rs);
    }
}

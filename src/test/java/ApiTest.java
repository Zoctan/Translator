import com.zoctan.translator.api.ApiFactory;

import org.junit.Before;
import org.junit.Test;

public class ApiTest {
    private ApiFactory factory = null;

    @Before
    public void setUp() throws Exception {
        factory = new ApiFactory();
    }

    @Test
    public void test() throws Exception {
        final String origin = "Xamarin.Forms has several layouts and features for organizing content on screen.\n Each layout control is described below, as well as details on how to handle screen orientation changes:";
        factory.getApiMap().forEach((key1, value1) -> System.out.println(key1 + " => " + value1.translate(origin)));

        final String origin2 = "这个巡展，大家想去的先注册，临近当地巡展时候，会有注册确认邮件发给大家，届时可以看到详细的活动地址。\n等你工作了你就知道你当时的观念是多么的幼稚";
        factory.getApiMap().forEach((key, value) -> System.out.println(key + " => " + value.translate(origin2)));
    }
}

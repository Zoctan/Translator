package test;

import api.ApiFactory;
import org.junit.Before;
import org.junit.Test;

public class TransTest {
    ApiFactory factory = null;

    @Before
    public void setUp() throws Exception {
        factory = new ApiFactory();
    }

    @Test
    public void test() throws Exception {
        String origin = "Xamarin.Forms has several layouts and features for organizing content on screen.\n Each layout control is described below, as well as details on how to handle screen orientation changes:";
        System.out.println("有道 : " + factory.get("Youdao").translate(origin));
        System.out.println("百度 : " + factory.get("Baidu").translate(origin));
        System.out.println("谷歌 : " + factory.get("Google").translate(origin));

        origin = "这个巡展，大家想去的先注册，临近当地巡展时候，会有注册确认邮件发给大家，届时可以看到详细的活动地址。\n等你工作了你就知道你当时的观念是多么的幼稚";

        System.out.println("有道 : " + factory.get("Youdao").translate(origin));
        System.out.println("百度 : " + factory.get("Baidu").translate(origin));
        System.out.println("谷歌 : " + factory.get("Google").translate(origin));
    }
}

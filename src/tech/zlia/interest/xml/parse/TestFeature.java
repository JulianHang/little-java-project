package tech.zlia.interest.xml.parse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import tech.zlia.interest.xml.parse.bean.AppSettings;
import tech.zlia.interest.xml.parse.bean.Configuration;

import java.io.InputStream;

/**
 * 测试类
 * <p>主要用来测试功能
 */
public class TestFeature {

    public static void main(String[] args)throws Exception {
        InputStream inputStream = XmlBeanDefinitionSAXReader.class.getResource("/tech/zlia/interest/xml/parse/config.xml").openStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        XmlBeanDefinitionSAXReader xdsr = new XmlBeanDefinitionSAXReader();

        //从根节点开始解析
        Element root = document.getRootElement();
        Configuration configuration = xdsr.startParseXmlAtSpecifiedElement(root,Configuration.class);
        System.out.println("对象属性："+configuration);

        System.out.println();

        //从指定节点开始解析
        Element appSettingsEle = root.element("appSettings");
        AppSettings appSettings = xdsr.startParseXmlAtSpecifiedElement(appSettingsEle,AppSettings.class);
        System.out.println("对象属性："+appSettings);
    }
}

/* 结果展示 记得引入dom4j和fastjson两个jar.dom4j主要用来解析xml，fastjson主要用来封装xml数据成json对象，并转换成相应的实体

<configuration id="id0" key="key0">
    <appSettings id="id1" key="key1"></appSettings>
    <appSettings id="id2" key="key2"></appSettings>
    <test id="id3" key="key3">23</test>
</configuration>

指定节点名称：appSettings
指定节点的Json对象：{"appSettings":{"id":"id1","key":"key1","nodeText":""}}
对象属性：id：id1,key：key1,text：null
 */

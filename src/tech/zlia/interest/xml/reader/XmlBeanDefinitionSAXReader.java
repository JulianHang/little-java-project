package tech.zlia.interest.xml.reader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import tech.zlia.interest.xml.reader.bean.AppSettings;
import tech.zlia.interest.xml.reader.bean.Configuration;

import java.io.InputStream;
import java.util.List;

/**
 * 解析指定路径下的xml文件
 * <p>根据指定节点开始解析
 * <p>返回指定元素类型的对象
 */
public class XmlBeanDefinitionSAXReader {

    /**
     * 文本节点的key值
     */
    private final static String NODE_TEXT = "nodeText";

    /**
     * 测试方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws  Exception {
        InputStream inputStream = XmlBeanDefinitionSAXReader.class.getResource("/tech/zlia/interest/xml/reader/config.xml").openStream();
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

    /**
     * 从指定节点开始解析
     * @param element 指定节点
     */
    public <T> T startParseXmlAtSpecifiedElement(Element element,Class<T> cls){
        JSONObject obj = parseXmlSpecifieElement(element);
        System.out.println("指定节点名称：" + element.getName());
        System.out.println("指定节点的Json对象："+obj);
        JSONObject json = obj.getJSONObject(element.getName());
        T t = JSON.parseObject(json.toJSONString(),cls);
        return t;
    }


    /**
     * 填充属性到当前节点的Json对象中
     * @param element 当前节点
     * @param curretnJson 当前节点的Json对象
     */
    public void fillAttributeAtElement(Element element,JSONObject curretnJson){
        List<Attribute> attributes = element.attributes();
        JSONObject addJson = null;
        String nodeName = element.getName();
        if(!attributes.isEmpty()) { //有属性的情况下
            addJson = new JSONObject();
            for(Attribute attribute : attributes){
                String name = attribute.getName();
                String value = attribute.getValue();
                addJson.put(name,value);
            }
        }
        if(addJson != null){
            curretnJson.put(nodeName,addJson);
        }
    }

    /**
     * 将当前节点的Json对象填充到父节点的Json对象中
     * <p>若已经有存在Key则用数组填充数据
     * <p>若没有则用对象填充数据
     * @param element 当前节点的子节点
     * @param currentJson  当前节点的Json对象
     * @param addJson 子节点的Json对象
     */
    public void fillDataToParentElement(Element element,JSONObject currentJson,JSONObject addJson){
        if(currentJson == null || addJson == null){
            throw new IllegalArgumentException("参数不能为空");
        }

        String currentEleName = element.getName(); //子节点
        Element parentElement = element.getParent();//子节点的父节点

        String parentName = parentElement.getName(); //父节点的节点名称
        JSONObject parentEleValueJsonObject = (JSONObject)currentJson.get(parentName); //获取父节点的Json对象
        Object currentEleValueJsonObject = addJson.get(currentEleName); //获取子节点的Json数据

        if(parentEleValueJsonObject.containsKey(currentEleName)){ //判断父节点的Json数据中是否有重复的key.若有的话则用数组来填充,若没有则用对象来填充
            Object json = parentEleValueJsonObject.get(currentEleName);
            JSONArray array = null;
            if(json instanceof JSONArray){ //父节点的Json数据为Json数组时
                array = (JSONArray)json;
            }else{ //父节点的Json数据为Json对象时
                array = new JSONArray();
                array.add(json); //将原来的元素添加到数组中去
            }
            array.add(currentEleValueJsonObject); //添加子节点的Json对象
            parentEleValueJsonObject.put(currentEleName, array); //将添加完子节点的Json对象的数组放入到父节点中
        }else{
            parentEleValueJsonObject.put(currentEleName,currentEleValueJsonObject); //添加子节点对象
        }
        currentJson.put(parentName,parentEleValueJsonObject);
    }

    /**
     * 当前节点是否有子节点
     * <p>若有则递归子节点并填充相应的属性
     * <p>若没有则读取当前节点的文本
     * @param element 当前节点
     * @param curretnJson 当前节点的Json对象
     */
    public void fillDataAtSubElement(Element element,JSONObject curretnJson){
        if(element == null || curretnJson == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        List<Element> subElements = element.elements(); //当前节点的子节点
        if(subElements.isEmpty()){ //如果没有子元素的话则获取文本
            String nodeValue = element.getTextTrim();
            if(curretnJson.isEmpty()){
                curretnJson.put(element.getName(),nodeValue); //添加前判断是否有已经有相同的标签已经添加过了，如果已经添加过了则要用数组来显示，若没有则用对象
            }else{
                JSONObject jsonObject = (JSONObject)curretnJson.get(element.getName()); //获取当前节点的Json数据
                jsonObject.put(NODE_TEXT,nodeValue); //"text":""
                curretnJson.put(element.getName(),jsonObject); //填充文本属性到当前节点中
            }
        }else{
            for(Element subElement : subElements){
                JSONObject addJson = parseXmlSpecifieElement(subElement); //解析子节点
                fillDataToParentElement(subElement,curretnJson,addJson); //将解析子节点后的Json对象填充到父节点中
            }
        }

    }

    /**
     * 从指定节点开始解析
     * <p>指定的节点不能为空
     * @param element 当前节点
     * @return JSONObject 填充完属性和文本的Json对象
     */
    public JSONObject parseXmlSpecifieElement(Element element){
        if(element == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        JSONObject currentJson = new JSONObject(); //新建Json对象来保存当前节点的属性
        fillAttributeAtElement(element,currentJson);//填充当前节点的属性和文本
        fillDataAtSubElement(element,currentJson);
        return currentJson;
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

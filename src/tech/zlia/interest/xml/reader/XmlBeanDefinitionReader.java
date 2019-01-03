package tech.zlia.interest.xml.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.w3c.dom.*;
import tech.zlia.interest.xml.factory.XmlBeanFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

/**
 * 负责解析Xml
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    /**
     * 从根节点开始解析Xml
     * <p>若为空或者Null则从根节点开始解析
     * @param configLocation xml路径
     * @param xmlBeanFactory 工厂类实例
     * @param xmlBeanId id
     */
    @Override
    public void loadBeanDefinitions(String configLocation,XmlBeanFactory xmlBeanFactory,String xmlBeanId) {
        try {
            setXmlBeanFactory(xmlBeanFactory); //关联工厂类实例
            InputStream inputStream = getClass().getResource(configLocation).openStream();
            doLoadBeanDefinitions(inputStream,xmlBeanId);
        }catch (Exception e){
            System.out.println("XmlBeanDefinitionReader > loadBeanDefinitions > 解析Xml发生异常 >" + e.toString());
        }
    }


    /**
     * 构建Document对象
     * @param inputStream xml流对象
     * @return Document对象
     * @throws ParserConfigurationException xml解析异常
     */
    private Document buildDocumentInstance(InputStream inputStream)throws Exception{
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            inputStream.close();
            return document;
        }catch (Exception e){
            System.out.println("XmlBeanDefinitionReader > doLoadBeanDefinitions > 构建Document对象发生异常 >" + e.toString());
            throw e ;
        }
    }

    /**
     * 构建Document对象并开始解析Xml
     * @param inputStream xml流对象
     * @param xmlBeanId id
     */
    public void doLoadBeanDefinitions(InputStream inputStream,String xmlBeanId)throws Exception{
        Document document = buildDocumentInstance(inputStream);
        registerBeanDefinitions(document,xmlBeanId);
    }

    /**
     * 从指定节点开始解析Xml
     * <p>若xmlBeanId为空或Null则从根节点开始解析
     * @param document document对象
     * @param xmlBeanId id
     */
    private void registerBeanDefinitions(Document document,String xmlBeanId){
        Element element;
        if(xmlBeanId == null || xmlBeanId == ""){
            element = document.getDocumentElement();//获取xml文件的根节点
        }else {
            element = document.getElementById(xmlBeanId); //TODO 要加入schema和dtd，较为麻烦，自己实现一套简单的，循环遍历查找
        }
        JsonObject obj = parseBeanDefinitions(element); //解析xml
        System.out.println(obj.toString());
    }

    /**
     * 从指定节点开始解析Xml
     * @param root 指定节点
     */
    private JsonObject parseBeanDefinitions(Element root){
        JsonObject currentElement = parseCurrentElement(root); //获取当前节点所有属性的Json对象
        NodeList nodeList = root.getChildNodes(); //获取当前节点的子节点列表
        for(int i = 0 ; i < nodeList.getLength(); i ++){//将读取所有节点的所有属性
            Node nodeItem = nodeList.item(i);
            if(nodeItem instanceof Element){
                Element elementItem = (Element) nodeItem;
                JsonObject subElement = parseBeanDefinitions(elementItem); //递归操作
                currentElement.add(elementItem.getNodeName(),subElement); //将子节点的属性添加到当前节点中，即父节点中
            }
        }
        return currentElement;
    }

    /**
     * 包含当前节点所有属性的Json对象
     * @param root 当前节点
     * @return 当前节点所有属性的Json对象
     */
    private JsonObject parseCurrentElement(Element root){
        NamedNodeMap namedNodeMap = root.getAttributes(); //获取所有属性名与属性值
        root.getTextContent();
        JsonObject nodeObject = new JsonObject();
        int nodeMapLength = namedNodeMap.getLength();
        for (int i = 0; i < nodeMapLength; i++) {
            Node nodeItem = namedNodeMap.item(i);
            String nodeName = nodeItem.getNodeName();
            String nodeValue = nodeItem.getNodeValue();
            nodeObject.addProperty(nodeName, nodeValue); //绑定属性名与属性值并添加到Json对象中
        }
        return nodeObject;
    }

    public static void main(String[] args) {
        XmlBeanDefinitionReader xdr = new XmlBeanDefinitionReader();
        xdr.loadBeanDefinitions("/config.xml",null,"");
    }
}

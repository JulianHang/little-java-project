package tech.zlia.interest.xml.reader;

import tech.zlia.interest.xml.factory.XmlBeanFactory;

/**
 * 负责解析Xml，关联工厂类
 */
public abstract class AbstractBeanDefinitionReader {


    /**
     * 工厂类实例
     */
    private XmlBeanFactory xmlBeanFactory;

    /**
     * 获取工厂类实例
     * @return 工厂类实例
     */
    protected XmlBeanFactory getXmlBeanFactory() {
        return xmlBeanFactory;
    }

    /**
     * 设置工厂类实例
     * @param xmlBeanFactory 工厂类实例
     */
    public void setXmlBeanFactory(XmlBeanFactory xmlBeanFactory) {
        this.xmlBeanFactory = xmlBeanFactory;
    }

    /**
     * 从根节点开始解析Xml
     * @param configLocation xml路径
     * @param xmlBeanFactory 工厂类实例
     * @param xmlBeanId id
     */
    public abstract void loadBeanDefinitions(String configLocation, XmlBeanFactory xmlBeanFactory,String xmlBeanId);
}

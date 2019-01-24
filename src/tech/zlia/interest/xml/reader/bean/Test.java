package tech.zlia.interest.xml.reader.bean;

/**
 * 对应config.xml中的test节点
 * @version 2018-12-20
 * @author zlia
 */
public class Test {

    /**
     * 对应config.xml中的test节点的id属性
     */
    private String id;

    /**
     * 对应config.xml中的test节点的key属性
     */
    private String key;

    /**
     * 对应config.xml中的test节点的文本
     */
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "id："+id+",key："+key+",text："+text;
    }
}

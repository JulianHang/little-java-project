package tech.zlia.interest.xml.parse.bean;

import java.util.List;

/**
 * 对应config.xml中的configuration节点
 * @verison 2018-12-20
 * @author zlia
 */
public class Configuration {

    /**
     * 对应config.xml中的configuration节点的id属性
     */
    private String id;

    /**
     * 对应config.xml中的configuration节点的key属性
     */
    private String key;

    /**
     * 对应config.xml中的configuration节点的文本
     */
    private String text;

    /**
     * 对应config.xml中的configuration节点的多个appSettings子节点
     */
    private List<AppSettings> appSettings;
    private Connection connection;

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

    public List<AppSettings> getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(List<AppSettings> appSettings) {
        this.appSettings = appSettings;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection test) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "id："+id+",key："+key+",text："+text+",appSettings："+appSettings + ",connection：" + connection;
    }
}

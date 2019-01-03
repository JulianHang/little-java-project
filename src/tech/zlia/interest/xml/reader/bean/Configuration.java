package tech.zlia.interest.xml.reader.bean;

import java.util.List;

public class Configuration {

    private String id;
    private String key;
    private String text;
    private List<AppSettings> appSettings;
    private Test test;

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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "id："+id+",key："+key+",text："+text+",appSettings："+appSettings + ",test：" + test;
    }
}

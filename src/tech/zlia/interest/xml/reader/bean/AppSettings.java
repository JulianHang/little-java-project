package tech.zlia.interest.xml.reader.bean;

public class AppSettings {

    private String id;
    private String key;
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

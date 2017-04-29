package NetWork.Okhttp;

/**
 * Created by XZC on 2017/4/15.
 */

public class key_value {
    String key;
    String value;

    public key_value(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

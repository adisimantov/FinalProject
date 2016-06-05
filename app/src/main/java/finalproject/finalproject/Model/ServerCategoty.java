package finalproject.finalproject.Model;


import org.json.JSONObject;

/**
 * Created by adi on 02-Apr-16.
 */
public class ServerCategoty {
    private String type;
    private int count;

    public ServerCategoty(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public ServerCategoty(String type) {
        this.type = type;
        this.count = 0;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addCount(int count) {
        this.count+=count;
    }
}

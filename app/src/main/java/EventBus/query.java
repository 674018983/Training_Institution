package EventBus;

/**
 * Created by XZC on 2017/5/5.
 */

public class query {
    private int Msg;
    public query(int query_what){
        Msg = query_what;
    }
    public int getMsg(){
        return Msg;
    }
}

package NetWork.Rx_and_Retrofit.Model;

/**
 * Created by XZC on 2017/4/15.
 */

public class Training_Institution {

    String pid;
    String name;
    String comment;
    String address;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                '\"' +"pid" + '\"' +":"+ '\"' + pid + '\"' +','+
                '\"' +"name" + '\"' +":"+  '\"' + name + '\"' +','+
                '\"' +"comment" + '\"' +":"+ '\"' + comment + '\"' +','+
                '\"' +"address" + '\"' +":"+ '\"' + address + '\"' +
                '}';
    }
}

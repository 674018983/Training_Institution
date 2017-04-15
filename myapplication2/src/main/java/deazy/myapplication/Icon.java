package deazy.myapplication;

/**
 * Created by XZC on 2017/4/12.
 */

public class Icon {
    int Id;
    int StartIcon;
    int EndIcon;

    public Icon(int id, int startIcon, int endIcon) {
        Id = id;
        StartIcon = startIcon;
        EndIcon = endIcon;
    }

    public int getId() {
        return Id;
    }

    public int getStartIcon() {
        return StartIcon;
    }

    public int getEndIcon() {
        return EndIcon;
    }

}

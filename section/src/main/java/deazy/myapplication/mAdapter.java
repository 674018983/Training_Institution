package deazy.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;

import java.util.List;

/**
 * Created by XZC on 2017/5/7.
 */

public class mAdapter extends BaseAdapter implements SectionIndexer {

    private Context context;
    private List<String> stringList;

    public mAdapter(Context context, List<String> stringList){
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {

        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        //根据索引获取组信息，这里不做处理
         return 0;
    }

    private final class ViewHolder{

    }
}

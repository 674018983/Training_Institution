package deazy.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XZC on 2017/5/7.
 */

public class mAdapter extends BaseAdapter implements SectionIndexer {

    private Context context;
    private String[] stringList;
    private LayoutInflater mInflater;
    private int height;

    public mAdapter(Context context, String[] stringList){
        this.context = context;
        this.stringList = stringList;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return stringList.length;
    }

    @Override
    public Object getItem(int position) {
        return stringList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.listview_item_layout,parent,false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.mtag  = (tag) convertView.findViewById(R.id.tag);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }



//
        viewHolder.title.setText("123sdfghjkl");
        height = viewHolder.title.getHeight();
//        viewHolder.title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));

//        convertView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
        return convertView;
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

    public int getheight(){
        return height;
    }

    private final class ViewHolder{
        TextView title ;
        tag mtag;
    }


}

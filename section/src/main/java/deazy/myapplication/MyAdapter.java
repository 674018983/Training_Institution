package deazy.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XZC on 2017/5/7.
 */
public class MyAdapter extends BaseAdapter implements SectionIndexer {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> stringList;

    public MyAdapter(Context context, List<String> stringList) {
        layoutInflater = LayoutInflater.from(context);
        this.stringList = stringList;
        this.context = context;
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
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.main_listview_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.normalTv = (TextView) convertView.findViewById(R.id.normal_tv);//普通项view
            viewHolder.sectionTv = (TextView) convertView.findViewById(R.id.section_tv);//组view

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String label = stringList.get(position);
        setSectionTv(position, viewHolder, label);
        setNormalTv(viewHolder, label);

        return convertView;
    }

    private void setNormalTv(ViewHolder viewHolder, String label) {
        viewHolder.normalTv.setText(label);
    }

    private void setSectionTv(int position, ViewHolder viewHolder, String label) {
        //获取每个item字符串的头一个字符
        char firstChar = label.toUpperCase().charAt(0);
        //若为第一个位置直接设置组view就行
        if (position == 0) {
            viewHolder.sectionTv.setVisibility(View.VISIBLE);
            viewHolder.sectionTv.setText(label.substring(0, 1).toUpperCase());
        }
        //若不是，需判断当前item首字母与上一个item首字母是否一致，再设置组view
        else {
            String preLabel = stringList.get(position - 1);
            //获取上一个item的首字母
            char preFirstChar = preLabel.toUpperCase().charAt(0);
            if (firstChar != preFirstChar) {
                viewHolder.sectionTv.setVisibility(View.VISIBLE);
                viewHolder.sectionTv.setText(label.substring(0, 1).toUpperCase());
            } else {
                //若与上一个item首字母一致则不需要重复设置组view
                viewHolder.sectionTv.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getPositionForSection(int section) {
        //根据组信息获取索引
        for (int i = 0; i < stringList.size(); i++) {
            String str = stringList.get(i);
            char firstChar = str.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        //根据索引获取组信息，这里不做处理
        return 0;
    }

    @Override
    public Object[] getSections() {
        //获取组信息的数组，比如这里可以返回char[]{'A','B',...}
        return null;
    }

    private final class ViewHolder {
        TextView normalTv;
        TextView sectionTv;
    }
}

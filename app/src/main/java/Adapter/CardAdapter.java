package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.Rx_and_Retrofit.Model.Training_Institution;
import deazy.myapp.R;
import okhttp3.Response;

/**
 * Created by XZC on 2017/4/15.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    List<Training_Institution> mItems;

    public CardAdapter(){
        super();
        mItems = new ArrayList<Training_Institution>();
    }

    public void addData(String response) throws IOException {
        Gson gson = new Gson();
        if(!response.equals("")&&response!=null) {
//            Training_Institution[] training_institution = gson.fromJson(response, Training_Institution[].class);
//            // 这时候想转成List的话调用如下方法
//            List<Training_Institution> foosList = Arrays.asList(training_institution);
            List<Training_Institution> foosList = gson.fromJson(response, new TypeToken<List<Training_Institution>>(){}.getType());
            for (Training_Institution ti : foosList) {
                mItems.add(ti);
            }
        }
        notifyDataSetChanged();
    }
    public void clear(){
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Training_Institution training_institution = mItems.get(position);
        holder.namee.setText(training_institution.getName());
        holder.addresss.setText(training_institution.getAddress());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
            public TextView namee;
            public TextView addresss;
        public ViewHolder(View itemView) {
            super(itemView);
            namee = (TextView) itemView.findViewById(R.id.namee);
            addresss = (TextView) itemView.findViewById(R.id.addresss);

        }
    }
}

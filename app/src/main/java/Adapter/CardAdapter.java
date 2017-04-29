package Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import NetWork.Rx_and_Retrofit.Model.Training_Institution;
import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/15.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    List<Training_Institution> mItems;
    private static final String TAG = "CardAdapter";

    public CardAdapter(){
        super();
        mItems = new ArrayList<Training_Institution>();
    }

    public void addData(Training_Institution training_institution) throws IOException {
//        Log.e(TAG, "addData: "+response );
        Gson gson = new Gson();
//        if(!response.equals("")&&response!=null) {
//
//            List<Training_Institution> foosList = gson.fromJson(response, new TypeToken<List<Training_Institution>>(){}.getType());
//            for (Training_Institution ti : foosList) {
//                mItems.add(ti);
//            }
//        }
        mItems.add(training_institution);
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

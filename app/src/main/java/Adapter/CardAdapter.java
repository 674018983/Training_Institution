package Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements View.OnClickListener{
    public static List<Training_Institution> mItems;
    private static final String TAG = "CardAdapter";
    private String mCompany = "";
    private boolean IsCheckAddress = false;
    private OnItemClickListener onItemClickListener = null ;
    public CardAdapter(){
        super();
        mItems = new ArrayList<Training_Institution>();
    }

    public void addData(Training_Institution training_institution,String Company,boolean Address) throws IOException {
//        Log.e(TAG, "addData: "+response );
//        Gson gson = new Gson();
//        if(!response.equals("")&&response!=null) {
//
//            List<Training_Institution> foosList = gson.fromJson(response, new TypeToken<List<Training_Institution>>(){}.getType());
//            for (Training_Institution ti : foosList) {
//                mItems.add(ti);
//            }
//        }
        this.mCompany = Company;
        this.IsCheckAddress = Address;
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
        v.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Training_Institution training_institution = mItems.get(position);
        //使部分字变成红色
        if(!IsCheckAddress) {
            //公司名部分变成红色
            if (mCompany.equals(training_institution.getName())) {
                holder.namee.setText(Html.fromHtml("<font color='#ff0000'>" + training_institution.getName() + "</font>"));
            } else {
                String[] split_company = training_institution.getName().split(mCompany);
                if (split_company.length > 1) {
                    holder.namee.setText(Html.fromHtml(split_company[0] + "<font color='#ff0000'>" + mCompany + "</font>" + split_company[1]));
                } else {
                    holder.namee.setText(training_institution.getName());
                }
            }
            holder.addresss.setText(training_institution.getAddress());
        }else {
            holder.namee.setText(training_institution.getName());
            //地址部分变成红色
            String[] split_address = training_institution.getAddress().split("号");
            holder.addresss.setText(Html.fromHtml("<font color='#ff0000'>" + split_address[0]+ "号"+"</font>"+split_address[1]));
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener1){
        this.onItemClickListener = onItemClickListener1;
    }

    public Training_Institution getItemInformation(int position){
        return mItems.get(position);
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

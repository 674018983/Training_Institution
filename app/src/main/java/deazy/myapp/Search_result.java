package deazy.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import Adapter.CardAdapter;
import Navigation_UI.Dialog.Waiting_Dialog;

/**
 * Created by XZC on 2017/4/26.
 */

public class Search_result extends Activity {

    private RecyclerView mRecyclerView;
    private ImageView mBack;
    private CardAdapter mCardAdapter;
    private Waiting_Dialog waiting_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.act_search_result);
        init();
        showInformation();
    }



    private void init() {
        mBack = (ImageView) findViewById(R.id.back);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_result_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);


    }
    private void showInformation() {
        waiting_dialog = new Waiting_Dialog(Search_result.this);
        waiting_dialog.show();
    }
}

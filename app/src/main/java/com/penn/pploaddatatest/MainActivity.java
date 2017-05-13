package com.penn.pploaddatatest;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PPLoadController.LoadDataProvider {
    SwipeRefreshLayout swipeRefreshLayout;
    PPAdapter2 ppAdapter2;
    LinearLayoutManager linearLayoutManager;
    PPLoadController ppLoadController;

    int i = 0;
    int j = 0;
    long baseLong = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.main_rv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_srl);
        linearLayoutManager = new LinearLayoutManager(this);
        ArrayList<Long> data = new ArrayList();
        for (int k = 0; k < 10; k++) {
            data.add(baseLong + j++);
        }
        ppAdapter2 = new PPAdapter2(data);

        ppLoadController = new PPLoadController(swipeRefreshLayout, rv, ppAdapter2, linearLayoutManager, this);
    }

    @Override
    public void refreshData() {
        i = 0;
        j = 0;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Long> data = new ArrayList();
                for (int k = 0; k < 10; k++) {
                    data.add(baseLong + j++);
                }
                //success with more
                ppLoadController.ppLoadDataAdapter.getRefreshData(data, true);
                ppLoadController.endRefreshSpinner();

                //success with no more
                //done(true)

                //failed
                //quit("test failed");
            }
        }, 2000);
    }

    @Override
    public void loadMoreData() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Long> data = new ArrayList();
                for (int k = 0; k < 10; k++) {
                    data.add(baseLong + j++);
                }
                //success with more
                ppLoadController.ppLoadDataAdapter.loadMoreEnd(data, ++i == 2);
                ppLoadController.removeLoadMoreSpinner();
                //success with no more
                //done(true)

                //failed
                //quit("test failed");
            }
        }, 2000);
    }
}

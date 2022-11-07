package com.sasoftbd.machine_learing_ml_kit.AutoScrollingBanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;


import com.sasoftbd.machine_learing_ml_kit.R;

import java.util.Timer;
import java.util.TimerTask;

public class AutoScrollingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AutoScrollingAdapter autoScrollingAdapter;
    LinearLayoutManager layoutManager;
    public static Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scrolling);

        recyclerView = findViewById(R.id.id_banner_recycler);
        b1 = findViewById(R.id.button4);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

        SetRV();

        b2.setBackgroundColor(Color.BLACK);
        b1.setBackgroundColor(Color.GREEN);
        b3.setBackgroundColor(Color.BLACK);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                changeColor(layoutManager.findLastVisibleItemPosition());
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }


    public void changeColor(int a) {
        if (a == 0) {
            b2.setBackgroundColor(Color.BLACK);
            b1.setBackgroundColor(Color.GREEN);
            b3.setBackgroundColor(Color.BLACK);
        }

        if (a == 1) {
            b2.setBackgroundColor(Color.GREEN);
            b1.setBackgroundColor(Color.BLACK);
            b3.setBackgroundColor(Color.BLACK);
        }
        if (a == 2) {
            b3.setBackgroundColor(Color.GREEN);
            b2.setBackgroundColor(Color.BLACK);
            b1.setBackgroundColor(Color.BLACK);
        }


    }

    private void SetRV() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        autoScrollingAdapter = new AutoScrollingAdapter(this);
        recyclerView.setAdapter(autoScrollingAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (layoutManager.findLastCompletelyVisibleItemPosition() < (autoScrollingAdapter.getItemCount() - 1)) {
                    layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), layoutManager.findLastCompletelyVisibleItemPosition() + 1);

                } else if (layoutManager.findLastCompletelyVisibleItemPosition() < (autoScrollingAdapter.getItemCount() - 1)) {
                    layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);

                } else {
                    layoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                }
            }
        }, 0, 3000);
    }
}
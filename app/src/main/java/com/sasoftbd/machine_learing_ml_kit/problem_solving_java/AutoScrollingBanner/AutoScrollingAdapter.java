package com.sasoftbd.machine_learing_ml_kit.problem_solving_java.AutoScrollingBanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sasoftbd.machine_learing_ml_kit.R;


public class AutoScrollingAdapter extends RecyclerView.Adapter<AutoScrollingAdapter.ViewHolder> {

    View view;
    Context context;

    public AutoScrollingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_auto_scroll_banner, parent, false);

        AutoScrollingAdapter.ViewHolder att = new AutoScrollingAdapter.ViewHolder(view);
        att.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), "Clicked Position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return att;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText("Position : " + position);

        if (position == 0) {
            holder.bannerImage.setImageResource(R.drawable.a022);
        }
        if (position == 1) {
            holder.bannerImage.setImageResource(R.drawable.a033);
        }
        if (position == 2) {
            holder.bannerImage.setImageResource(R.drawable.a011);
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView bannerImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_position);
            bannerImage = itemView.findViewById(R.id.imageView7);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }

        private AutoScrollingAdapter.ViewHolder.ClickListener mClickListener;

        public interface ClickListener {
            void onItemClick(View view, int position);
        }

        public void setOnClickListener(AutoScrollingAdapter.ViewHolder.ClickListener clickListener) {
            mClickListener = clickListener;
        }
    }
}

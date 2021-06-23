package com.example.testall.cardview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testall.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<Movie> movieList;
    private ishanClickListener<Movie> clickListener;
    private  Context appctx;

    public RecyclerViewAdapter(List<Movie> movieList,Context ctx) {
        this.movieList = movieList;
        this.appctx=ctx;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {

        final Movie movie = movieList.get(position);

        holder.title.setText(movie.getTitle());
        Glide.with(appctx).load(movie.getImage()).into(holder.image);

      //  holder.image.setBackgroundResource(movie.getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(movie);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setOnItemClickListener(ishanClickListener<Movie> movieClickListener) {
        this.clickListener = movieClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtname);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.carView);
        }
    }
    interface myclicklistner<T> {
        void onItemClick(T data);
    }

}




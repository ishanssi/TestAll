package com.example.testall.nestedrecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testall.R;
import com.example.testall.cardview.Movie;
import com.example.testall.cardview.ishanClickListener;
import com.example.testall.contactgallery.Contatcsmodel;
import com.example.testall.contactgallery.ishanClickListenerforcontatcs;

import java.util.ArrayList;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<ChildModel> childModelArrayList;
    Context cxt;
    private ishanClickListenerforcontatcs<Contatcsmodel> contactclicklistner;
    private ishanClickListener<Movie> clickListener;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView heroImage;
        public TextView movieName;

        public MyViewHolder(View itemView) {
            super(itemView);
            heroImage = itemView.findViewById(R.id.hero_image);
            movieName = itemView.findViewById(R.id.movie_name);
        }
    }

    public ChildRecyclerViewAdapter(ArrayList<ChildModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel currentItem = childModelArrayList.get(position);
        holder.heroImage.setImageResource(currentItem.getHeroImage());
        holder.movieName.setText(currentItem.getMovieName());

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}
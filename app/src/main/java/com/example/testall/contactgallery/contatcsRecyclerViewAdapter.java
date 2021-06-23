package com.example.testall.contactgallery;

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

 public class contatcsRecyclerViewAdapter  extends RecyclerView.Adapter<contatcsRecyclerViewAdapter.MyViewHolder>{

    private List<Contatcsmodel> contatcsList;
    private ishanClickListenerforcontatcs<Contatcsmodel> clickListener;
    private Context appctx;

    public contatcsRecyclerViewAdapter(List<Contatcsmodel> contatcslist, Context ctx) {
        this.contatcsList = contatcslist;
        this.appctx=ctx;
    }

    @Override
    public contatcsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactgalerycard, parent, false);
        return new contatcsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(contatcsRecyclerViewAdapter.MyViewHolder holder, final int position) {

        final Contatcsmodel contatcsobj = contatcsList.get(position);


        holder.name.setText(contatcsobj.getName());
        holder.email.setText(contatcsobj.getEmail());
        holder.mobile.setText(contatcsobj.getMobile());
        Glide.with(appctx).load(contatcsobj.getImageurl()).into(holder.image);

        //  holder.image.setBackgroundResource(movie.getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClickcontacs(contatcsobj);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contatcsList.size();
    }

    public void setOnItemClickListener(ishanClickListenerforcontatcs<Contatcsmodel> movieClickListener) {
        this.clickListener = movieClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView email;
        private TextView mobile;
        private ImageView image;

        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtname);
            email = itemView.findViewById(R.id.txtemail);
             mobile = itemView.findViewById(R.id.txtmobile);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.carView);
        }
    }


}




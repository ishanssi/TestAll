package com.example.testall.nestedrecycleview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testall.R;
import com.example.testall.cardview.Movie;
import com.example.testall.cardview.RecyclerViewAdapter;
import com.example.testall.cardview.ishanClickListener;
import com.example.testall.collassingactivity;
import com.example.testall.contactgallery.Constants;
import com.example.testall.contactgallery.Contatcsmodel;
import com.example.testall.contactgallery.contatcsRecyclerViewAdapter;
import com.example.testall.contactgallery.ishanClickListenerforcontatcs;
import com.example.testall.databinding.FragmentGalleryBinding;
import com.example.testall.ui.gallery.GalleryViewModel;
import com.example.testall.ui.home.HomeFragment;
import com.google.android.material.badge.BadgeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofitdata.Api;

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<ParentModel> parentModelArrayList;
    public Context cxt;
    Movie movieobj;
    private List<Movie> movieList;
    boolean firstitemloaded= false;

    private ishanClickListenerforcontatcs<Contatcsmodel> contactclicklistner;
    private ishanClickListener<Movie> clickListener;

    private ProgressDialog pDialog;
    private List<Contatcsmodel> contatclist;
    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    Contatcsmodel contatcobj;
    MyViewHolder newholder;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView category;
        public Button btnviewall;
        public RecyclerView childRecyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.Movie_category);
            btnviewall = itemView.findViewById(R.id.btnviewall);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);
        }
    }

    public ParentRecyclerViewAdapter(ArrayList<ParentModel> exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    public void setOnItemClickListener(ishanClickListenerforcontatcs<Contatcsmodel> contatcclicklitner) {
        this.contactclicklistner = contatcclicklitner;
    }

    public void setOnItemClickListener(ishanClickListener<Movie> movieClickListener) {
        this.clickListener = movieClickListener;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        this.newholder=holder;
        ParentModel currentItem = parentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);

        holder.category.setText(currentItem.movieCategory());

        ArrayList<ChildModel> arrayList = new ArrayList<>();

        Log.d("pakayo", "contact list size is from home is   "+HomeFragment.contatclist2.size());
      //  getcontatcs();
        // added the first child row
        if (parentModelArrayList.get(position).movieCategory().equals("Category1")) {


            holder.btnviewall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                             Toast.makeText(cxt,"Category1",Toast.LENGTH_LONG).show();

                }
            });

//          loaddata();

     //Log.d("pakaya", "contact list size is  "+contatclist.size());
        contatcsRecyclerViewAdapter recyclerViewAdapter = new contatcsRecyclerViewAdapter(HomeFragment.contatclist2,cxt);
        holder.childRecyclerView.setAdapter(recyclerViewAdapter);



            recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforcontatcs<Contatcsmodel>() {
                @Override
                public void onItemClickcontacs(Contatcsmodel postion) {
                    Toast.makeText(cxt, postion.getEmail(), Toast.LENGTH_SHORT).show();
                }


            });

//
//            arrayList.add(new ChildModel(R.drawable.themartian,"Movie Name"));
//            arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//            arrayList.add(new ChildModel( R.drawable.mov2,"Movie Name"));
//            arrayList.add(new ChildModel( R.drawable.blackp,"Movie Name"));
//            arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//            arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//
//           ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
//            holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);









        }

            // added in second child row
            if (parentModelArrayList.get(position).movieCategory().equals("Category2")) {

            prepareMovie();

                holder.btnviewall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(cxt,"Category2",Toast.LENGTH_LONG).show();

                    }
                });

            //    getcontatcs();
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//
//                ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
//                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
            }

            // added in third child row
            if (parentModelArrayList.get(position).movieCategory().equals("Category3")) {
                contatcsRecyclerViewAdapter recyclerViewAdapter = new contatcsRecyclerViewAdapter(HomeFragment.contatclist2,cxt);
                holder.childRecyclerView.setAdapter(recyclerViewAdapter);

                recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforcontatcs<Contatcsmodel>() {
                    @Override
                    public void onItemClickcontacs(Contatcsmodel postion) {
                        Toast.makeText(cxt, postion.getEmail(), Toast.LENGTH_SHORT).show();
                    }


                });

                holder.btnviewall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(cxt,"Category3",Toast.LENGTH_LONG).show();

                    }
                });

                //getcontatcs();
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//
//                ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
//                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
            }

            // added in fourth child row
            if (parentModelArrayList.get(position).movieCategory().equals("Category4")) {
                contatcsRecyclerViewAdapter recyclerViewAdapter = new contatcsRecyclerViewAdapter(HomeFragment.contatclist2,cxt);
                holder.childRecyclerView.setAdapter(recyclerViewAdapter);

                recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforcontatcs<Contatcsmodel>() {
                    @Override
                    public void onItemClickcontacs(Contatcsmodel postion) {
                        Toast.makeText(cxt, postion.getEmail(), Toast.LENGTH_SHORT).show();
                    }


                });


                holder.btnviewall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(cxt,"Category4",Toast.LENGTH_LONG).show();

                    }
                });
               // getcontatcs();
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//
//                ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
//                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
            }

            // added in fifth child row
            if (parentModelArrayList.get(position).movieCategory().equals("Category5")) {



                holder.btnviewall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Toast.makeText(cxt,"Category5",Toast.LENGTH_LONG).show();
                    }
                });


                contatcsRecyclerViewAdapter recyclerViewAdapter = new contatcsRecyclerViewAdapter(HomeFragment.contatclist2,cxt);
                holder.childRecyclerView.setAdapter(recyclerViewAdapter);

                recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforcontatcs<Contatcsmodel>() {
                    @Override
                    public void onItemClickcontacs(Contatcsmodel postion) {
                        Toast.makeText(cxt, postion.getEmail(), Toast.LENGTH_SHORT).show();
                    }


                });

                //getcontatcs();
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.mov2,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//
//                ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
//                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
            }

            // added in sixth child row
            if (parentModelArrayList.get(position).movieCategory().equals("Category6")) {


                holder.btnviewall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(cxt,"Category6",Toast.LENGTH_LONG).show();

                    }
                });

                contatcsRecyclerViewAdapter recyclerViewAdapter = new contatcsRecyclerViewAdapter(HomeFragment.contatclist2,cxt);
                holder.childRecyclerView.setAdapter(recyclerViewAdapter);

                recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforcontatcs<Contatcsmodel>() {
                    @Override
                    public void onItemClickcontacs(Contatcsmodel postion) {
                        Toast.makeText(cxt, postion.getEmail(), Toast.LENGTH_SHORT).show();
                    }


                });

                //getcontatcs();
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.blackp,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel( R.drawable.moana,"Movie Name"));
//                arrayList.add(new ChildModel(R.drawable.moana,"Movie Name"));
//
//                ChildRecyclerViewAdapter childRecyclerViewAdapter = new ChildRecyclerViewAdapter(arrayList,holder.childRecyclerView.getContext());
//                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
            }






    }


    private void prepareMovie(){
        movieList = new ArrayList<>();
        movieobj = new Movie("Star Wars The Last Jedi","https://st2.cricketcountry.com/wp-content/uploads/cricket/image_20130828123507.jpg");
        movieList.add(movieobj);
        movieobj = new Movie("Onella walles","https://media-exp1.licdn.com/dms/image/C5103AQGxyMkF9NwxKA/profile-displayphoto-shrink_100_100/0/1534682013829?e=1626307200&v=beta&t=nB7hBiGfoQsdEY_fM4NiHkjbleouMNeWDoEx7ToZiQo");
        movieList.add(movieobj);
        movieobj = new Movie("Justice League ","https://www.topblogmania.com/wp-content/uploads/2019/09/skysports-lasith-malinga-sri-lanka_4688166.jpg");
        movieList.add(movieobj);

        RecyclerViewAdapter   recyclerViewAdapter = new RecyclerViewAdapter(movieList,cxt);
        newholder.childRecyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new ishanClickListener<Movie>() {
            @Override
            public void onItemClick(Movie postion) {
                Toast.makeText(cxt, postion.getTitle(), Toast.LENGTH_SHORT).show();
            }




        });


       // getcontatcs();



       // Log.d("asd","list size is "+movieList.size());
        // recyclerViewAdapter.notifyDataSetChanged();

    }
//    private void getcontatcs() {
//        Toast.makeText(cxt, "getting contatcs new", Toast.LENGTH_LONG).show();
//
//        //
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .baseUrl(Api.BASE_URL)
//                .build();
//
//        Api scalarService = retrofit.create(Api.class);
//        Call<String> stringCall = scalarService.getStringResponse();
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    String responseString = response.body();
//                    Log.d("Ishan data ","response is new "+responseString);
//
//                    decodejson(responseString);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.d("Ishan data ","response is fail "+t.toString());
//            }
//        });
//
//
//    }
//
//    private void decodejson(String responseString) {
//        hidePDialog();
//        try {
//
//            JSONObject jObject = new JSONObject(responseString);
//            Log.d(Constants.TAG, "all json objetcs are  "+jObject.getString("contacts"));
//            JSONArray ja_data = jObject.getJSONArray("contacts");
//
//            Log.d(Constants.TAG, "data lenth is   "+ja_data.length());
//            int length = ja_data .length();
//            contatclist = new ArrayList<>();
//            for(int i=0; i<length; i++)
//            {
//                Log.d(Constants.TAG, "into forloop ");
//                JSONObject jObj = ja_data.getJSONObject(i);
//                Log.d(Constants.TAG, "name is  "+jObj.getString("name"));
//                //getting inner array Ingredients
//                String id = jObj.getString("id");
//                String name = jObj.getString("name");
//                String email = jObj.getString("email");
//                String address = jObj.getString("address");
//                String gender = jObj.getString("gender");
//
//
//                JSONObject phone = jObj.getJSONObject("phone");
//                String mobile = phone.getString("mobile");
//                String home = phone.getString("home");
//                String office = phone.getString("office");
//
//
//                contatcobj= new Contatcsmodel(id,name,email,gender,mobile);
//                contatclist.add(contatcobj);
//
//
//
//
//            }
//
//            Log.d(Constants.TAG, "contact list sie is  "+contatclist.size());
//
//
//         loaddata();
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//            hidePDialog();
//        }
//
//
//    }

    private void loaddata() {
//


          contatcsRecyclerViewAdapter recyclerViewAdapter = new contatcsRecyclerViewAdapter(contatclist,cxt);
          newholder.childRecyclerView.setAdapter(recyclerViewAdapter);
          firstitemloaded=true;




    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
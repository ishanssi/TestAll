package com.example.testall.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.testall.MainActivity;
import com.example.testall.R;
import com.example.testall.cardview.Movie;
import com.example.testall.cardview.RecyclerViewAdapter;
import com.example.testall.cardview.ishanClickListener;
import com.example.testall.collassingactivity;
import com.example.testall.contactgallery.Constants;
import com.example.testall.contactgallery.Contatcsmodel;
import com.example.testall.contactgallery.GridSpacingItemDecoration;
import com.example.testall.databinding.FragmentHomeBinding;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
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

public class HomeFragment extends Fragment {
    Movie movieobj;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private RecyclerView recyclerView5;
    private RecyclerView recyclerView6;
    private RecyclerView recyclerView7;
    private List<Contatcsmodel> contatclist;
    public static List<Contatcsmodel> contatclist2;
    CarouselView aa;

   public static Contatcsmodel contatcobj;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Movie> movieList;
    public static List<String> imageurllist;
    RecyclerView.LayoutManager layoutManager;
    AsyncTask<?, ?, ?> runningTask;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        prepareMovie();

loadimageurllist();

            new LongOperation().execute();




         GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
         recyclerView = binding.resview;
         recyclerViewAdapter = new RecyclerViewAdapter(movieList,getContext());
         layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(recyclerViewAdapter);

        //recyclerViewAdapter = new RecyclerViewAdapter(movieList,getContext());
        //recyclerView.setAdapter(recyclerViewAdapter);



        ImageCarousel carousel = binding.carousel;
        List<CarouselItem> list = new ArrayList<>();
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080",
                        "Photo by Aaron Wu on Unsplash"
                )
        );

// Just image URL
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );

// Image drawable with caption
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );

// Just image drawable
        list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
                )
        );

        carousel.addData(list);













        recyclerViewAdapter.setOnItemClickListener(new ishanClickListener<Movie>() {
            @Override
            public void onItemClick(Movie data) {
                Toast.makeText(getContext(), data.getTitle(), Toast.LENGTH_SHORT).show();

                Intent ii=new Intent(getContext(), collassingactivity.class);
                ii.putExtra("title", data.getTitle());
                ii.putExtra("imageurl", data.getImage());
                startActivity(ii);

            }
        });





//        final SwipeRefreshLayout refreshlayout = binding.swiperefresh;
//        refreshlayout.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        Toast.makeText(getContext(),"refreshing",Toast.LENGTH_LONG).show();
//
//                        // This method performs the actual data-refresh operation.
//                        // The method calls setRefreshing(false) when it's finished.
//                       // myUpdateOperation();
//
//                        refreshlayout.setRefreshing(false);
//                    }
//
//                }
//        );

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//           // public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void loadimageurllist() {



        imageurllist= new ArrayList<>();

        imageurllist.add("https://s01.sgp1.cdn.digitaloceanspaces.com/article/120309-wkljqrtthy-1558623272.jpg");
        imageurllist.add("https://ss.thgim.com/cricket/article27267668.ece/alternates/FREE_380/dimuth-karunaratnejpg");
        imageurllist.add("http://s3.amazonaws.com/themorning-aruna/wp-content/uploads/2019/09/07154420/Malinga_Four_in_Four_07092019_ee16_REVW.jpg");
        imageurllist.add("https://images.outlookindia.com/public/uploads/articles/2020/7/5/Kusal-Mendis-AP-file_571_855.jpg");
        imageurllist.add("https://bsmedia.business-standard.com/_media/bs/img/article/2019-06/21/full/1561129291-1719.jpg");
        imageurllist.add("https://www.mykhel.com/img/2021/05/kusal-perera-sri-lanka-1620812958.jpg");
        imageurllist.add("https://cricketaddictor.gumlet.io/wp-content/uploads/2021/02/jayawardene-file-rsgopan.jpg?compress=true&quality=80&w=768&dpr=2.6");
        imageurllist.add("https://images.newindianexpress.com/uploads/user/imagelibrary/2020/5/25/w900X450/Shehan_Madushanka_AFP.jpg");
        imageurllist.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNH8ZINKXb85FkPAUhyxvAbIagcofajRCTiw&usqp=CAU");
        imageurllist.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2uHAAwLsd8REJXImMdNYk8PLMM7E7Y6KLEA&usqp=CAU");
        imageurllist.add("https://s3.ap-southeast-1.amazonaws.com/images.deccanchronicle.com/dc-Cover-hh59vf3pr2hmrikn36vgtfqi10-20170629005324.Medi.jpeg");
        imageurllist.add("https://assets.telegraphindia.com/telegraph/23spomalinga.jpg");
        imageurllist.add("https://www.newswire.lk/wp-content/uploads/2020/09/MM.jpg");
    }


    private void prepareMovie(){
        movieList = new ArrayList<>();
        movieobj = new Movie("Star Wars The Last Jedi","https://st2.cricketcountry.com/wp-content/uploads/cricket/image_20130828123507.jpg");
        movieList.add(movieobj);
        movieobj = new Movie("Onella walles","https://media-exp1.licdn.com/dms/image/C5103AQGxyMkF9NwxKA/profile-displayphoto-shrink_100_100/0/1534682013829?e=1626307200&v=beta&t=nB7hBiGfoQsdEY_fM4NiHkjbleouMNeWDoEx7ToZiQo");
        movieList.add(movieobj);
        movieobj = new Movie("Justice League ","https://www.topblogmania.com/wp-content/uploads/2019/09/skysports-lasith-malinga-sri-lanka_4688166.jpg");
        movieList.add(movieobj);
        movieobj = new Movie("Thor: Ragnarok","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQofkOjKhnaChctU2NMCgw8YeRxsSNBLtXTPA&usqp=CAU");
        movieList.add(movieobj);
        movieobj = new Movie("Thor: Ragnarok","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Star Wars The Last Jedi","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Coco","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Justice League ","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Thor: Ragnarok","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Star Wars The Last Jedi","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Coco","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Justice League ","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);
        movieobj = new Movie("Thor: Ragnarok","https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/21/w1200X800/Lasith_Malinga_AP_Photo.png");
        movieList.add(movieobj);


        Log.d("asd","list size is "+movieList.size());
       // recyclerViewAdapter.notifyDataSetChanged();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


     public  void getcontacts()
     {



       //  Toast.makeText(cxt, "getting contatcs new", Toast.LENGTH_LONG).show();

         //

         Retrofit retrofit = new Retrofit.Builder()
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .baseUrl(Api.BASE_URL)
                 .build();

         Api scalarService = retrofit.create(Api.class);
         Call<String> stringCall = scalarService.getStringResponse();
         stringCall.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 if (response.isSuccessful()) {
                     String responseString = response.body();
                     Log.d("Ishan data ","response is new "+responseString);

                     decodejson(responseString);
                 }

             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 Log.d("Ishan data ","response is fail "+t.toString());
             }
         });



     }
    private void decodejson(String responseString) {
       // hidePDialog();
        try {

            JSONObject jObject = new JSONObject(responseString);
            Log.d(Constants.TAG, "all json objetcs are  "+jObject.getString("contacts"));
            JSONArray ja_data = jObject.getJSONArray("contacts");

            Log.d(Constants.TAG, "data lenth is   "+ja_data.length());
            int length = ja_data .length();
            contatclist = new ArrayList<>();
            for(int i=0; i<length; i++)
            {
                Log.d(Constants.TAG, "into forloop ");
                JSONObject jObj = ja_data.getJSONObject(i);
                Log.d(Constants.TAG, "name is  "+jObj.getString("name"));
                //getting inner array Ingredients
                String id = jObj.getString("id");
                String name = jObj.getString("name");
                String email = jObj.getString("email");
                String address = jObj.getString("address");
                String gender = jObj.getString("gender");


                JSONObject phone = jObj.getJSONObject("phone");
                String mobile = phone.getString("mobile");
                String home = phone.getString("home");
                String office = phone.getString("office");





                imageurllist= new ArrayList<>();

                imageurllist.add("https://s01.sgp1.cdn.digitaloceanspaces.com/article/120309-wkljqrtthy-1558623272.jpg");
                imageurllist.add("https://ss.thgim.com/cricket/article27267668.ece/alternates/FREE_380/dimuth-karunaratnejpg");
                imageurllist.add("http://s3.amazonaws.com/themorning-aruna/wp-content/uploads/2019/09/07154420/Malinga_Four_in_Four_07092019_ee16_REVW.jpg");
                imageurllist.add("https://images.outlookindia.com/public/uploads/articles/2020/7/5/Kusal-Mendis-AP-file_571_855.jpg");
                imageurllist.add("https://bsmedia.business-standard.com/_media/bs/img/article/2019-06/21/full/1561129291-1719.jpg");
                imageurllist.add("https://www.mykhel.com/img/2021/05/kusal-perera-sri-lanka-1620812958.jpg");
                imageurllist.add("https://cricketaddictor.gumlet.io/wp-content/uploads/2021/02/jayawardene-file-rsgopan.jpg?compress=true&quality=80&w=768&dpr=2.6");
                imageurllist.add("https://images.newindianexpress.com/uploads/user/imagelibrary/2020/5/25/w900X450/Shehan_Madushanka_AFP.jpg");
                imageurllist.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNH8ZINKXb85FkPAUhyxvAbIagcofajRCTiw&usqp=CAU");
                imageurllist.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2uHAAwLsd8REJXImMdNYk8PLMM7E7Y6KLEA&usqp=CAU");
                imageurllist.add("https://s3.ap-southeast-1.amazonaws.com/images.deccanchronicle.com/dc-Cover-hh59vf3pr2hmrikn36vgtfqi10-20170629005324.Medi.jpeg");
                imageurllist.add("https://assets.telegraphindia.com/telegraph/23spomalinga.jpg");
                imageurllist.add("https://www.newswire.lk/wp-content/uploads/2020/09/MM.jpg");


                contatcobj= new Contatcsmodel(id,imageurllist.get(i),name,email,gender,mobile);
                contatclist.add(contatcobj);




            }

            Log.d(Constants.TAG, "contact list sie is  "+contatclist.size());





        } catch (JSONException e) {
            e.printStackTrace();


        }


    }



        public static final class LongOperation extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {


                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .baseUrl(Api.BASE_URL)
                        .build();

                Api scalarService = retrofit.create(Api.class);
                Call<String> stringCall = scalarService.getStringResponse();
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseString = response.body();
                            Log.d("Ishan data ","response is new "+responseString);

                            decodejson2(responseString);
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Ishan data ","response is fail "+t.toString());
                    }
                });
                return "Executed";
            }

            @Override
            protected void onPostExecute(String result) {
              //  TextView txt = (TextView) findViewById(R.id.output);
               // txt.setText("Executed"); // txt.setText(result);
                // You might want to change "executed" for the returned string
                // passed into onPostExecute(), but that is up to you
            }
        }

    private static void decodejson2(String responseString) {
      //  hidePDialog();
        try {

            JSONObject jObject = new JSONObject(responseString);
            Log.d(Constants.TAG, "all json objetcs are  "+jObject.getString("contacts"));
            JSONArray ja_data = jObject.getJSONArray("contacts");

            Log.d(Constants.TAG, "data lenth is   "+ja_data.length());
            int length = ja_data .length();
            contatclist2 = new ArrayList<>();
            for(int i=0; i<length; i++)
            {
                Log.d(Constants.TAG, "into forloop ");
                JSONObject jObj = ja_data.getJSONObject(i);
                Log.d(Constants.TAG, "name is  "+jObj.getString("name"));
                //getting inner array Ingredients
                String id = jObj.getString("id");
                String name = jObj.getString("name");
                String email = jObj.getString("email");
                String address = jObj.getString("address");
                String gender = jObj.getString("gender");


                JSONObject phone = jObj.getJSONObject("phone");
                String mobile = phone.getString("mobile");
                String home = phone.getString("home");
                String office = phone.getString("office");




                contatcobj= new Contatcsmodel(id,imageurllist.get(i),name,email,gender,mobile);
                contatclist2.add(contatcobj);



//
//
//                contatcobj= new Contatcsmodel(id,imgurl,name,email,gender,mobile);
//                contatclist2.add(contatcobj);




            }

           // Log.d(Constants.TAG, "contact list sie is  "+contatclist.size());


           // loaddata();


        } catch (JSONException e) {
            e.printStackTrace();

           // hidePDialog();
        }


    }


}
package com.example.testall.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testall.cardview.Movie;
import com.example.testall.cardview.RecyclerViewAdapter;
import com.example.testall.cardview.ishanClickListener;
import com.example.testall.collassingactivity;
import com.example.testall.contactgallery.Constants;
import com.example.testall.contactgallery.Contatcsmodel;
import com.example.testall.contactgallery.GridSpacingItemDecoration;
import com.example.testall.contactgallery.contatcsRecyclerViewAdapter;
import com.example.testall.contactgallery.ishanClickListenerforcontatcs;
import com.example.testall.databinding.FragmentGalleryBinding;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import needle.Needle;
import retrofit2.Call;
import retrofit2.Callback;
 import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofitdata.Api;

import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonObject;

public class GalleryFragment extends Fragment {

    private ProgressDialog pDialog;
    private List<Contatcsmodel> contatclist;
    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    Contatcsmodel contatcobj;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private contatcsRecyclerViewAdapter recyclerViewAdapter;

    private static final String url = "https://api.androidhive.info/contatcs";
   // ArrayList<HashMap<String, String>> contactList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading data...");
       pDialog.show();

        ///////



                //////

      getcontatcs();

//        getcontatcs();




        return root;
    }

    private void loaddata() {

        Log.d(Constants.TAG, "contact list size is on load data "+contatclist.size());
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView = binding.resviewgallery;
        recyclerViewAdapter = new contatcsRecyclerViewAdapter(contatclist,getContext());
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);






        Log.d("data loaded with ","data loaded with  "+contatclist.size());

        recyclerViewAdapter.setOnItemClickListener(new ishanClickListenerforcontatcs<Contatcsmodel>() {
            @Override
            public void onItemClickcontacs(Contatcsmodel postion) {

                Toast.makeText(getContext(), postion.getEmail(), Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void getcontatcs() {
        Toast.makeText(getContext(), "getting contatcs", Toast.LENGTH_LONG).show();
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
                    Log.d("Ishan data ","response is "+responseString);

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
        hidePDialog();
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


                ArrayList<String>   imageurllist= new ArrayList<>();

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


 loaddata();


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("isha error is   ", e.toString());
            hidePDialog();
        }


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


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
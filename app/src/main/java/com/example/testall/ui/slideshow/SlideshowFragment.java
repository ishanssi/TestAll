package com.example.testall.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testall.MainActivity;
import com.example.testall.R;
import com.example.testall.cardview.Movie;
import com.example.testall.cardview.ishanClickListener;
import com.example.testall.collassingactivity;
import com.example.testall.contactgallery.Contatcsmodel;
import com.example.testall.contactgallery.ishanClickListenerforcontatcs;
import com.example.testall.databinding.FragmentSlideshowBinding;
import com.example.testall.nestedrecycleview.ParentModel;
import com.example.testall.nestedrecycleview.ParentRecyclerViewAdapter;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    boolean loading = true;
    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




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



        parentModelArrayList.add(new ParentModel("Category1"));
        parentModelArrayList.add(new ParentModel("Category2"));
        parentModelArrayList.add(new ParentModel("Category3"));
        parentModelArrayList.add(new ParentModel("Category4"));
        parentModelArrayList.add(new ParentModel("Category5"));
        parentModelArrayList.add(new ParentModel("Category6"));

        parentRecyclerView = binding.parentresview;
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(getContext());
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, getContext());
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();







     //   Log.d("data loaded with ","data loaded with  "+contatclist.size());






//
//
//
//        ImageCarousel carousel = binding.carousel;
//        List<CarouselItem> list = new ArrayList<>();
//        list.add(
//                new CarouselItem(
//                        "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080",
//                        "Photo by Aaron Wu on Unsplash"
//                )
//        );
//
//// Just image URL
//        list.add(
//                new CarouselItem(
//                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
//                )
//        );
//
//// Image drawable with caption
//        list.add(
//                new CarouselItem(
//                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
//                )
//        );
//
//// Just image drawable
//        list.add(
//                new CarouselItem(
//                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
//                )
//        );
//
//        carousel.addData(list);


//
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.anjana.luxgan.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.ULocale;
import   android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anjana.luxgan.R;
import com.anjana.luxgan.activities.ShowAllActivity;
import com.anjana.luxgan.adapter.CategoryAdapter;
import com.anjana.luxgan.adapter.NewProductsAdapter;
import com.anjana.luxgan.adapter.PopularProductsAdapter;
import com.anjana.luxgan.adapter.ShowItemAdapter;
import com.anjana.luxgan.models.CategoryModel;
import com.anjana.luxgan.models.NewProductsModel;
import com.anjana.luxgan.models.PopularProductsModel;
import com.anjana.luxgan.models.ShowItemModel;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public  class HomeFragment extends Fragment {


    TextView catShowAll,popularShowAll,newProductShowAll, itemShowAll;

    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    RecyclerView catRecyclerview,newProductRecyclerview,popularRecyclerview, showallRecyclerview;

    //Category recycleview;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;



   // New product  Recyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    //Popular Product
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;

    //Show Item
    ShowItemAdapter showItemAdapter;
    List<ShowItemModel> showItemModelList;



 //Firestore
   FirebaseFirestore db ;
    public HomeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);

        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getActivity());
        catRecyclerview = root.findViewById(R.id.rec_category);
        newProductRecyclerview = root.findViewById(R.id.new_product_rec);
        popularRecyclerview = root.findViewById(R.id.popular_rec);
        showallRecyclerview=root.findViewById(R.id.show_rec);


        catShowAll =root.findViewById(R.id.category_see_all);
        popularShowAll =root.findViewById(R.id.popular_see_all);
        newProductShowAll =root.findViewById(R.id.newProducts_see_all);
        itemShowAll =root.findViewById(R.id.show_see_all);




        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        itemShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });


        linearLayout = root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);


        //Image Slider
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner4,"Discount on Rolex Collection", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner5,"Discount on Hublot ", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner6 ,"40% OFF", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome to Luxgan Store");
        progressDialog.setMessage("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    //Category
          catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
          categoryModelList = new ArrayList<>();
         categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
         catRecyclerview.setAdapter(categoryAdapter);

    db.collection("Category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){

                    CategoryModel categoryModel = document.toObject(CategoryModel.class);
                    categoryModelList.add(categoryModel);
                    categoryAdapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                }
            }else {
                Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();
            }
        }
    });
//New Products

 newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
 newProductsModelList = new ArrayList<>();
 newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList);
 newProductRecyclerview.setAdapter(newProductsAdapter);


        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){

                        NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                        newProductsModelList.add(newProductsModel);
                        newProductsAdapter.notifyDataSetChanged();
                    }
                }else {
                    Toast.makeText(getActivity(),""+task.getException(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        //Popular Products

        popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularRecyclerview.setAdapter(popularProductsAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                PopularProductsModel popularProductsModel= document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(),""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        //Show All item

        showallRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        showItemModelList = new ArrayList<>();
        showItemAdapter = new ShowItemAdapter(getContext(),showItemModelList);
        showallRecyclerview.setAdapter(showItemAdapter);

        db.collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                ShowItemModel showItemModel= document.toObject(ShowItemModel.class);
                                showItemModelList.add(showItemModel);
                                showItemAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(),""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        return root;

    }
}


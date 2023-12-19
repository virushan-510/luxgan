package com.anjana.luxgan.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjana.luxgan.R;
import com.anjana.luxgan.models.MyCartModel;
import com.anjana.luxgan.models.NewProductsModel;
import com.anjana.luxgan.models.PopularProductsModel;
import com.anjana.luxgan.models.ShowAllModel;
import com.anjana.luxgan.models.ShowItemModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailedImg;
    TextView rating,name,description,price,quantity;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;
    Toolbar toolbar;
    int totalQuantity = 1;
    int totalPrice = 0;

    //New Products
    NewProductsModel newProductsModel =null;

    //popular Product
    PopularProductsModel popularProductsModel = null;

    //Show All
    ShowAllModel showAllModel = null;

    //show item
    ShowItemModel showItemModel = null;

    MyCartModel myCartModel = null;
    FirebaseAuth auth;

    private  FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


         firestore =FirebaseFirestore.getInstance();
         auth = FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");
        if(obj instanceof NewProductsModel){
               newProductsModel = (NewProductsModel) obj;
        }else if (obj instanceof  PopularProductsModel){
            popularProductsModel = (PopularProductsModel) obj;
        } else if (obj instanceof  ShowAllModel){
            showAllModel = (ShowAllModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);
        quantity= findViewById(R.id.quantity);
        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.remove_item);

        //New Products
        if(newProductsModel != null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));

            totalPrice = newProductsModel.getPrice() * totalQuantity;

        }

        //Popular product
        if(popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));

            totalPrice = popularProductsModel.getPrice() * totalQuantity;
        }


        //show item
        if(showItemModel != null){
            Glide.with(getApplicationContext()).load(showItemModel.getImage()).into(detailedImg);
            name.setText(showItemModel.getName());
//            rating.setText(showItemModel.getRating());
//            description.setText(showItemModel.getDescription());
            price.setText(String.valueOf(showItemModel.getPrice()));

            totalPrice = popularProductsModel.getPrice() * totalQuantity;
        }

        //Show All Product
        if(showAllModel != null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            totalPrice = showAllModel.getPrice() * totalQuantity;

        }
        //Buy Now
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);
                if(newProductsModel!=null){
                    intent.putExtra("item",newProductsModel);
                }
                if(popularProductsModel != null){
                    intent.putExtra("item",popularProductsModel);

                }
                if(showAllModel != null){
                    intent.putExtra("item",showAllModel);

                }
                if(myCartModel != null){
                    intent.putExtra("item",myCartModel);
                }
                startActivity(intent);
            }
        });

        //Add To cart

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addtoCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(totalQuantity < 15){
                totalQuantity++;
                quantity.setText(String.valueOf(totalQuantity));
                if(newProductsModel != null){
                    totalPrice = newProductsModel.getPrice() * totalQuantity;
                }
                if(popularProductsModel != null){
                    totalPrice = popularProductsModel.getPrice() * totalQuantity;

                }if(showItemModel != null){
                    totalPrice = showItemModel.getPrice() * totalQuantity;
                }

                if(showAllModel != null){
                    totalPrice = showAllModel.getPrice() * totalQuantity;
                }

            }
          }
        });


        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    if(newProductsModel != null){
                        totalPrice = newProductsModel.getPrice() * totalQuantity;
                    }
                    if(popularProductsModel != null){
                        totalPrice = popularProductsModel.getPrice() * totalQuantity;

                    }if(showItemModel != null){
                        totalPrice = showItemModel.getPrice() * totalQuantity;
                    }

                    if(showAllModel != null){
                        totalPrice = showAllModel.getPrice() * totalQuantity;
                    }


                }
            }
        });
    }

    private void addtoCart () {
        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("ProductName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("totalPrice",totalPrice);
        cartMap.put("totalQuantity",quantity.getText().toString());


        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DetailedActivity.this,"Added To A Cart",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });











    }
}
package com.anjana.luxgan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anjana.luxgan.R;
import com.anjana.luxgan.activities.DetailedActivity;
import com.anjana.luxgan.models.ShowItemModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShowItemAdapter  extends RecyclerView.Adapter<ShowItemAdapter.ViewHolder> {

    private Context context;

    private List<ShowItemModel> showItemModelList;

    public ShowItemAdapter(Context context ,List<ShowItemModel>showItemModelList){
        this.context = context;
        this.showItemModelList =showItemModelList;
    }
    @NonNull
    @Override
    public ShowItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowItemAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(showItemModelList.get(position).getImage()).into(holder.imageView);
        holder.name.setText(showItemModelList.get(position).getName());
        holder.price.setText(String.valueOf( showItemModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                aniwa_DetailedActivity_gihin_balpan

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",showItemModelList.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return showItemModelList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.show_img);
            name = itemView.findViewById(R.id.show_product_name);
            price =itemView.findViewById(R.id.show_price);



        }
    }
}

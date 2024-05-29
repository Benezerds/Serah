package com.neztech.serah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;

import java.util.List;

public class PoppingRestoRecyclerViewAdapter extends RecyclerView.Adapter<PoppingRestoRecyclerViewAdapter.ViewHolder> {
    private List<Restaurant> restaurants;
    private LayoutInflater mInflater;

    public PoppingRestoRecyclerViewAdapter(Context context, List<Restaurant> restaurants) {
        this.mInflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public PoppingRestoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoppingRestoRecyclerViewAdapter.ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.restoName.setText(restaurant.getRestoName());
        holder.description.setText(restaurant.getDescription());
        holder.rating.setText(String.valueOf(restaurant.getRating()));
        // You need to handle the location and image loading based on your requirements
        //  Handle Image using picasso
        Picasso.get()
                .load(restaurant.getRestoImageUrl()) // replace with your image url
                .placeholder(R.drawable.resto) // optional, placeholder image while loading or in case of error
                .error(R.drawable.splash) // optional, image to use in case of error
                .into(holder.restoImage); // your ImageView to load the image into
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restoName;
        TextView description;
        TextView rating;
        ImageView restoImage;
        TextView distance;

        ViewHolder(View itemView) {
            super(itemView);
            restoName = itemView.findViewById(R.id.text_view_restoname);
            description = itemView.findViewById(R.id.text_view_resto_location_details);
            rating = itemView.findViewById(R.id.text_view_popping_rating);
            restoImage = itemView.findViewById(R.id.image_view_resto_popping);
            distance = itemView.findViewById(R.id.text_view_popping_distance);
        }
    }
}

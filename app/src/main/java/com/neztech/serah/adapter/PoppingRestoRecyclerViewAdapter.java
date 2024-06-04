package com.neztech.serah.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neztech.serah.model.User;
import com.neztech.serah.restaurant.RestaurantDetailsActivity;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neztech.serah.R;
import com.neztech.serah.model.Restaurant;

import java.util.List;

public class PoppingRestoRecyclerViewAdapter extends RecyclerView.Adapter<PoppingRestoRecyclerViewAdapter.ViewHolder> {
    private List<Restaurant> restaurants;
    private LayoutInflater mInflater;
    private Context context;
    private User user;

    public PoppingRestoRecyclerViewAdapter(Context context, List<Restaurant> restaurants, User user) {
        this.mInflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
        this.context = context;
        this.user = user;
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

        // Assuming user.getLocation() returns a string in the format "locationname,lat,lng"
        String[] userLocationParts = user.getLocation().split(",");
        double userLat = Double.parseDouble(userLocationParts[0]);
        double userLng = Double.parseDouble(userLocationParts[1]);

        // Assuming restaurant.getLocation() returns a string in the format "locationname,lat,lng"
        String[] restoLocationParts = restaurant.getLocation().split(",");
        double restoLat = Double.parseDouble(restoLocationParts[1]);
        double restoLng = Double.parseDouble(restoLocationParts[2]);

        // Calculate the distance between userLat, userLng and restoLat, restoLng
        double distance = calculateDistance(userLat, userLng, restoLat, restoLng);

        // Format the distance to one decimal place followed by "km"
        String formattedDistance = String.format("%.1fkm", distance);

        holder.restoName.setText(restaurant.getRestoName());
        holder.description.setText(restaurant.getDescription());

        // Handle Image using picasso
        Picasso.get()
                .load(restaurant.getRestoImageUrl())
                .placeholder(R.drawable.resto)
                .error(R.drawable.splash)
                .into(holder.restoImage);
        holder.distance.setText(formattedDistance);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            // Create an Intent to navigate to RestaurantDetailsActivity
            Intent intent = new Intent(context, RestaurantDetailsActivity.class);
            // Pass the clicked restaurant data using serialization
            intent.putExtra("selectedRestaurant", restaurant);
            context.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restoName;
        TextView description;
        ImageView restoImage;
        TextView distance;

        ViewHolder(View itemView) {
            super(itemView);
            restoName = itemView.findViewById(R.id.text_view_restoname);
            description = itemView.findViewById(R.id.text_view_resto_location_details);
            restoImage = itemView.findViewById(R.id.image_view_resto_popping);
            distance = itemView.findViewById(R.id.text_view_popping_distance);
        }
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // convert to km
    }

}

package com.neztech.serah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neztech.serah.R;
import com.neztech.serah.model.Review;

import java.util.List;

public class RestoReviewAdapter extends RecyclerView.Adapter<RestoReviewAdapter.ViewHolder> {

    private Context context;
    private List<Review> reviewList;

    public RestoReviewAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resto_review_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);

        // Set user profile image (you can use Picasso or Glide here)
        holder.imageViewProfile.setImageResource(R.drawable.profile_avatar);

        holder.textViewReviewName.setText(review.getUser().getUsername());
        holder.textViewReviewDate.setText(review.getDate());

        holder.ratingBar.setRating(review.getRating());

        holder.textViewReviewComment.setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfile;
        TextView textViewReviewName;
        TextView textViewReviewDate;
        RatingBar ratingBar;
        TextView textViewReviewComment;

        ViewHolder(View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.image_view_review_profile);
            textViewReviewName = itemView.findViewById(R.id.text_view_reviewname);
            textViewReviewDate = itemView.findViewById(R.id.text_view_review_date);
            ratingBar = itemView.findViewById(R.id.rating_bar_review_item_ratingbar);
            textViewReviewComment = itemView.findViewById(R.id.text_view_review_item_comment);
        }
    }
}

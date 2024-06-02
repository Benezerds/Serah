package com.neztech.serah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neztech.serah.R;
import com.neztech.serah.model.Reservation;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservationList;

    public ReservationAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        // TODO: Load image into holder.imageViewHistoryImage
        holder.textViewHistoryStatus.setText(reservation.getReservationStatus());
        holder.textViewReviewPax.setText(reservation.getPartySize());
        holder.textViewHistoryDate.setText(reservation.getReservationDate());
        holder.textViewHistoryTime.setText(reservation.getReservationDate());
        holder.textViewHistoryLocation.setText(reservation.getRestaurant().getLocation());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewHistoryImage;
        TextView textViewHistoryStatus;
        TextView textViewReviewPax;
        TextView textViewHistoryDate;
        TextView textViewHistoryTime;
        TextView textViewHistoryLocation;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHistoryImage = itemView.findViewById(R.id.image_view_history_image);
            textViewHistoryStatus = itemView.findViewById(R.id.text_view_history_status);
            textViewReviewPax = itemView.findViewById(R.id.text_view_review_pax);
            textViewHistoryDate = itemView.findViewById(R.id.text_view_history_date);
            textViewHistoryTime = itemView.findViewById(R.id.text_view_history_time);
            textViewHistoryLocation = itemView.findViewById(R.id.text_view_history_location);
        }
    }
}

package com.srikanth.cinemareview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter4 extends RecyclerView.Adapter<MovieAdapter4.MovieViewHolder3> {
    private Context mContext3;
    private ArrayList<Details> mMovieList3;
    private OnItemClickListener4 mListener3;


    public interface OnItemClickListener4 {
        void onItemClick4(int position4);

    }

    public void setOnItemClickListener4(OnItemClickListener4 listener) {
        mListener3 = listener;
    }


    public MovieAdapter4(Context context, ArrayList<Details> movieList) {
        mContext3 = context;
        mMovieList3 = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext3).inflate(R.layout.horizontalview1_layout, parent, false);
        return new MovieViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder3 holder, int position) {
        Details currentDetail = mMovieList3.get(position);
        String posterUrl = "https://image.tmdb.org/t/p/w500" + currentDetail.getmPosterUrl();
        String title = currentDetail.getmTitle();
        int rating = currentDetail.getmVoteAverage();
        holder.mTitle.setText(title);
        holder.mRating.setText(""+rating);
        // Picasso.get().load(posterUrl).into(holder.mPosterView);
        Glide.with(mContext3)

                .load(posterUrl)
                .into(holder.mPosterView);
    }

    @Override
    public int getItemCount() {
        return mMovieList3.size();
    }

    public class MovieViewHolder3 extends RecyclerView.ViewHolder {
        public ImageView mPosterView;
        public TextView mTitle;
        public TextView mRating;


        public MovieViewHolder3(@NonNull View itemView) {
            super(itemView);
            mPosterView = itemView.findViewById(R.id.dPoster1);
            mTitle = itemView.findViewById(R.id.dTitle1);
            mRating = itemView.findViewById(R.id.dRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener3 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener3.onItemClick4(position);
                        }
                    }

                }
            });


        }


    }
}

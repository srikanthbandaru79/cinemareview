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

public class MovieAdapter2 extends RecyclerView.Adapter<MovieAdapter2.MovieViewHolder2> {
    private Context mContext2;
    private ArrayList<Details> mMovieList2;
    private OnItemClickListener2 mListener2;


    public interface OnItemClickListener2 {
        void onItemClick2(int position2);

    }

    public void setOnItemClickListener2(OnItemClickListener2 listener) {
        mListener2 = listener;
    }


    public MovieAdapter2(Context context, ArrayList<Details> movieList) {
        mContext2 = context;
        mMovieList2 = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext2).inflate(R.layout.horizontalview1_layout, parent, false);
        return new MovieViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder2 holder, int position) {
        Details currentDetail = mMovieList2.get(position);
        String posterUrl = "https://image.tmdb.org/t/p/w500" + currentDetail.getmPosterUrl();
        String title = currentDetail.getmTitle();
        int rating = currentDetail.getmVoteAverage();
        holder.mTitle.setText(title);
        holder.mRating.setText(""+rating);
        // Picasso.get().load(posterUrl).into(holder.mPosterView);
        Glide.with(mContext2)

                .load(posterUrl)
                .into(holder.mPosterView);
    }

    @Override
    public int getItemCount() {
        return mMovieList2.size();
    }

    public class MovieViewHolder2 extends RecyclerView.ViewHolder {
        public ImageView mPosterView;
        public TextView mTitle;
        public TextView mRating;


        public MovieViewHolder2(@NonNull View itemView) {
            super(itemView);
            mPosterView = itemView.findViewById(R.id.dPoster1);
            mTitle = itemView.findViewById(R.id.dTitle1);
            mRating = itemView.findViewById(R.id.dRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener2 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener2.onItemClick2(position);
                        }
                    }

                }
            });


        }


    }
}

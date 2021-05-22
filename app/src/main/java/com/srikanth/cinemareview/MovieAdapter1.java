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

public class MovieAdapter1 extends RecyclerView.Adapter<MovieAdapter1.MovieViewHolder1> {
    private Context mContext1;
    private ArrayList<Details> mMovieList1;
    private OnItemClickListener1 mListener1;


    public interface OnItemClickListener1 {
        void onItemClick1(int position1);

    }

    public void setOnItemClickListener1(OnItemClickListener1 listener) {
        mListener1 = listener;
    }


    public MovieAdapter1(Context context, ArrayList<Details> movieList) {
        mContext1 = context;
        mMovieList1 = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext1).inflate(R.layout.horizontalview1_layout, parent, false);
        return new MovieViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder1 holder, int position) {
        Details currentDetail = mMovieList1.get(position);
        String posterUrl = "https://image.tmdb.org/t/p/w500" + currentDetail.getmPosterUrl();
        String title = currentDetail.getmTitle();
        int rating = currentDetail.getmVoteAverage();
        holder.mTitle.setText(title);
        holder.mRating.setText(""+rating);
        // Picasso.get().load(posterUrl).into(holder.mPosterView);
        Glide.with(mContext1)

                .load(posterUrl)
                .into(holder.mPosterView);
    }

    @Override
    public int getItemCount() {
        return mMovieList1.size();
    }

    public class MovieViewHolder1 extends RecyclerView.ViewHolder {
        public ImageView mPosterView;
        public TextView mTitle;
        public TextView mRating;


        public MovieViewHolder1(@NonNull View itemView) {
            super(itemView);
            mPosterView = itemView.findViewById(R.id.dPoster1);
            mTitle = itemView.findViewById(R.id.dTitle1);
            mRating = itemView.findViewById(R.id.dRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener1 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener1.onItemClick1(position);
                        }
                    }

                }
            });


        }


    }
}

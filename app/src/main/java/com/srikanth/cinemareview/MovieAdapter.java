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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context mContext;
    private ArrayList<Details> mMovieList;
    private OnItemClickListener mListener;




    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MovieAdapter(Context context, ArrayList<Details> movieList) {
        mContext = context;
        mMovieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.horizontalview1_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Details currentDetail = mMovieList.get(position);
        String posterUrl = "https://image.tmdb.org/t/p/w500" + currentDetail.getmPosterUrl();
        String title = currentDetail.getmTitle();
        int rating=currentDetail.getmVoteAverage();

        holder.mTitle.setText(title);
        holder.mRating.setText(""+rating);
        // Picasso.get().load(posterUrl).into(holder.mPosterView);
        Glide.with(mContext)

                .load(posterUrl)
                .into(holder.mPosterView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView mPosterView;
        public TextView mTitle;
        public TextView mRating;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mPosterView = itemView.findViewById(R.id.dPoster1);
            mTitle = itemView.findViewById(R.id.dTitle1);
            mRating = itemView.findViewById(R.id.dRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }

                }
            });


        }


    }
}

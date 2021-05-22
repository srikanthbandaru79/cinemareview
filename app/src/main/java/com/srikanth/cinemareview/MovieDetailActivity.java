package com.srikanth.cinemareview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.srikanth.cinemareview.HomeFragment.ID;
import static com.srikanth.cinemareview.HomeFragment.TITLE;
import static com.srikanth.cinemareview.HomeFragment.POSTER_URL;
import static com.srikanth.cinemareview.HomeFragment.LANGUAGE;
import static com.srikanth.cinemareview.HomeFragment.OVERVIEW;
import static com.srikanth.cinemareview.HomeFragment.RELEASE_DATE;
import static com.srikanth.cinemareview.HomeFragment.BACKDROP_URL;
import static com.srikanth.cinemareview.HomeFragment.VOTE_AVERAGE;
import static com.srikanth.cinemareview.HomeFragment.VOTE_COUNT;



public class MovieDetailActivity extends AppCompatActivity implements MovieAdapterReleated.OnItemClickListener {
    ImageView mbackdrop;
    TextView mtitle, moverview, mrating, mlanguage, mReleaseDate;
    private RecyclerView mRecyclerViewReleated;
    private ArrayList<Details> mMovieList1;
    private RequestQueue mRequestQueue1;
    private MovieAdapterReleated mAdapter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        id = intent.getIntExtra(ID, 0);
        String title = intent.getStringExtra(TITLE);
        String posterurl = intent.getStringExtra(POSTER_URL);
        String language = intent.getStringExtra(LANGUAGE);
        String overview = intent.getStringExtra(OVERVIEW);
        String releasedate = intent.getStringExtra(RELEASE_DATE);
        String backdropurl = intent.getStringExtra(BACKDROP_URL);
        int voteaverage = intent.getIntExtra(VOTE_AVERAGE, 0);
        int votecount = intent.getIntExtra(VOTE_COUNT, 0);

        mbackdrop = (ImageView) findViewById(R.id.imageview_backdrop_details);
        mtitle = (TextView) findViewById(R.id.title_detail);
        moverview = (TextView) findViewById(R.id.overview_detail);
        mrating = (TextView) findViewById(R.id.rating_detail);
        mlanguage = (TextView) findViewById(R.id.language_detail);
        mReleaseDate = (TextView) findViewById(R.id.release_date_details);

        mRecyclerViewReleated = (RecyclerView) findViewById(R.id.recyclerviewreleated);
        mRecyclerViewReleated.setHasFixedSize(true);
        // mRecyclerViewReleated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewReleated.setLayoutManager(new GridLayoutManager(this, 2));

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + posterurl).into(mbackdrop);
        mtitle.setText(title);
        moverview.setText("Overview:\n" + overview);
        mrating.setText("IMDB Rating :" + voteaverage + "(" + votecount + ")");
        mlanguage.setText("Language : " + language);
        mReleaseDate.setText("Release Date : " + releasedate);

        mMovieList1 = new ArrayList<>();
        mRequestQueue1 = Volley.newRequestQueue(this);


        AparseJson1();


    }

    private void AparseJson1() {

        String url = "https://api.themoviedb.org/3/movie/" + id + "/similar?api_key=1069fb29d7ff035c5425b8c63b1512b8&language=en-US&page=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject details = jsonArray.getJSONObject(i);

                                String title = details.getString("title");
                                String posterurl = details.getString("poster_path");
                                String language = details.getString("original_language");
                                String overview = details.getString("overview");
                                String release_date = details.getString("release_date");
                                String back_dropurl = details.getString("backdrop_path");
                                int vote_average = details.getInt("vote_average");
                                int vote_count = details.getInt("vote_count");
                                int id = details.getInt("id");


                                mMovieList1.add(new Details(posterurl, title, overview, release_date, back_dropurl, language, vote_average, vote_count, id));

                            }


                            mAdapter = new MovieAdapterReleated(MovieDetailActivity.this, mMovieList1);
                            mRecyclerViewReleated.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MovieDetailActivity.this);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mRequestQueue1.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(MovieDetailActivity.this, MovieDetailActivity.class);
        Details clickedItem1 = mMovieList1.get(position);
        detailIntent.putExtra(ID, clickedItem1.getmId());
        detailIntent.putExtra(TITLE, clickedItem1.getmTitle());
        detailIntent.putExtra(POSTER_URL, clickedItem1.getmPosterUrl());
        detailIntent.putExtra(LANGUAGE, clickedItem1.getmLanguage());
        detailIntent.putExtra(OVERVIEW, clickedItem1.getmOverView());
        detailIntent.putExtra(RELEASE_DATE, clickedItem1.getmReleaseDate());
        detailIntent.putExtra(BACKDROP_URL, clickedItem1.getmBackDropPath());
        detailIntent.putExtra(VOTE_AVERAGE, clickedItem1.getmVoteAverage());
        detailIntent.putExtra(VOTE_COUNT, clickedItem1.getmVoteCount());
        startActivity(detailIntent);
    }
}
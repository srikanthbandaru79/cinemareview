package com.srikanth.cinemareview;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String POSTER_URL = "posterurl";
    public static final String LANGUAGE = "language";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "releasedate";
    public static final String BACKDROP_URL = "backdropurl";
    public static final String VOTE_AVERAGE = "voteaverage";
    public static final String VOTE_COUNT = "votecount";
    private RecyclerView mRecyclerviewexplore;
    private ArrayList<Details> mMovieList1;
    private RequestQueue mRequestQueue1;
    private MovieAdapterSearch mAdapter;

    private EditText searchedttxt;

    public ExploreFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_frag_explore, container, false);
        searchedttxt = rootView.findViewById(R.id.search);
        mRecyclerviewexplore = rootView.findViewById(R.id.recyclerviewexplore);
        mRecyclerviewexplore.setHasFixedSize(true);
        // mRecyclerViewReleated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerviewexplore.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mMovieList1 = new ArrayList<>();
        mRequestQueue1 = Volley.newRequestQueue(getActivity());

        searchedttxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        AparseJson1();
        return rootView;
    }

    private void AparseJson1() {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=1069fb29d7ff035c5425b8c63b1512b8";
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


                            mAdapter = new MovieAdapterSearch(getActivity(), mMovieList1);
                            mRecyclerviewexplore.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(new MovieAdapterSearch.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
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
                            });





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

private void filter(String text)
{
    ArrayList<Details> filteredlist=new ArrayList<>();
    for (Details details:mMovieList1)
    {
        if (details.getmTitle().toLowerCase().contains(text.toLowerCase()))
        {
            filteredlist.add(details);
        }
    }
    mAdapter.filterList(filteredlist);
}
}

package com.srikanth.cinemareview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String POSTER_URL = "posterurl";
    public static final String LANGUAGE = "language";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "releasedate";
    public static final String BACKDROP_URL = "backdropurl";
    public static final String VOTE_AVERAGE = "voteaverage";
    public static final String VOTE_COUNT = "votecount";
    public static final String TYPE = "type";


    private RecyclerView mRecyclerView1, mRecyclerView2, mRecyclerView3, mRecyclerView4,mRecyclerViewTrend;
    private MovieAdapter mAdapter;
    private MovieAdapter1 mAdapter1;
    private MovieAdapter2 mAdapter2;
    private MovieAdapter3 mAdapter3;
    private MovieAdapter4 mAdapter4;
    private ArrayList<Details> mMovieList1, mMovieList2, mMovieList3, mMovieList4,mMovieList5;
    private RequestQueue mRequestQueue1, mRequestQueue2, mRequestQueue3, mRequestQueue4,mRequestQueue5;


    public HomeFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_frag_home, container, false);




            mRecyclerView1 = rootView.findViewById(R.id.recyclerview1);
            mRecyclerView1.setHasFixedSize(true);
            mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


            mRecyclerView2 = rootView.findViewById(R.id.recyclerview2);
            mRecyclerView2.setHasFixedSize(true);
            mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

            mRecyclerView3 = rootView.findViewById(R.id.recyclerview3);
            mRecyclerView3.setHasFixedSize(true);
            mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


            mRecyclerView4 =rootView.findViewById(R.id.recyclerview4);
            mRecyclerView4.setHasFixedSize(true);
            mRecyclerView4.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

            mRecyclerViewTrend = rootView.findViewById(R.id.recyclerviewtrend);
            mRecyclerViewTrend.setHasFixedSize(true);
            mRecyclerViewTrend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


            mMovieList1 = new ArrayList<>();
            mRequestQueue1 = Volley.newRequestQueue(getActivity());

            mMovieList2 = new ArrayList<>();
            mRequestQueue2 = Volley.newRequestQueue(getActivity());

            mMovieList3 = new ArrayList<>();
            mRequestQueue3 = Volley.newRequestQueue(getActivity());

            mMovieList4 = new ArrayList<>();
            mRequestQueue4 = Volley.newRequestQueue(getActivity());

            mMovieList5 = new ArrayList<>();
            mRequestQueue5 = Volley.newRequestQueue(getActivity());


            AparseJson1();
            AparseJson2();
            AparseJson3();
            AparseJson4();
            AparseJson5();

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


                                mAdapter = new MovieAdapter(getActivity(), mMovieList1);
                                mRecyclerView1.setAdapter(mAdapter);
                              mAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
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

        private void AparseJson2() {

            String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=1069fb29d7ff035c5425b8c63b1512b8";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 1; i < jsonArray.length(); i++) {

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


                                    mMovieList2.add(new Details(posterurl, title, overview, release_date, back_dropurl, language, vote_average, vote_count, id));


                                }


                                mAdapter1 = new MovieAdapter1(getActivity(), mMovieList2);
                                mRecyclerView2.setAdapter(mAdapter1);
                              mAdapter1.setOnItemClickListener1(new MovieAdapter1.OnItemClickListener1() {
                                  @Override
                                  public void onItemClick1(int position1) {
                                      Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                                      Details clickedItem1 = mMovieList2.get(position1);
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
            mRequestQueue2.add(request);
        }

        private void AparseJson3() {

            String url = "https://api.themoviedb.org/3/tv/popular?api_key=1069fb29d7ff035c5425b8c63b1512b8&language=en-US&page=1";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject details = jsonArray.getJSONObject(i);

                                    String title = details.getString("name");
                                    String posterurl = details.getString("poster_path");
                                    String language = details.getString("original_language");
                                    String overview = details.getString("overview");
                                    String release_date = details.getString("first_air_date");
                                    String back_dropurl = details.getString("backdrop_path");
                                    int vote_average = details.getInt("vote_average");
                                    int vote_count = details.getInt("vote_count");
                                    int id = details.getInt("id");


                                    mMovieList3.add(new Details(posterurl, title, overview, release_date, back_dropurl, language, vote_average, vote_count, id));


                                }


                                mAdapter2 = new MovieAdapter2(getActivity(), mMovieList3);
                                mRecyclerView3.setAdapter(mAdapter2);
                               mAdapter2.setOnItemClickListener2(new MovieAdapter2.OnItemClickListener2() {
                                   @Override
                                   public void onItemClick2(int position2) {
                                       Intent detailIntent = new Intent(getActivity(), TvDetailActivity.class);
                                       Details clickedItem1 = mMovieList3.get(position2);
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
            mRequestQueue3.add(request);
        }

        private void AparseJson4() {

            String url = "https://api.themoviedb.org/3/tv/top_rated?api_key=1069fb29d7ff035c5425b8c63b1512b8&language=en-US&page=1";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject details = jsonArray.getJSONObject(i);

                                    String title = details.getString("name");
                                    String posterurl = details.getString("poster_path");
                                    String language = details.getString("original_language");
                                    String overview = details.getString("overview");
                                    String release_date = details.getString("first_air_date");
                                    String back_dropurl = details.getString("backdrop_path");
                                    int vote_average = details.getInt("vote_average");
                                    int vote_count = details.getInt("vote_count");
                                    int id = details.getInt("id");


                                    mMovieList4.add(new Details(posterurl, title, overview, release_date, back_dropurl, language, vote_average, vote_count, id));


                                }


                                mAdapter4 = new MovieAdapter4(getActivity(), mMovieList4);
                                mRecyclerView4.setAdapter(mAdapter4);
                              mAdapter4.setOnItemClickListener4(new MovieAdapter4.OnItemClickListener4() {
                                  @Override
                                  public void onItemClick4(int position4) {
                                      Intent detailIntent = new Intent(getActivity(), TvDetailActivity.class);
                                      Details clickedItem1 = mMovieList4.get(position4);
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
            mRequestQueue4.add(request);
        }

        private void AparseJson5() {

            String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=1069fb29d7ff035c5425b8c63b1512b8";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 1; i < jsonArray.length(); i++) {

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


                                    mMovieList5.add(new Details(posterurl, title, overview, release_date, back_dropurl, language, vote_average, vote_count, id));

                                }


                                mAdapter3 = new MovieAdapter3(getActivity(), mMovieList5);
                                mRecyclerViewTrend.setAdapter(mAdapter3);

                           mAdapter3.setOnItemClickListener3(new MovieAdapter3.OnItemClickListener3() {
                               @Override
                               public void onItemClick3(int position3) {
                                   Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                                   Details clickedItem1 = mMovieList5.get(position3);
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
            mRequestQueue5.add(request);
        }











    }


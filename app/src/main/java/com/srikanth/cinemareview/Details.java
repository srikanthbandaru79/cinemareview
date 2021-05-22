package com.srikanth.cinemareview;

public class Details {

   private String mPosterUrl,mTitle,mOverView,mReleaseDate,mBackDropPath,mLanguage;
   private  int mVoteAverage,mVoteCount,mId;

    public Details() {
    }

    public Details(String mPosterUrl, String mTitle, String mOverView, String mReleaseDate, String mBackDropPath, String mLanguage, int mVoteAverage, int mVoteCount, int mId) {
        this.mPosterUrl = mPosterUrl;
        this.mTitle = mTitle;
        this.mOverView = mOverView;
        this.mReleaseDate = mReleaseDate;
        this.mBackDropPath = mBackDropPath;
        this.mLanguage = mLanguage;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
        this.mId = mId;
    }

    public String getmPosterUrl() {
        return mPosterUrl;
    }

    public void setmPosterUrl(String mPosterUrl) {
        this.mPosterUrl = mPosterUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmOverView() {
        return mOverView;
    }

    public void setmOverView(String mOverView) {
        this.mOverView = mOverView;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmBackDropPath() {
        return mBackDropPath;
    }

    public void setmBackDropPath(String mBackDropPath) {
        this.mBackDropPath = mBackDropPath;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public void setmLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public int getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(int mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public int getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}

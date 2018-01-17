package com.example.assignment.meeshoassignment1.remote;

import com.example.assignment.meeshoassignment1.Source;
import com.example.assignment.meeshoassignment1.data.PullRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by msk on 17/1/18.
 */

public class RemoteSource implements Source {

    private static RemoteSource instance;

    private GithubApi api;

    private RemoteSource() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(GithubApi.BASE_URL);
        Retrofit retrofit = retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(GithubApi.class);
    }

    public static RemoteSource getInstance() {
        if (instance == null) {
            instance = new RemoteSource();
        }
        return instance;
    }

    @Override
    public Single<List<PullRequest>> getPullRequests(String user, String repo) {
        return api.getPullRequests(user, repo);
    }
}

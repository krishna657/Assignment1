package com.example.assignment.meeshoassignment1.remote;

import com.example.assignment.meeshoassignment1.data.PullRequest;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by msk on 17/1/18.
 */

public interface GithubApi {
    public static final String BASE_URL = "https://api.github.com/";
    @Headers({
            "Accept: application/vnd.github.v3+json",
            "User-Agent: Assignment1"
    })

    @GET("repos/{user}/{repo_path}/pulls")
    Single<List<PullRequest>> getPullRequests(@Path("user") String user, @Path("repo_path") String repoPath);
}

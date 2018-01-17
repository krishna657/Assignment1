package com.example.assignment.meeshoassignment1;

import com.example.assignment.meeshoassignment1.data.PullRequest;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by msk on 17/1/18.
 */

public interface Source {
    Single<List<PullRequest>> getPullRequests(String user, String repo);
}

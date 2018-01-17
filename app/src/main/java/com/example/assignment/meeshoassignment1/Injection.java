package com.example.assignment.meeshoassignment1;

import com.example.assignment.meeshoassignment1.remote.RemoteSource;

/**
 * Created by msk on 17/1/18.
 */

public class Injection {
    public static Source getSourceForPullRequests() {
        return RemoteSource.getInstance();
    }
}

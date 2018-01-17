package com.example.assignment.meeshoassignment1;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.assignment.meeshoassignment1.data.PullRequest;
import com.example.assignment.meeshoassignment1.pullrequest.PullRequestsListAdapter;

import java.util.List;

/**
 * Created by msk on 17/1/18.
 */

public class BindingAdapters {

    @BindingAdapter("pulls")
    public static void setPullRequests(RecyclerView view, List<PullRequest> pulls) {
        ((PullRequestsListAdapter) view.getAdapter()).setPulls(pulls);
    }

}

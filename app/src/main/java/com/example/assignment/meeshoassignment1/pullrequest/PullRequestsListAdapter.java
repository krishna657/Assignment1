package com.example.assignment.meeshoassignment1.pullrequest;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.assignment.meeshoassignment1.R;
import com.example.assignment.meeshoassignment1.data.PullRequest;
import com.example.assignment.meeshoassignment1.databinding.PullItemViewBinding;

import java.util.List;

/**
 * Created by msk on 17/1/18.
 */

public class PullRequestsListAdapter extends RecyclerView.Adapter<PullRequestsListAdapter.PullRequestHolder> {

    private List<PullRequest> pullRequests;

    public PullRequestsListAdapter(List<PullRequest>pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public PullRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PullItemViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.pull_item_view, parent, false);
        return new PullRequestHolder(binding);
    }

    @Override
    public void onBindViewHolder(PullRequestHolder holder, int position) {
        holder.bind(pullRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    public class PullRequestHolder extends RecyclerView.ViewHolder {
        private final PullItemViewBinding binding;

        public PullRequestHolder(PullItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PullRequest pullRequest) {
            binding.setPullrequest(pullRequest);
            binding.executePendingBindings();
        }
    }
    public void setPulls(List<PullRequest> pulls) {
        this.pullRequests = pulls;
        notifyDataSetChanged();
    }


}

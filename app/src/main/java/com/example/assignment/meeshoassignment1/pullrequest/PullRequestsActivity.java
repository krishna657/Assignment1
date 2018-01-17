package com.example.assignment.meeshoassignment1.pullrequest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment.meeshoassignment1.Injection;
import com.example.assignment.meeshoassignment1.R;
import com.example.assignment.meeshoassignment1.Source;
import com.example.assignment.meeshoassignment1.ViewModelHolder;
import com.example.assignment.meeshoassignment1.data.PullRequest;
import com.example.assignment.meeshoassignment1.databinding.ActivityMainBinding;
import com.example.assignment.meeshoassignment1.pullrequest.PullRequestsListAdapter;
import com.example.assignment.meeshoassignment1.pullrequest.PullRequestsViewModel;

import java.util.ArrayList;

public class PullRequestsActivity extends AppCompatActivity {
    public static final String PULL_REQUESTS_VIEWMODEL = "pulls_view_model";

    private PullRequestsViewModel pullRequestsViewModel;
    private ActivityMainBinding binding;
    private Observable.OnPropertyChangedCallback snackBarCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        pullRequestsViewModel = findOrCreateViewModel();
        binding.setPullsViewModel(pullRequestsViewModel);
        final EditText etUserRepo = binding.userRepo;
        etUserRepo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    pullRequestsViewModel.getPullRequests(etUserRepo.getText().toString());
                    hideKeyBoard(v);
                    etUserRepo.clearFocus();
                    return true;
                }
                return false;
            }
        });
        setUpSnackBarCallBack();
        setUpRecyclerView();
        pullRequestsViewModel.onCreate();
    }

    private PullRequestsViewModel findOrCreateViewModel() {
        ViewModelHolder<PullRequestsViewModel> retainedViewModel =
                (ViewModelHolder<PullRequestsViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(PULL_REQUESTS_VIEWMODEL);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            return retainedViewModel.getViewmodel();
        } else {
            Source source = Injection.getSourceForPullRequests();

            PullRequestsViewModel viewModel = new PullRequestsViewModel(
                    source,
                    getApplicationContext());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(ViewModelHolder.createContainer(viewModel), PULL_REQUESTS_VIEWMODEL);
            transaction.commit();
            return viewModel;
        }
    }

    private void hideKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void setUpRecyclerView() {
        RecyclerView rvPullRequests = binding.pullRequests;
        rvPullRequests.setLayoutManager(new LinearLayoutManager(this));
        rvPullRequests.setAdapter(new PullRequestsListAdapter(new ArrayList<PullRequest>()));
        rvPullRequests.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
    }

    private void setUpSnackBarCallBack() {
        snackBarCallBack = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Snackbar.make(binding.getRoot(), pullRequestsViewModel.snackBarString.get(), BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        };
        pullRequestsViewModel.snackBarString.addOnPropertyChangedCallback(snackBarCallBack);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pullRequestsViewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pullRequestsViewModel.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pullRequestsViewModel.onDestroy();
    }
}

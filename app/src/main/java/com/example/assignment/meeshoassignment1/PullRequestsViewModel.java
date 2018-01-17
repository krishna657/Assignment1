package com.example.assignment.meeshoassignment1;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.assignment.meeshoassignment1.data.PullRequest;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by msk on 17/1/18.
 */

public class PullRequestsViewModel implements BaseViewModel {
    private static final String TAG = "pulls_view_model";
    private Source source;
    public final ObservableArrayList<PullRequest> pullRequestItems = new ObservableArrayList<>();
    public final ObservableBoolean loading = new ObservableBoolean();
    public final ObservableField<String> snackBarString = new ObservableField<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;
    public PullRequestsViewModel(@NonNull Source pullRequestsSource, @NonNull Context context) {
        source = pullRequestsSource;
        this.context = context.getApplicationContext();
    }

    public void getPullRequests(String userRepoPath) {
        String userRepo = userRepoPath.trim();
        String[] userRepoArray = userRepo.split("\\/");
        if (userRepoArray.length > 1) {
            loading.set(true);
            Disposable d = source.getPullRequests(userRepoArray[0], userRepoArray[1])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<PullRequest>>() {
                        @Override
                        public void onSuccess(List<PullRequest> pullRequests) {
                            loading.set(false);
                            pullRequestItems.clear();
                            pullRequestItems.addAll(pullRequests);
                            if (pullRequests.size() == 0) {
                                snackBarString.set(context.getString(R.string.pull_requests_zero));
                                snackBarString.notifyChange();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            loading.set(false);
                            snackBarString.set(e.getMessage());
                            snackBarString.notifyChange();
                        }
                    });
            compositeDisposable.add(d);
        } else {
            snackBarString.set(context.getString(R.string.error_edit_text_insert));
            snackBarString.notifyChange();
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}

package com.example.assignment.meeshoassignment1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by msk on 17/1/18.
 */

public class ViewModelHolder<T> extends Fragment {
    private T viewModel;
    public ViewModelHolder() { }

    public static <M> ViewModelHolder createContainer(@NonNull M viewModel) {
        ViewModelHolder<M> viewModelContainer = new ViewModelHolder<>();
        viewModelContainer.setViewModel(viewModel);
        return viewModelContainer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable public T getViewmodel() {
        return viewModel;
    }

    public void setViewModel(@NonNull T viewModel) {
        this.viewModel = viewModel;
    }
}

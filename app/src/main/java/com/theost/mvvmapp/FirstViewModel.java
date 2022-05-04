package com.theost.mvvmapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FirstViewModel extends ViewModel {

    private final MutableLiveData<Status> _loadingStatus = new MutableLiveData<>();
    public LiveData<Status> loadingStatus = _loadingStatus;

    private final MutableLiveData<Integer> _number = new MutableLiveData<>();
    public LiveData<Integer> number = _number;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // number.postValue(); - потокобезопасно
    // number.setValue(); - потоконебезопасно

    public void loadData() {
        _loadingStatus.postValue(Status.LOADING);
        compositeDisposable.add(
                Single.just(new Random().nextInt())
                        .delay(5000, TimeUnit.MILLISECONDS)
                        .subscribe(integer -> {
                            _number.postValue(integer);
                            _loadingStatus.postValue(Status.SUCCESS);
                        }, throwable -> {
                            throwable.printStackTrace();
                            _loadingStatus.postValue(Status.ERROR);
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

package com.example.databindinglearn.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class SimpleViewModel extends ViewModel {
    private MutableLiveData<String> _name = new MutableLiveData<>("ning");
    private MutableLiveData<String> _lastName = new MutableLiveData<>("zhou");
    private MutableLiveData<Integer> _likes = new MutableLiveData<>(0);

    private LiveData<String> name = _name;
    private LiveData<String> lastName = _lastName;
    private LiveData<Integer> likes = _likes;

    public void onLike() {
        _likes.setValue(_likes.getValue() + 1);
    }

    public LiveData<String> getName() {
        return name;
    }

    public void setName(LiveData<String> name) {
        this.name = name;
    }

    public LiveData<String> getLastName() {
        return lastName;
    }

    public void setLastName(LiveData<String> lastName) {
        this.lastName = lastName;
    }

    public LiveData<Integer> getLikes() {
        return likes;
    }

    public void setLikes(LiveData<Integer> likes) {
        this.likes = likes;
    }

    LiveData<Popularity> popularity = Transformations.map(_likes, (likes) -> {
        if (likes > 9)
            return Popularity.STAR;
        else if (likes > 4)
            return Popularity.POPULAR;
        else
            return Popularity.NORMAL;
    });

    public LiveData<Popularity> getPopularity() {
        return popularity;
    }
}


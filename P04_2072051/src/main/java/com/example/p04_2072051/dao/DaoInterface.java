package com.example.p04_2072051.dao;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

public interface DaoInterface<T> {
    ObservableList<T> getData();
    void addData(T data);
    void setData(T data);
    void delData(T data);
}

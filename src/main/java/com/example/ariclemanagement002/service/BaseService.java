package com.example.ariclemanagement002.service;


public interface BaseService<T, ID, RES>{

    void save(T obj);
    RES getById(ID id);
    T deleteById(ID id, T obj);
    T disableById(ID id, T obj);
    RES enableById(ID id, T obj);

}

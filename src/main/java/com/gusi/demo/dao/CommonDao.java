package com.gusi.demo.dao;

public interface CommonDao<T>
{
    public T getObjectById(long id);

    public boolean insertObject(T obj);
}

package com.thi.challenge.litteratura.Service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> Tclass);
}

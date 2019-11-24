package com.demo.musicwiki.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final NetworkStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    private Resource(@NonNull NetworkStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    static <T> Resource<T> success (@Nullable T data) {
        return new Resource<>(NetworkStatus.SUCCESS, data, null);
    }

    static <T> Resource<T> error(@NonNull String msg, @Nullable T data) {
        return new Resource<>(NetworkStatus.ERROR, data, msg);
    }

    static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(NetworkStatus.LOADING, data, null);
    }

    public enum NetworkStatus {SUCCESS, ERROR, LOADING}

}

package com.demo.musicwiki.data;

import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetworkBoundResource<DbCacheData, NetworkRequest> {
    private final MediatorLiveData<Resource<DbCacheData>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        LiveData<DbCacheData> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(final LiveData<DbCacheData> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<NetworkRequest>() {
            @Override
            public void onResponse(Call<NetworkRequest> call, Response<NetworkRequest> response) {
                result.removeSource(dbSource);
                saveResultAndReInit(response.body());
            }

            @Override
            public void onFailure(Call<NetworkRequest> call, Throwable t) {
                onFetchFailed();
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(t.getMessage(), newData)));
            }
        });
    }

    @MainThread
    private void saveResultAndReInit(NetworkRequest response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDb(), newData -> result.setValue(Resource.success(newData)));
            }
        }.execute();
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull NetworkRequest item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable DbCacheData data);

    @NonNull
    @MainThread
    protected abstract LiveData<DbCacheData> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Call<NetworkRequest> createCall();

    @MainThread
    protected void onFetchFailed() {
    }

    public final LiveData<Resource<DbCacheData>> getAsLiveData() {
        return result;
    }
}

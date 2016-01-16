package me.tedlandis.stompinggrounds.api;

import retrofit.RestAdapter;

public class ApiProvider {
    private static ApiProvider instance;

    public static ApiProvider getInstance() {
        if (instance == null) {
            instance = new ApiProvider();
        }
        return instance;
    }

    private final RestAdapter restAdapter;

    private ApiProvider() {
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint("")
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();
    }

    public <T> T getService(Class<T> clzz) {
        return restAdapter.create(clzz);
    }
}
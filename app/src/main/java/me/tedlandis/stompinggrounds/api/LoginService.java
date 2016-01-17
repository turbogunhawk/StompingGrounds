package me.tedlandis.stompinggrounds.api;

import retrofit.http.Field;
import retrofit.http.POST;
import rx.Observable;

public interface LoginService {
    @POST("/login")
    Observable<Boolean> login(@Field("username") String username,
                             @Field("password") String password);
}
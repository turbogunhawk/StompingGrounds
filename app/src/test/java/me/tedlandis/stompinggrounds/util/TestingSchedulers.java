package me.tedlandis.stompinggrounds.util;

import me.tedlandis.stompinggrounds.api.RxSchedulerApplier;
import rx.Observable;
import rx.schedulers.Schedulers;

public class TestingSchedulers {
    public static RxSchedulerApplier immediate() {
        return new RxSchedulerApplier() {
            @Override
            public <T> Observable.Transformer<T, T> getTransformer() {
                return new Observable.Transformer<T, T>() {
                    @Override
                    public Observable<T> call(Observable<T> observable) {
                        return observable
                                .subscribeOn(Schedulers.immediate())
                                .observeOn(Schedulers.immediate());
                    }
                };
            }
        };
    }
}

package me.tedlandis.stompinggrounds.api;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public interface RxSchedulerApplier {
    <T> Observable.Transformer<T, T> getTransformer();

    RxSchedulerApplier SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN = new RxSchedulerApplier() {
        @Override
        public <T> Observable.Transformer<T, T> getTransformer() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
    };
}
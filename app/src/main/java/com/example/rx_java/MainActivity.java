package com.example.rx_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.threadactivity.R;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    private void fromArray() {

        // طباعة معلومات من array عادية
        TaskData[] arr = new TaskData[5];
        arr[0] = (new TaskData("geecoders", true, 2));
        arr[1] = (new TaskData("the web developer", false, 5));
        arr[2] = (new TaskData("the android developer", false, 1));
        arr[3] = (new TaskData("let's go to learn new programing language", true, 3));
        arr[4] = (new TaskData("geocoders is the best website to learn programing language by simple", false, 4));

        Observable<TaskData> taskDataObservable = Observable.fromArray(arr)
                .filter(new Predicate<TaskData>() {
                    @Override
                    public boolean test(TaskData taskData) throws Throwable {
                        if (taskData.priority >= 3) {
                            return true;
                        }
                        return false;
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        taskDataObservable.subscribe(new Observer<TaskData>() {

            // يعمل اول مره فقط عندما يتم تشغيل الوظيفة
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            //يحدث اثناء تشغيل الوظيفة
            @Override
            public void onNext(@NonNull TaskData taskData) {
                Log.i(TAG, "onNext: " + taskData.getTitle());
            }

            // يحدث عندما يوجد مشكلة او ايرور
            @Override
            public void onError(@NonNull Throwable e) {

            }

            // يحدق عندما تكتمل الوظيفة وتنتهي
            @Override
            public void onComplete() {

            }
        });

    }

    private void getData_with_filter() {

        // طباعة معلومات من داخل ArrayList في كلاس اخر
        Observable<TaskData> taskDataObservable = Observable.fromIterable(ArrayActivity.array())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<TaskData>() {
                    @Override
                    public boolean test(TaskData taskData) throws Throwable {
                        // اذا اردت معرفة الاسماءالتي تبدء بكلمة gee
                        if (taskData.getTitle().contains("gee")) {
                            return true;
                        }
                        return false;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskDataObservable.subscribe(new Observer<TaskData>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TaskData taskData) {
                Log.i(TAG, "onNext: " + taskData.getTitle());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    //
    private void range_operator() {

        // طباعة من 0 الى 10000 وهنا نستخدم computation لان العمليه كبيره
        Flowable.range(0, 10000)
                .subscribeOn(Schedulers.computation())
                .subscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void range_operator_with_Repeat() {
        // يطبع الاعداد من 0الى 8 مرتين

        Observable.range(0, 8)
                .repeat(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void interval() {
        // delay
        Observable<Long> inLongObservable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Throwable {
                        return aLong <= 30;
                    }
                });

        inLongObservable.subscribe(new Observer<Long>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            // هنا يعد مقلوب من 30 الى 0
            @Override
            public void onNext(@NonNull Long aLong) {
                Log.i(TAG, "onNext: " + (30 - aLong));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void timer_splash_screenn() {
        Observable<Long> inLongObservable = Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
        inLongObservable.subscribe(new Observer<Long>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Log.i(TAG, "onNext: Done");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void take_array() {

        // ياخذ اول ثلاث عناصر من الـ Array ويتجاهل الباقي
        Observable<TaskData> taskDataObservable = Observable.fromIterable(ArrayActivity.array())
                .subscribeOn(Schedulers.io())
                .take(3)
                .observeOn(AndroidSchedulers.mainThread());

        taskDataObservable.subscribe(new Observer<TaskData>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TaskData taskData) {
                Log.i(TAG, "onNext: " + taskData.getTitle());
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });

    }


}

// اذا اردت معرفة المهام المكتمله
//        return taskData.isComplete;

// اذا اردت معرفة المهام غير المكتمله
//        return !taskData.isComplete;

// اذا اردت معرفة الاسماءالتي بها كلمة مطور
//        return taskData.getTitle().contains("developer");

// اذا اردت معرفة الاسماءالتي تبدء بكلمة gee
//        return taskData.getTitle().startsWith("gee");
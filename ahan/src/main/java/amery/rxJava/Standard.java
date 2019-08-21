package amery.rxJava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by ahan on 14/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class Standard {

    //深入浅出RxJava（一：基础篇） - 大头鬼Bruce - 博客频道 - CSDN.NET
    //http://blog.csdn.net/lzyzsd/article/details/41833541/

    @Test
    public void main() {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        myObservable.subscribe(mySubscriber);
    }
}

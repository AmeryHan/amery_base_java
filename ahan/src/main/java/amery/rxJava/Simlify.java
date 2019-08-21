package amery.rxJava;

import junit.framework.Assert;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by ahan on 14/02/2017.
 */
public class Simlify {

    @Test
    public void main() {

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

        Observable<String> myObservable1 = Observable.just("Hello, world!");
        myObservable1.subscribe(mySubscriber);

        Assert.assertTrue(1 == 1);
    }
}

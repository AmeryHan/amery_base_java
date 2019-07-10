package rxJava2;

import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by ahan on 20/02/2017.
 */
@Slf4j
public class FlowableTest {

	//http://www.open-open.com/lib/view/open1481699983419.html
	public static void main(String[] args) {
		Flowable.range(0,10)
			.subscribe(new Subscriber<Integer>() {
				Subscription sub;
				//当订阅后，会首先调用这个方法，其实就相当于onStart()，
				//传入的Subscription s参数可以用于请求数据或者取消订阅
				@Override
				public void onSubscribe(Subscription s) {
					log.warn("onsubscribe start");
					sub=s;
					sub.request(1);
					log.warn("onsubscribe end");
				}

				@Override
				public void onNext(Integer o) {
					log.warn("onNext--->"+o);
					sub.request(1);
				}
				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
				}
				@Override
				public void onComplete() {
					log.warn("onComplete");
				}
			});
	}
}

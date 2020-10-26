package com.zaotao.base.rx.flowable;

import org.reactivestreams.Publisher;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

/**
 * Description RetryWithDelay Flowable request again
 * Created by wangisu@qq.com on 2019/7/15.
 */
public class RetryWithDelay implements Function<Flowable<? extends Throwable>, Flowable<?>> {
    private int maxRetries = 3;
    private long retryDelayMillis = 5000;
    private long increaseDelay = 5000;

    public RetryWithDelay() {
    }

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    public RetryWithDelay(int maxRetries, long retryDelayMillis, long increaseDelay) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.increaseDelay = increaseDelay;
    }

    @Override
    public Flowable<?> apply(@NonNull Flowable<? extends Throwable> observable) throws Exception {
        return observable
                .zipWith(Flowable.range(1, maxRetries + 1), new BiFunction<Throwable, Integer, Wrapper>() {
                    @Override
                    public Wrapper apply(Throwable throwable, Integer integer) {
                        return new Wrapper(throwable, integer);
                    }
                })
                .flatMap((Function<Wrapper, Publisher<?>>) wrapper -> {
                    if ((wrapper.throwable instanceof ConnectException
                            || wrapper.throwable instanceof SocketTimeoutException
                            || wrapper.throwable instanceof TimeoutException
                            || wrapper.throwable instanceof HttpException)
                            && wrapper.index < maxRetries + 1) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                        return Flowable.timer(retryDelayMillis + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);
                    }
                    return Flowable.error(wrapper.throwable);
                });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}

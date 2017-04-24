package com.wangzh.vultr.model.net;


import retrofit2.HttpException;
import rx.Subscriber;



/**
 * Created by WangZH on 2017/4/24.
 */

public abstract class CallBack<Model> extends Subscriber<Model> {

    public abstract void onSuccess(Model model);
    public abstract void onFail(String msg);
    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            String msg = httpException.getMessage();;
            onFail(msg);
        } else {
            onFail(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(Model model) {
        onSuccess(model);
    }
}

package com.wangzh.vultr.presenter;

import com.wangzh.vultr.model.net.Api;
import com.wangzh.vultr.model.net.Request;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by WangZH on 2017/4/24.
 */

public class BasePresenter<V> {

    public V view;
    private CompositeSubscription mCompositeSubscription;
    protected Api mApiWithJson;



    public void attachView(V view){
        this.view = view;
        mApiWithJson = Request.retrofit().create(Api.class);
    }

    public void detachView(){
        this.view = null;
        unSubscribe();
    }

    /**
     * 添加订阅者
     * @param observable
     * @param subscriber
     */
    public void addSubscribe(Observable observable, Subscriber subscriber){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    /**
     * 注销订阅
     */
    public void unSubscribe(){
        if (mCompositeSubscription !=null
                && mCompositeSubscription.hasSubscriptions()
                && !mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.unsubscribe();
        }
    }
}

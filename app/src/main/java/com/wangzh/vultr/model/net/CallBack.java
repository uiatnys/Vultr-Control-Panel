package com.wangzh.vultr.model.net;


import android.app.ProgressDialog;

import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.others.constants.ConstValues;

import retrofit2.HttpException;
import rx.Subscriber;



/**
 * Created by WangZH on 2017/4/24.
 */

public abstract class CallBack<Model> extends Subscriber<Model> {

    public abstract void onSuccess(Model model);
    public abstract void onFail(HttpErrorVo msg);
    public abstract void onFinish();

    private ProgressDialog mProgressDialog;

    public CallBack(ProgressDialog progressDialog){
        this.mProgressDialog = progressDialog;
    }

    public void hideProgress(){
        if (mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onCompleted() {
        onFinish();
        hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        HttpErrorVo vo = new HttpErrorVo();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            vo.setCode(httpException.code());
            vo.setMessage(httpException.message());
            onFail(vo);
        } else {
            vo.setCode(ConstValues.ERROR_NONHTTP);
            vo.setMessage(e.getMessage());
            onFail(vo);
        }
        onFinish();
        hideProgress();
    }

    @Override
    public void onNext(Model model) {
        onSuccess(model);
        hideProgress();
    }
}

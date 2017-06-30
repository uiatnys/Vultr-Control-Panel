package com.wangzh.vultr.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangzh.vultr.R;

/**
 * Created by WangZH on 2017/4/26.
 */

public class AlertDialogBuilder{

    private AlertDialogOkClickListener mListener;

    public AlertDialogBuilder(AlertDialogOkClickListener listener){
        this.mListener = listener;
    }

    /**
     * AlertDialog 按钮点击一次默认会dismiss，监听onShow再做操作防止隐藏
     * @param context
     * @param message
     * @param viewId
     * @return
     */
    public AlertDialog createDialogStyleA(Context context, String message
            , int viewId){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        mBuilder.setMessage(message)
                .setView(viewId)
                .setCancelable(false)
                .setPositiveButton("Ok",null)
                .setNegativeButton("Cancel",null)
                .setNeutralButton("Clear",null);
        AlertDialog dialog = mBuilder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                Button negativeBtn = ((AlertDialog)dialogInterface).getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogInterface.dismiss();
                    }
                });
                Button positiveBtn = ((AlertDialog)dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE);
                positiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog dialog = (AlertDialog) dialogInterface;
                        Editable mEditable = ((EditText) dialog.findViewById(R.id.edt_input)).getText();
                        mListener.onOkBtnClicked(mEditable == null?"":mEditable.toString());
                    }
                });
                Button neutralBtn = ((AlertDialog)dialogInterface).getButton(AlertDialog.BUTTON_NEUTRAL);
                neutralBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog dialog = (AlertDialog) dialogInterface;
                        Editable mEditable = ((EditText) dialog.findViewById(R.id.edt_input)).getText();
                        mEditable.clear();
                    }
                });
            }
        });
        return dialog;
    }

    public AlertDialog createDialogStyleB(Context context, String message,final String text){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        mBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok",null)
                .setNegativeButton("Cancel",null);
        AlertDialog dialog = mBuilder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                Button negativeBtn = ((AlertDialog)dialogInterface).getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogInterface.dismiss();
                    }
                });
                Button positiveBtn = ((AlertDialog)dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE);
                positiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onOkBtnClicked(text);
                        dialogInterface.dismiss();
                    }
                });
            }
        });
        return dialog;
    }

    public interface AlertDialogOkClickListener{
        void onOkBtnClicked(String value);
    }
}

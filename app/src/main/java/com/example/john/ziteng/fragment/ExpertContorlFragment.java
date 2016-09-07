package com.example.john.ziteng.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.ziteng.R;

/**
 *  创建对话框
 * Created by john on 2016/6/13.
 */
public class ExpertContorlFragment extends DialogFragment implements View.OnClickListener {
    public interface InputListener {
        void InputComplete(String text);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.fragment_expertcontorl, null);
        RelativeLayout startcd= (RelativeLayout) view.findViewById(R.id.rl_startcd);
        RelativeLayout stopcd= (RelativeLayout) view.findViewById(R.id.rl_stoptcd);
        RelativeLayout startfd= (RelativeLayout) view.findViewById(R.id.rl_startfd);
        RelativeLayout stopfd= (RelativeLayout) view.findViewById(R.id.rl_stopfd);
        RelativeLayout sc= (RelativeLayout) view.findViewById(R.id.rl_sc);
        RelativeLayout qx= (RelativeLayout) view.findViewById(R.id.rl_qx);
        startcd.setOnClickListener(this);
        stopcd.setOnClickListener(this);
        startfd.setOnClickListener(this);
        stopfd.setOnClickListener(this);
        sc.setOnClickListener(this);
        qx.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }
    InputListener listener= (InputListener) getActivity();
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.rl_startcd://开始充电
                listener= (InputListener) getActivity();
                listener.InputComplete("开始充电");
                break;
            case R.id.rl_stoptcd://终止充电
                listener= (InputListener) getActivity();
                listener.InputComplete("终止充电");
                break;
            case R.id.rl_startfd://开始放电
                listener= (InputListener) getActivity();
                listener.InputComplete("开始放电");
                break;
            case R.id.rl_stopfd://终止放电
                listener= (InputListener) getActivity();
                listener.InputComplete("终止放电");
                break;
            case R.id.rl_sc://删除
                listener= (InputListener) getActivity();
                listener.InputComplete("");
                break;
            case R.id.rl_qx://取消
                listener= (InputListener) getActivity();
                listener.InputComplete("取消");
                break;
        }
    }
}

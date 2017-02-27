package com.example.john.ziteng.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.john.ziteng.R;


/**
 * 设备控制密码对话框
 * 创建对话框fragment
 * Created by john on 2016/4/11.
 */
public class DevicePassWordFragment extends DialogFragment {

    private EditText password;

    //回调接口
    public interface LoginInputListener {
        void onLoginInputComplete(String password);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_devicepassword, null);
        password = (EditText) view.findViewById(R.id.device_password);
        builder.setView(view)
                .setPositiveButton(getActivity().getResources().getString(R.string.confirm),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                LoginInputListener listener = (LoginInputListener) getActivity();
                                listener.onLoginInputComplete(password.getText().toString());
                            }
                        })
                .setNegativeButton(getActivity().getResources().getString(R.string.qx1),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
            }
        });

        return builder.create();
    }

}

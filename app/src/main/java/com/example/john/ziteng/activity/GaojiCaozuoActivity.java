package com.example.john.ziteng.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.john.ziteng.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 高级操作
 * Created by john on 2016/12/20.
 */
public class GaojiCaozuoActivity extends BaseActivity {
    @BindView(R.id.device_control_fanhui)
    ImageView deviceControlFanhui;
    @BindView(R.id.tb_chongdian)
    ToggleButton tbChongdian;
    @BindView(R.id.tb_fangdian)
    ToggleButton tbFangdian;
    @BindView(R.id.device_control_title)
    TextView deviceControlTitle;
    @BindView(R.id.tv_zt)
    TextView tvZt;
    private String zt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaoji);
        ButterKnife.bind(this);
        zt = getIntent().getStringExtra("zt");
        intview();

    }

    boolean flag;
    boolean flag1;

    private void intview() {
        if (zt.equals("1")) {
            tvZt.setText(getResources().getString(R.string.cd));
        }else {
            tvZt.setText(getResources().getString(R.string.cjdrcd));
        }

        tbChongdian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (flag) {
                        showDialog();
                    }
                } else {
                    if (!flag) {
                        showDialog();
                    }
                }
            }
        });

        tbFangdian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (flag1) {
                        showDialog1();
                    }
                } else {
                    if (!flag1) {
                        showDialog1();
                    }
                }
            }
        });
    }

    @OnClick(R.id.device_control_fanhui)
    public void onClick() {
        finish();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.ts))
                .setMessage(getResources().getString(R.string.qrts))
                .setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbChongdian.isChecked()) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.qx1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbChongdian.isChecked()) {
                            tbChongdian.setChecked(true);
                            flag = false;
                        } else {
                            tbChongdian.setChecked(false);
                            flag = true;
                        }
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }


    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.ts))
                .setMessage(getResources().getString(R.string.qrts))
                .setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbFangdian.isChecked()) {
                            flag1 = true;
                        } else {
                            flag1 = false;
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.qx1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!tbFangdian.isChecked()) {
                            tbFangdian.setChecked(true);
                            flag1 = false;
                        } else {
                            tbFangdian.setChecked(false);
                            flag1 = true;
                        }
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }
}

//package com.example.john.ziteng.adapter;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.john.ziteng.R;
//import com.example.john.ziteng.domain.DeviceModer;
//
//import java.util.ArrayList;
//
///**
// *
// * 单元控制适配器 listview局部刷新
// * Created by john on 2016/4/6.
// */
//public class DeviceMondlerAdapter extends BaseAdapter {
//    private Context context;
//    private ListView mListView;
//    private ArrayList<DeviceModer> list;
//
//    public DeviceMondlerAdapter(Context context, ArrayList<DeviceModer> list) {
//        this.context = context;
//
//        this.list = list;
//    }
//
//    // 设置listview对象
//    public void setListView(ListView lisv) {
//        this.mListView = lisv;
//    }
//
//    //listview的单条数据
//    public void updateItemData(DeviceModer item) {
//        Message msg = Message.obtain();
//        int ids = -1;
//        // 进行数据对比获取对应数据在list中的位置
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getModuleId() == item.getModuleId()) {
//                ids = i;
//            }
//        }
//        msg.arg1 = ids;
//        // 更新mDataList对应位置的数据
//        list.set(ids, item);
//        // handle刷新界面
//        han.sendMessage(msg);
//    }
//
//    private Handler han = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            updateItem(msg.arg1);
//        }
//    };
//
//    /**
//     * 刷新指定item
//     */
//    private void updateItem(int index)
//    {
//        if (mListView == null)
//        {
//            return;
//        }
//        // 获取当前可以看到的item位置
//        int visiblePosition = mListView.getFirstVisiblePosition();
//        View view = mListView.getChildAt(index - visiblePosition);
//        TextView txt = (TextView) view.findViewById(R.id.device_moder);
//        // 获取mDataList.set(ids, item);更新的数据
//        DeviceModer deviceModer = (DeviceModer) getItem(index);
//        // 重新设置界面显示数据
//        txt.setText(String.valueOf(deviceModer.getElectricCurren()));
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        DeviceModer moder = list.get(position);
//        final viewHolder holder;
//        if (convertView == null) {
//            holder = new viewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.list_moder, null);
//            holder.moder = (TextView) convertView.findViewById(R.id.tv_device_moder);
//            holder.electricCurrent = (EditText)convertView.findViewById(R.id.device_moder);
//            //设置光标在右边
//            holder.electricCurrent.setSelection(holder.electricCurrent.getText().length());
//            //获得焦点时全选文本
//            holder.electricCurrent.setSelectAllOnFocus(true);
//            convertView.setTag(holder);
//        } else {
//            holder = (viewHolder) convertView.getTag();
//        }
//        holder.moder.setText(moder.getModuleId());
//        holder.electricCurrent.setText(String.valueOf(moder.getElectricCurren()));
//        return convertView;
//    }
//
//     static class viewHolder {
//        TextView moder;
//        EditText electricCurrent;
//    }
//}
//

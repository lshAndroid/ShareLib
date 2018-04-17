package com.jdjtlibrary.fitadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 这是listView 或者GridView 的一个通用adpter
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item);

    /**
     * 获取Item 数据的 postion
     *
     * @return
     */
    public int getItemPostion(T data) {
        return mDatas.indexOf(data);
    }

    /**
     * 初始化数据
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        if (mDatas != null && datas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }
    public void setDataMul(List<T> datas) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
    }
    public void setDataMulClear() {
        if (mDatas != null) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 追加集合数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        if (mDatas != null && datas != null) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(T data) {
        if (mDatas != null && data != null) {
            mDatas.add(0, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除下标为index的数据
     *
     * @param index
     */
    public void removeData(int index) {
        if (mDatas != null) {
            mDatas.remove(index);
            notifyDataSetChanged();
        }
    }


    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

}

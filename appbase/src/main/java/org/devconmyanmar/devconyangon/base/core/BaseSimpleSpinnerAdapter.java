//package org.devconmyanmar.devconyangon.base.core;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class BaseSimpleSpinnerAdapter<T> extends BaseAdapter {
//
//  protected final List<T> itemList;
//
//  public BaseSimpleSpinnerAdapter() {
//    this.itemList = new ArrayList<>();
//  }
//
//  public BaseSimpleSpinnerAdapter(List<T> itemList) {
//    this.itemList = itemList;
//  }
//
//  @Override public int getCount() {
//    return itemList.size();
//  }
//
//  public List<T> getItems() {
//    return this.itemList;
//  }
//
//  @Override public T getItem(int position) {
//    return itemList.get(position);
//  }
//
//  @Override public View getView(int position, View convertView, ViewGroup parent) {
//    return getCustomView(position, convertView, parent);
//  }
//
//  @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
//    return getCustomView(position, convertView, parent);
//  }
//
//  protected View getCustomView(int position, View convertView, ViewGroup parent) {
//    ViewHolder holder;
//    if (convertView == null) {
//      convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_row, null);
//      holder = new ViewHolder(convertView);
//      convertView.setTag(holder);
//    } else {
//      holder = (ViewHolder) convertView.getTag();
//    }
//    holder.textView.setText(getDisplayString(position, convertView.getContext()));
//    modifyHolder(holder);
//    return convertView;
//  }
//
//  protected void modifyHolder(@NonNull ViewHolder holder) {
//
//  }
//
//  protected abstract String getDisplayString(int position, @NonNull Context context);
//
//  public void submitList(List<T> itemList) {
//    this.itemList.clear();
//    if (itemList != null) {
//      this.itemList.addAll(itemList);
//    }
//
//    notifyDataSetChanged();
//  }
//
//  protected static class ViewHolder {
//
//    public TextView textView;
//
//    public ViewHolder(View itemView) {
//      this.textView = itemView.findViewById(R.id.tv_list);
//    }
//  }
//
//  public int getPositionOfId(Long id) {
//    if (itemList != null) {
//      for (int position = 0, size = getCount(); position < size; position++) {
//        if (getItemId(position) == id) {
//          return position;
//        }
//      }
//    }
//    return -1;
//  }
//}

package com.example.pic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilelistAdapter extends RecyclerView.Adapter<FilelistAdapter.ViewHolder> {
    List<String> list;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.msg_title);
        }
    }
    public void setlist(List<String> list){
        this.list=list;
    }

    public FilelistAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String string=list.get(position);
        holder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        if(list==null)
            return 0;
        return list.size();
    }
}

package com.divyam.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.Myviewholder> {

    List<Model> data;
    Context context;

    public MyAdapter(List<Model> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    public List<Model> getData(List<Model> data) {
        return this.data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerviewholder ,parent,false);
        return (new Myviewholder(view)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.id.setText(data.get(position).getId());
        holder.title.setText(data.get(position).getTitle());
        String mssg = String.valueOf(data.get(position).isCompleted());
        holder.status.setText(mssg);
        if (data.get(position).isCompleted()){
            holder.done.setVisibility(View.VISIBLE);
        }
        else {
            holder.done.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
       if (data == null){
           return 0;
       }
        return data.size();

    }

    class Myviewholder extends RecyclerView.ViewHolder{
        CardView card;
        TextView id,title,status;
        ImageView done;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            done = itemView.findViewById(R.id.done);
        }


    }
}

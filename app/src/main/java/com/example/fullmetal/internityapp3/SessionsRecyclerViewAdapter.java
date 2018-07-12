package com.example.fullmetal.internityapp3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SessionsRecyclerViewAdapter extends RecyclerView.Adapter<SessionsRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Sessions> dataSet;

    public SessionsRecyclerViewAdapter(ArrayList<Sessions> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_card_row_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView title = holder.title;
        TextView details = holder.details;
        TextView date = holder.date;
        TextView time = holder.time;

        title.setText(dataSet.get(position).getTitle_session());
        details.setText(dataSet.get(position).getTitle_session());
        date.setText(dataSet.get(position).getDate_session());
        time.setText(dataSet.get(position).getTime_session());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView details;
        TextView date;
        TextView time;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.session_title);
            this.details = itemView.findViewById(R.id.session_details);
            this.date = itemView.findViewById(R.id.session_date);
            this.time = itemView.findViewById(R.id.session_time);
        }
    }
}

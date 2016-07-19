package com.offshootlab.onlinedataparsing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 15/7/16.
 */
public class ActorAdaprter extends RecyclerView.Adapter<ActorAdaprter.MyViewHolder> {

    private List<Actor> list;

    public ActorAdaprter(List<Actor> list) {

        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Actor actor = list.get(position);

        holder.name.setText(actor.getName());
        holder.country.setText(actor.getCountry());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, country;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.actorName);
            country = (TextView) itemView.findViewById(R.id.countryName);
        }
    }
}

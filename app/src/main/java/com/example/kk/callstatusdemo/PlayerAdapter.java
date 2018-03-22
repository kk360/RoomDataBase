package com.example.kk.callstatusdemo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.TextView;

import com.example.kk.callstatusdemo.Model.User;

import java.util.List;

/**
 * Created by KK on 3/23/2018.
 */

class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<User> users;

    public PlayerAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {
        holder.tvfname.setText(users.get(position).getFirstName());
        holder.tvlname.setText(users.get(position).getLastName());
        holder.tvemail.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvfname;
        public TextView tvlname;
        public TextView tvemail;
        public Button btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);

            tvfname = itemView.findViewById(R.id.etfname);
            tvlname = itemView.findViewById(R.id.etlname);
            tvemail = itemView.findViewById(R.id.etemail);
        }
    }
}

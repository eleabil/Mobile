package com.example.firebaseauth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PanelAdapter extends RecyclerView.Adapter<PanelAdapter.PanelViewHolder> {

    private LayoutInflater inflater;
    private List<Panel> panelList;

    PanelAdapter(Context ctx, List<Panel> panelList){

        inflater = LayoutInflater.from(ctx);
        this.panelList = panelList;
    }

    @NonNull
    @Override
    public PanelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_panel, parent, false);
        return new PanelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PanelAdapter.PanelViewHolder holder, int position) {

        Picasso.get().load(panelList.get(position).getPhotoUrl()).into(holder.photoUrl);
        holder.panelType.setText(panelList.get(position).getPanelType());
        holder.power.setText(panelList.get(position).getPower());
        holder.capacity.setText(panelList.get(position).getCapacity());
        holder.usagePeriod.setText(panelList.get(position).getUsagePeriod());
        holder.address.setText(panelList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return panelList.size();
    }

    class PanelViewHolder extends RecyclerView.ViewHolder{

        TextView panelType;
        TextView power;
        TextView capacity;
        TextView usagePeriod;
        TextView address;
        ImageView photoUrl;


        PanelViewHolder(View itemView) {
            super(itemView);

            photoUrl = itemView.findViewById(R.id.item_panel_image_view);
            panelType =  itemView.findViewById(R.id.item_panel_type);
            power = itemView.findViewById(R.id.item_panel_power);
            capacity =  itemView.findViewById(R.id.item_panel_capacity);
            usagePeriod =  itemView.findViewById(R.id.item_panel_usage_period);
            address =  itemView.findViewById(R.id.item_panel_address);
        }
    }
}
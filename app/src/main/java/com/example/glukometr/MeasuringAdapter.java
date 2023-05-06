package com.example.glukometr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeasuringAdapter extends RecyclerView.Adapter<MeasuringAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Measuring> measurings;
    MeasuringAdapter(Context context,List<Measuring> measurings){
        this.measurings = measurings;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public MeasuringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = inflater.inflate(R.layout.recycleview_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MeasuringAdapter.ViewHolder holder,int position){
        Measuring measuring = measurings.get(position);
        holder.currentDateView.setText(measuring.getFullDate());
        holder.wavingView.setText(measuring.getWaving());
        holder.averageValueView.setText(measuring.getAverageValue());
    }
    @Override
    public int getItemCount(){
        return measurings.size();
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        final TextView currentDateView, wavingView, averageValueView;
        ViewHolder(View view){
            super(view);
            currentDateView = view.findViewById(R.id.current_date);
            wavingView = view.findViewById(R.id.waving_value);
            averageValueView = view.findViewById(R.id.average_value);
        }
    }

}

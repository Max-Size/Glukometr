package com.example.glukometr.data_layer;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.glukometr.R;
import com.example.glukometr.ui_layer.RecentListActivity;

import java.util.List;

public class MeasuringAdapter extends RecyclerView.Adapter<MeasuringAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Measuring> measurings;
    public MeasuringAdapter(Context context,List<Measuring> measurings){
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
        holder.URi = measuring.getChartURi();
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
        final Button detailsBtnView;
        String URi;
        ViewHolder(View view){
            super(view);
            currentDateView = view.findViewById(R.id.current_date);
            wavingView = view.findViewById(R.id.waving_value);
            averageValueView = view.findViewById(R.id.average_value);
            detailsBtnView = view.findViewById(R.id.details);
            detailsBtnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(URi));
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}

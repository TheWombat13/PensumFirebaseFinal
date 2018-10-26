package com.example.jonathanlarsen.pensumfirebase.PensumList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jonathanlarsen.pensumfirebase.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PensumList_Adapter extends ArrayAdapter<PensumModel> {


    private Context context;

    public PensumList_Adapter(@NonNull Context context, int resource, List<PensumModel> items) {
        super(context, resource, items);
        this.context = context;
    }

    private class ViewHolder {
        TextView pensumView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        PensumModel pensum = getItem(position);


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.pensumlist_item, null);
            holder = new ViewHolder();
            holder.pensumView = (TextView) convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //assert pensum != null;
        holder.pensumView.setText(pensum.getTitle() + "");
        return convertView;
    }
}

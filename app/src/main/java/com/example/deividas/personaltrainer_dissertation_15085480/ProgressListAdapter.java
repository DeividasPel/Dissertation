package com.example.deividas.personaltrainer_dissertation_15085480;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ProgressListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProgressList> mProgressList;

    public ProgressListAdapter(Context mContext, List<ProgressList> mProgressList) {
        this.mContext = mContext;
        this.mProgressList = mProgressList;
    }

    @Override
    public int getCount() {
        return mProgressList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProgressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.progress, null);
        TextView tvName = v.findViewById(R.id.tv_main);
        TextView tvMeals = v.findViewById(R.id.tv_meal);
        TextView tvWorkouts = v.findViewById(R.id.tv_workout);
        TextView tvSteps = v.findViewById(R.id.tv_steps);
        tvName.setText(mProgressList.get(position).getNameSurname());
        tvMeals.setText(mProgressList.get(position).getMeals());
        tvWorkouts.setText(mProgressList.get(position).getWorkouts());
        tvSteps.setText(mProgressList.get(position).getSteps());

        v.setTag(mProgressList.get(position).getId());
        return v;
    }
}

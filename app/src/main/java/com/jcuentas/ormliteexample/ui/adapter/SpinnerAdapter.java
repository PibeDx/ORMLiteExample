package com.jcuentas.ormliteexample.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<SpinnerVal> {

	private Context context;
//	private SpinnerVal[] values;
	private List<SpinnerVal> listaValues;
	LayoutInflater inflater;

	public SpinnerAdapter(Context context, int textViewResourceId,
			/*SpinnerVal[] values,*/ List<SpinnerVal> listValues) {
		super(context, textViewResourceId, listValues);
		this.context = context;
//		this.values = values;
		this.listaValues =  listValues;
		this.inflater = LayoutInflater.from(context);
	}

	public int getCount() {
//		return values.length;
		return listaValues.size();
	}

	public SpinnerVal getItem(int position) {
//		return values[position];
		return listaValues.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		label.setTextColor(Color.BLACK);
		label.setPadding(10, 10, 0, 10);
		label.setTextSize(16f);
		label.setText(listaValues.get(position).getValue());
		return label;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		label.setTextColor(Color.BLACK);
		label.setPadding(10, 10, 0, 10);
		label.setTextSize(16f);
		label.setText(listaValues.get(position).getValue());
		return label;
	}

	class PlaceHolder {
		TextView txvValue;
	}

}

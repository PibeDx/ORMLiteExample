package com.jcuentas.example.ui.activity;

import android.support.v4.app.FragmentActivity;

import com.jcuentas.example.data.DBHelper;


public class BaseActivity extends FragmentActivity {

	private DBHelper dbHelper = null;

	public DBHelper getDbHelper() {
		if (dbHelper == null) {
			dbHelper = new DBHelper(this);

		}
		return dbHelper;
	}

	public void limpiarDbHelper() {
		if (dbHelper != null) {
			dbHelper.close();
		}
		dbHelper = null;
	}

	@Override
	protected void onPause() {
		super.onPause();
//		limpiarDbHelper();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
}

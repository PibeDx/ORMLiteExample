package com.jcuentas.ormliteexample.data.dao;

import android.database.Cursor;

import com.jcuentas.ormliteexample.data.DBHelper;
import com.jcuentas.ormliteexample.data.model.Account;

import org.json.JSONArray;

import java.util.List;

public class AccountDao extends BaseDao<Account, Integer>{

	public AccountDao(DBHelper dbHelper, Class<Account> c) {
		super(dbHelper, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long crear(Account entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long actualizar(Account entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarIds(JSONArray array) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account obtenerPorId(Integer llave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarPorId(Integer llave) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarTodos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Account obtenerDesdeCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

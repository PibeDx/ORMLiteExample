package com.jcuentas.example.data.dao;

import android.database.Cursor;

import com.jcuentas.example.data.DBHelper;
import com.jcuentas.example.data.model.Account;

import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public class AccountDao extends BaseDao<Account, Integer>{

	public static final String TAG = "AccountDao";

	public AccountDao(DBHelper dbHelper, Class<Account> c) {
		super(dbHelper, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long crear(Account entidad) {
		long id = -1;
		try {
			getDao().create(entidad);
			id = entidad.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
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
		Account account = null;
		try {
			account = getDao().queryForId(llave);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public List<Account> obtenerTodos() {
		List<Account> accounts = null;
		try {
			accounts = getDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
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

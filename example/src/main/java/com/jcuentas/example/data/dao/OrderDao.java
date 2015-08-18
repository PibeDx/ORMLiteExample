package com.jcuentas.example.data.dao;

import android.database.Cursor;

import com.jcuentas.example.data.DBHelper;
import com.jcuentas.example.data.model.Order;

import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public class OrderDao extends BaseDao<Order, Integer>{
	public static final String TAG = "OrderDao";

	public OrderDao(DBHelper dbHelper, Class<Order> c) {
		super(dbHelper, c);
	}

	@Override
	public long crear(Order entidad) {
		long id =-1;
		try {
			getDao().create(entidad);
			id=entidad.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public long actualizar(Order entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarIds(JSONArray array) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order obtenerPorId(Integer llave) {
		Order order = null;
		try {
			order = getDao().queryForId(llave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> obtenerTodos() {
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
	protected Order obtenerDesdeCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

}

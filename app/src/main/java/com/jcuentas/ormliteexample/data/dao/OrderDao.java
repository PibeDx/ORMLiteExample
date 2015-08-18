package com.jcuentas.ormliteexample.data.dao;

import android.database.Cursor;

import com.jcuentas.ormliteexample.data.DBHelper;
import com.jcuentas.ormliteexample.data.model.Order;

import org.json.JSONArray;

import java.util.List;

public class OrderDao extends BaseDao<Order, Integer>{

	public OrderDao(DBHelper dbHelper, Class<Order> c) {
		super(dbHelper, c);
	}

	@Override
	public long crear(Order entidad) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
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

package com.jcuentas.ormliteexample.data.dao;

import android.database.Cursor;
import android.util.Log;

import com.jcuentas.ormliteexample.data.DBHelper;
import com.jcuentas.ormliteexample.data.model.Grupo;

import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public class GrupoDao extends BaseDao<Grupo, Integer> {

	public GrupoDao(DBHelper dbHelper, Class<Grupo> c) {
		super(dbHelper, c);
	}

	@Override
	public long crear(Grupo entidad) {
		int id = 0;
		try {
			mDao = getDao();
			id = mDao.create(entidad);
			id = entidad.getId();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("UsuarioDao", "Error creando grupo\n + ");
		}
		return id;
	}

	@Override
	public long actualizar(Grupo entidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarIds(JSONArray array) {
		// TODO Auto-generated method stub

	}

	@Override
	public Grupo obtenerPorId(Integer llave) {
		Grupo grupo = null;
		
		try {
		    mDao = getDao();
//		    QueryBuilder<Grupo, Integer> queryBuilder = mDao.queryBuilder();
//		    queryBuilder.where().eq(Usuario.ID, llave);
//		    PreparedQuery<Grupo> usuQuery = queryBuilder.prepare();
		    grupo = mDao.queryForId(llave);
		} catch (SQLException e) {
		    Log.e("UsuarioDao", "Error creando usuario");
		}
		return grupo;
	}

	@Override
	public List<Grupo> obtenerTodos() {
		List<Grupo> grupos = null;
		try {
			mDao = getDao();
			grupos = mDao.queryForAll();
		} catch (SQLException e) {
			Log.e("UsuarioDao", "Error creando usuario");
		}
		return grupos;
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
	protected Grupo obtenerDesdeCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

}

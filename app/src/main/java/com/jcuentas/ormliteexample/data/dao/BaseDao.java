package com.jcuentas.ormliteexample.data.dao;

import android.database.Cursor;

import com.j256.ormlite.dao.Dao;
import com.jcuentas.ormliteexample.data.DBHelper;

import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<E, K> {

//	private SQLiteOpenHelper dbHelper = null;
	private DBHelper mDBHelper;
	protected Dao<E, K> mDao;
	private E entidad;
	private Class<E> classEntidad;
	
//	protected Dao<E, K> dao;
//	public BaseDao(SQLiteOpenHelper dbHelper) {
//		this.dbHelper = dbHelper;
//
//	}
	
	public BaseDao(DBHelper dbHelper, Class<E> c) {
		this.mDBHelper = dbHelper;
		classEntidad=c;
	}

	protected DBHelper getDbHelper() {
		return mDBHelper;
	}
	
	public Dao<E, K> getDao() throws SQLException {
		if (mDao == null) {
			mDao = mDBHelper.getDao(classEntidad);
		}
		return mDao;
	}
	
	public void close(){
		mDao=null;
	}

	public abstract long crear(E entidad);

	public abstract long actualizar(E entidad);

	public abstract void actualizarIds(JSONArray array);

	public abstract E obtenerPorId(K llave);

	public abstract List<E> obtenerTodos();

	public abstract boolean eliminarPorId(K llave);

	public abstract boolean eliminarTodos();

	protected abstract E obtenerDesdeCursor(Cursor cursor);
	

}

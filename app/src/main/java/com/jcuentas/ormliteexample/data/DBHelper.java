package com.jcuentas.ormliteexample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jcuentas.ormliteexample.data.model.Account;
import com.jcuentas.ormliteexample.data.model.Grupo;
import com.jcuentas.ormliteexample.data.model.Order;
import com.jcuentas.ormliteexample.data.model.Usuario;

import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "androcode_ormlite.db";
	private static final int DATABASE_VERSION = 15;

//	private Dao<Usuario, Integer> usuarioDao;
//	private Dao<Grupo, Integer> grupoDao;

	
	private Dao<Account, Integer> accountDao;
	private Dao<Order, Integer> orderDao;
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, Usuario.class);
			TableUtils.createTable(connectionSource, Grupo.class);
			
			TableUtils.createTable(connectionSource, Account.class);
			TableUtils.createTable(connectionSource, Order.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		try {
//			TableUtils.clearTable(connectionSource,  Usuario.class);
//			TableUtils.clearTable(connectionSource,  Grupo.class);
			TableUtils.dropTable(connectionSource, Usuario.class, true);
			TableUtils.dropTable(connectionSource, Grupo.class, true);
			
			
			TableUtils.dropTable(connectionSource, Account.class, true);
			TableUtils.dropTable(connectionSource, Order.class, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onCreate(arg0, connectionSource);

	}

	/*public Dao<Usuario, Integer> getUsuarioDao() throws SQLException {
		if (usuarioDao == null) {
			usuarioDao = getDao(Usuario.class);
		}
		return usuarioDao;
	}

	public Dao<Grupo, Integer> getGrupoDao() throws SQLException {
		if (grupoDao == null) {
			grupoDao = getDao(Grupo.class);
		}
		return grupoDao;
	}*/

	@Override
	public void close() {
		super.close();
//		usuarioDao = null;
//		grupoDao = null;
	}
	
	

}



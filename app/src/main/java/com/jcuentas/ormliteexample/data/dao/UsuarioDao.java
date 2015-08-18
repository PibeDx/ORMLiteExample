package com.jcuentas.ormliteexample.data.dao;

import android.database.Cursor;
import android.util.Log;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.table.TableUtils;
import com.jcuentas.ormliteexample.data.DBHelper;
import com.jcuentas.ormliteexample.data.model.Grupo;
import com.jcuentas.ormliteexample.data.model.Usuario;

import org.json.JSONArray;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UsuarioDao extends BaseDao<Usuario, Integer> {

	public UsuarioDao(DBHelper dbHelper, Class c) {
		super(dbHelper, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long crear(Usuario entidad) {
		int id  = 0;
		try {
			mDao = getDao();
			Usuario usuario = new Usuario();
			usuario.setFechaNacimiento(new Date());
			usuario.setNombre(entidad.getNombre());
			usuario.setGrupo(entidad.getGrupo());
			id=mDao.create(usuario);
			id= usuario.getId();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("UsuarioDao", "Error creando usuarios + "+e);
		}
		return id;
	}

	@Override
	public long actualizar(Usuario entidad) {
		int update = 0;
		try {
		    mDao = getDao();
		    UpdateBuilder<Usuario, Integer> updateBuilder =getDao().updateBuilder();
		    updateBuilder.updateColumnValue(Usuario.NOMBRE, entidad.getNombre());
		    updateBuilder.where().eq(Usuario.ID, entidad.getId());
		    update= updateBuilder.update();
		    Log.d("UsuarioDao", "Usuario modificado: " + entidad.getNombre());
		} catch (SQLException e) {
		    Log.e("UsuarioDao", "Error creando usuario");
		}
		return update;
	}

	@Override
	public void actualizarIds(JSONArray array) {

	}

	@Override
	public Usuario obtenerPorId(Integer llave) {
		return null;
	}
	
	public List<Usuario> obtenerTodosPorNombre(Usuario entidad) {
		
		List<Usuario> usuarios = null;
		
		try {
		    mDao = getDao();
		    QueryBuilder<Usuario, Integer> queryBuilder = mDao.queryBuilder();
		    queryBuilder.where().eq(Usuario.NOMBRE, entidad.getNombre());
		    PreparedQuery<Usuario> usuQuery = queryBuilder.prepare();
		    usuarios = mDao.query(usuQuery);
		} catch (SQLException e) {
		    Log.e("UsuarioDao", "Error creando usuario");
		}
		return usuarios;
	}
	public List<Usuario> obtenerTodosSubTodos() {
		
		List<Usuario> usuarios = null;
		
		try {
		    mDao = getDao();
		    GrupoDao grupoDao = new GrupoDao(getDbHelper(), Grupo.class);
		    
//		    QueryBuilder<Usuario, Integer> queryBuilder = mDao.queryBuilder();
//		    queryBuilder.where().eq(Usuario.NOMBRE, entidad.getNombre());
//		    PreparedQuery<Usuario> usuQuery = queryBuilder.prepare();
//		    usuarios = mDao.query(usuQuery);
		    
		    
		    
		    

			// outer query on UnitResult table
			QueryBuilder<Grupo,Integer> grupoBuilder = grupoDao.getDao().queryBuilder();
			
			
//			grupoBuilder.where().eq(Grupo.NOMBRE, "");
			
			
			// setup our sub-query on the Unit table first
			QueryBuilder<Usuario,Integer> usuarioBuilder = mDao.queryBuilder();
			// in using the sub-query
//			usuarioBuilder.where().in(Usuario.ID, grupoBuilder);
			usuarioBuilder.join(grupoBuilder);
			Log.e("UsuarioDao", "as "+usuarioBuilder.prepareStatementString());
			usuarios = usuarioBuilder.query();
		} catch (SQLException e) {
			e.printStackTrace();
		    Log.e("UsuarioDao", "Error al listar Usuario");
		}
		return usuarios;
	}
	
public List<Usuario> obtenerTodosSubTodos2() {
		
		List<Usuario> usuarios = null;
		
		try {
		    mDao = getDao();
		    GrupoDao grupoDao = new GrupoDao(getDbHelper(), Grupo.class);
		    
		    Log.e("UsuarioDao", "SIZE grupoDao "+grupoDao.obtenerTodos().size());
			// outer query on UnitResult table
			QueryBuilder<Grupo,Integer> grupoBuilder = grupoDao.getDao().queryBuilder();
//			grupoBuilder.where().eq(Grupo.ID, Usuario.GRUPO);
			// setup our sub-query on the Unit table first
			QueryBuilder<Usuario,Integer> usuarioBuilder = mDao.queryBuilder();
			
			// in using the sub-query
			usuarioBuilder.join(grupoBuilder);
			Log.e("UsuarioDao", "as "+usuarioBuilder.prepareStatementString());
			usuarios = usuarioBuilder.query();
			for (int i = 0; i < usuarios.size(); i++) {
				Usuario usuario = (Usuario) usuarios.get(i);
				usuario.setGrupo(grupoDao.obtenerPorId(usuario.getGrupo().getId()));
				usuarios.set(i, usuario);
				Log.d("UsuarioDao", "as "+usuario.getGrupo().getNombre());
			}
//			for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
//				Usuario usuario = (Usuario) iterator.next();
//				usuario.setGrupo(grupoDao.obtenerPorId(usuario.getId()));
//				Log.e("UsuarioDao", "as "+grupoDao.obtenerPorId(usuario.getId()).getNombre());
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e("UsuarioDao", "Error al listar Usuario");
		}
		return usuarios;
	}
	
	

	@Override
	public List<Usuario> obtenerTodos() {
		List<Usuario> usuarios = null;
		try {
			mDao = getDao();
		    /*
		    Usuario usuario = (Usuario) dao.queryForId(1);
		    if (usuario == null) {
		        Log.d("UsuarioDao", "Ning�n usuario con id = 1");                
		    } else {
		        Log.d("UsuarioDao", "Recuperado usuario con id = 1: " + usuario.getNombre());
		    }
		    */
		    /*
		    usuarios = dao.queryForEq(Usuario.NOMBRE, "Jos�");
		    if (usuarios.isEmpty()) {
		        Log.d("UsuarioDao", "No se encontraron usuarios con nombre = Fede");                
		    } else {
		        Log.d("UsuarioDao", "Recuperado usuarios con nombre = Fede " + usuarios);                
		    }
		    */
		    usuarios = mDao.queryForAll();
		} catch (SQLException e) {
		    Log.e("UsuarioDao", "Error creando usuario");
		}
		return usuarios;
	}

	@Override
	public boolean eliminarPorId(Integer llave) {
		try {
			mDao= getDao();
			DeleteBuilder<Usuario, Integer> queryBuilder = mDao.deleteBuilder();
			queryBuilder.where().eq(Usuario.ID, llave);
			queryBuilder.delete();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean eliminarTodos() {
		try {
			mDao = getDao();
		    /*List list = dao.queryForAll();
		    dao.delete(list);*/
		    int cantEliminado = TableUtils.clearTable(mDao.getConnectionSource(),  Usuario.class);
		    Log.e("UsuarioDao", "Eliminado: "+cantEliminado);
		    return true;
		} catch (SQLException e) {
		    Log.e("UsuarioDao", "Error creando usuario");
		    return false;
		}
	}

	@Override
	protected Usuario obtenerDesdeCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

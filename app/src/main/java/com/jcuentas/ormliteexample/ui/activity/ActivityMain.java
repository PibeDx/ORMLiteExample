package com.jcuentas.ormliteexample.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcuentas.ormliteexample.R;
import com.jcuentas.ormliteexample.data.dao.GrupoDao;
import com.jcuentas.ormliteexample.data.dao.UsuarioDao;
import com.jcuentas.ormliteexample.data.model.Grupo;
import com.jcuentas.ormliteexample.data.model.Usuario;
import com.jcuentas.ormliteexample.ui.adapter.SpinnerGrupoAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityMain extends BaseActivity {
	
	EditText edtCodigo, edtNombre;
	Spinner spListaGrupo;
	Button btnInsert, btnUpdate, btnEliminar, btnEliminarTodos, btnListarTodos, btnListarPorGrupo, btnExportarDB;
	UsuarioDao usuarioDao;
	GrupoDao grupoDao;
	SpinnerGrupoAdapter adapterGrupoSpinner;
//	DBHelper dbHelper;
	long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		InicializarVariables();
		InicializarDao();
		InicializarButton();
		CargaDataSpinner();
		
//		dbHelper = new DBHelper(this);
		
		
		/*
		Usuario usuario = new Usuario();
		usuario.setNombre("Jos�");
//		Grupo grupo = new Grupo();
//		grupo.setId(0);
//		grupo.setNombre("Grupo_"+0);
		usuario.setGrupo(grupo);
		id = usuarioDao.crear(usuario);
		Log.i("ActivityMain", "id: "+id);
*/
		

		
		
	}

	private void CargaDataSpinner() {
		List<Grupo> grupos = grupoDao.obtenerTodos();
		//Ingresamos data por Default
		grupos.add(0,new Grupo(-1, "-Seleccione-"));
		
		adapterGrupoSpinner= new SpinnerGrupoAdapter(this, android.R.layout.simple_spinner_item, grupos);
		spListaGrupo.setAdapter(adapterGrupoSpinner);
	}

	private void InicializarButton() {
		btnInsert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Grupo grupo = adapterGrupoSpinner.getItem(spListaGrupo.getSelectedItemPosition());
				Usuario usuario = new Usuario();
				usuario.setNombre(edtNombre.getText().toString());
				usuario.setGrupo(grupo);
				id = usuarioDao.crear(usuario);
				Log.i("ActivityMain", "id: "+id);
			}
		});
		btnUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Usuario usuario = new Usuario();
				usuario.setId(Integer.parseInt(edtCodigo.getText().toString()));
				usuario.setNombre(edtNombre.getText().toString());
				int result = (int) usuarioDao.actualizar(usuario);
				Log.i("ActivityMain", "Se actualizo: " + result);
			}
		});
		btnEliminar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Usuario usuario = new Usuario();
				usuario.setId(Integer.parseInt(edtCodigo.getText().toString()));
				usuarioDao.eliminarPorId(usuario.getId());
			}
		});

		btnEliminarTodos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				usuarioDao.eliminarTodos();
			}
		});
		
		btnListarTodos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				List<Usuario> lsUsuario = usuarioDao.obtenerTodos();
				for (int i = 0; i < lsUsuario.size(); i++) {
					Log.i("Nombre: ", lsUsuario.get(i).getNombre());
					Log.i("Codigo: ",""+ lsUsuario.get(i).getId());
				}
				
			}
		});
		
		btnListarPorGrupo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				List<Usuario> lsUsuario = usuarioDao.obtenerTodosSubTodos();
				List<Usuario> lsUsuario = usuarioDao.obtenerTodosSubTodos2();
				Log.i("asd", "size "+lsUsuario.size());
				for (int i = 0; i < lsUsuario.size(); i++) {
					Log.i("asd", lsUsuario.get(i).getNombre());
					Log.i("asd",""+ lsUsuario.get(i).getId());
					Log.i("asd","x"+ lsUsuario.get(i).getGrupo().getId());
					Log.i("asd","x"+ lsUsuario.get(i).getGrupo().getNombre());
				}
			}
		});
		
		btnExportarDB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exportDB();
			}
		});
	}

	private void InicializarDao() {
		usuarioDao = new UsuarioDao(getDbHelper(),Usuario.class);
		grupoDao = new GrupoDao(getDbHelper(), Grupo.class);
		//Insertamos en la taba Grupo
		if (grupoDao.obtenerTodos().size()<1) {
			Grupo grupo = null;
			int cantGrupo=5;
			for (int i = 0; i < cantGrupo; i++) {
			 	grupo = new Grupo();
				grupo.setNombre("Grupo_"+i);
				int id = (int) grupoDao.crear(grupo);
				grupo.setId(id);
			}
		}
	}

	private void InicializarVariables() {
		edtCodigo = (EditText) findViewById(R.id.codigo);
		edtNombre = (EditText) findViewById(R.id.nombre);
		btnInsert = (Button) findViewById(R.id.btnInsertar);
		btnUpdate = (Button) findViewById(R.id.btnActualizar);
		btnEliminar = (Button) findViewById(R.id.btnEliminar);
		btnEliminarTodos = (Button) findViewById(R.id.btnEliminarTodos);
		btnListarTodos =(Button)findViewById(R.id.btnListaTodos);
		btnListarPorGrupo= (Button)findViewById(R.id.btnListarPorGrupo);
		btnExportarDB =(Button)findViewById(R.id.btnExportardb);
		spListaGrupo = (Spinner)findViewById(R.id.spListaGrupo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	
private void exportDB(){
		
		SimpleDateFormat descf = new SimpleDateFormat(
				"dd-MM-yyyy hh:mm:ss", Locale.US);
		String fecha=descf.format(new Date());
		String SAMPLE_DB_NAME = "androcode_ormlite.db";
		String SAMPLE_DB_NAME_DESTINO = "SuSalud_"+fecha+".db";
		
		File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        
        File dir = new File(sd.getAbsolutePath()+"/databasesSusalud");
        dir.mkdirs();

        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.jcuentas.ormliteexample" +"/databases/"+ SAMPLE_DB_NAME;
        String backupDBPath = SAMPLE_DB_NAME_DESTINO;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd+"/databasesSuSalud", backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "�DB Exportada!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
	

}

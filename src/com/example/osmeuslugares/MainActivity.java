package com.example.osmeuslugares;

import com.example.osmeuslugares.modelo.bd.LugaresDb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TEST Crear BBDD
		try {
			LugaresDb lugaresDb = new LugaresDb(getBaseContext());
			Log.i("INFO", "BBDD creada");
			Toast.makeText(getBaseContext(), "Base de datos preparada",
					Toast.LENGTH_LONG).show();

		} catch (Exception e) {
			Log.e(getClass().toString(), e.getMessage());
		}

		mediaPlayer = MediaPlayer.create(this, R.raw.musica_fondo);

		// //////////////////

	}

	public boolean getPreferenciaMusica() {
		SharedPreferences preferencias = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		return preferencias.getBoolean("musica", false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {

		case R.id.listLugares: {
			lanzarListadoLugares();
			break;
		}

		case R.id.listCategorias: {
			lanzarListadoCategorias();
			break;
		}

		case R.id.action_settings: {
			lanzarPreferencias();
			break;
		}

		case R.id.acerca_de: {
			Toast.makeText(this, "Acerca De", Toast.LENGTH_SHORT).show();
			// lanzarAcercaDe();
			break;
		}

		case R.id.salir: {
			finish();
			break;
		}
		}
		

		return super.onOptionsItemSelected(item);
	}
	private void lanzarListadoCategorias() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, ListCategorias.class);
		startActivity(i);
		
	}

	private void lanzarListadoLugares() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, ListLugares.class);
		startActivity(i);

	}

	private void lanzarPreferencias() {
		Intent i = new Intent(this, PreferenciasActivity.class);
		startActivity(i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (this.getPreferenciaMusica()) {
			mediaPlayer.start();
		} else {
			mediaPlayer.stop();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mediaPlayer.stop();
	}

}

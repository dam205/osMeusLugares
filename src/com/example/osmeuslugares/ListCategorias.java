package com.example.osmeuslugares;

import com.example.osmeuslugares.modelo.Categoria;
import com.example.osmeuslugares.modelo.bd.LugaresDb;


import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ListCategorias extends ListActivity {

	private ListCategoriasAdapter listCategoriasAdapter;
	Bundle extras = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_categorias);
		registerForContextMenu(super.getListView());
		listCategoriasAdapter = new ListCategoriasAdapter(this);
		setListAdapter(listCategoriasAdapter);

	}

	/*public void imageButtonAddCategoriasrOnClick(View v) {
		Bundle extras = new Bundle();
		extras.putBoolean("add", true);
		lanzarEditCategoiras(extras);
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView,
	 * android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Categoria itemCategoria = (Categoria) getListAdapter()
				.getItem(position);
		// bundle para pasar datos
		Bundle extras = itemCategoria.getBundle();
		System.out.println("Desde listCategorias" + itemCategoria.getId());
		extras.putBoolean("add", false);
		lanzarEditCategoiras(extras);

		/*
		 * Toast.makeText(this, "Selecci—n: " + Integer.toString(position) +
		 * " - " + itemLugar.toString(),Toast.LENGTH_LONG).show();
		 */
	}

	private void lanzarEditCategoiras(Bundle extras) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, EditCategoriasActivity.class);
		i.putExtras(extras);
		startActivityForResult(i, 1234);
	}

	public boolean getPreferenciaVerInfoAmpliada() {
		SharedPreferences preferencias = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		return preferencias.getBoolean("ver_info_ampliada", false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1234 && resultCode == RESULT_OK) {
			String resultado = data.getExtras().getString("resultado");
			Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_categorias, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id == R.id.add_categorias) {
			extras.clear();
			extras.putBoolean("add", true);
			lanzarEditCategoiras(extras);
			return true;
		}
		if (id == R.id.cerrar) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
	 * android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list_categorias_contextual, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Categoria categoria = (Categoria) listCategoriasAdapter
				.getItem(info.position);
		LugaresDb lugaresDb = new LugaresDb(this);
		
		switch (item.getItemId()) {

		case R.id.edit_categoria:
			lugaresDb.editCategoria(categoria);
			return true;

		case R.id.delete_categoria:
			lugaresDb.eliminarCategoria(categoria);
			Toast.makeText(getBaseContext(), "Eliminar: " + categoria.getNombre(),
					Toast.LENGTH_SHORT).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onRestart() {
		super.onRestart();
		listCategoriasAdapter.actualizarDesdeBd();
		listCategoriasAdapter.notifyDataSetChanged();

	}

}

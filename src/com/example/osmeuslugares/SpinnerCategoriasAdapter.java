package com.example.osmeuslugares;

import java.util.Vector;

import com.example.osmeuslugares.modelo.Categoria;
import com.example.osmeuslugares.modelo.bd.LugaresDb;

import android.app.Activity;
import android.database.SQLException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerCategoriasAdapter extends BaseAdapter {

	private final Activity activity;
	private Vector<Categoria> lista;
	private LugaresDb lugaresDb;

	/**
	 * @param activity
	 * @param lista
	 */
	public SpinnerCategoriasAdapter(Activity activity) {
		super();
		this.activity = activity;
		this.lista = new Vector<Categoria>();
		cargarDatosDesdeBd();
	}

	public void cargarDatosDesdeBd() throws SQLException{
		lugaresDb = new LugaresDb(activity);
		this.lista = lugaresDb.cargarCategoriasDesdeBD(true);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.elementAt(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Categoria categoria = (Categoria)getItem(position);
		return categoria.getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		Categoria categoria = (Categoria)lista.elementAt(position);
		TextView text = new TextView(activity);
		text.setText(categoria.getNombre());
        return text;
		
	}
	
	
	public int getPositionById(Long id) {
		//Buscar en lista 
		Categoria buscar = new Categoria();
		buscar.setId(id);
		return lista.indexOf(buscar);
	}
}

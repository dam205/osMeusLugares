package com.example.osmeuslugares;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.example.osmeuslugares.modelo.Categoria;
import com.example.osmeuslugares.modelo.bd.LugaresDb;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCategoriasAdapter extends BaseAdapter {

	private final Activity activity;
	private Vector<Categoria> lista;
	private LugaresDb lugaresDb;
	private boolean verInfoAmpliada;
	
	//private RecursoIcono recursoIcono;
	/**
	 * 
	 * @param activity
	 * @param lista
	 */
	public ListCategoriasAdapter(Activity activity) {
		super();
		this.activity = activity;
		this.verInfoAmpliada = true;
		this.lista = new Vector<Categoria>();
		actualizarDesdeBd();
		// Cargar recursos iconos
		//this.recursoIcono = new RecursoIcono(activity);

	}

	/**
	 * @return the verInfoAmpliada
	 */
	public boolean isVerInfoAmpliada() {
		return verInfoAmpliada;
	}

	/**
	 * @param verInfoAmpliada
	 *            the verInfoAmpliada to set
	 */
	public void setVerInfoAmpliada(boolean verInfoAmpliada) {
		this.verInfoAmpliada = verInfoAmpliada;
	}

	// idea: crear lugaresdb y cargar lista después los métodos hacen uso de
	// estos métodos
	public void actualizarDesdeBd() throws SQLException {
		lugaresDb = new LugaresDb(activity);
		this.lista = lugaresDb.cargarCategoriasDesdeBD(false);
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.elementAt(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Categoria categoira = (Categoria) getItem(position);
		return categoira.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup) suministrar a lislugares
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// habrá im if o switch para informacióin ampliada o no
		// TODO Auto-generated method stub
		LayoutInflater inflater = activity.getLayoutInflater();
		View view;
		
		view = inflater.inflate(R.layout.elemento_lista, null, true);
		TextView textViewTitulo = (TextView) view
			.findViewById(R.id.textViewTitulo);
		ImageView imgViewIcono = (ImageView) view.findViewById(R.id.icono);
		TextView textViewInfo = (TextView) view
				.findViewById(R.id.textViewInfo);
		Categoria categoria = (Categoria) lista.elementAt(position);
		textViewTitulo.setText(categoria.getNombre());
		textViewInfo.setText(categoria.getIcon());
		//textViewTitulo.setText(lista.elementAt(position));
		// pendiente textViewrUrl
		
		/*Drawable icon = recursoIcono.obtenDrawableIcon(categoria.getIcon());
		imgViewIcono.setImageDrawable(icon);*/
		
		return view;
	}
	
}

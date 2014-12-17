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

	Resources res;
	TypedArray drawableIconosCategorias;
	List<String> valoresIconosCategorias;

	/**
	 * @param activity
	 * @param lista
	 */
	public ListCategoriasAdapter(Activity activity) {
		super();
		this.activity = activity;
		cargarDatosDesdeBd();


		res = activity.getResources();
		drawableIconosCategorias = res
				.obtainTypedArray(R.array.drawable_iconos_lugares);

		valoresIconosCategorias = Arrays.asList(res
				.getStringArray(R.array.valores_iconos_lugares));

	}

	public void cargarDatosDesdeBd() throws SQLException {
		lugaresDb = new LugaresDb(activity);
		this.lista = lugaresDb.cargarCategoriasDesdeBD();

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
		Categoria categoria = (Categoria) getItem(position);
		return categoria.getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = activity.getLayoutInflater();
		View view = inflater.inflate(R.layout.elemento_lista_categorias, null,
				true);
		Categoria categoria = lista.elementAt(position);
		TextView text = (TextView) view.findViewById(R.id.textViewTitulo);
		text.setText(categoria.getNombre());
		ImageView imgViewIcono = (ImageView) view.findViewById(R.id.icono);
		Drawable icon = obtenDrawableIcon(categoria.getIcon());
		imgViewIcono.setImageDrawable(icon);
		return view;
	}

	public int getPositionById(Long id) {
		Categoria buscar = new Categoria();
		buscar.setId(id);
		return lista.indexOf(buscar);
	}

	public Drawable obtenDrawableIcon(String icon) {
		int posicion = valoresIconosCategorias.indexOf(icon);
		if (posicion == -1)
			posicion = 0;
		return drawableIconosCategorias.getDrawable(posicion);
	}
}

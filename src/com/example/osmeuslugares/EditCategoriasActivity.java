package com.example.osmeuslugares;

import com.example.osmeuslugares.modelo.Categoria;
import com.example.osmeuslugares.modelo.Lugar;
import com.example.osmeuslugares.modelo.bd.LugaresDb;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditCategoriasActivity extends Activity {
	
	private Categoria categoriaEdit;

	private Spinner spinnerCategoria;
	private TextView editTextNombre;
	private boolean add;
	LugaresDb lugaresDb;

	SpinnerCategoriasAdapter categoriasAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_categoria);

		// Nombre
		editTextNombre = (TextView) findViewById(R.id.editTextNombre);
		// Categoria 
		spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
		categoriasAdapter = new SpinnerCategoriasAdapter(this);
		spinnerCategoria.setAdapter(categoriasAdapter);
		
		categoriaEdit = new Categoria();
		Bundle extras = new Bundle();
		extras = getIntent().getExtras();
		add = extras.getBoolean("add");
		if (add) {
			Toast.makeText(getBaseContext(), "Añadir Categoria", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getBaseContext(), extras.getString(Lugar.C_NOMBRE),
					Toast.LENGTH_LONG).show();
			categoriaEdit.setBundle(extras);
		}

		establecerValoresEditar();

	}

	public void onClickCancelar(View v) {
		confirmar("Salir?", "Comfirmar");
	}

	private void confirmar(String texto, String titulo) {
		AlertDialog.Builder dialogConfirmar = new AlertDialog.Builder(this);
		dialogConfirmar.setTitle(titulo);
		dialogConfirmar.setMessage(texto);
		dialogConfirmar.setCancelable(false);
		dialogConfirmar.setPositiveButton("Confirmar",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						confirmar();
					}

					private void confirmar() {
						// TODO Auto-generated method stub
						finish();
					}

				});
		dialogConfirmar.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						cancelar();
					}

					private void cancelar() {
						// TODO Auto-generated method stub
						// hacer toast para poner opración cancelada
						Toast.makeText(getBaseContext(), "Operación cancelada",
								Toast.LENGTH_LONG).show();
					}
				});
		dialogConfirmar.show();// mostrar ventana de alert
	}

	private void establecerValoresEditar() {
		// TODO Auto-generated method stub
		editTextNombre.setText(categoriaEdit.getNombre());
		
		//int position = categoriasAdapter.getPositionById(categoriaEdit.getId());
		int position = 0;
		
		if (!add) {
			long i = categoriaEdit.getId();
			position = (int) i;
			Log.i("INFO", "Position=" + position);
		}
		spinnerCategoria.setSelection(position);

	}
	
	

	public void onClickGuardar(View v) {

		// Intent i=new Intent();
		// i.putExtra("resultado", "RESULTADO..");
		// setResult(RESULT_OK, i);
		// finish();

		if (add) {
			Categoria categoria = new Categoria();
			LugaresDb lugaresDb = new LugaresDb(this);

			categoria.setNombre(editTextNombre.getText().toString());
			categoria.setIcon(spinnerCategoria.getSelectedItem().toString());

			lugaresDb.createCategoria(categoria);
			Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_LONG)
					.show();
		} else {
			// poñer todo o de arriba pero con lugaredit

			Categoria editCategoria = new Categoria();
			LugaresDb lugaresDb = new LugaresDb(this);

			editCategoria.setId(categoriaEdit.getId());
			editCategoria.setNombre(editTextNombre.getText().toString());
			editCategoria.setIcon(spinnerCategoria.getSelectedItem().toString());
			// editLugar.setId();
			lugaresDb.editCategoria(editCategoria);
			Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_categoria, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {

		case R.id.action_settings: 
		return true;

		default:
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}

}

package com.example.osmeuslugares;

import com.example.osmeuslugares.modelo.Lugar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditLugarActivity extends Activity {
	private Lugar lugarEdit;

	private Spinner spinnerCategoria;
	private TextView editTextNombre;
	private TextView editTextDireccion;
	private TextView editTextTelefono;
	private TextView editTextUrl;
	private TextView editTextComentario;

	SpinnerCategoriasAdapter categoriasAdapter;
	private boolean add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_lugar);

		// Nombre
		editTextNombre = (TextView) findViewById(R.id.editTextNombre);
		// Categoria
		spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
		categoriasAdapter = new SpinnerCategoriasAdapter(this);
		spinnerCategoria.setAdapter(categoriasAdapter);
		// Direccion
		editTextDireccion = (TextView) findViewById(R.id.editDireccion);
		// Telefono
		editTextTelefono = (TextView) findViewById(R.id.editTelefono);
		// Url
		editTextUrl = (TextView) findViewById(R.id.editUrl);
		// Comentario
		editTextComentario = (TextView) findViewById(R.id.editComentario);

		lugarEdit = new Lugar();
		Bundle extras = new Bundle();
		extras = getIntent().getExtras();
		add = extras.getBoolean("add");
		if (add) {
			Toast.makeText(getBaseContext(), "ADD", Toast.LENGTH_LONG).show();
			// lugarEdit.setValoresIniciales();
		} else {
			Toast.makeText(getBaseContext(), extras.getString(Lugar.C_NOMBRE),
					Toast.LENGTH_LONG).show();
			lugarEdit.setBundle(extras);
		}

		// Establecer valores desde lugarEdit a widget edici—n
		establecerValoresEditar();

	}

	private void establecerValoresEditar() {
		// TODO Auto-generated method stub
		editTextNombre.setText(lugarEdit.getNombre());

		int position = 0;
		if (!add) {
			position = categoriasAdapter.getPositionById(lugarEdit
					.getCategoria().getId());
		}
		spinnerCategoria.setSelection(position);

		editTextDireccion.setText(lugarEdit.getDireccion());
		editTextTelefono.setText(lugarEdit.getTelefono());
		editTextUrl.setText(lugarEdit.getUrl());
		editTextComentario.setText(lugarEdit.getComentario());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_lugar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public void buttonGuardarOnClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent();
		i.putExtra("resultado", "RESULTADO..");
		setResult(RESULT_OK, i);
		finish();
	}
	
	
}

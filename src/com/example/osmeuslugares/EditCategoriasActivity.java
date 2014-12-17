package com.example.osmeuslugares;

import java.util.Arrays;
import java.util.List;

import com.example.osmeuslugares.modelo.Categoria;
import com.example.osmeuslugares.modelo.bd.LugaresDb;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditCategoriasActivity extends Activity {

	private TextView editTextNombre;
	private Spinner spinnerIconos;
	private Categoria categoria;
	private boolean anhadir;
	SpinnerCategoriasAdapter spinnerAdaptador;
	List<String> valoresIconosCategorias;
	Resources res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_categoria);
		editTextNombre=(TextView) findViewById(R.id.editTextNombre);
		spinnerIconos=(Spinner) findViewById(R.id.spinnerCategoria);
		spinnerAdaptador = new SpinnerCategoriasAdapter(this);
		spinnerIconos.setAdapter(spinnerAdaptador);
		res = this.getResources();
		valoresIconosCategorias = Arrays.asList(res.getStringArray(R.array.valores_iconos_lugares));
		

	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_categoria, menu);
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
		
		String a=editTextNombre.getText().toString();
		String b=valoresIconosCategorias.get(spinnerIconos.getSelectedItemPosition());
		categoria=new Categoria();
			
			categoria.setNombre(a);
			categoria.setCategoria(b);
			
			LugaresDb modificar=new LugaresDb(this);
			try{
			modificar.createCategoria(categoria);
			}catch(Exception error){
				Toast.makeText(this, "Imposible guardar los datos"+error.getStackTrace().toString(),Toast.LENGTH_LONG).show();
			}
			Toast.makeText(this, "Datos Guardados Correctamente", Toast.LENGTH_LONG).show() ;
			finish();

	}
	

}
package com.example.osmeuslugares.modelo.bd;

import java.util.Vector;

import com.example.osmeuslugares.modelo.Categoria;
import com.example.osmeuslugares.modelo.Lugar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LugaresDb extends SQLiteOpenHelper {

	private static String LOGTAG = "LugaresDb";
	private SQLiteDatabase db;
	private static String nombre = "lugares.db";
	private static CursorFactory factory = null;
	private static int version = 10;

	public LugaresDb(Context context) {
		super(context, nombre, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		this.db = db;
		try {
			String sql = "CREATE TABLE lugar("
					+ "lug_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "lug_nombre TEXT NOT NULL, "
					+ "lug_categoria_id INTEGER NOT NULL,"
					+ "lug_direccion TEXT," + "lug_ciudad TEXT,"
					+ "lug_telefono TEXT, " + "lug_url TEXT,"
					+ "lug_comentario TEXT);";

			db.execSQL(sql);

			sql = "CREATE UNIQUE INDEX idx_lug_nombre ON Lugar(lug_nombre ASC)";
			db.execSQL(sql);

			sql = "CREATE TABLE Categoria("
					+ "cat_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "cat_nombre TEXT NOT NULL, " + "cat_icon TEXT NOT NULL"
					+ ");";

			db.execSQL(sql);

			sql = "CREATE UNIQUE INDEX idx_cat_nombre ON Categoria(cat_nombre ASC)";
			db.execSQL(sql);
			// Insertar datos de prueba
			insertarLugaresPrueba();
		} catch (SQLException e) {
			Log.e(getClass().toString(), e.getMessage());
		}

	}

	private void insertarLugaresPrueba() {
		db.execSQL("INSERT INTO Categoria(cat_nombre, cat_icon) "
				+ "VALUES('Playas', 'icono_playa')");
		db.execSQL("INSERT INTO Categoria(cat_nombre, cat_icon) "
				+ "VALUES('Restaurantes', 'icono_restaurante')");
		db.execSQL("INSERT INTO Categoria(cat_nombre, cat_icon) "
				+ "VALUES('Hoteles', 'icono_hotel')");
		db.execSQL("INSERT INTO Categoria(cat_nombre, cat_icon) "
				+ "VALUES('Otros','icono_vista_panoramica')");

		db.execSQL("INSERT INTO Lugar(lug_nombre, lug_categoria_id, lug_direccion, lug_ciudad, lug_telefono, lug_url, lug_comentario) "
				+ "VALUES('Praia de Riazor',1, 'Riazor','A Coruña','981000000','','')");
		db.execSQL("INSERT INTO Lugar(lug_nombre, lug_categoria_id, lug_direccion, lug_ciudad, lug_telefono, lug_url, lug_comentario) "
				+ "VALUES('Praia do Orzan',1, 'Orzan','A Coruña','981000000','','')");
		db.execSQL("INSERT INTO Lugar(lug_nombre, lug_categoria_id, lug_direccion, lug_ciudad, lug_telefono, lug_url, lug_comentario) "
				+ "VALUES('O Bebedeiro',2, 'Monte Alto','A Coruña','981000000','','')");

		Log.i("INFO", "Registros de prueba insertados");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("INFO", "Base de datos: onUpgrade" + oldVersion + "->"
				+ newVersion);
		if (newVersion > oldVersion) {
			try {
				db.execSQL("DROP TABLE IF EXISTS lugar");
				db.execSQL("DROP INDEX IF EXISTS idx_lug_nombre");
				db.execSQL("DROP TABLE IF EXISTS categoria");
				db.execSQL("DROP INDEX IF EXISTS idx_cat_nombre");
			} catch (Exception e) {
				Log.e(this.getClass().toString(), e.getMessage());
			}
			onCreate(db);

			Log.i(this.getClass().toString(),
					"Base de datos actualizada. versión 2");
		}

	}

	public Vector<Lugar> cargarLugaresDesdeBD() {
		Vector<Lugar> resultado = new Vector<Lugar>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Lugar.*, cat_nombre, cat_icon "
				+ "FROM Lugar join Categoria on lug_categoria_id = cat_id",
				null);

		while (cursor.moveToNext()) {
			Lugar lugar = new Lugar();
			lugar.setId(cursor.getLong(cursor.getColumnIndex(Lugar.C_ID)));
			lugar.setNombre(cursor.getString(cursor
					.getColumnIndex(Lugar.C_NOMBRE)));
			Long idCategoria = cursor.getLong(cursor
					.getColumnIndex(Lugar.C_CATEGORIA_ID));
			String nombreCategoria = cursor.getString(cursor
					.getColumnIndex(Categoria.C_NOMBRE));
			String icon = cursor.getString(cursor
					.getColumnIndex(Categoria.C_ICON));

			lugar.setCategoria(new Categoria(idCategoria, nombreCategoria, icon));

			lugar.setDireccion(cursor.getString(cursor
					.getColumnIndex(Lugar.C_DIRECCION)));
			lugar.setCiudad(cursor.getString(cursor
					.getColumnIndex(Lugar.C_CIUDAD)));
			lugar.setTelefono(cursor.getString(cursor
					.getColumnIndex(Lugar.C_TELEFONO)));
			lugar.setUrl(cursor.getString(cursor.getColumnIndex(Lugar.C_URL)));
			lugar.setComentario(cursor.getString(cursor
					.getColumnIndex(Lugar.C_COMENTARIO)));
			resultado.add(lugar);
		}
		return resultado;
	}

	public Vector<Categoria> cargarCategoriasDesdeBD() {
		Vector<Categoria> resultado = new Vector<Categoria>();
		Categoria categoria = new Categoria();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM Categoria ORDER By cat_id",
				null);

		resultado.add(new Categoria(0L, "Seleccionar...", "icono_nd"));
		while (cursor.moveToNext()) {
			categoria = new Categoria();
			categoria.setId(cursor.getLong(cursor
					.getColumnIndex(Categoria.C_ID)));
			categoria.setNombre(cursor.getString(cursor
					.getColumnIndex(Categoria.C_NOMBRE)));
			categoria.setIcon(cursor.getString(cursor
					.getColumnIndex(Categoria.C_ICON)));
			resultado.add(categoria);
		}
		return resultado;
	}

	public void createLugar(Lugar newLugar) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues reg = new ContentValues();

		reg = newLugar.getContentValues();
		db.insert("Lugar", null, reg);
	}

	public void updateLugar(Lugar newLugar) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues reg = new ContentValues();

		reg = newLugar.getContentValues();
		db.update("Lugar", reg, Lugar.C_ID + "=" + newLugar.getId(), null);
	}

	public void deleteLugar(Lugar newLugar) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("Lugar", Lugar.C_ID + "=" + newLugar.getId(), null);
	}

	public void createCategoria(Categoria newCat) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues reg = new ContentValues();

		reg = newCat.getContentValues();
		db.insert("Categoria", null, reg);
	}

	public void updateCategoria(Categoria newCat) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues reg = new ContentValues();

		reg = newCat.getContentValues();
		db.update("Categoria", reg, Categoria.C_ID + "=" + newCat.getId(), null);
	}

	public void deleteCategoria(Categoria newCat) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("Categoria", Categoria.C_ID + "=" + newCat.getId(), null);
	}

}
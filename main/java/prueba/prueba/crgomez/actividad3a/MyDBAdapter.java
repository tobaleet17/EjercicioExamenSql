package prueba.prueba.crgomez.actividad3a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;


public class MyDBAdapter {

    private static final String DATABASE_NAME = "guardar.db";
    private static final String  TABLA_PROFESORES= "profesores";
    private static final String TABLA_ESTUDIANTES = "estudiantes";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "name";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA_MEDIA = "notaMedia";
    private static final String DESPACHO = "despacho";


    private static final String DATABASE_CREATE = "CREATE TABLE "+TABLA_PROFESORES+" (_id integer primary key autoincrement, name text, edad integer, ciclo text, curso text, notaMedia integer );";
    private static final String DATABASE_CREATE2 = "CREATE TABLE "+TABLA_ESTUDIANTES+" (_id integer primary key autoincrement, name text, edad integer, ciclo text, curso text, despacho text );";

    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+TABLA_PROFESORES+";";
    private static final String DATABASE_DROP2 = "DROP TABLE IF EXISTS "+TABLA_ESTUDIANTES+";";


    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

        open();
        //Tenemos que abrir el método OPEN para que nuestra base de datos nos escriba y sino por lo menos la lea
    }


    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarEstudiantes(String nom, String age, String cicl, String curs, String nota){
        //Creamos el content values para después con el PUT añadir cada valor en su tabla
        ContentValues newEstudiantes = new ContentValues();

        newEstudiantes.put(NOMBRE,nom);
        newEstudiantes.put(EDAD,age);
        newEstudiantes.put(CICLO,cicl);
        newEstudiantes.put(CURSO,curs);
        newEstudiantes.put(NOTA_MEDIA,nota);

        db.insert(TABLA_ESTUDIANTES,null,newEstudiantes);


    }
    public void insertarProfesores(String nom, int age, String cicl, String curs, float despacho){
        //Creamos el content values para después con el PUT añadir cada valor en su tabla
        ContentValues newProfesores = new ContentValues();

        newProfesores.put(NOMBRE,nom);
        newProfesores.put(EDAD,age);
        newProfesores.put(CICLO,cicl);
        newProfesores.put(CURSO,curs);
        newProfesores.put(DESPACHO,despacho);

        db.insert(TABLA_PROFESORES,null,newProfesores);


    }



    private static class MyDbHelper extends SQLiteOpenHelper{

        public MyDbHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

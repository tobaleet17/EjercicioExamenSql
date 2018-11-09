package prueba.prueba.crgomez.actividad3a;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;


public class MyDBAdapter {

    private static final String DATABASE_NAME = "guardar.db";
    private static final String  TABLA_PROFESORES= "profesores";
    private static final String TABLA_ESTUDIANTES = "estudiantes";
    private static final String TABLA_ASIGNATURAS = "asignaturas";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "name";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA_MEDIA = "notaMedia";
    private static final String DESPACHO = "despacho";
    private static final String NOMBRE_ASIG = "nombreAsig";
    private static final String NUM_ALUM = "numAlum";



    private static final String DATABASE_CREATE = "CREATE TABLE "+TABLA_PROFESORES+" (_id integer primary key autoincrement, name text, edad text, ciclo text, curso text, despacho text );";
    private static final String DATABASE_CREATE2 = "CREATE TABLE "+TABLA_ESTUDIANTES+" (_id integer primary key autoincrement, name text, edad integer, ciclo text, curso text, notaMedia text );";
    private static final String DATABASE_CREATE3 = "CREATE TABLE "+TABLA_ASIGNATURAS+" (_id integer primary key autoincrement, nombreAsig text, numAlum integer );";

    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+TABLA_PROFESORES+";";
    private static final String DATABASE_DROP2 = "DROP TABLE IF EXISTS "+TABLA_ESTUDIANTES+";";
    private static final String DATABASE_DROP3 = "DROP TABLE IF EXISTS "+TABLA_ASIGNATURAS+";";


    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //db.close();
        //open();
        //Tenemos que abrir el método OPEN para que nuestra base de datos nos escriba y sino por lo menos la lea
    }


    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }


    //EJERCICIO NUM3 AÑADIR ASIGNATURA
    public void insertarAsignatura(String nomAsi, String numAlu ){
        ContentValues newAsignatura = new ContentValues();

        newAsignatura.put(NOMBRE_ASIG,nomAsi);
        newAsignatura.put(NUM_ALUM,numAlu);

        db.insert(TABLA_ASIGNATURAS,null,newAsignatura);

    }


    //EJERCICIO 2 PARA COMPARAR LOS ESTUDIANTES!!
    public void insertarEstudiantes(String nom, String age, String cicl, String curs, String nota){
        //Creamos el content values para después con el PUT añadir cada valor en su tabla

        //EJERCICIO 2 PARA COMPARAR LOS ESTUDIANTES!!

        ContentValues newEstudiantes = new ContentValues();
        ArrayList<String[]> estudiante = new ArrayList<String[]>();
        String [] valores = new String[5];

        Cursor cursor = db.query(TABLA_ESTUDIANTES,null,null,null,null,null, null);
        if (cursor != null && cursor.moveToFirst()){

            do{


                valores[0]=(cursor.getString(1));
                valores[1]=(cursor.getString(2));
                valores[2]=(cursor.getString(3));
                valores[3]=(cursor.getString(4));
                valores[4]=(cursor.getString(5));

                estudiante.add(valores);

            }while (cursor.moveToNext());
        }
        if (valores[0].equals(nom)) {
            Toast toast1 = Toast.makeText(context.getApplicationContext(), "Error insertando estudiante EXISTE.", Toast.LENGTH_LONG);
            toast1.show();

        }else{
            newEstudiantes.put(NOMBRE,nom);
            newEstudiantes.put(EDAD,age);
            newEstudiantes.put(CICLO,cicl);
            newEstudiantes.put(CURSO,curs);
            newEstudiantes.put(NOTA_MEDIA,nota);

            db.insert(TABLA_ESTUDIANTES,null,newEstudiantes);

        }

    }
    public void insertarProfesores(String nom, String age, String cicl, String curs, String despacho){
        //Creamos el content values para después con el PUT añadir cada valor en su tabla
        ContentValues newProfesores = new ContentValues();




            newProfesores.put(NOMBRE, nom);
            newProfesores.put(EDAD, age);
            newProfesores.put(CICLO, cicl);
            newProfesores.put(CURSO, curs);
            newProfesores.put(DESPACHO, despacho);

            db.insert(TABLA_PROFESORES, null, newProfesores);


    }

    //ESTA FORMA ES LA BUENA ASÍ LO SACO TODO OK PASANDO DIRECTAMENTE LOS VALORES

    public ArrayList<String[]> recuperarEstudiantes(){

        //ESTA ES LA PRIMERA PREGUNTA DEL EXAMEN DONDE HACEMOS LA CONSULTA DE BUSQUEDA!!

        ArrayList<String[]> estudianteCiclo = new ArrayList<String[]>();
        //Cursor cursor = db.query(TABLA_ESTUDIANTES,null,null,null,null,null,
        // null);
        Cursor cursor = db.rawQuery("select * from " + TABLA_ESTUDIANTES + " where edad BETWEEN '" + 20 + "' AND '" + 25 + "' ORDER BY edad ASC", null);
        Log.d("caca","entrooo");
        if (cursor != null && cursor.moveToFirst()){
            Log.d("caca","entrooo");
            do{
                String [] valores = new String[5];
                //estudianteCiclo.add(cursor.getString(1)+"\n"+estudianteCiclo.add(cursor.getString(2)));
                valores[0]=(cursor.getString(1));
                valores[1]=(cursor.getString(2));
                valores[2]=(cursor.getString(3));
                valores[3]=(cursor.getString(4));
                valores[4]=(cursor.getString(5));

                estudianteCiclo.add(valores);

            }while (cursor.moveToNext());
        }
        return estudianteCiclo;


    }

    public ArrayList<String[]> recuperarAsignatura(String asignaturaInt){

        ArrayList<String[]> asignatura= new ArrayList<String[]>();
        Cursor cursor = db.query(TABLA_ASIGNATURAS,null,"nombreAsig='"+asignaturaInt+"'",null,null,null,null);
        if (cursor != null && cursor.moveToFirst()){

            do{
                String [] valores = new String[2];

                valores[0]=cursor.getString(1);
                valores[1]=cursor.getString(2);


                asignatura.add(valores);

            }while (cursor.moveToNext());
        }
        return asignatura;

    }

    public ArrayList<String[]> recuperarProfesores(){


        ArrayList<String[]> profesores= new ArrayList<String[]>();
        //Cursor cursor = db.query(TABLA_PROFESORES,null,null,null,null,null,null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLA_PROFESORES,null);
        Log.d("caca","entrooo");
        if (cursor != null && cursor.moveToFirst()){
            Log.d("caca","entrooo");
            do{
                String [] valores = new String[5];
                //profesores.add(cursor.getString(1));
                valores[0]=cursor.getString(1);
                valores[1]=cursor.getString(2);
                valores[2]=cursor.getString(3);
                valores[3]=cursor.getString(4);
                valores[4]=cursor.getString(5);

                profesores.add(valores);

            }while (cursor.moveToNext());
        }
        return profesores;


    }

    public ArrayList<String> recuperarEstudiantesPorCiclo(String ciclo){


        ArrayList<String> cicloBus= new ArrayList<String>();
        Cursor cursor = db.query(TABLA_ESTUDIANTES,null,"ciclo='"+ciclo+"'",null,null,null,null);


        Log.d("caca","entrooo");
        if (cursor != null && cursor.moveToFirst()){
            Log.d("caca","entrooo");
            do{
                cicloBus.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return cicloBus;


    }


    public void cambioNombre (){

        ContentValues cv = new ContentValues();
        cv.put(NOMBRE,"pepe");
        db.update(TABLA_PROFESORES, cv, "name=?",new String[]{"aitor"});

    }


    private static class MyDbHelper extends SQLiteOpenHelper{

        public MyDbHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE2);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(DATABASE_DROP);
            db.execSQL(DATABASE_DROP2);
            onCreate(db);

        }
    }

}

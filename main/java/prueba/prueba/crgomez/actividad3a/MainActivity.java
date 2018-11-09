package prueba.prueba.crgomez.actividad3a;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {


    Button enviar;
    Button recuperar;
    Button inserProfe;
    Button inserEstu;
    EditText nombre;
    EditText nombreUsua;
    EditText fecha;
    RadioButton sexo;

    public static final String PREFS="My preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inserEstu = (Button) findViewById(R.id.btnInsertar);
        inserProfe = (Button) findViewById(R.id.btnInsertar2);
        enviar = (Button) findViewById(R.id.btnRegister);
        recuperar = (Button) findViewById(R.id.btnRecuperar);
        nombre = (EditText) findViewById(R.id.txtUserName);
        nombreUsua = (EditText) findViewById(R.id.txtNombre);
        fecha = (EditText) findViewById(R.id.txtFecha);
        sexo = (RadioButton) findViewById(R.id.radioFemella);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferencias = getSharedPreferences(PREFS,Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("nombre",nombre.getText().toString());
                editor.putString("nombreUsuario",nombreUsua.getText().toString());
                editor.putString("fecha",fecha.getText().toString());

                if (sexo.isActivated()){

                    editor.putBoolean("esMujer",true);


                }else{

                    editor.putBoolean("esMujer",false);
                }

                editor.commit();

                recuperar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent llamarSub = new Intent(getApplicationContext(),MostrarPref.class);
                        startActivity(llamarSub);

                    }
                });

            }
        });
        inserProfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentProf = new Intent(getApplicationContext(),activityProfesores.class);
                startActivity(intentProf);

            }
        });

        inserEstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentEstu = new Intent(getApplicationContext(),activityEstudiantes.class);
                startActivity(intentEstu);

            }
        });


    }
}

package prueba.prueba.crgomez.actividad3a;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarPref extends AppCompatActivity {

    private TextView nombre;
    private TextView nombreUser;
    private TextView fecha;
    private TextView sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_pref);

        nombre = (TextView) findViewById(R.id.txtNombre);
        nombreUser = (TextView) findViewById(R.id.txtNombreId);
        fecha = (TextView) findViewById(R.id.txtFecha);
        sexo =(TextView) findViewById(R.id.txtSexo);

        SharedPreferences preferenciasSub = getSharedPreferences(MainActivity.PREFS,Activity.MODE_PRIVATE);

        nombre.setText(preferenciasSub.getString("nombre",""));
        nombreUser.setText(preferenciasSub.getString("nombreUsuario",""));
        fecha.setText(preferenciasSub.getString("fecha",""));
        if (preferenciasSub.getBoolean("esMujer", true)){

            sexo.setText("Mujer");
        }else{

            sexo.setText("Hombre");
        }

    }
}

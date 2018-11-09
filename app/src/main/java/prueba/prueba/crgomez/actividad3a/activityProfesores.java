package prueba.prueba.crgomez.actividad3a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activityProfesores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        final Button guardar = (Button) findViewById(R.id.btnGuardar);
        final EditText nombre = (EditText) findViewById(R.id.txtNombreProfe);
        final EditText edad = (EditText) findViewById(R.id.txtEdadProfe);
        final EditText ciclo = (EditText) findViewById(R.id.txtCiclo);
        final EditText curso = (EditText) findViewById(R.id.txtCurso);
        final EditText despacho = (EditText) findViewById(R.id.txtDespacho);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDBAdapter adapter = new MyDBAdapter(getApplicationContext());

                adapter.open();

                adapter.insertarProfesores(nombre.getText().toString(),edad.getText().toString(),ciclo.getText().toString(),curso.getText().toString(),despacho.getText().toString());

                setResult(RESULT_OK);
                finish();
            }
        });

    }
}

package prueba.prueba.crgomez.actividad3a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activityEstudiantes extends AppCompatActivity {

    private Button guardar;
    private MyDBAdapter dbAdapter;
    private EditText nombre;
    private EditText edad;
    private EditText ciclo;
    private EditText curso;
    private EditText nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);


         //dbAdapter.open();
        guardar = (Button) findViewById(R.id.btnGuardar);
        nombre = (EditText) findViewById(R.id.txtNombreEstu);
        edad = (EditText) findViewById(R.id.txtEdadEstu);
        ciclo = (EditText) findViewById(R.id.txtCicloEstu);
        curso = (EditText) findViewById(R.id.txtCursoEstu);
        nota = (EditText) findViewById(R.id.txtNota);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter = new MyDBAdapter(getApplicationContext());

                dbAdapter.insertarEstudiantes(nombre.getText().toString(),edad.getText().toString(),ciclo.getText().toString(),curso.getText().toString(),nota.getText().toString());



            }
        });
    }
}

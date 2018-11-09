package prueba.prueba.crgomez.actividad3a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activityAsignatura extends AppCompatActivity {

    private MyDBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura);

        final Button guardar = (Button) this.findViewById(R.id.btnGuardarAsig);

        final EditText nombreAsig = (EditText) this.findViewById(R.id.txtNombreAsigna);
        final EditText numAlum = (EditText) this.findViewById(R.id.txtNumAlum);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();
                dbAdapter.insertarAsignatura(nombreAsig.getText().toString(),numAlum.getText().toString());

                Intent i = new Intent();
                setResult(RESULT_OK);
                finish();



            }
        });




    }
}

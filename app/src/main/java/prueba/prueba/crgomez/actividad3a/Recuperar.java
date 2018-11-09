package prueba.prueba.crgomez.actividad3a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Recuperar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        final TextView textView = (TextView) this.findViewById(R.id.textViewP);
        final EditText ciclo = (EditText) this.findViewById(R.id.meterCurso);

        final Button recuperarEstudiantes = (Button) this.findViewById(R.id.btnRecuperarTodos);
        final Button recuperarProfes = (Button) this.findViewById(R.id.RcpProf);
        final Button recuperarTodos = (Button) this.findViewById(R.id.RcpTodos);
        final Button recuperarPorCiclo = (Button) this.findViewById(R.id.EstudianteCur);


        recuperarPorCiclo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter dbAdapter;
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();
                ArrayList<String> busCiclo = dbAdapter.recuperarEstudiantesPorCiclo(ciclo.getText().toString());
                for (int i=0;i<busCiclo.size();i++){


                    textView.setText(textView.getText()+"\n"+busCiclo.get(i));

                }


            }
        });
        recuperarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDBAdapter dbAdapter;
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();

                ArrayList<String[]> estudiante = dbAdapter.recuperarEstudiantes();
                ArrayList<String[]> profes = dbAdapter.recuperarProfesores();
                for (String [] estu:estudiante){


                    textView.setText(textView.getText()+"Nombre: "+estu[0]+"\n");

                }

                textView.setText(textView.getText()+"\n"+"Profesores:");
                for (String [] profe: profes){


                    textView.setText(textView.getText()+"Nombre: "+profe[0]+"\n");

                }

            }
        });
        recuperarEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDBAdapter dbAdapter;
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();

                ArrayList<String[]> estudiante = dbAdapter.recuperarEstudiantes();
                textView.setText("");
                textView.setText("Estudiantes:"+"\n");
                for (String[] estu: estudiante){

                    textView.setText(textView.getText()+"Nombre: "+estu[0]+"\n");
                    textView.setText(textView.getText()+"Edad: "+estu[1]+"\n");

                }



            }
        });

        recuperarProfes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDBAdapter dbAdapter;
                dbAdapter = new MyDBAdapter(getApplicationContext());
                dbAdapter.open();

                ArrayList<String[]> profes = dbAdapter.recuperarProfesores();
                textView.setText("");
                for (String[] profe: profes){


                    textView.setText(textView.getText()+"Nombre: "+profe[0]+"\n");
                    textView.setText(textView.getText()+"Edad: "+profe[1]+"\n");

                }


            }
        });

       // MyDBAdapter actualizar = new MyDBAdapter(getApplicationContext());
        //actualizar.open();
        //actualizar.cambioNombre();



    }

}

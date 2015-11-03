package com.example.estruch18.proyecto3b;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Activity2 extends Activity {

    //Atributos de la clase
    private TextView nombre, dni, nacimiento, sexo;
    private Button btnAtras;

    public static final String PREFS = "MyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        //Declaración de atributos de la clase
        nombre = (TextView)findViewById(R.id.tv_nombre);
        dni = (TextView)findViewById(R.id.tv_dni);
        nacimiento = (TextView)findViewById(R.id.tv_fecha);
        sexo = (TextView)findViewById(R.id.tv_sexo);
        btnAtras = (Button)findViewById(R.id.btn_Atras);

        //Ejecución de métodos
        recuperarPreferencias();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Método que realiza la obtención de datos recogidos del bundle y los asigna a los TextViews correspondientes
    public void recuperarPreferencias(){
        //Creación de un objeto SharedPreferences con el cual recuperaremos las preferencias
        SharedPreferences preferenciasRecuperadas = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

        //Seteamos los campos con las preferencias recuperadas.
        nombre.setText(preferenciasRecuperadas.getString("nombre",""));
        dni.setText(preferenciasRecuperadas.getString("dni",""));
        nacimiento.setText(preferenciasRecuperadas.getString("fecha", ""));
        sexo.setText(preferenciasRecuperadas.getString("sexo", ""));
    }

    //Listener del botón atrás incluido en el activity 2
    public void accionBtnAtras(View v){
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acciones que realiza dicho botón
                Intent act1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(act1);
            }
        });
    }
}

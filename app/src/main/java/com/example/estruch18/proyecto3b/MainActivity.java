package com.example.estruch18.proyecto3b;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    //Atributos de la clase
    private TextView tvNombre, tvDni, tvNacimiento;
    private EditText etNombre, etDni, etNacimiento;
    private Button btnGuardar;
    private RadioGroup grupoSexo;
    private String sexo;
    private SharedPreferences misPreferencias;

    public static final String PREFS = "MyPreferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaración de atributos de la clase
        tvNombre = (TextView)findViewById(R.id.tv_nombre);
        tvDni = (TextView)findViewById(R.id.tv_dni);
        tvNacimiento = (TextView)findViewById(R.id.tv_fechaNacimiento);

        etNombre = (EditText)findViewById(R.id.et_nombre);
        etDni = (EditText)findViewById(R.id.et_dni);
        etNacimiento = (EditText)findViewById(R.id.et_fechaNacimiento);
        grupoSexo = (RadioGroup)findViewById(R.id.rdg_sexo);

        btnGuardar = (Button)findViewById(R.id.btn_guardarPref);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //Método cuya finalidad es obtener la selección del radioGrupo seleccionada por el usuario para posteriormente, ser procesada
    public void seleccionSexo(){
        //Obtenemos el id del radioButton seleccionado
        int idRadioSelected = grupoSexo.getCheckedRadioButtonId();
        //Obtenemos dicho radioButton
        RadioButton radioSelected = (RadioButton)findViewById(idRadioSelected);

        //Comprobamos y seteamos la variable de la clase llamada "sexo"
        if(radioSelected.getText().toString().equals("Masculino")){
            sexo = "Masculino";
        }
        else{
            sexo = "Femenino";
        }
    }

    //Método encargado de guardar las preferencias que nos interesan
    public void guardarPreferencias(){
        //Creación de las preferencias
        misPreferencias = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

        //Obtención de un editor para la modificación de las preferencias
        SharedPreferences.Editor editor = misPreferencias.edit();

        //Guardado de los datos que nos interesa capturar
        editor.putString("nombre", etNombre.getText().toString());
        editor.putString("dni", etDni.getText().toString());
        editor.putString("fecha", etNacimiento.getText().toString());
        editor.putString("sexo", sexo);

        //Guardar cambios
        editor.commit();
    }

    //Listener del botón "guardar preferencias"
    public void accionBtnGuardar(View v){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener datos acerca de el sexo seleccionado
                seleccionSexo();
                //Guardamos los datos como preferencias
                guardarPreferencias();
                //Acciones que realiza dicho botón
                Intent act2 = new Intent(getApplicationContext(), Activity2.class);

                //Comprobando que no existen campos vacios
                if(etNombre.getText().toString().length()==0
                   || etDni.getText().toString().length()==0
                   || etNacimiento.getText().toString().length()==0){
                    //Información por pantalla
                    Toast.makeText(getApplicationContext(), "No pueden existir campos vacios", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Información sobre el guardado de datos (preferencias)
                    Toast.makeText(getApplicationContext(), "Se han guardado las preferencias OK.", Toast.LENGTH_SHORT).show();
                    //Arrancamos el activity2 si existen todos los campos requeridos
                    startActivity(act2);
                }
            }
        });
    }
}

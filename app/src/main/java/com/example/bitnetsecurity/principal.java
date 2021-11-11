package com.example.bitnetsecurity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.bitnetsecurity.modelo.ArrayListShared;
import com.example.bitnetsecurity.modelo.Usuario;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.List;

public class principal extends AppCompatActivity {

    //Creacion de un toolbar
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        //Iniciacion del layout
        setContentView(R.layout.activity_principal);

        //Al tb (toolbar) creado, le asignamos nuestro layout toolbar
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //Definir fragmento inicial de la vista
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.contenedor, new reportes());
        fragmentTransaction.commit();


        //Recuperar el navigationview
        NavigationView nav = (NavigationView) findViewById(R.id.nav);

        try{
            //Leer el array de usuarios del sharedPreference
            List<Usuario> usuarios = ArrayListShared.readArray(this);

            SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
            String usertxt = datos.getString("person","");
            if(usertxt.equals("")){
                //Accedemos a shared preferences para eliminar el user recordado

                SharedPreferences.Editor editor = datos.edit();
                editor.remove("user");
                editor.remove("person");
                editor.apply();
                //finish para cerrar la app y volver a la primera pantalla
                finish();
            }
            Usuario usuario = new Usuario();
            for (Usuario u: usuarios
                 ) {
                if(u.getRut().equals(usertxt)){
                    usuario = (Usuario) u;
                    break;
                }
            }



            //Incorporar datos del usuarios en el header del Nav
            View headerView = nav.getHeaderView(0);

            TextView navEmpresa = (TextView) headerView.findViewById(R.id.perfilEmpresa);
            navEmpresa.setText(usuario.getEmpresa());
            TextView navNombre = (TextView) headerView.findViewById(R.id.perfilPersona);
            navNombre.setText(usuario.getNombre()+" "+ usuario.getApellido());
            TextView navCargo = (TextView) headerView.findViewById(R.id.perfilCargo);
            navCargo.setText(usuario.getCargo());

        }catch(Exception e){
            SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = datos.edit();
            editor.remove("user");
            editor.remove("person");
            editor.apply();
            //finish para cerrar la app y volver a la primera pantalla
            finish();
        }








        //Incorporacion del menu lateral mediante un listener
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Acceder al administrador de fragmentos
                FragmentManager fragmentManager=getSupportFragmentManager();

                //Recuperar la opcion del menu lateral
                int id = item.getItemId();//Recuperar la id seleccionada del menu lateral
                if(id==R.id.ope_reporte){
                    //Segun la id recuperada, llamamos al fragmento correspondiente
                    fragmentManager.beginTransaction().replace(R.id.contenedor,new reportes()).commit();
                    //Toast.makeText(getApplicationContext(), "Vas al reporte", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.ope_perfiles){
                    //Segun la id recuperada, llamamos al fragmento correspondiente
                    fragmentManager.beginTransaction().replace(R.id.contenedor,new perfiles()).commit();
                    //Toast.makeText(getApplicationContext(), "Vas a los perfiles", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });




        //Habilitar el DrawerLayout
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout_principal);
        //SET CUSTOM HOME BUTTON?????
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Habilitar DraweLayout se abra y cierre
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.nav_draw_open,
                R.string.nav_draw_close
        );
        //Establecemos el toogle a nuestro drawerLayout
        dl.addDrawerListener(toggle);
        toggle.syncState();

        //Establece cuando abrir y/o cerrar el Drawer en base al listener
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }else{
                    dl.openDrawer((int)Gravity.START);
                }
            }
        });





    }


    //Incorporacion del menu del toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }


    //Accion boton salir
    public void salir (View v){
        //Accedemos a shared preferences para eliminar el user recordado
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = datos.edit();
        editor.remove("user");
        editor.remove("person");
        editor.apply();
        //finish para cerrar la app y volver a la primera pantalla
        finish();
    }







}
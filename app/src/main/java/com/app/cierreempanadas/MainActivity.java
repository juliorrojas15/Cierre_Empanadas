package com.app.cierreempanadas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static android.graphics.Color.GREEN;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, Alert_Confirmar.Alert_Confirmar_Listener {
    //#########################################################################################     //Variables que se traen de la otras activities
    public static int iDineroTotal_global;
    //#########################################################################################     //Keys de la base de datos

    //#########################################################################################     //Objetos del Layout
    Button obTecla_0,obTecla_1,obTecla_2,obTecla_3,obTecla_4,obTecla_5,obTecla_6,obTecla_7,
            obTecla_8,obTecla_9;
    Button obBorrar,obEnviar;
    Button obCompradasHoy,obDevueltasAyer,obDañadasHoy,obObsequiadasHoy, obDevueltasHoy,
            obCoffeeHoy,obTinterasAyer,obTinterasHoy,obTinterasPendiente,obVendedor_1_Ayer,
            obVendedor_1_Hoy, obVendedor_1_Pendiente,obVendedor_2_Ayer, obVendedor_2_Hoy,
            obVendedor_2_Pendiente;
    TextView otvDisponiblesHoy, otvConteo, otvFaltantes, otvDineroVendedor_1, otvDineroVendedor_2;


    //#########################################################################################     Variables Globales
    int iValorVenta=1500;
    int iCompradasHoy,iDevueltasAyer,iDañadasHoy,iObsequiadasHoy, iDevueltasHoy,
            iCoffeeHoy,iTinterasAyer,iTinterasHoy,iTinterasPendiente,iVendedor_1_Ayer,
            iVendedor_1_Hoy, iVendedor_1_Pendiente,iVendedor_2_Ayer, iVendedor_2_Hoy,
            iVendedor_2_Pendiente;
    int iDisponiblesHoy, iConteo, iFaltantes, iDineroVendedor_1, iDineroVendedor_2,iDineroVendedores_Total;

    ProgressDialog pdConfirmar;
    Context context;
    //#########################################################################################     Objetos

    //#########################################################################################################################################
    //#########################################################################################     ON CREATE
    //#########################################################################################################################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //#####################################################################################     Relación de objetos con Layout
        obTecla_0=(Button)findViewById(R.id.bTecla_0);obTecla_1=(Button)findViewById(R.id.bTecla_1);
        obTecla_2=(Button)findViewById(R.id.bTecla_2);obTecla_3=(Button)findViewById(R.id.bTecla_3);
        obTecla_4=(Button)findViewById(R.id.bTecla_4);obTecla_5=(Button)findViewById(R.id.bTecla_5);
        obTecla_6=(Button)findViewById(R.id.bTecla_6);obTecla_7=(Button)findViewById(R.id.bTecla_7);
        obTecla_8=(Button)findViewById(R.id.bTecla_8);obTecla_9=(Button)findViewById(R.id.bTecla_9);

        obCompradasHoy=(Button)findViewById(R.id.bCompradasHoy);
        obDevueltasAyer=(Button)findViewById(R.id.bDevueltasAyer);
        obDañadasHoy=(Button)findViewById(R.id.bDañadasHoy);
        obObsequiadasHoy=(Button)findViewById(R.id.bObsequiadasHoy);
        obDevueltasHoy=(Button)findViewById(R.id.bDevueltasHoy);

        obCoffeeHoy=(Button)findViewById(R.id.bCoffeeHoy);
        obTinterasAyer=(Button)findViewById(R.id.bTinterasAyer);
        obTinterasHoy=(Button)findViewById(R.id.bTinterasHoy);
        obTinterasPendiente=(Button)findViewById(R.id.bTinterasPendiente);

        obVendedor_1_Ayer=(Button)findViewById(R.id.bVendedor_1_Ayer);
        obVendedor_1_Hoy=(Button)findViewById(R.id.bVendedor_1_Hoy);
        obVendedor_1_Pendiente=(Button)findViewById(R.id.bVendedor_1_Pendiente);
        obVendedor_2_Ayer=(Button)findViewById(R.id.bVendedor_2_Ayer);
        obVendedor_2_Hoy=(Button)findViewById(R.id.bVendedor_2_Hoy);
        obVendedor_2_Pendiente=(Button)findViewById(R.id.bVendedor_2_Pendiente);

        obBorrar=(Button)findViewById(R.id.bBorrar);
        obEnviar=(Button)findViewById(R.id.bEnviar);

        otvDisponiblesHoy=(TextView)findViewById(R.id.tvDisponiblesHoy);
        otvConteo=(TextView)findViewById(R.id.tvConteo);
        otvFaltantes=(TextView)findViewById(R.id.tvFaltantes);
        otvDineroVendedor_1=(TextView)findViewById(R.id.tvDineroVendedor_1);
        otvDineroVendedor_2=(TextView)findViewById(R.id.tvDineroVendedor_2);

        obTecla_0.setOnClickListener(this);obTecla_1.setOnClickListener(this);obTecla_2.setOnClickListener(this);
        obTecla_3.setOnClickListener(this);obTecla_4.setOnClickListener(this);obTecla_5.setOnClickListener(this);
        obTecla_6.setOnClickListener(this);obTecla_7.setOnClickListener(this);obTecla_8.setOnClickListener(this);
        obTecla_9.setOnClickListener(this);

        obCompradasHoy.setOnClickListener(this); obDevueltasAyer.setOnClickListener(this);
        obDañadasHoy.setOnClickListener(this); obObsequiadasHoy.setOnClickListener(this);
        obDevueltasHoy.setOnClickListener(this);

        obCoffeeHoy.setOnClickListener(this); obTinterasAyer.setOnClickListener(this);
        obTinterasHoy.setOnClickListener(this); obTinterasPendiente.setOnClickListener(this);

        obVendedor_1_Ayer.setOnClickListener(this);obVendedor_1_Hoy.setOnClickListener(this);
        obVendedor_1_Pendiente.setOnClickListener(this);obVendedor_2_Ayer.setOnClickListener(this);
        obVendedor_2_Hoy.setOnClickListener(this); obVendedor_2_Pendiente.setOnClickListener(this);

        //##################################################################################         Declaración de objetos
        //##################################################################################         Acciones iniciales
        fBorrarTodo();
        fNuevosValoresPrevios();
        //##########################################################################################    Dialog Alert de Horas
        /*pdConfirmar=new ProgressDialog(MainActivity.this);
        final AlertDialog.Builder ConfirmarBuilder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirmar Cierre?")
                .setCancelable(true)
                .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        final AlertDialog ConfirmarDialog = ConfirmarBuilder.create();*/
        //##################################################################################         Acciones de botones y objetos
        obBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fBorrarTodo();
                fNuevosValoresPrevios();
            }
        });

        obEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sMensaje="";
                if (iCompradasHoy==0) {
                    sMensaje = "No has ingresado Compras";
                }
                else{
                    if (iFaltantes>0){
                        sMensaje="Aún queda por ajustar las cantidades";
                    }
                    else{
                        fConfirmar();
                        return;
                    }
                }
                Toast.makeText(MainActivity.this,sMensaje,Toast.LENGTH_SHORT).show();
            }
        });

        //##################################################################################         Acciones asincronas
    }
    //#########################################################################################################################################
    //#########################################################################################
    //#########################################################################################################################################
    void fBorrarTodo(){

        obCompradasHoy.setText("0");
        obDañadasHoy.setText("0");obObsequiadasHoy.setText("0");obDevueltasHoy.setText("0");

        obCoffeeHoy.setText("0");obTinterasHoy.setText("0");obTinterasPendiente.setText("0");

        obVendedor_1_Hoy.setText("0"); obVendedor_1_Pendiente.setText("0");
        obVendedor_2_Hoy.setText("0"); obVendedor_2_Pendiente.setText("0");

        otvDisponiblesHoy.setText("Disponibles Hoy: 0");
        otvConteo.setText("Van Contadas:\n0");
        otvFaltantes.setText("Hacen falta: \n0");
        otvDineroVendedor_1.setText("Vend. 1 Entrega:\n$ 0");
        otvDineroVendedor_2.setText("Vend. 2 Entrega:\n$ 0");

        iCompradasHoy=0;iDañadasHoy=0;iObsequiadasHoy=0; iDevueltasHoy=0;
        iCoffeeHoy=0;iTinterasHoy=0;iTinterasPendiente=0;
        iVendedor_1_Hoy=0; iVendedor_1_Pendiente=0;
         iVendedor_2_Hoy=0;iVendedor_2_Pendiente=0;
        iDisponiblesHoy=0; iConteo=0; iFaltantes=0; iDineroVendedor_1=0; iDineroVendedor_2=0;
        iDineroVendedores_Total=0;
        imDineroVendedor_1="0";imDineroVendedor_2="0";imDineroVendedores_Total="0";

        sNumero="";
        iNumero=0;
        sTeclaAccionada="";

        fBackGround();
    }



    // ############################################################################################     FUNCIONAMIENTO PAGINA
    String sNumero="";
    int iNumero=0;
    String sTeclaAccionada="";
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bTecla_0:sNumero=sNumero+"0";fValores();break;
            case R.id.bTecla_1:sNumero=sNumero+"1";fValores();break;
            case R.id.bTecla_2:sNumero=sNumero+"2";fValores();break;
            case R.id.bTecla_3:sNumero=sNumero+"3";fValores();break;
            case R.id.bTecla_4:sNumero=sNumero+"4";fValores();break;
            case R.id.bTecla_5:sNumero=sNumero+"5";fValores();break;
            case R.id.bTecla_6:sNumero=sNumero+"6";fValores();break;
            case R.id.bTecla_7:sNumero=sNumero+"7";fValores();break;
            case R.id.bTecla_8:sNumero=sNumero+"8";fValores();break;
            case R.id.bTecla_9:sNumero=sNumero+"9";fValores();break;

            case R.id.bCompradasHoy:sTeclaAccionada="CompradasHoy";fSeleccion();break;
            case R.id.bDevueltasAyer:sTeclaAccionada="DevueltasAyer";fSeleccion();break;
            case R.id.bDañadasHoy:sTeclaAccionada="DañadasHoy";fSeleccion();break;
            case R.id.bObsequiadasHoy:sTeclaAccionada="ObsequiadasHoy";fSeleccion();break;
            case R.id.bDevueltasHoy:sTeclaAccionada="DevueltasHoy";fSeleccion();break;

            case R.id.bCoffeeHoy:sTeclaAccionada="CoffeeHoy";fSeleccion();break;
            case R.id.bTinterasAyer:sTeclaAccionada="TinterasAyer";fSeleccion();break;
            case R.id.bTinterasHoy:sTeclaAccionada="TinterasHoy";fSeleccion();break;
            case R.id.bTinterasPendiente:sTeclaAccionada="TinterasPendiente";fSeleccion();break;
            
            case R.id.bVendedor_1_Ayer:sTeclaAccionada="Vendedor_1_Ayer";fSeleccion();break;
            case R.id.bVendedor_1_Hoy:sTeclaAccionada="Vendedor_1_Hoy";fSeleccion();break;
            case R.id.bVendedor_1_Pendiente:sTeclaAccionada="Vendedor_1_Pendiente";fSeleccion();break;
            case R.id.bVendedor_2_Ayer:sTeclaAccionada="Vendedor_2_Ayer";fSeleccion();break;
            case R.id.bVendedor_2_Hoy:sTeclaAccionada="Vendedor_2_Hoy";fSeleccion();break;
            case R.id.bVendedor_2_Pendiente:sTeclaAccionada="Vendedor_2_Pendiente";fSeleccion();break;

        }
    }
    void fValores(){
        if(sTeclaAccionada==""){
            Toast.makeText(MainActivity.this,"Selecciona un dato a ingresar!",Toast.LENGTH_SHORT).show();
            return;
        }
        iNumero=Integer.parseInt(sNumero);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String imNumero;
        imNumero = decimalFormat.format(iNumero);
        switch (sTeclaAccionada){
            case "CompradasHoy":obCompradasHoy.setText(sNumero);iCompradasHoy=iNumero;break;
            case "DevueltasAyer":obDevueltasAyer.setText(sNumero);iDevueltasAyer=iNumero;break;
            case "DañadasHoy":obDañadasHoy.setText(sNumero);iDañadasHoy=iNumero;break;
            case "ObsequiadasHoy":obObsequiadasHoy.setText(sNumero);iObsequiadasHoy=iNumero;break;
            case "DevueltasHoy":obDevueltasHoy.setText(sNumero);iDevueltasHoy=iNumero;break;
           
            case "CoffeeHoy":obCoffeeHoy.setText(sNumero);iCoffeeHoy=iNumero;break;
            case "TinterasAyer":obTinterasAyer.setText(sNumero);iTinterasAyer=iNumero;break;
            case "TinterasHoy":obTinterasHoy.setText(sNumero);iTinterasHoy=iNumero;break;
            case "TinterasPendiente":obTinterasPendiente.setText(sNumero);iTinterasPendiente=iNumero;break;

            case "Vendedor_1_Ayer":obVendedor_1_Ayer.setText(sNumero);iVendedor_1_Ayer=iNumero;break;
            case "Vendedor_1_Hoy":obVendedor_1_Hoy.setText(sNumero);iVendedor_1_Hoy=iNumero;break;
            case "Vendedor_1_Pendiente":obVendedor_1_Pendiente.setText(sNumero);iVendedor_1_Pendiente=iNumero;break;
            case "Vendedor_2_Ayer":obVendedor_2_Ayer.setText(sNumero);iVendedor_2_Ayer=iNumero;break;
            case "Vendedor_2_Hoy":obVendedor_2_Hoy.setText(sNumero);iVendedor_2_Hoy=iNumero;break;
            case "Vendedor_2_Pendiente":obVendedor_2_Pendiente.setText(sNumero);iVendedor_2_Pendiente=iNumero;break;

        }
        fCalculos();
    }
    void fSeleccion(){
        iNumero=0;
        sNumero="";
        switch (sTeclaAccionada){
            case "CompradasHoy":fBackGround();obCompradasHoy.setBackgroundColor(GREEN);break;
            case "DevueltasAyer":fBackGround();obDevueltasAyer.setBackgroundColor(GREEN);break;
            case "DañadasHoy":fBackGround();obDañadasHoy.setBackgroundColor(GREEN);break;
            case "ObsequiadasHoy":fBackGround();obObsequiadasHoy.setBackgroundColor(GREEN);break;
            case "DevueltasHoy":fBackGround();obDevueltasHoy.setBackgroundColor(GREEN);break;

            case "CoffeeHoy":fBackGround();obCoffeeHoy.setBackgroundColor(GREEN);break;
            case "TinterasAyer":fBackGround();obTinterasAyer.setBackgroundColor(GREEN);break;
            case "TinterasHoy":fBackGround();obTinterasHoy.setBackgroundColor(GREEN);break;
            case "TinterasPendiente":fBackGround();obTinterasPendiente.setBackgroundColor(GREEN);break;

            case "Vendedor_1_Ayer":fBackGround();obVendedor_1_Ayer.setBackgroundColor(GREEN);break;
            case "Vendedor_1_Hoy":fBackGround();obVendedor_1_Hoy.setBackgroundColor(GREEN);break;
            case "Vendedor_1_Pendiente":fBackGround();obVendedor_1_Pendiente.setBackgroundColor(GREEN);break;
            case "Vendedor_2_Ayer":fBackGround();obVendedor_2_Ayer.setBackgroundColor(GREEN);break;
            case "Vendedor_2_Hoy":fBackGround();obVendedor_2_Hoy.setBackgroundColor(GREEN);break;
            case "Vendedor_2_Pendiente":fBackGround();obVendedor_2_Pendiente.setBackgroundColor(GREEN);break;
        }
    }

    void fBackGround(){
        obCompradasHoy.setBackgroundResource(R.drawable.boton_gris);
        obDevueltasAyer.setBackgroundResource(R.drawable.boton_gris);
        obDañadasHoy.setBackgroundResource(R.drawable.boton_gris);
        obObsequiadasHoy.setBackgroundResource(R.drawable.boton_gris);
        obDevueltasHoy.setBackgroundResource(R.drawable.boton_gris);

        obCoffeeHoy.setBackgroundResource(R.drawable.boton_gris);
        obTinterasAyer.setBackgroundResource(R.drawable.boton_gris);
        obTinterasHoy.setBackgroundResource(R.drawable.boton_gris);
        obTinterasPendiente.setBackgroundResource(R.drawable.boton_gris);

        obVendedor_1_Ayer.setBackgroundResource(R.drawable.boton_gris);
        obVendedor_1_Hoy.setBackgroundResource(R.drawable.boton_gris);
        obVendedor_1_Pendiente.setBackgroundResource(R.drawable.boton_gris);
        obVendedor_2_Ayer.setBackgroundResource(R.drawable.boton_gris);
        obVendedor_2_Hoy.setBackgroundResource(R.drawable.boton_gris);
        obVendedor_2_Pendiente.setBackgroundResource(R.drawable.boton_gris);

    }

    void fCalculos(){

        iDisponiblesHoy=iCompradasHoy+iDevueltasAyer;
        iConteo=iDañadasHoy+iObsequiadasHoy+iDevueltasHoy+
                iCoffeeHoy+iTinterasHoy+iTinterasPendiente+
                iVendedor_1_Hoy+iVendedor_1_Pendiente+iVendedor_2_Hoy+iVendedor_2_Pendiente;
        iFaltantes=iDisponiblesHoy-iConteo;
        iDineroVendedor_1=(iVendedor_1_Ayer+iVendedor_1_Hoy)*iValorVenta;
        iDineroVendedor_2=(iVendedor_2_Ayer+iVendedor_2_Hoy)*iValorVenta;

        iDineroTotal_global=iDineroVendedor_1+iDineroVendedor_2;
        fTextoVentas();

    }

    String imDineroVendedor_1,imDineroVendedor_2,imDineroVendedores_Total;
    void fTextoVentas(){
        otvDisponiblesHoy.setText("Disponibles Hoy: "+iDisponiblesHoy);
        otvConteo.setText("Van Contadas: \n"+iConteo);
        otvFaltantes.setText("Hacen falta: \n"+iFaltantes);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        imDineroVendedor_1 = decimalFormat.format(iDineroVendedor_1);
        imDineroVendedor_2 = decimalFormat.format(iDineroVendedor_2);
        imDineroVendedores_Total=decimalFormat.format(iDineroVendedor_1+iDineroVendedor_2);

        otvDineroVendedor_1.setText("Vend. 1 Entrega:\n$ "+imDineroVendedor_1);
        otvDineroVendedor_2.setText("Vend. 2 Emtrega:\n$ "+imDineroVendedor_2);


    }

    void fGuardarEnLocal(){
        SharedPreferences sharedDatoslocales = getPreferences(context.MODE_PRIVATE);
        SharedPreferences.Editor editorDatosLocales = sharedDatoslocales.edit();
        editorDatosLocales.putInt("Devueltas", iDevueltasHoy);
        editorDatosLocales.putInt("Tinteras Pendiente", iTinterasPendiente);
        editorDatosLocales.putInt("Vendedor 1 Pendiente", iVendedor_1_Pendiente);
        editorDatosLocales.putInt("Vendedor 2 Pendiente", iVendedor_2_Pendiente);
        editorDatosLocales.commit();
    }

    void fTextoWhatsApp(String sFecha,String imComprasPapeleria,String imComprasBolsas, String imComprasOtros,
                        String imComprasTotal,String imEfectivoTotal){

        String lCelular="573123888490";

        String sMensaje="Hola, el cierre de Empanadas del *"+
                sFecha+"* arrojó los siguientes resultados:"+
                "%0A"+
                "%0ACompradas Hoy: "+ iCompradasHoy+
                "%0ADevueltas de Ayer: "+iDevueltasAyer+
                "%0ADañadas Hoy: "+iDañadasHoy+
                "%0AObsequiadas Hoy: "+iObsequiadasHoy+
                "%0ADevueltas Hoy: "+iDevueltasHoy+
                "%0A"+
                "%0ACoffee Compró: "+iCoffeeHoy+
                "%0A*Tinteras:*"+
                "%0A     pagan de Ayer: "+iTinterasAyer+
                "%0A     Compraron Hoy: "+iTinterasHoy+
                "%0A     quedan debiendo: "+iTinterasPendiente+
                "%0A*Vendedor 1:*"+
                "%0A     paga de Ayer: "+iVendedor_1_Ayer+
                "%0A     paga de Hoy: "+iVendedor_1_Hoy+
                "%0A     queda debiendo: "+iVendedor_1_Pendiente+
                "%0A     Debe Entregar: *$ "+imDineroVendedor_1+"*"+
                "%0A*Vendedor 2:*"+
                "%0A     paga de Ayer: "+iVendedor_2_Ayer+
                "%0A     paga de Hoy: "+iVendedor_2_Hoy+
                "%0A     queda debiendo: "+iVendedor_2_Pendiente+
                "%0A     Debe Entregar: *$ "+imDineroVendedor_2+"*"+
                "%0A"+
                "%0ATotal Facturado:   $ "+imDineroVendedores_Total+
                "%0ACompras Papeleria: $ "+imComprasPapeleria+
                "%0ACompras Bolsas:    $ "+imComprasBolsas+
                "%0ACompras Otros:     $ "+imComprasOtros+
                "%0A"+
                "%0ACompras Total:     $ "+imComprasTotal+
                "%0A*EFECTIVO TOTAL:   $ "+imEfectivoTotal+"*"+
                "%0A"+
                "%0AGracias... vamos por más!!!";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+lCelular +"&text="+sMensaje));
        startActivity(intent);
    }

    void fNuevosValoresPrevios(){

        SharedPreferences sharedDatosLocales = getPreferences(context.MODE_PRIVATE);
        if (sharedDatosLocales.getAll().size()==0){
            SharedPreferences sharedDatoslocales = getPreferences(context.MODE_PRIVATE);
            SharedPreferences.Editor editorDatosLocales = sharedDatoslocales.edit();
            editorDatosLocales.putInt("Devueltas", 0);
            editorDatosLocales.putInt("Tinteras Pendiente", 0);
            editorDatosLocales.putInt("Vendedor 1 Pendiente", 0);
            editorDatosLocales.putInt("Vendedor 2 Pendiente", 0);
            editorDatosLocales.commit();
        }

        Map<String, Integer> mapDatosLocales;
        mapDatosLocales = (Map<String, Integer>) sharedDatosLocales.getAll();
        iDevueltasAyer = (int) mapDatosLocales.get("Devueltas");
        iTinterasAyer = (int) mapDatosLocales.get("Tinteras Pendiente");
        iVendedor_1_Ayer = (int) mapDatosLocales.get("Vendedor 1 Pendiente");
        iVendedor_2_Ayer = (int) mapDatosLocales.get("Vendedor 2 Pendiente");

        obDevueltasAyer.setText(String.valueOf(iDevueltasAyer));
        obTinterasAyer.setText(String.valueOf(iTinterasAyer));
        obVendedor_1_Ayer.setText(String.valueOf(iVendedor_1_Ayer));
        obVendedor_2_Ayer.setText(String.valueOf(iVendedor_2_Ayer));

    }

    ArrayList fCalendario(){

        ArrayList alDatosCalendario=new ArrayList();

        Calendar Date=Calendar.getInstance();
        int iAño= Date.get(Calendar.YEAR);
        int iMes=Date.get(Calendar.MONTH)+1;
        int iFecha=Date.get(Calendar.DAY_OF_MONTH);
        int iDia=Date.get(Calendar.DAY_OF_WEEK);
        String sDia="",sMes="";
        switch (iDia){
            case 1:sDia="Dom";break;
            case 2:sDia="Lun";break;
            case 3:sDia="Mar";break;
            case 4:sDia="Mie";break;
            case 5:sDia="Jue";break;
            case 6:sDia="Vie";break;
            case 7:sDia="Sab";break;
        }
        switch (iMes){
            case 1:sMes="Ene";break;
            case 2:sMes="Feb";break;
            case 3:sMes="Mar";break;
            case 4:sMes="Abr";break;
            case 5:sMes="May";break;
            case 6:sMes="Jun";break;
            case 7:sMes="Jul";break;
            case 8:sMes="Ago";break;
            case 9:sMes="Sep";break;
            case 10:sMes="Oct";break;
            case 11:sMes="Nov";break;
            case 12:sMes="Dic";break;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:");
        SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2=new SimpleDateFormat("HHmmddssMMyy");
        String sHora=sdf.format(Date.getTime());
        String sHoraSeg=sdf1.format(Date.getTime());
        String sTodo=sdf2.format(Date.getTime());
        String sFecha=sDia +" "+ iFecha +" de "+ sMes+ " de " + iAño;

        alDatosCalendario.add(iAño);
        alDatosCalendario.add(iMes);
        alDatosCalendario.add(iFecha);
        alDatosCalendario.add(sDia);
        alDatosCalendario.add(sMes);
        alDatosCalendario.add(sHora);
        alDatosCalendario.add(sFecha);
        alDatosCalendario.add(sTodo);
        alDatosCalendario.add(sHoraSeg);
        return(alDatosCalendario);
    }



    void fConfirmar() {

        Alert_Confirmar alert_confirmar = new Alert_Confirmar();
        alert_confirmar.show(getSupportFragmentManager(), "Confirmación de informe");
    }
    @Override
    public void fConfirmar(String imComprasPapeleria,String imComprasBolsas, String imComprasOtros,
                           String imComprasTotal,String imEfectivoTotal) {
        final ArrayList alDatosCalendario=fCalendario();
        fGuardarEnLocal();
        fTextoWhatsApp(alDatosCalendario.get(6).toString(),imComprasPapeleria,
                imComprasBolsas, imComprasOtros,imComprasTotal,imEfectivoTotal);
        fBorrarTodo();
        fNuevosValoresPrevios();
    }
}
package com.app.cierreempanadas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Alert_Confirmar extends AppCompatDialogFragment {
    TextView otvDineroTotal,otvComprasTotal,otvEfectivoTotal;
    EditText oetComprasPapeleria,oetComprasBolsas,oetComprasOtros;

    private Alert_Confirmar.Alert_Confirmar_Listener Listener;

    int iDineroTotal=0,iComprasPapeleria=0,iComprasBolsas=0,iComprasOtros=0,
        iComprasTotal=0,iEfectivoTotal=0;

    String imComprasPapeleria,imComprasBolsas, imComprasOtros,imDineroTotal,imComprasTotal,imEfectivoTotal;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.alert_confirmar,null);

        iDineroTotal=MainActivity.iDineroTotal_global;


        otvDineroTotal=(TextView)view.findViewById(R.id.tvDineroTotal);
        otvComprasTotal=(TextView)view.findViewById(R.id.tvCompras);
        otvEfectivoTotal=(TextView)view.findViewById(R.id.tvEfectivo);
        oetComprasPapeleria=(EditText)view.findViewById(R.id.etComprasPapeleria);
        oetComprasBolsas=(EditText)view.findViewById(R.id.etComprasBolsas);
        oetComprasOtros=(EditText)view.findViewById(R.id.etComprasOtros);

        fCalcular();
        //########################################################
        oetComprasPapeleria.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                fCalcular();
                return false;
            }
        });
        oetComprasPapeleria.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fCalcular();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //########################################################
        oetComprasBolsas.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                fCalcular();
                return false;
            }
        });
        oetComprasBolsas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fCalcular();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //########################################################

        oetComprasOtros.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                 fCalcular();
                return false;
            }
        });
        oetComprasOtros.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fCalcular();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Constructor del Custom Dialog

        builder.setView(view)
                .setTitle("Finalizar y Confirmar Informe")
                .setCancelable(true)
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            Listener.fConfirmar(imComprasPapeleria,imComprasBolsas, imComprasOtros,
                                    imComprasTotal,imEfectivoTotal);
                    }
                });




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Listener = (Alert_Confirmar.Alert_Confirmar_Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "Seleccione un Cliente");
        }
    }

    public interface Alert_Confirmar_Listener{
        void fConfirmar(String iComprasPapeleria,String iComprasBolsas, String iComprasOtros,
                        String iComprasTotal,String iEfectivoTotal);
    }


    void fCalcular(){

        if(oetComprasPapeleria.getText().toString().isEmpty()){
            iComprasPapeleria=0;
        }
        else{
            iComprasPapeleria= Integer.valueOf(oetComprasPapeleria.getText().toString());
        }

        if(oetComprasBolsas.getText().toString().isEmpty()){
            iComprasBolsas=0;
        }
        else{
            iComprasBolsas= Integer.valueOf(oetComprasBolsas.getText().toString());
        }

        if(oetComprasOtros.getText().toString().isEmpty()){
            iComprasOtros=0;
        }
        else{
            iComprasOtros= Integer.valueOf(oetComprasOtros.getText().toString());
        }


        iComprasTotal=iComprasPapeleria+iComprasBolsas+iComprasOtros;
        iEfectivoTotal=iDineroTotal-iComprasTotal;

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        imComprasPapeleria = decimalFormat.format(iComprasPapeleria);
        imComprasBolsas = decimalFormat.format(iComprasBolsas);
        imComprasOtros = decimalFormat.format(iComprasOtros);
        imDineroTotal = decimalFormat.format(iDineroTotal);
        imComprasTotal = decimalFormat.format(iComprasTotal);
        imEfectivoTotal = decimalFormat.format(iEfectivoTotal);


        otvDineroTotal.setText("Dinero Total: $"+imDineroTotal);
        otvComprasTotal.setText("- Compras Total: $"+imComprasTotal);
        otvEfectivoTotal.setText("= Efectivo Total: $"+imEfectivoTotal);
    }

}

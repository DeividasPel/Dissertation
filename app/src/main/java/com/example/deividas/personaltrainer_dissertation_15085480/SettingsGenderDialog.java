package com.example.deividas.personaltrainer_dissertation_15085480;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

public class SettingsGenderDialog extends AppCompatDialogFragment{
    private Spinner editGender;
    private ExampleDialogListener listener;
    private ArrayList<String> gender_list = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.spinner_dialog, null);

        builder.setView(view).setTitle("Change gender").setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String gender = editGender.getSelectedItem().toString();
                        listener.applyTextGender(gender);
                    }
                });

        gender_list.add("Male");
        gender_list.add("Female");
        gender_list.add("Other");
        editGender = view.findViewById(R.id.edit_spinner);

        HintSpinner<String> hintSpinner_gender = new HintSpinner<>(editGender, new HintAdapter<>(getActivity(), R.string.gender_hint, gender_list), new HintSpinner.Callback<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                //
            }
        });
        hintSpinner_gender.init();

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTextGender(String gender);
    }
}

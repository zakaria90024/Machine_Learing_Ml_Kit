package com.sasoftbd.machine_learing_ml_kit.Translation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.sasoftbd.machine_learing_ml_kit.R;

public class TranslationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);

        EditText editTextTranslate = (EditText) findViewById(R.id.editTextTranslate);
        EditText editTextTranslateResult = (EditText) findViewById(R.id.editTextTextPersonName2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Button btnTranslete = (Button) findViewById(R.id.btnTranslate);

        btnTranslete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextTranslate.getText().toString())){
                    Toast.makeText(TranslationActivity.this, "empty", Toast.LENGTH_SHORT).show();
                }else {


                    // Create an English-German translator:
                    TranslatorOptions options =
                            new TranslatorOptions.Builder()
                                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                                    .setTargetLanguage(TranslateLanguage.BENGALI)
                                    .build();
                    final Translator englishGermanTranslator =
                            Translation.getClient(options);

                    String sorceText = editTextTranslate.getText().toString();
                    ProgressDialog progressDialog = new ProgressDialog(TranslationActivity.this);
                    progressDialog.setTitle("Downloading the module...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    englishGermanTranslator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TranslationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    Task<String> result = englishGermanTranslator.translate(sorceText).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            editTextTranslateResult.setText(""+s);
                            Toast.makeText(TranslationActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TranslationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }
}
package com.sasoftbd.machine_learing_ml_kit.ImageToText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.sasoftbd.machine_learing_ml_kit.FaceDetection.FaceDetectionActivity;
import com.sasoftbd.machine_learing_ml_kit.R;
import com.sasoftbd.machine_learing_ml_kit.zone_Panel.ZoneActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ImageToTextActivity extends AppCompatActivity {

    Button btn_capture, btn_copy, btn_play, btn_say;
    EditText txt_Show_Text;
    private static final int REQUEST_CODE_FOR_CAMERA = 100;
    Bitmap bitmap;
    String fromGetImage;

    //for read Text
    private TextToSpeech mtts;
    private SeekBar skb_pitch;
    private SeekBar skb_speed;

    /**
     * now try to Speak to text
     *
     * @param savedInstanceState
     */

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_text);
        btn_capture = findViewById(R.id.btn_capture);
        btn_copy = findViewById(R.id.btn_text);
        btn_play = findViewById(R.id.btn_Tst);
        btn_say = findViewById(R.id.btn_say);
        skb_pitch = findViewById(R.id.skb_1);
        skb_speed = findViewById(R.id.skb_2);
        txt_Show_Text = findViewById(R.id.showText);
        skb_pitch.setProgress(55);
        skb_speed.setProgress(38);

        /*
            for text to speech
        */
        mtts = new TextToSpeech(ImageToTextActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mtts.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTs", "Language not support");
                    } else {
                        //button added
                        btn_play.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization Failed");
                }
            }
        });


        //for Open Recorder
        btn_say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(ImageToTextActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (ContextCompat.checkSelfPermission(ImageToTextActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ImageToTextActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CODE_FOR_CAMERA);
        }

        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(ImageToTextActivity.this);
            }
        });

        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scnned_text = txt_Show_Text.getText().toString();
                copyToClipBoard(scnned_text);
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageToTextActivity.this, "Played", Toast.LENGTH_SHORT).show();
                speak();
            }
        });

    }


    //for text read
    private void speak() {

        float pitch = (float) skb_pitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.10f;

        float speed = (float) skb_speed.getProgress() / 50;
        if (speed < 0.1) speed = 0.10f;

        mtts.setPitch(pitch);
        mtts.setSpeechRate(speed);

        if (!txt_Show_Text.getText().toString().isEmpty()) {
            mtts.speak(txt_Show_Text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onDestroy() {
        if (mtts != null) {
            mtts.stop();
            mtts.shutdown();
        }
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Toast.makeText(ImageToTextActivity.this, "" + Objects.requireNonNull(result).get(0), Toast.LENGTH_SHORT).show();
                if (Objects.requireNonNull(result).get(0).equals("Open") || Objects.requireNonNull(result).get(0).equals("open")) {
                    btn_capture.performClick();
                }
                if(Objects.requireNonNull(result).get(0).equals("What") || Objects.requireNonNull(result).get(0).equals("what")){
                    Intent intent = new Intent(ImageToTextActivity.this, ZoneActivity.class);
                    startActivity(intent);
                }
                if(Objects.requireNonNull(result).get(0).equals("Face") || Objects.requireNonNull(result).get(0).equals("face")){
                    Intent intent = new Intent(ImageToTextActivity.this, FaceDetectionActivity.class);
                    startActivity(intent);
                }
            }
        }


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    getTextFromImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void getTextFromImage(Bitmap bitmap) {
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()) {
            Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < textBlockSparseArray.size(); i++) {
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");

            }
            fromGetImage = stringBuilder.toString();
            txt_Show_Text.setText(stringBuilder.toString());
            btn_capture.setText("ReTry");
            btn_capture.setVisibility(View.VISIBLE);

        }

    }


    private void copyToClipBoard(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Date", text);
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

}
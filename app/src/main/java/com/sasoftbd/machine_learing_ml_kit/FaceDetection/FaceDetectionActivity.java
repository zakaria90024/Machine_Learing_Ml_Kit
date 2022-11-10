package com.sasoftbd.machine_learing_ml_kit.FaceDetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.sasoftbd.machine_learing_ml_kit.R;

import java.util.List;

public class FaceDetectionActivity extends AppCompatActivity {

    ImageView original_Image_id, croped_image;
    Button btn_Detect;

    private static final String TAG = "FACE_DETECT_TAG";
    private static final int SCALING_FACTOR = 10;
    private FaceDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detection);


        original_Image_id = findViewById(R.id.original_Image_id);
        btn_Detect = findViewById(R.id.btn_Detect);
        croped_image = findViewById(R.id.croped_image);

        FaceDetectorOptions realTimeFDO = new FaceDetectorOptions.Builder()
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .build();

        //init FaceDetector obj
        detector = FaceDetection.getClient(realTimeFDO);


        btn_Detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dennis_ritchie);

//                Uri imguri = null;
//                try {
//                    Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), imguri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) original_Image_id.getDrawable();
//                Bitmap bitmap1 = bitmapDrawable.getBitmap();


                analyzePhoto(bitmap);
            }
        });


    }

    private void analyzePhoto(Bitmap bitmap) {
        Log.d(TAG, "analyzePhoto");

        Bitmap smallerbitmap = Bitmap.createScaledBitmap(bitmap,
                bitmap.getWidth() / SCALING_FACTOR,
                bitmap.getHeight() / SCALING_FACTOR,
                false);

        InputImage inputImage = InputImage.fromBitmap(smallerbitmap, 0);

        detector.process(inputImage)
                .addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {
                        //multiple face detect from one image
                        Toast.makeText(FaceDetectionActivity.this, "Found Faces = " + faces.size(), Toast.LENGTH_LONG).show();
                        for (Face face : faces) {
                            Rect rect = face.getBoundingBox();
                            rect.set(
                                    rect.left * SCALING_FACTOR,
                                    rect.top * (SCALING_FACTOR - 1),
                                    rect.right * SCALING_FACTOR,
                                    (rect.bottom * SCALING_FACTOR) + 90
                            );
                        }

                        chropedDetectedFace(bitmap, faces);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FaceDetectionActivity.this, "Fail" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void chropedDetectedFace(Bitmap bitmap, List<Face> faces) {
        Log.d(TAG, "croupDetectedFaces");

        Rect rect = faces.get(0).getBoundingBox();
        int x = Math.max(rect.left, 0);
        int y = Math.max(rect.top, 0);
        int width = rect.width();
        int height = rect.height();

        Bitmap croppedBitmap = Bitmap.createBitmap(
                bitmap,
                x,
                y,
                (x + width > bitmap.getWidth()) ? bitmap.getWidth() - x : width,
                (y + height > bitmap.getHeight()) ? bitmap.getHeight() - y : height

        );

        //set the cropped image in bitmap
        croped_image.setImageBitmap(croppedBitmap);
    }
}
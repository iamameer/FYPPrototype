package fyp.fypprototype;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnCapture;
    ImageView imgCapture;

    static final int CAM_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapture = (Button) findViewById(R.id.button);
        imgCapture = (ImageView) findViewById(R.id.imgCapture);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create intent object
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_REQUEST);
            }
        });
    }

    //create folder on storage
    private File getFile(){
        File folder = new File("sdcard/camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        //filename
        File image_file = new File(folder,"cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camera_app/cam_image.jpg";
        imgCapture.setImageDrawable(Drawable.createFromPath(path));
    }
}

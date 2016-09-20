package krstics.easycast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import krstics.easycast.FileBrowser.FileChooser;

public class EasyCastActivity extends Activity {

    private static final int REQUEST_PATH = 1;
    String curFileName;
    String curFilePath;
    EditText etFileSelected;
    Button browseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_cast);

        etFileSelected = (EditText) findViewById(R.id.etFileSelected);
        browseButton = (Button) findViewById(R.id.browseButton);

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFile(view);
            }
        });
    }

    private void getFile(View view) {
        Intent intent = new Intent(this, FileChooser.class);
        startActivityForResult(intent, REQUEST_PATH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PATH){
            if(resultCode == RESULT_OK){
                curFileName = data.getStringExtra("GetFileName");
                curFilePath = data.getStringExtra("GetPath");
                etFileSelected.setText(curFilePath + "/" + curFileName);
            }
        }
    }
}

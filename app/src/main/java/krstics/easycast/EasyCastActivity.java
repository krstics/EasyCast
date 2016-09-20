package krstics.easycast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.ConnectableDeviceListener;
import com.connectsdk.device.DevicePicker;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.command.ServiceCommandError;

import java.util.List;

import krstics.easycast.FileBrowser.FileChooser;

public class EasyCastActivity extends Activity{

    private static final int REQUEST_PATH = 1;
    String curFileName;
    String curFilePath;
    EditText etFileSelected;
    Button browseButton;

    private DevicePicker devicePicker;
    private ConnectableDevice connectableDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_cast);

        init();
    }

    private void init() {
        etFileSelected = (EditText) findViewById(R.id.etFileSelected);
        browseButton = (Button) findViewById(R.id.browseButton);

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFile(view);
            }
        });

        DiscoveryManager.init(getApplicationContext());
        DiscoveryManager.getInstance().start();

        devicePicker = new DevicePicker(this);

        Button imageButton = (Button) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(showImageClickListener);
    }

    private View.OnClickListener showImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog dialog = devicePicker.getPickerDialog("Select a device", pickerClickListener);
            dialog.show();
        }
    };

    private AdapterView.OnItemClickListener pickerClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            connectableDevice = (ConnectableDevice) parent.getItemAtPosition(position);
            connectableDevice.addListener(deviceListener);
            connectableDevice.connect();
        }
    };

    private ConnectableDeviceListener deviceListener = new ConnectableDeviceListener() {
        @Override
        public void onDeviceReady(ConnectableDevice device) {

        }

        @Override
        public void onDeviceDisconnected(ConnectableDevice device) {

        }

        @Override
        public void onPairingRequired(ConnectableDevice device, DeviceService service, DeviceService.PairingType pairingType) {

        }

        @Override
        public void onCapabilityUpdated(ConnectableDevice device, List<String> added, List<String> removed) {

        }

        @Override
        public void onConnectionFailed(ConnectableDevice device, ServiceCommandError error) {

        }
    };

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

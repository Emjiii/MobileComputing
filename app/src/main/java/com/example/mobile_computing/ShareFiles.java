package com.example.mobile_computing;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registor_form.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareFiles extends AppCompatActivity {
    Button buttonopenDailog, buttonUp, send;
    TextView textFolder;
    EditText dataPath;
    ListView dialog_ListView;
    File root, curFolder;
    private List<String> fileList = new ArrayList<>();
    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;
    BluetoothAdapter btAdatper = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_files);
        dataPath = findViewById(R.id.filePath);
        buttonopenDailog = findViewById(R.id.openDialog);
        send = findViewById(R.id.sendBtooth);

        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;

        // Open the file dialog on button click
        buttonopenDailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPath.setText("");
                showFileDialog();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendViaBluetooth();
            }
        });
    }

    private void showFileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("File Selector");
        View dialogView = getLayoutInflater().inflate(R.layout.share_dialog, null);
        builder.setView(dialogView);

        textFolder = dialogView.findViewById(R.id.folder);
        buttonUp = dialogView.findViewById(R.id.up);
        dialog_ListView = dialogView.findViewById(R.id.dialoglist);

        // Set up the "Up" button for navigating up a directory level
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curFolder != null && curFolder.getParentFile() != null) {
                    ListDir(curFolder.getParentFile());
                } else {
                    Toast.makeText(ShareFiles.this, "No parent directory", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File selected = new File(fileList.get(position));
                if (selected.isDirectory()) {
                    ListDir(selected);
                } else if (selected.isFile()) {
                    getselectedFile(selected);
                }
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Load the initial directory
        ListDir(curFolder);
    }


    public void getselectedFile(File f) {
        dataPath.setText(f.getAbsolutePath());
        fileList.clear();
    }

    public void ListDir(File f) {
        if (f.equals(root)) {
            buttonUp.setEnabled(false);
        } else {
            buttonUp.setEnabled(true);
        }
        curFolder = f;
        textFolder.setText(f.getAbsolutePath());
        dataPath.setText(f.getAbsolutePath());
        File[] files = f.listFiles();
        fileList.clear();

        if (files != null) {
            for (File file : files) {
                fileList.add(file.getPath());
            }
        }

        ArrayAdapter<String> directoryList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileList);
        dialog_ListView.setAdapter(directoryList);
    }

    // Method for sending a file via Bluetooth
    public void sendViaBluetooth() {
        if (dataPath.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please select a file.", Toast.LENGTH_LONG).show();
            return;
        }

        if (btAdatper == null) {
            Toast.makeText(this, "Device does not support Bluetooth", Toast.LENGTH_LONG).show();
            return;
        }

        enableBluetooth();
    }

    public void enableBluetooth() {
        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVER_DURATION);
        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == DISCOVER_DURATION && requestCode == REQUEST_BLU) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.setType("*/*");
            File file = new File(dataPath.getText().toString());

            i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));

            PackageManager pm = getPackageManager();
            List<ResolveInfo> list = pm.queryIntentActivities(i, 0);
            boolean found = false;

            for (ResolveInfo info : list) {
                String packageName = info.activityInfo.packageName;
                if (packageName.equals("com.android.bluetooth")) {
                    i.setClassName(packageName, info.activityInfo.name);
                    found = true;
                    break;
                }
            }
            if (found) {
                startActivity(i);
            } else {
                Toast.makeText(this, "Bluetooth not found", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bluetooth is cancelled", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Developer: Santosh Kumar Singh\nContact: superssingh@gmail.com", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

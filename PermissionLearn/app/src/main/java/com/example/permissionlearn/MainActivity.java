package com.example.permissionlearn;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private TextView mTextView;
    final private static int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Log.d(TAG, "Manifest.permission.CAMERA is  granted");
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            showNormalDialog();
            //第一种方式使用 ActivityResultLauncher
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.

            //第一种方式使用 ActivityResultLauncher
//            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
            //第二种方式
            // You can directly ask for the permission.
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        }
    }

    //注册权限回调，它处理用户对系统权限对话框的响应。
    //将返回值(ActivityResultLauncher的一个实例)保存为实例变量。
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // 权限被授予的情况
                    Toast.makeText(MainActivity.this, "第一种方式：权限被授予", Toast.LENGTH_LONG).show();
                } else {
                    //向用户解释该功能不可用，因为该功能需要用户拒绝的权限。
                    // 同时，尊重用户的决定。不要链接到系统设置，试图说服用户改变他们的决定。
                    Toast.makeText(MainActivity.this, "第一种方式：权限未授予", Toast.LENGTH_LONG).show();
                }
            });

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    Toast.makeText(MainActivity.this, "第二种方式：权限被授予", Toast.LENGTH_LONG).show();
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    Toast.makeText(MainActivity.this, "第二种方式：权限未被授予", Toast.LENGTH_LONG).show();
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        normalDialog.setTitle("我是一个普通Dialog");
        normalDialog.setMessage("我需要摄像头权限，快给我！！！");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}
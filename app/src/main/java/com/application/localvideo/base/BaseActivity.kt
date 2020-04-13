package com.application.localvideo.base

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.application.localvideo.utils.MethodLoadDirectory
import com.application.localvideo.utils.StorageUtils
import java.io.File


abstract class BaseActivity : AppCompatActivity() {
    private lateinit var fileUri: Uri
    private var storage: File? = null
    private lateinit var storagePaths: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayOutId())
        checkStorageAccessPermission()
    }

    abstract fun getLayOutId(): Int

    open fun initViewModel() {
        // Subclass will override this method¬
    }

    open fun checkStorageAccessPermission() {
        //ContextCompat use to retrieve resources. It provide uniform interface to access resources.
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to access media file in your phone")
                    .setPositiveButton(
                        "OK"
                    ) { dialog, which ->
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            1
                        )
                    }
                    .setNegativeButton(
                        "CANCEL"
                    ) { dialog, which -> dialog.dismiss() }.show()
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode === Activity.RESULT_OK) {
                fileUri = data?.data!!
                contentResolver.takePersistableUriPermission(
                    fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                            or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode === 1) {
            if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                //load data here
                //for first time data will be loaded here
                //then it will be loaded in splash screen
                //because if we could not have permission then we could not load data in splash screen window
                storagePaths = StorageUtils.getStorageDirectories(this)
                for (path in storagePaths) {
                    storage = File(path)
                    MethodLoadDirectory.load_Directory_Files(storage)
                }
                //  recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }
}

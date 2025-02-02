package com.example.radiate

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide


class WriteJournal : AppCompatActivity() {

    lateinit var edtJournalContent : EditText
    lateinit var plusIcon : ImageView
    lateinit var journalImage : ImageView

    private lateinit var galleryLauncher:ActivityResultLauncher<Intent>
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_journal)

        //Setting up the toolbar
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)  //setting toolbar as action bar
        supportActionBar?.title=""   //default title for the toolbar

        //initializing the title edittext in toolbar
        val journalTitle = findViewById<EditText>(R.id.titleToolbar)
        journalTitle.isFocusable = true

        //using FocusChangeListener to set the title once user enter it
        journalTitle.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                val newTitle = journalTitle.text.toString()
                supportActionBar?.title=newTitle
            }
        }

        //Setting the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Initializing objects of image and content of journal
        plusIcon=findViewById<ImageView>(R.id.imgPlusIcon)
        journalImage = findViewById<ImageView>(R.id.imgPreview)
        edtJournalContent = findViewById<EditText>(R.id.edtJournalContent)

        //Initializing gallery launcher to display gallery and show selected image
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if (result.resultCode == Activity.RESULT_OK){
                val imageUri : Uri? = result.data?.data
                imageUri?.let {
                    journalImage.setImageURI(it)
                    journalImage.visibility = ImageView.VISIBLE
                    plusIcon.visibility = ImageView.INVISIBLE
                }
            }
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if (result.resultCode==Activity.RESULT_OK){
                val imageBitmap=result.data?.extras?.get("data") as Bitmap
                journalImage.setImageBitmap(imageBitmap)
                journalImage.visibility = ImageView.VISIBLE
                plusIcon.visibility = ImageView.INVISIBLE
            }
        }

        //Handling click event on the plus icon imageview to open the dialog box
        plusIcon.setOnClickListener {
            showImagePickerDialog()
        }
    }

    private fun showImagePickerDialog() {
        val option = arrayOf("Choose from gallery","Take a photo")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Image")
        builder.setItems(option){_, which->
            when(which){
                0 -> openGallery()
                1 -> openCamera()
            }
        }
        builder.show()
    }

    //creating function to open gallery
    private fun openGallery(){
        //Checking if the current version in the mobile is > Tiramisu or not if yes then permissions will be accordingly
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            Manifest.permission.READ_MEDIA_IMAGES
        }else{
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this,permission)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(permission),101)
        }else{
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(intent)
        }
    }

    //creating function to open camera
    private fun openCamera(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.CAMERA),102)
        }else{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            when(requestCode){
                101 -> openGallery()
                102 -> openCamera()
            }
        }else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.journal_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            R.id.shareJournal ->{
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.deleteJournal ->{
                Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.clearJournal ->{
                Toast.makeText(this,"Cleared",Toast.LENGTH_SHORT).show()
                edtJournalContent.text.clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
package com.example.radiate

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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

    private val PICK_IMAGE_REQUEST = 1
    private val CAMERA_REQUEST = 1
    private val STORAGE_PERMISSION_CODE = 101
    private val CAMERA_PERMISSION_CODE = 102


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

        //Handling click event on the plus icon imageview to open the dialog box
        plusIcon.setOnClickListener {
            showImagePickerDialog()
        }


    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Choose from gallery", "Take a photo")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Image")
        builder.setItems(options){_, which->
            when(which){
                0->openGallery()
                1->openCamera()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA_PERMISSION_CODE)
        }else{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,CAMERA_REQUEST)
        }
    }

    private fun openGallery() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),STORAGE_PERMISSION_CODE)
        }else{
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,PICK_IMAGE_REQUEST)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                PICK_IMAGE_REQUEST ->{
                    val imageUri: Uri? = data?.data
                    imageUri?.let {
                        Glide.with(this).load(it).into(journalImage)  // Load image into ImageView using Glide
                        journalImage.visibility = ImageView.VISIBLE
                        plusIcon.visibility = ImageView.INVISIBLE
                    }
                }
                CAMERA_REQUEST -> {
                // Handling image captured from the camera
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    journalImage.visibility = ImageView.VISIBLE
                    plusIcon.visibility = ImageView.INVISIBLE
                    journalImage.setImageBitmap(imageBitmap)  // Display the captured image

                }
            }
        }
    }
}
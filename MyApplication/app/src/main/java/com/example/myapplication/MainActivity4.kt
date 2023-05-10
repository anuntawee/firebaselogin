package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity4 : AppCompatActivity() {

    lateinit var txtEmailForget:EditText
    lateinit var buttonOnreset:Button
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

    
        mAuth = FirebaseAuth.getInstance()
        buttonOnreset = findViewById(R.id.getrepass)
        txtEmailForget=findViewById(R.id.textemailforget)

        buttonOnreset.setOnClickListener {
            val email=txtEmailForget.text.toString()
            if(TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"Please Enter you Email", Toast.LENGTH_SHORT).show()
            } else{
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    task -> if(task.isSuccessful){
                    Toast.makeText(this@MainActivity4,"Please Check you Email",
                        Toast.LENGTH_SHORT).show()
                    }else{
                    Toast.makeText(applicationContext,"Fail to send reset password ",
                        Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
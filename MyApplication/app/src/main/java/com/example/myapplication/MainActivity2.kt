package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity2 : AppCompatActivity() {

    lateinit var txtemailcreate: EditText
    lateinit var txtpassworcrete: EditText
    lateinit var btnregiscrete: Button

    lateinit var email:String
    lateinit var password:String

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        txtemailcreate = findViewById(R.id.txtemailcreta)
        txtpassworcrete = findViewById(R.id.txtpasswordcreta)
        btnregiscrete = findViewById(R.id.btnregiscreate)

        mAuth = FirebaseAuth.getInstance();
        btnregiscrete.setOnClickListener {
            createAccount()
        }
    }
    /*override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        UpdateUI(currentUser)
    }*/

    private fun createAccount() {
       email = txtemailcreate.text.toString()
        password = txtemailcreate.text.toString()
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            task ->if (task.isSuccessful){
                Log.d("Myadd","Create new User")
            Toast.makeText(this@MainActivity2, "Create new User",Toast.LENGTH_SHORT).show()
            val  user = mAuth!!.currentUser
            UpdateUI(user)
            }else {
            Log.d("Myadd","Failure new User", task.exception)
            Toast.makeText(this@MainActivity2, "Authentiocation Filed",Toast.LENGTH_SHORT).show()
            UpdateUI(null)
            }
        }
    }

    private fun UpdateUI(user: FirebaseUser?) {
        if(user != null){
            val uid = user.uid
            val email = user.email
            Toast.makeText(this@MainActivity2,"Welcome: $email Your Id is : $uid",Toast.LENGTH_SHORT).show()
            val intentsession = Intent(this, MainActivity3::class.java)
            startActivity(intentsession)
        }
    }
}



package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var txtemail:EditText
    lateinit var txtpassword:EditText
    lateinit var btnregis:Button
    lateinit var repass:Button
    lateinit var btnlogin:Button
    lateinit var email:String
    lateinit var password:String
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtemail = findViewById(R.id.txtemail)
        txtpassword = findViewById(R.id.txtpassword)
        btnlogin = findViewById(R.id.btnlogin)
        btnregis = findViewById(R.id.btncreate)
        repass =findViewById(R.id.repassword)
        mAuth = FirebaseAuth.getInstance();
        btnregis.setOnClickListener {
            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        repass.setOnClickListener {
            var getrepass = Intent(this,MainActivity4::class.java)
            startActivity(getrepass)
        }
        btnlogin.setOnClickListener {
            loginemail()
        }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        UpdateUI(currentUser)
    }
    private fun loginemail(){
        email = txtemail.text.toString()
        password = txtpassword.text.toString()
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                task ->if (task.isSuccessful){
            Log.d("Myadd","Login")
            Toast.makeText(this@MainActivity, "Login new User", Toast.LENGTH_SHORT).show()
            val  user = mAuth!!.currentUser
            UpdateUI(user)

        }else {
            Log.d("Myadd","Failure new User", task.exception)
            Toast.makeText(this@MainActivity, "Authentiocation Filed", Toast.LENGTH_SHORT).show()
            UpdateUI(null)
           }
        }
    }


    private fun UpdateUI(user: FirebaseUser?) {
        if(user != null){
           val uid = user.uid
            val email = user.email
            Toast.makeText(this@MainActivity,"Welcome: $email Your Id is : $uid",Toast.LENGTH_SHORT).show()
            val intentsession = Intent(this, MainActivity3::class.java)
            startActivity(intentsession)
        }else {
            Toast.makeText(this@MainActivity, "Authentiocation Filed", Toast.LENGTH_SHORT).show()
        }
    }

}
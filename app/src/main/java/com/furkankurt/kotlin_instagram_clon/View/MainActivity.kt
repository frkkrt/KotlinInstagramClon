package com.furkankurt.kotlin_instagram_clon.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.furkankurt.kotlin_instagram_clon.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        auth=Firebase.auth

        val currentUser=auth.currentUser
        if(currentUser!=null)
        {
            val intent=Intent(this, FeedActivity::class.java)
            startActivity(intent)
        }


    }
    fun sign_in_click(view : View)
    {
        val email =binding.emailText.text.toString()
        val password=binding.password.text.toString()

        if(email.equals("")||password.equals(""))
        {
            Toast.makeText(this,"Enter email and password",Toast.LENGTH_LONG).show()
        }
        else
        {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent =Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
    fun sign_up_click(view : View)
    {
        //KULLANICI KAYDI
        val email=binding.emailText.text.toString()
        val password=binding.password.text.toString()
        //email ve password boş değilse
        //if(email.isNotEmpty()&&password.isNotEmpty())
        if(email.equals("")||password.equals(""))
        {
            Toast.makeText(this,"Enter email and password ",Toast.LENGTH_LONG).show()
        }
        else
        {
            //Success
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent= Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            //Failure
            }.addOnFailureListener{
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()

            }


        }

    }


}
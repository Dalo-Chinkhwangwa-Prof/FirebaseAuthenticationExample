package com.illicitintelligence.fblogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private val loginFragment: LoginFragment = LoginFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //TODO: check if user is logged in
        if (FirebaseAuth.getInstance().currentUser != null && FirebaseAuth.getInstance().currentUser?.isEmailVerified == true)
            openProfileFragment()
        else if (FirebaseAuth.getInstance().currentUser != null && FirebaseAuth.getInstance().currentUser?.isEmailVerified == false) {
            Toast.makeText(this, "Please check email for verification", Toast.LENGTH_SHORT).show()
            openLoginFragment()
        } else {
            openLoginFragment()
        }
    }

    private fun openLoginFragment() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_anim,
                R.anim.slide_out_anim,
                R.anim.slide_in_anim,
                R.anim.slide_out_anim
            )
            .add(R.id.main_frame_layout, loginFragment)
            .commit()
    }


    private fun openProfileFragment() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_anim,
                R.anim.slide_out_anim,
                R.anim.slide_in_anim,
                R.anim.slide_out_anim
            )
            .replace(R.id.main_frame_layout, profileFragment)
            .commit()
    }


    fun signUpUser(email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                checkResult(task, true)
            }

    }

    fun loginUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, password
        )
            .addOnCompleteListener { task ->
                checkResult(task, false)
            }

    }

    private fun checkResult(task: Task<AuthResult>, isNew: Boolean) {
        if (task.isSuccessful) {
            if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                openProfileFragment()
            } else {
                if (isNew){
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                }
                Toast.makeText(
                    this,
                    "Please check email for verification",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG)
                .show()
        }
    }
}

package com.illicitintelligence.fblogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.signup_fragment_layout.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {
            if(checkInput()){
                val email = email_edittext.text.toString()
                val password = password_edittext.text.toString()
                (activity as MainActivity).signUpUser(email, password)
                parentFragment?.childFragmentManager
                    ?.popBackStack()
            }
        }


        ic_close_imageview.setOnClickListener {
            parentFragment?.childFragmentManager
                ?.popBackStack()
        }
    }

    private fun checkInput(): Boolean{

        if(email_edittext.text.isNullOrBlank() || verifiy_email_edittext.text.isNullOrBlank() || password_edittext.text.isNullOrBlank() || verify_password_edittext.text.isNullOrBlank()){
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        } else if(email_edittext.text.toString() != verifiy_email_edittext.text.toString()){
            Toast.makeText(context, "Emails do not match", Toast.LENGTH_SHORT).show()
            return false
        } else if(password_edittext.text.toString() != verify_password_edittext.text.toString()){
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }else
            return true
    }
}
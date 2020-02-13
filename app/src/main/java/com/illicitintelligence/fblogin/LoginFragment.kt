package com.illicitintelligence.fblogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment_layout.*

class LoginFragment : Fragment() {

    private val signUpFragment = SignUpFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener {
            if (checkInput()) {
                val email = email_edittext.text.toString()
                val password = password_edittext.text.toString()

                (activity as MainActivity).loginUser(email, password)
            }

        }

        signup_textview.setOnClickListener {

            childFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_anim,
                    R.anim.slide_out_anim,
                    R.anim.slide_in_anim,
                    R.anim.slide_out_anim
                ).add(R.id.signup_frame, signUpFragment)
                .addToBackStack(signUpFragment.tag)
                .commit()
        }


    }


    private fun checkInput(): Boolean {
        if (email_edittext.text.isNullOrBlank() || password_edittext.text.isNullOrBlank()) {
            Toast.makeText(context, "Fields cannot be null", Toast.LENGTH_SHORT).show()
            return false
        } else
            return true
    }

}
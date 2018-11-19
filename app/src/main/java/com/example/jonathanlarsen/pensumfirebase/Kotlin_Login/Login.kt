package com.example.jonathanlarsen.pensumfirebase.Kotlin_Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

import android.widget.Toast
import com.example.jonathanlarsen.pensumfirebase.MainActivity
import com.example.jonathanlarsen.pensumfirebase.R
import com.google.firebase.auth.FirebaseAuth


class Login: ProgressActivity(), View.OnClickListener{

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEditText : EditText
    private lateinit var passEditText : EditText

    //final val userid = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_login)


        emailEditText = findViewById(R.id.fieldEmail)
        passEditText = findViewById(R.id.fieldPassword)

        val emailSignInbtn = findViewById<Button>(R.id.emailSignInButton)
        val emailcreatebtn = findViewById<Button>(R.id.emailCreateAccountButton)

        emailcreatebtn.setOnClickListener(this)
        emailSignInbtn.setOnClickListener(this)

        //Init Auth
        auth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }

        //showProgressDialog()
        System.out.println("Sup: ")

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        newIntent(this)
                        //updateUI(user)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }

                    //hideProgressDialog()
                }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }



        //showProgressDialog()

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        newIntent(this)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }


                    if (!task.isSuccessful) {
                         //status.setText(R.string.auth_failed) set textfield
                    }
                }

    }

    /*
    private fun sendEmailVerification() {
        // Disable button
        verifyEmailButton.isEnabled = false

        // Send verification email
        // [START send_email_verification]
        val user = auth.currentUser
        user?.sendEmailVerification()
                ?.addOnCompleteListener(this) { task ->
                    // [START_EXCLUDE]
                    // Re-enable button
                    verifyEmailButton.isEnabled = true

                    if (task.isSuccessful) {
                        Toast.makeText(baseContext,
                                "Verification email sent to ${user.email} ",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.exception)
                        Toast.makeText(baseContext,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                    // [END_EXCLUDE]
                }
        // [END send_email_verification]
    }

    */

    private fun validateForm(): Boolean {
        var valid = true

        val email = emailEditText.text.toString()
        if (TextUtils.isEmpty(email)) {
            emailEditText.error = "Required."
            valid = false
        } else {
            emailEditText.error = null
        }

        val password = passEditText.text.toString()
        if (TextUtils.isEmpty(password)) {
            passEditText.error = "Required"
            valid = false
        } else {
            passEditText.error = null
        }

        return valid
    }
/*
    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
        if (user != null) {

            /*
            status.text = getString(R.string.emailpassword_status_fmt,
                    user.email, user.isEmailVerified)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            emailPasswordButtons.visibility = View.GONE
            emailPasswordFields.visibility = View.GONE
            signedInButtons.visibility = View.VISIBLE

            verifyEmailButton.isEnabled = !user.isEmailVerified
            */
        } else {
            /*
            status.setText(R.string.signed_out)
            detail.text = null

            emailPasswordButtons.visibility = View.VISIBLE
            emailPasswordFields.visibility = View.VISIBLE
            signedInButtons.visibility = View.GONE
        }
        */
        }
    }
*/
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.emailCreateAccountButton -> createAccount(emailEditText.text.toString(), passEditText.text.toString())
            R.id.emailSignInButton -> signIn(emailEditText.text.toString(), passEditText.text.toString())
        }

    }

    fun newIntent(context: Context) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    companion object {
        private const val TAG = "EmailPassword"
    }

}
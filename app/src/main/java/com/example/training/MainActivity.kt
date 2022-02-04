package com.example.training

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var textInputEditText: TextInputEditText
    private lateinit var textInputLayout: TextInputLayout

    private val textWatcher: TextWatcher = object : SimpleTextWatcher(){
        override fun afterTextChanged(s: Editable?) {
            val input = s.toString()
            if(input.endsWith("@g")){
                val fullEmail = "${input}mail.com"
                textInputEditText.setTextCorrectly(fullEmail)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpannable()

        setImage()

        setEditText()

        setButton()



    }

    private fun setButton() {
        val loginButton = findViewById<Button>(R.id.button)
        val contentLayout = findViewById<LinearLayout>(R.id.contentLayout)
        val progressBar = findViewById<ProgressBar>(R.id.pb)
        loginButton.setOnClickListener {
            if(EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()){
                hideKeyBoard(textInputEditText)
                contentLayout.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                Snackbar.make(loginButton,"Go to postLogin",Snackbar.LENGTH_LONG).show()
                Handler(Looper.myLooper()!!).postDelayed({
                    contentLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    val dialog = BottomSheetDialog(this)
                    val view = LayoutInflater.from(this).inflate(R.layout.dialog,contentLayout,false)
                    dialog.setCancelable(false)
                    view.findViewById<View>(R.id.closeButton).setOnClickListener{
                        dialog.dismiss()
                    }
                    dialog.setContentView(view)
                    dialog.show()
                },3000)
            }else{
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = getString(R.string.invalid_email_message)

            }
        }
    }

    private fun AppCompatActivity.hideKeyBoard(view: View){
        val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }

    private fun setEditText() {
        textInputLayout = findViewById(R.id.textInputLayout)
        textInputEditText = textInputLayout.editText as TextInputEditText
        textInputEditText.addTextChangedListener(textWatcher)

        textInputEditText.listenChanges { textInputLayout.isErrorEnabled = false }
    }

    private fun TextInputEditText.listenChanges(block: (text: String) -> Unit){
        addTextChangedListener(object : SimpleTextWatcher(){
            override fun afterTextChanged(p0: Editable?) {
                block.invoke(text.toString())
            }
        })
    }

    private fun TextInputEditText.setTextCorrectly(text: String) {
        setText(text)
        setSelection(text.length)
    }

    private fun setImage() {

        val image = findViewById<ImageView>(R.id.image)

        Glide
            .with(this)
            .load(URL)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(image);


    }

    fun setSpannable(){
        val tv = findViewById<TextView>(R.id.text_agreement)

        val fullText = getString(R.string.agreement_full_text)
        val confidential = getString(R.string.confidential_enfo)
        val policy = getString(R.string.privacy_policy)

        val spannableString = SpannableString(fullText)

        val confidentialClickable = CustomClickableSpan{
            Snackbar.make(it,"Go to link 1 ", Snackbar.LENGTH_LONG).show()
        }

        val policyClickable = CustomClickableSpan{
            Snackbar.make(it,"Go to link 2 ", Snackbar.LENGTH_LONG).show()
        }

        spannableString.setSpan(
            confidentialClickable,
            fullText.indexOf(confidential),
            fullText.indexOf(confidential) + confidential.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            policyClickable,
            fullText.indexOf(policy),
            fullText.indexOf(policy)+ policy.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tv.run{
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    companion object{
        const val URL = "https://cdn.icon-icons.com/icons2/3276/PNG/512/slice_cake_birthday_cake_dessert_sweet_icon_207992.png"
        const val TAG = "text_changed"
    }
}




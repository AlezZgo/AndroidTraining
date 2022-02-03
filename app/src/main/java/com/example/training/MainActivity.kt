package com.example.training

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.text)

        val fullText = getString(R.string.agreement_full_text)
        val confidential = getString(R.string.confidential_enfo)
        val policy = getString(R.string.privacy_policy)

        val spannableString = SpannableString(fullText)

        val confidentialClickable = object : ClickableSpan(){
            override fun onClick(widget: View) {
                Snackbar.make(widget,"Go to link 1 ", Snackbar.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = getColor(R.color.purple_500)
            }

        }

        val policyClickable = object : ClickableSpan(){
            override fun onClick(widget: View) {
                Snackbar.make(widget,"Go to link 2 ", Snackbar.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = getColor(R.color.purple_500)
            }

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
}
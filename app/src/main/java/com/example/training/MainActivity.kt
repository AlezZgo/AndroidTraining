package com.example.training

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpannable()

        setImage()


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
        val tv = findViewById<TextView>(R.id.text)

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
    }
}


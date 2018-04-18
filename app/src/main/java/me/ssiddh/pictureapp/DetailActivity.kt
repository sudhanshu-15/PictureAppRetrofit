package me.ssiddh.pictureapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import me.ssiddh.pictureapp.models.Photo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val imageView = findViewById(R.id.imageView) as ImageView

        val photo = intent.getSerializableExtra(PHOTO) as Photo?

        photo?.webformatURL.let {
            Picasso.get().load(photo?.webformatURL).into(imageView)
        }

        imageView.setOnClickListener {
            finish()
        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}

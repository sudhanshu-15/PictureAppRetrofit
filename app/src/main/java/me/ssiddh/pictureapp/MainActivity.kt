package me.ssiddh.pictureapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.ssiddh.pictureapp.api.PhotoRetriver
import me.ssiddh.pictureapp.models.Photo
import me.ssiddh.pictureapp.models.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var photos: List<Photo>? = null
    var pictureAdapter: PictureAdapter? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        var retriever = PhotoRetriver()

        val callback = object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Error in Api call", t)
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.hits

                    pictureAdapter = PictureAdapter(this@MainActivity.photos!!,
                            this@MainActivity)
                    recyclerView.adapter = pictureAdapter
                }
            }
        }

        retriever.getPhotos(callback)


    }

    override fun onClick(p0: View?) {
        Log.d("MainActivity", "Image Clicked")
        val intent = Intent(this, DetailActivity::class.java)
        val holder = p0?.tag as PictureAdapter.PhotoViewHolder
        intent.putExtra(DetailActivity.PHOTO,
                pictureAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

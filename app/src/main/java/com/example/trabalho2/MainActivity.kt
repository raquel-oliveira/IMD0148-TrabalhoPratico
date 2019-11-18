package com.example.trabalho2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var retrofit: Retrofit? = null

    private var photos = mutableListOf<Photo>()
    private var adapter = PhotoAdapter(photos, this::onPhotoItemClick)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        getPhotos()
        initRecycleView()

    }

    fun initRecycleView() {
        rvPhotos.adapter = adapter
        val layoutManager = LinearLayoutManager(this)

        rvPhotos.layoutManager = layoutManager

        initSwipeDelete()
    }

    fun initSwipeDelete() {
        val swipe = object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                photos.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(rvPhotos)
    }

    private fun getPhotos(){
        var photoService: PhotoService = retrofit!!.create(PhotoService::class.java)
        var call: Call<List<Photo>> = photoService.getPhotos()

        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                /*var photos: List<Photo>? = null
                if(response.isSuccessful){
                    photos = response.body()
                    val adapter = PhotoAdapter(photos!!)
                    rvPhotos.adapter = adapter
                */
                if(response.isSuccessful){
                    photos.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun onPhotoItemClick(photo: Photo) {

        Toast.makeText(this, "TODO: change to notification", Toast.LENGTH_LONG).show()
    }
}
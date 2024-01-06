package com.example.spotify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myrecyclerView: RecyclerView
    lateinit var myadapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myrecyclerView = findViewById(R.id.recycleView)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)

        val retrofitdata = apiInterface.getData("eminem")

        retrofitdata.enqueue(object : Callback<MusicInfo?> {
            override fun onResponse(call: Call<MusicInfo?>, response: Response<MusicInfo?>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val dataList = body.data
                        if (dataList != null) {
                            myadapter = MyAdapter(this@MainActivity, dataList)
                            myrecyclerView.adapter = myadapter
                            myrecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                            Log.d("Tag: onResponse", "onResponse:" + response.body())
                        } else {
                            // Handle the case where dataList is null
                            Log.e("Tag: onResponse", "Data list is null")
                        }
                    } else {
                        // Handle the case where response body is null
                        Log.e("Tag: onResponse", "Response body is null")
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("Tag: onResponse", "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MusicInfo?>, t: Throwable) {
                Log.d("Tag: onFailure", "onFailure:" + t.message)
            }
        })
    }
}

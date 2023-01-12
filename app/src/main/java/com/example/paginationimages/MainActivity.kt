package com.example.paginationimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationimages.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val adapter = ImageAdapter()
    private var page : Int = 1
    var isScrolling = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNext.setOnClickListener{
             page++
             getImages()
        }
        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isScrolling && dy < 0){
                    isScrolling = false
                    page++
                    getImages()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }
        })



        binding.btnRequest.setOnClickListener { getImages() }
        binding.rvImages.adapter = adapter
    }
        private fun getImages(){
            App.api.getImages(q = binding.etRequest.text.toString(), page = page, per_page = 10).enqueue(
                object: retrofit2.Callback<PixModel>{
                    override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {

                        if (response.isSuccessful) {
                            adapter.addImage(response.body()!!.hits)
                        }

                    }

                    override fun onFailure(call: Call<PixModel>, t: Throwable) {

                    }

                }
            )

        }


}
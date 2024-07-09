package com.example.latihan_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.latihan_2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RVAdapter(this@MainActivity, arrayListOf())

        binding.rvMain.adapter = adapter
        binding.rvMain.setHasFixedSize(true)

        remoteGetUsers()
    }

    fun remoteGetUsers(){
        ApiClient.apiService.getUser().enqueue(object : Callback<UsersResponse>{
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>
            ) {
                if(response.isSuccessful){
                    val usersResponse = response.body()
                    val data = usersResponse?.data
                    if(data != null) {
                        setDataToAdapter(data)
                    } else {
                        Log.e("Error", "Response body is null or empty.")
                    }
                } else {
                    Log.e("Error", "Failed to fetch data: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("Error", "Failed to fetch data: ${t.message}")
            }
        })
    }

    fun setDataToAdapter(data: ArrayList<UserMode>){
        adapter.setData(data)
    }
}
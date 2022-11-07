package com.sasoftbd.machine_learing_ml_kit.zone_Panel.orderFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.sasoftbd.machine_learing_ml_kit.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class orderFragment : Fragment() {

    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: OrderAdapter
    lateinit var courseList: ArrayList<OrderModel>
    private var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var shimmerVal: ShimmerFrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        courseList = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order, container, false)
        courseRV = view.findViewById(R.id.orderRecycler)
        shimmerVal = view.findViewById(R.id.shimmerFrameLayout)
        shimmerVal.startShimmerAnimation()
        shimmerVal.visibility = View.VISIBLE
        var numbers: IntArray = intArrayOf(1)

        courseRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                numbers[0] = newState
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("dy=", "$dy")
                if (dy > 0 && (numbers[0] == 0 || numbers[0] == 2)) {
                    hideToolber()
                } else if (dy <= 8) {
                    showToolber()
                }
            }

        })

        getOrderDate()
        return view
    }


    //hide toolbar
    private fun showToolber() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    private fun hideToolber() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    private fun getOrderDate() {
        val retrofit = Retrofit.Builder().baseUrl("http://192.168.1.83:8080/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val retrofitApi = retrofit.create(APIService1::class.java)
        val call: Call<ArrayList<OrderModel>?>? = retrofitApi.getEmployee()

        call!!.enqueue(object : Callback<ArrayList<OrderModel>?> {
            override fun onResponse(
                call: Call<ArrayList<OrderModel>?>,
                response: Response<ArrayList<OrderModel>?>
            ) {
                if (response.isSuccessful) {
                    shimmerVal.startShimmerAnimation()
                    shimmerVal.visibility = View.GONE
                    courseList = response.body()!!
                }

                courseRV.visibility = View.VISIBLE
                courseRVAdapter = OrderAdapter(courseList)
                layoutManager = LinearLayoutManager(context)
                courseRV.layoutManager = layoutManager
                courseRV.adapter = courseRVAdapter

                courseRVAdapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        Toast.makeText(context, "clicked$position", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun onFailure(call: Call<ArrayList<OrderModel>?>, t: Throwable) {
                Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        shimmerVal.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        shimmerVal.stopShimmerAnimation()
    }


}
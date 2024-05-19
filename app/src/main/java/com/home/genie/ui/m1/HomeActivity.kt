package com.home.genie.ui.m1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.home.genie.R
import com.home.genie.databinding.ActivityHomeBinding
import com.home.genie.ui.m1.adapter.BeautyAdapter
import com.home.genie.ui.m1.adapter.BeautyModel
import com.home.genie.ui.m1.adapter.CleaningAdapter
import com.home.genie.ui.m1.adapter.ViewPagerAdapter
import java.util.Timer
import java.util.TimerTask

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    private lateinit var mViewPager: ViewPager
    private var currentPage = 0
    private var timer: Timer? = null
    private val DELAY_MS: Long = 2000 // Delay in milliseconds before task is to be executed
    private val PERIOD_MS: Long = 2000 // Time in milliseconds between successive task executions.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startAutoScroll()
        initControl()

    }
    private fun startAutoScroll() {
        val handler = android.os.Handler()
        val update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            binding.mViewPager2.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() { // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel() // Cancel timer on fragment destroy to prevent memory leaks
    }

    companion object {
        private const val NUM_PAGES = 6 /* Set the number of pages in your ViewPager adapter */
    }

    private fun initControl() {

        var lis3 = ArrayList<BeautyModel>()
        lis3.add(BeautyModel("Pain relief",R.drawable.beauty))
        lis3.add(BeautyModel("Stress relief",R.drawable.beauty))
        lis3.add(BeautyModel("Post workout",R.drawable.beauty))
        lis3.add(BeautyModel("body massage",R.drawable.beauty))



        var list = ArrayList<BeautyModel>()
        list.add(BeautyModel("Women Spa",R.drawable.beauty))
        list.add(BeautyModel("Men's Salon",R.drawable.beauty))
        list.add(BeautyModel("Ac Appliance",R.drawable.beauty))
        list.add(BeautyModel("Cleaning",R.drawable.beauty))
             list.add(BeautyModel("Electronics",R.drawable.beauty))
        list.add(BeautyModel("Water Purifier",R.drawable.beauty))


          var lis2 = ArrayList<BeautyModel>()
        lis2.add(BeautyModel("Ac Repair Services",R.drawable.beauty))
        lis2.add(BeautyModel("Water \nPurifier",R.drawable.beauty))
        lis2.add(BeautyModel("Chimney Repair",R.drawable.beauty))
        lis2.add(BeautyModel("Refridgerator Repair",R.drawable.beauty))
        lis2.add(BeautyModel("Washing Machine Repair",R.drawable.beauty))
        lis2.add(BeautyModel("Television Repair",R.drawable.beauty))

        binding.mViewPager2.adapter = ViewPagerAdapter(this, lis2)
        binding.mDotsIndicator.setViewPager(binding.mViewPager2)





        binding.rvBeauty.adapter = BeautyAdapter(list)
        binding.rvCleaning.adapter = CleaningAdapter(lis2)
        binding.rvMessage.adapter = CleaningAdapter(lis3)
    }
}
package com.example.radiate

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2

class OnboardingPages : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_pages)

        val items = listOf(
            OnboardingItemsDC(R.drawable.onboardingjournal,"Journal Your Emotions","Effortlessly track your moods and moments. Discover patterns and nurture self-awareness with our journal feature."),
            OnboardingItemsDC(R.drawable.onboardingmeditation,"Guided Meditation for Calm","Find peace with guided meditation and breathing exercises to relieve stress and enhance mindfulness."),
            OnboardingItemsDC(R.drawable.onboardingblogs,"Discover Mental Wellness Blogs","Explore curated blogs on mental health, packed with insights and tips to boost resilience and well-being."),
            OnboardingItemsDC(R.drawable.onboardinggroup,"Connect with Care","Join a supportive community to share and grow anonymously. Build meaningful connections without fear of judgment.")
        )

        val adapter = OnboardingAdapter(items)
        val viewpager = findViewById<ViewPager2>(R.id.viewPager)
        viewpager.adapter=adapter

        val nextBtn = findViewById<ImageButton>(R.id.imageBtnNext)

        nextBtn.setOnClickListener {
            if(viewpager.currentItem+1<items.size){
                viewpager.currentItem+=1
            }else{
                startActivity(Intent(this,SigninPage::class.java))
                finish()
            }
        }

        setUpIndicators(items.size)
        setCurrentIndicator(0)

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
                if(position == items.lastIndex){
                    nextBtn.setBackgroundResource(R.drawable.fab_button_background_tick)
                }else{
                    nextBtn.setBackgroundResource(R.drawable.fab_custom_background)
                }
            }
        })

    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = findViewById<LinearLayout>(R.id.indicatorLayout).childCount
        for (i in 0 until childCount){
            val imageView = findViewById<LinearLayout>(R.id.indicatorLayout).getChildAt(i) as ImageView
            if(i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.indicator_active))
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.indicator_inactive))
            }
        }
    }

    private fun setUpIndicators(count: Int) {
        val indicators = Array(count){ImageView(this)}
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        indicators.forEach { indicator->
            indicator.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.indicator_inactive))
            indicator.layoutParams = layoutParams
            findViewById<LinearLayout>(R.id.indicatorLayout).addView(indicator)
        }
    }
}
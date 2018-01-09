package com.example.shree.wlug;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class MainBoard extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {

    SliderLayout layout;
    HashMap<String,String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);
        hashMap=new HashMap<String,String>();
        layout= (SliderLayout) findViewById(R.id.slider);
        hashMap.put("President","http://colorsfx.com/android/files/splash1.png");
        hashMap.put("VicePresident","http://colorsfx.com/android/files/splash2.png");
        hashMap.put("Secretary","http://colorsfx.com/android/files/splash3.png");
        hashMap.put("CSD","http://colorsfx.com/android/files/splash4.png");
        for(String name:hashMap.keySet())
        {
            TextSliderView textSliderView=new TextSliderView(getApplicationContext());
            textSliderView.description(name)
                    .image(hashMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
            .setOnSliderClickListener(this);
            layout.addSlider(textSliderView);


        }
        layout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        layout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        layout.setCustomAnimation(new DescriptionAnimation());
        layout.setDuration(3000);
       layout.addOnPageChangeListener(this);

    }

    @Override
    protected void onStop()
    {
        layout.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}

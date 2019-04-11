package com.wangweiming.myapplication.view.activity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.wangweiming.myapplication.R;

public class Activity_welcome extends Activity {
    ImageView welcomeImg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qidong);
        welcomeImg = (ImageView) this.findViewById(R.id.qidong);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(100);// 设置动画显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }
    private class AnimationImpl implements Animation.AnimationListener
    {

        @Override

        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.bb);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }


        private void skip() {
            Intent intent = new Intent();
            intent.setClass(Activity_welcome.this, Activity_main.class);
            startActivity(intent);
        }
    }

}


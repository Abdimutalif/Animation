package uz.mobiler.animationg2122;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import uz.mobiler.animationg2122.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    private ActivityMainBinding binding;

    private boolean isImage1 = false;
    private boolean isImage2 = false;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.image1.setOnClickListener(v -> {
            count++;
            isImage1 = true;
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale1);
            binding.image1.startAnimation(animation);
            animation.setAnimationListener(this);
        });

        binding.image2.setOnClickListener(v -> {
            count++;
            isImage2 = true;
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale1);
            binding.image2.startAnimation(animation);
            animation.setAnimationListener(this);
        });


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale2);

        if (isImage1) {
            binding.image1.setImageResource(R.drawable.ic_launcher_foreground);
            binding.image1.startAnimation(animation1);
            isImage1 = false;
        } else if (isImage2) {
            binding.image2.setImageResource(R.drawable.ic_launcher_foreground);
            binding.image2.startAnimation(animation1);
            isImage2 = false;
        }

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (count == 2) {
                    if (binding.image1.getTag() == binding.image2.getTag()) {
                        binding.image1.setVisibility(View.INVISIBLE);
                        binding.image2.setVisibility(View.INVISIBLE);
                    } else {
                        closeImages();
                        count = 0;
                    }
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void closeImages() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale1);
        binding.image1.startAnimation(animation);
        binding.image2.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.image1.setImageResource(R.drawable.ic_launcher_background);
                binding.image2.setImageResource(R.drawable.ic_launcher_background);
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale2);
                binding.image1.startAnimation(animation1);
                binding.image2.startAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
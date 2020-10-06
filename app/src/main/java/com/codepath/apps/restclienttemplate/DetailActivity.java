package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DetailActivity extends AppCompatActivity {
    ImageView ivProfileImage;
    TextView tvName;
    TextView tvUsername;
    TextView tvTweet;
    TextView tvTime;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvTweet = findViewById(R.id.tvTweet);
        tvTime = findViewById(R.id.tvTime);
        tvDate = findViewById(R.id.tvDate);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvName.setText(tweet.user.name);
        tvUsername.setText("@" + tweet.user.username);
        tvTweet.setText(tweet.tweet);
        Glide.with(this).load(tweet.user.profilePicURL).transform(new RoundedCorners(25)).into(ivProfileImage);

        String date = tweet.createdAt;
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("mm/d/yy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        String newDate = "N/A";
        String newTime = "N/A";

        try {
            Date tweetDate = formatter.parse(date);
            newDate = dateFormatter.format(tweetDate);
            newTime = timeFormatter.format(tweetDate);
        } catch (ParseException e) {
            Log.e("!!!", "Could not parse date for details", e);
            e.printStackTrace();
        }

        tvTime.setText(newDate);
        tvDate.setText(newTime);
    }
}
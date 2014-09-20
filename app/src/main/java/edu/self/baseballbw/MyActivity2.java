package edu.self.baseballbw;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import com.peoplewave.peoplewaveapi.PeopleWaveAPI;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;

import java.util.logging.LogRecord;


public class MyActivity2 extends Activity {

    // private Button backButton;
    // private ImageView img = (ImageView) findViewById(R.id.ball);
    private TextView scoreText;
    private int delaySec;
    private final int MSG_TICK=0x01;
    private int point = 0;
    private int flg = 0;

    private PeopleWaveAPI peoplewave = new PeopleWaveAPI();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //for (int i = 0; i < 10; i++) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);


        handler.sendEmptyMessage(MSG_TICK);
        peoplewave.BW.connectAutomatic(0, null, this);

        delaySec = 0;
        Message ball3Message = new Message();
        ball3Message.what = 100;
        handler.sendMessageDelayed(ball3Message, delaySec);

        delaySec = 1100;
        Message ball2Message = new Message();
        ball2Message.what = 200;
        handler.sendMessageDelayed(ball2Message, delaySec);

        delaySec = 2200;
        Message ball1Message = new Message();
        ball1Message.what = 300;
        handler.sendMessageDelayed(ball1Message, delaySec);

        delaySec = 3300;
        Message startMessage = new Message();
        startMessage.what = 400;
        handler.sendMessageDelayed(startMessage, delaySec);


        for (int i = 0; i < 10; i++) {

            delaySec = i * 2200 + 4300;
            Message message = new Message();
            message.what = 500;
            handler.sendMessageDelayed(message, delaySec);

        }

        delaySec = 26400;
        Message endMessage = new Message();
        endMessage.what = 600;
        handler.sendMessageDelayed(endMessage, delaySec);

        delaySec = 27500;
        Message endFinishMessage = new Message();
        endFinishMessage.what = 700;
        handler.sendMessageDelayed(endFinishMessage, delaySec);

        delaySec = 27550;
        Message scoreMessage = new Message();
        scoreMessage.what = 800;
        handler.sendMessageDelayed(scoreMessage, delaySec);


        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public void execTime1() {
        ImageView ball1Img = (ImageView) findViewById(R.id.ball1);
        ball1Img.setVisibility(View.INVISIBLE);
        ScaleAnimation scale = new ScaleAnimation(1f, 1f, 1f, 1f);
        scale.setDuration(1000);
        ball1Img.startAnimation(scale);
    }

    public void execTime2() {
        ImageView ball2Img = (ImageView) findViewById(R.id.ball2);
        ball2Img.setVisibility(View.INVISIBLE);
        ScaleAnimation scale = new ScaleAnimation(1f, 1f, 1f, 1f);
        scale.setDuration(1000);
        ball2Img.startAnimation(scale);
    }

    //public ImageView ball3Img = (ImageView) findViewById(R.id.ball3);
    public void execTime3() {
        final ImageView ball3Img = (ImageView) findViewById(R.id.ball3);
        ball3Img.setVisibility(View.INVISIBLE);
        ScaleAnimation scale = new ScaleAnimation(1f, 1f, 1f, 1f);
        scale.setDuration(1000);
        ball3Img.startAnimation(scale);


    }

    public void execStart() {
        ImageView startImg = (ImageView) findViewById(R.id.startBall);
        startImg.setVisibility(View.INVISIBLE);
        ScaleAnimation scale = new ScaleAnimation(1f, 1f, 1f, 1f);
        scale.setDuration(1000);
        startImg.startAnimation(scale);
    }

    public void execAnimation() {
        if( flg > 0 )
            point++;
        flg = 0;

        ImageView img = (ImageView) findViewById(R.id.ball);
        img.setVisibility(View.INVISIBLE);
        ScaleAnimation scale = new ScaleAnimation(0.01f, 2f, 0.01f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new AccelerateInterpolator(5.0f));
        scale.setDuration(2000);
        img.startAnimation(scale);
    }

    public void endGame() {
        Log.d("EIJI", "Endgame");
        ImageView endImg = (ImageView) findViewById(R.id.endBall);
        endImg.setVisibility(View.VISIBLE);
        endImg.invalidate();

        ScaleAnimation scale = new ScaleAnimation(1f, 1f, 1f, 1f);
        scale.setDuration(1000);
        endImg.startAnimation(scale);
    }

    public void endFinish() {
        Log.d("EIJI", "Endfiinish");
        ImageView endImg = (ImageView) findViewById(R.id.endBall);
        endImg.setVisibility(View.INVISIBLE);
        endImg.invalidate();
        peoplewave.BW.disconnect();
    }

    public void scoreView() {
        Log.d("EIJI", "score");
        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        ImageView scoreBallImg = (ImageView) findViewById(R.id.scoreBall);
        scoreView.setVisibility(View.VISIBLE);
        scoreBallImg.setVisibility(View.VISIBLE);
        scoreView.invalidate();
        scoreBallImg.invalidate();
        scoreView.setText( String.format("あなたの得点は %d点 です", point));
    }

    private void tick() {

        int att = peoplewave.BW.getAttention();
        int med = peoplewave.BW.getMeditation();

        int score = att * med;
        if (score > 2000) {
            flg = 1;
        }

        //TextView timerText = (TextView) findViewById(R.id.timer);
        //timerText.setText( String.format("ATT:%d MED:%d", att, med));
        //Log.d("CHECK", "ATT:" + att + " MED:" + med);
    }

    /*
    private void countUp(int att, int med) {

        int wins = 0;
        int score = att * med;
        if (score > 3000) {
            wins += 1;
        }
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    execTime3();
                    break;

                case 200:
                    execTime2();
                    break;

                case 300:
                    execTime1();
                    break;

                case 400:
                    execStart();
                    break;

                case 500:
                    execAnimation();
                    break;

                case 600:
                    endGame();
                    break;

                case 700:
                    endFinish();
                    break;

                case 800:
                    scoreView();
                    break;

                case MSG_TICK:
                    tick();
                    sendEmptyMessageDelayed(MSG_TICK, 50);
                    break;
            }
        }

    };
}

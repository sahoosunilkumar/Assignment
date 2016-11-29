package com.myntra.assignment.memorygame;

/**
 * Created by sunilkumarsahoo on 11/14/16.
 */

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends Activity implements OnDragListener,
        OnItemLongClickListener, OnViewModifiedListener {

    private static final String TAG = GameActivity.class.getName();
    private GridView gridView;
    private ItemAdapter adapter;
    private TextView countdownTimerTV, titleTV;
    private Timer timer;
    private ProgressBar progressBar;
    private GameViewViewModel gameViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer(Constants.TOTAL_TICKER_TIME, Constants
                .TICKER_INTERVAL);
        gameViewModel = new GameViewViewModel(this);
        gridView = (GridView) findViewById(R.id.grid_view);
        adapter = new ItemAdapter(this, gameViewModel.getModelChangeListener
                ().onFetchGame().getDrawables(), getGridItemWidth(this));
        gridView.setAdapter(adapter);
        gridView.setColumnWidth(getGridItemWidth(this));
        countdownTimerTV = (TextView) findViewById(R.id.countdownTimerTV);
        titleTV = (TextView) findViewById(R.id.titleTV);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        gameViewModel.onStateChanged(Constants.State.INITIALIZING);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DROP:
                int destinationPosition = getItemAt(dragEvent.getX(),
                        dragEvent.getY());
                gameViewModel.onAnswerSelected(destinationPosition, (
                        (ViewGroup) view).getChildAt(destinationPosition));
                break;
        }
        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView gridView, View view,
                                   int position, long row) {
        ClipData.Item item = new ClipData.Item((String) view.getTag());
        ClipData clipData = new ClipData((CharSequence) view.getTag(),
                new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
        view.startDrag(clipData, new View.DragShadowBuilder(view), null, 0);
        if (position == gameViewModel.getModelChangeListener().onFetchGame()
                .getRandomPosition()) {
            gridView.setOnDragListener(GameActivity.this);
        }
        return true;
    }

    private void applyAnimation(View view) {

        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY",
                0.0f, 360f);
        animation.setDuration(1000);
        animation.setRepeatCount(1);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    private int getItemAt(float x, float y) {
        return gridView.pointToPosition((int) x, (int) y);
    }


    @Override
    public void onItemLongClickStateChanged(int state) {
        switch (state) {
            case Constants.LongClickState.ADD:
                gridView.setOnItemLongClickListener(this);
                break;
            case Constants.LongClickState.REMOVE:
                gridView.setOnItemLongClickListener(null);
                break;
        }

    }

    @Override
    public void onListItemUpdated() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void onTimerStatusChanged(int status) {
        switch (status) {
            case Constants.TimerStatus.START:
                startTimer();
                break;
            case Constants.TimerStatus.STOP:
                break;
        }
    }

    @Override
    public void changeStateTo(Constants.State state) {
        switch (state) {
            case RUNNING:
                titleTV.setText(R.string.place_image_hint);
                countdownTimerTV.setVisibility(View.INVISIBLE);
                gameViewModel.invalidate();
                onItemLongClickStateChanged(Constants.LongClickState.ADD);
                break;
        }
    }

    @Override
    public void onAnswerSelected(View view, boolean isCorrectAnswer) {
        if (isCorrectAnswer) {
            applyAnimation(view);
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    private void startTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleTV.setText(R.string.remaining_time);
                countdownTimerTV.setVisibility(View.VISIBLE);
                timer.start();
            }
        });

    }

    public int getGridItemWidth(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics
                (displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;
        return (int) ((screenHeight > screenWidth ? screenWidth :
                screenHeight) / 3.5);
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
        super.onDestroy();
    }

    class Timer extends CountDownTimer {
        public Timer(int totalTime, int tickInterval) {
            super(totalTime, tickInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            countdownTimerTV.setText("" + (int) millisUntilFinished /
                    Constants.TICKER_INTERVAL);
        }

        @Override
        public void onFinish() {
            gameViewModel.onStateChanged(Constants.State.RUNNING);
        }
    }
}

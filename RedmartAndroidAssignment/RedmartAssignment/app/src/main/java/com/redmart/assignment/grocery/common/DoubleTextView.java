package com.redmart.assignment.grocery.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redmart.assignment.grocery.R;

public class DoubleTextView extends LinearLayout {
    LinearLayout layout = null;
    TextView[] textView = new TextView[2];
    Context mContext = null;

    public DoubleTextView(Context context) {

        super(context);
        mContext = context;
    }

    public DoubleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DoubleText);

        String leftText = a.getString(R.styleable.DoubleText_text1);
        String rightText = a.getString(R.styleable.DoubleText_text2);
        String orientation = a.getString(R.styleable.DoubleText_orientation);
        leftText = leftText == null ? "" : leftText;

        rightText = rightText == null ? "" : rightText;

        String service = Context.LAYOUT_INFLATER_SERVICE;

        LayoutInflater li = (LayoutInflater) getContext().getSystemService(service);

        View view = li.inflate(R.layout.double_text, this, true);
        layout = (LinearLayout) view.findViewById(R.id.container);
        layout.setOrientation(!TextUtils.isEmpty(orientation) && orientation.equals("vertical") ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
        textView[0] = (TextView) layout.findViewById(R.id.left_text);
        textView[1] = (TextView) layout.findViewById(R.id.right_text);

        textView[0].setText(leftText);
        textView[1].setText(rightText);

        layout.invalidate();

        a.recycle();
    }

    public DoubleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @SuppressWarnings("unused")
    public void setText1(String text) {
        Log.d("TAG", "left text : "+text);
        textView[0].setText(text);
    }

    @SuppressWarnings("unused")
    public void setText2(String text) {
        Log.d("TAG", "right text : "+text);
        textView[1].setText(text);
    }

    @SuppressWarnings("unused")
    public String getText1() {
        return textView[0].getText().toString();
    }

    @SuppressWarnings("unused")
    public String getRightText() {
        return textView[1].getText().toString();
    }

}

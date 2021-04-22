package com.junxin.freepaykotlin.view;

/*
 * ：Created by z on 2020-09-04
 */

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.junxin.freepaykotlin.R;
import com.junxin.freepaykotlin.utils.DensityUtil;

import java.util.List;

/*
 * ：Created by zhuhaiwen on 2020-09-28
 */

public class SecTab extends RelativeLayout {


    public SecTab(Context context) {
        super(context);
        initView(context);
    }

    public SecTab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SecTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public interface SelectListener {
        void onSlec(int posi);
    }

    private SelectListener listener;

    public void setSelectedListener(SelectListener listener) {
        this.listener = listener;
    }

    /*
     *  供外部调用，改变此view的被选中条目
     */
    public void setSlect(int posi) {
        LinearLayout tvP = root.findViewById(R.id.tab_title_p);
        for (int i = 0; i < tvP.getChildCount(); i++) {
            TextView child = (TextView) tvP.getChildAt(i);
            if (i == posi) {
                selectedIndex = posi;
                setTvRed(child);
                doAnim(posi);
            } else {
                setTvBlack(child);
            }
        }
    }

    private View root;

    private void initView(Context context) {
        root = LayoutInflater.from(context).inflate(R.layout.sec_tab, this, true);
    }

    public void setItems(List tabs) {
        LinearLayout tvP = root.findViewById(R.id.tab_title_p);
        tvP.setWeightSum(tabs.size());
        for (int i = 0; i < tabs.size(); i++) {
            TextView child = new TextView(this.getContext());
            child.setText(tabs.get(i).toString());
            child.setTextSize(DensityUtil.sp2px(getContext(), 15));
            child.setGravity(Gravity.CENTER);

            tvP.addView(child);
            setTvBlack(child);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) child.getLayoutParams();
            //params.leftMargin = DensityUtil.dip2px(getContext(), 16);
            params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            child.setLayoutParams(params);

            int finalI = i;
            child.setOnClickListener(v -> {
                setSlect(finalI);

                selectedIndex = finalI;
                if (listener != null) {
                    listener.onSlec(finalI);
                }
            });
        }
    }

    private int selectedIndex = 0;

    private void doAnim(int position) {

        //1,先设置红线的左边距
//        LinearLayout tvP = root.findViewById(R.id.tab_title_p);
//        View firstChild = tvP.getChildAt(0);
        View line = root.findViewById(R.id.tab_line);
//        LinearLayout.LayoutParams lineParams = (LinearLayout.LayoutParams) line.getLayoutParams();
//        lineParams.leftMargin = (firstChild.getWidth() - line.getWidth()) / 2;
//        line.setLayoutParams(lineParams);

        int lineW = line.getWidth();
        //获取被选中标签的左边距值
        LinearLayout titleP = root.findViewById(R.id.tab_title_p);
        View firstChild = titleP.getChildAt(0);
        int lefMar = (firstChild.getWidth() - lineW) / 2;
        int dis = 0;
        //红线需要挪动的距离
        for (int i = 0; i <= position; i++) {
            if (i == 0) {
                //dis+=childParams.width / 2;
            } else {
                View preChild = titleP.getChildAt(i - 1);
//                LinearLayout.LayoutParams preChildParams = (LinearLayout.LayoutParams) preChild.getLayoutParams();
//                dis += preChildParams.width;
                dis += preChild.getWidth();
            }
        }
        View curChild = titleP.getChildAt(position);
        LinearLayout.LayoutParams curChildParams = (LinearLayout.LayoutParams) curChild.getLayoutParams();
        int lef = curChildParams.leftMargin;
        int w = curChild.getWidth();
        int toPosiX = dis + (w - lineW) / 2 + lef;
        int delX = toPosiX - lefMar;
        //改变红色横线的marginLet的值
        ObjectAnimator animX_right = ObjectAnimator.ofFloat(line, "translationX", delX);
//        animX_right.setInterpolator(new BounceInterpolator());
        animX_right.setDuration(150);
        animX_right.setRepeatCount(0);
        animX_right.start();
    }

    private void setTvBlack(TextView textView) {
        textView.setTextColor(getResources().getColor(R.color.app_color_999));
        textView.setTextSize(DensityUtil.sp2px(getContext(), 15));
    }

    private void setTvRed(TextView textView) {
        textView.setTextColor(getResources().getColor(R.color.app_color_F35));
        textView.setTextSize(DensityUtil.sp2px(getContext(), 16));
    }
}

package com.imooc.nick.cardtestproject.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imooc.nick.cardtestproject.R;
import com.imooc.nick.cardtestproject.Util.DeviceUtils;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


public class PraiseButton extends RelativeLayout {
    private static final String TAG = "PraiseButton";
    private boolean mIsPraised = false;  //是否是已点赞状态
    private static final int SPARK_DURATION = 40; //火花每张图切换默认0.04s
    private int currentSparkIndex = 0;
    private int praiseCount;  //点赞数
    private int[] sparkImages = {R.drawable.apk_ani_good1, R.drawable.apk_ani_good2, R.drawable.apk_ani_good3,
            R.drawable.apk_ani_good4, R.drawable.apk_ani_good5, R.drawable.apk_ani_good6, R.drawable.apk_ani_good7,
            R.drawable.apk_ani_good8, R.drawable.apk_ani_good9, R.drawable.apk_ani_good10, R.drawable.apk_ani_good11,
            R.drawable.apk_ani_good12};
    private Context mContext;
    private Handler mHandler;
    private Runnable mRunnable;
    private boolean mIsAnimating = false; //是否正在执行动画
    private String defaultText = "赞";

    private ImageView ivNoPraise, ivPraise; //分别是火花，未点赞状态，点赞状态
    private TextView tvPraiseCount; //点赞次数
    private ImageView imageView;
    private PopupWindow popupWindow;
    private int[] location = new int[2];
    private int animationHeight;//动画的高度
    private OnPraiseButtonClickListener mListener;

    public PraiseButton(Context context) {
        super(context);
        init(context);
    }

    public PraiseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        animationHeight = DeviceUtils.dip2px(context, 55);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentSparkIndex <= sparkImages.length - 1) {
                    imageView.setImageResource(sparkImages[currentSparkIndex]);
                    currentSparkIndex++;
                    mHandler.postDelayed(mRunnable, SPARK_DURATION);
                } else {
                    //隐藏并回到初始状态
                    mIsAnimating = false;
                    imageView.setVisibility(View.GONE);
                    currentSparkIndex = 0;
                    popupWindow.dismiss();
                    popupWindow=null;
                }
            }
        };
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_praise_button, this);
        ivNoPraise = (ImageView) contentView.findViewById(R.id.iv_no_praise);
        ivNoPraise.setVisibility(View.VISIBLE);
        ivPraise = (ImageView) contentView.findViewById(R.id.iv_praise);
        ivPraise.setVisibility(View.GONE);
        tvPraiseCount = (TextView) contentView.findViewById(R.id.tv_praise_count);
        tvPraiseCount.setHint(defaultText);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAnimating) {
                    //还在执行动画跳过点击
                    return;
                }
                if (mListener != null) {
                    boolean result = mListener.onClick(!mIsPraised);
                    if (result) {
                        //处理点击，并开始执行成功动画
                        handleClick();
                    } else {
                        //开始执行不成功
                        startFailedAnimation(mIsPraised);
                    }
                }
            }
        });
    }

    private void showPopupWindow(View view) {
        // 设置按钮的点击事件
        imageView = new ImageView(mContext);
        popupWindow = new PopupWindow(imageView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(false);
        popupWindow.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        ivPraise.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.TOP, location[0] - (animationHeight / 2) + (ivNoPraise.getMeasuredWidth() / 2), location[1] - (animationHeight / 2) + (ivNoPraise.getMeasuredHeight() / 2));
        mHandler.postDelayed(mRunnable, 160);
    }

    /**
     * 开始执行失败时动画
     */
    private void startFailedAnimation(boolean isPraised) {
        //停掉之前的动画
        stopAnimation();
        this.mIsAnimating = true;

        ImageView ivShow, ivHide;
        if (isPraised) {
            ivShow = ivPraise;
            ivHide = ivNoPraise;
        } else {
            ivShow = ivNoPraise;
            ivHide = ivPraise;
        }

        //点赞按钮动画 （用补间动画不知道为何会有问题）
        AnimatorSet praiseAnimatorSet = new AnimatorSet();
        ObjectAnimator s2x = ObjectAnimator.ofFloat(ivShow, "scaleX", 0.5f, 1.3f, 1.0f);
        s2x.setStartDelay(80);
        s2x.setDuration(240);
        ObjectAnimator s2y = ObjectAnimator.ofFloat(ivShow, "scaleY", 0.5f, 1.3f, 1.0f);
        s2y.setStartDelay(80);
        s2y.setDuration(240);
//        praiseAnimatorSet.setStartDelay(120);
        praiseAnimatorSet.playTogether(s2x, s2y);
        praiseAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsAnimating = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        ivShow.setVisibility(View.VISIBLE);
        ivHide.setVisibility(View.GONE);
        praiseAnimatorSet.start();
    }

    /**
     * 开始点赞动画，取消点赞无动画用setPraiseState(boolean mIsPraised)
     */
    private void startPraiseAnimation() {
        //停掉之前的动画
        stopAnimation();
        this.mIsAnimating = true;

        //未点赞按钮动画
        AnimationSet NoPraiseAnimationSet = new AnimationSet(false);
        NoPraiseAnimationSet.setFillAfter(true);
        ScaleAnimation s1 = new ScaleAnimation(0.9f, 0.3f, 0.9f, 0.3f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        s1.setDuration(80);
        s1.setInterpolator(new AccelerateDecelerateInterpolator());
        NoPraiseAnimationSet.addAnimation(s1);
        AlphaAnimation a1 = new AlphaAnimation(1.0f, 0.0f);
        a1.setDuration(160);
        a1.setInterpolator(new DecelerateInterpolator());
        NoPraiseAnimationSet.addAnimation(a1);

        //点赞按钮动画 （用补间动画不知道为何会有问题）
        AnimatorSet praiseAnimatorSet = new AnimatorSet();
        ObjectAnimator s2x = ObjectAnimator.ofFloat(ivPraise, "scaleX", 0.5f, 1.3f, 1.0f);
        s2x.setStartDelay(80);
        s2x.setDuration(240);
        ObjectAnimator s2y = ObjectAnimator.ofFloat(ivPraise, "scaleY", 0.5f, 1.3f, 1.0f);
        s2y.setStartDelay(80);
        s2y.setDuration(240);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(ivPraise, "alpha", 0.0f, 1.0f);
        a2.setDuration(200);
        a2.setInterpolator(new DecelerateInterpolator());
//        praiseAnimatorSet.setStartDelay(120);
        praiseAnimatorSet.playTogether(s2x, s2y, a2);

        ivNoPraise.setVisibility(View.VISIBLE);
        ivPraise.setVisibility(View.VISIBLE);
        //执行动画
        ivNoPraise.startAnimation(NoPraiseAnimationSet);
        praiseAnimatorSet.start();
        showPopupWindow(ivPraise);
    }

    /**
     * 设置点赞状态，不做动画
     *
     * @param isPraised 是否点赞
     */
    public void setPraiseState(boolean isPraised) {
        this.mIsPraised = isPraised;
        stopAnimation();
        if (isPraised) {
            ivPraise.setVisibility(View.VISIBLE);
            ivNoPraise.setVisibility(View.GONE);
        } else {
            ivPraise.setVisibility(View.GONE);
            ivNoPraise.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置点赞次数
     *
     * @param praiseCount
     */
    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
        if (praiseCount <= 0) {
            tvPraiseCount.setText("");
        } else if (praiseCount >= 10000) {
            tvPraiseCount.setText((praiseCount / 10000) + "万");
        } else {
            tvPraiseCount.setText(String.valueOf(praiseCount));
        }
    }

    /**
     * 停止动画
     */
    public void stopAnimation() {
        ivPraise.clearAnimation();
        ivNoPraise.clearAnimation();
        currentSparkIndex = 0;
        mIsAnimating = false;
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 处理按钮点击，当前是已点赞状态则取消点赞，当前是未点赞状态则展示点赞动画
     */
    private void handleClick() {
        if (mIsAnimating) {
            //还在执行动画跳过点击
            return;
        }
        setPraiseState(!mIsPraised);
        if (!mIsPraised) {
            praiseCount--;
            setPraiseCount(praiseCount);
        } else {
            praiseCount++;
            setPraiseCount(praiseCount);
            tvPraiseCount.post(new Runnable() {
                @Override
                public void run() {
                    startPraiseAnimation();
                }
            });
        }
    }

    public void setOnPraiseButtonListener(OnPraiseButtonClickListener listener) {
        this.mListener = listener;
    }

    public interface OnPraiseButtonClickListener {
        /**
         * @param isPraised 是否是点赞动作
         * @return 点赞按钮是否需要做UI变化
         */
        boolean onClick(boolean isPraised);
    }

    public TextView getTvPraiseCount() {
        return tvPraiseCount;
    }
}

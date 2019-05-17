package com.imooc.nick.cardtestproject.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.imooc.nick.cardtestproject.R;

public class TipView extends FrameLayout {

	private LinearLayout mBobyLayout;
	private ImageView imageView;

	private TextView textView;

	private ProgressBar progressBar;

	private Button button;

	public TipView(Context context) {
		super(context);
		init();
	}

	public TipView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TipView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void init() {
		LayoutInflater.from(getContext())
				.inflate(R.layout.widget_tip_view, this);
		mBobyLayout = (LinearLayout) findViewById(R.id.tip_body_layout);
		imageView = (ImageView) findViewById(R.id.tip_icon);
		textView = (TextView) findViewById(R.id.tip_message);
		progressBar = (ProgressBar)findViewById(R.id.progress);
		button = (Button) findViewById(R.id.tip_button);
        setBgColorRes(R.color.color_f5f5f5);
	}

    public void setBgColorRes(int color) {
        setBackgroundColor(getResources().getColor(color));
    }

    public void setTipIcon(int resId) {
		if (resId != 0) {
			try {
                if (getVisibility() != VISIBLE) {
                    setVisibility(VISIBLE);
                }
                imageView.setImageResource(resId);
            } catch (Throwable e) {
			}
			imageView.setVisibility(VISIBLE);
			setClickable(true);
			progressBar.setVisibility(GONE);
		}
	}

    /**
     * @param show true 显示加载的布局
     *             false 会隐藏TipView里面的控件，但是并没有让TipView 隐藏掉，如果想要整个TipView隐藏，可以直接调用 hideView
     */
    public void setLoadingData(boolean show){
        if (show && getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
        imageView.setVisibility(GONE);
		button.setVisibility(GONE);
		setClickable(show);
		if(show) {
			progressBar.setVisibility(VISIBLE);
			setTipMessage(R.string.loading);
		}else{
			progressBar.setVisibility(GONE);
			setTipMessage("");
		}
	}

	public void hideView(){
        setVisibility(GONE);
    }

	public void showFailure(int iconResId, int msgResId) {
		setTipIcon(iconResId);
		setTipMessage(msgResId);
		setButtonText(R.string.refresh);
		showButton(true);
	}

	public void setTextColor(int resId) {
		if(resId != 0) {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
            textView.setTextColor(resId);
        }
	}

	public void setTextSize(float size) {
		if(size != 0) {
			textView.setTextSize(size);
		}
	}

	public void setTipMessage(int resId) {
		if(resId != 0) {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
			textView.setText(getContext().getString(resId));
		}
	}

	public void setTipMessage(String text) {
        if (!TextUtils.isEmpty(text) && getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
		textView.setText(text);
	}

	public void setButtonBackground(int resId) {
		if(resId != 0) {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
            button.setBackgroundResource(resId);
        }
	}

	public void setButtonTextColor(int resId) {
		if(resId != 0) {
			if (getVisibility() != VISIBLE) {
				setVisibility(VISIBLE);
			}
			button.setTextColor(resId);
		}
	}

	public void setButtonText(int resId) {
		if(resId != 0) {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
            button.setText(resId);
        }
	}

	public void setButtonText(String text) {
        if (!TextUtils.isEmpty(text) && getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
		button.setText(text);
	}

	public void showButton(boolean show) {
        if (show && getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
		button.setVisibility(show ? VISIBLE : GONE);
	}

	public void setClickListener(OnClickListener listener) {
		button.setOnClickListener(listener);
	}

	@Override
	public void setOnClickListener(OnClickListener listener) {
		setClickListener(listener);
	}

}

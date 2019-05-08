package nickgao.com.SlidingTabLayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nickgao.com.emotionrain.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * To be used with ViewPager to provide a tab indicator component which give constant feedback as to
 * the user's scroll progress.
 * <p/>
 * To use the component, simply add it to your view hierarchy. Then in your
 * {@link android.app.Activity} or {@link android.support.v4.app.Fragment} call
 * {@link #setViewPager(ViewPager)} providing it the ViewPager this layout is being used for.
 * <p/>
 * The colors can be customized in two ways. The first and simplest is to provide an array of colors
 * via {@link #setSelectedIndicatorColors(int...)} and {@link #setDividerColors(int...)}. The
 * alternative is via the {@link TabColorizer} interface which provides you complete control over
 * which color is used for any individual position.
 * <p/>
 * The views used as tabs can be customized by calling {@link #setCustomTabView(int, int)},
 * providing the layout ID of your custom layout.
 */
public class HomeSlidingTabLayout extends HorizontalScrollView {

    private boolean isDrawDiver;

    /**
     * Allows complete control over the colors drawn in the tab layout. Set with
     * {@link #setCustomTabColorizer(TabColorizer)}.
     */
    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

        /**
         * @return return the color of the divider drawn to the right of {@code position}.
         */
        int getDividerColor(int position);

    }

    public static final int LAYOUT_STYLE_DEFAULT = 0;
    public static final int LAYOUT_STYLE_ALIGN_CENTER = 1;
    public static final int LAYOUT_STYLE_AVERAGE = 2;
    public static final int LAYOUT_STYLE_CUST = 3;//自定义布局

    Context mContext;
    /**
     * text size
     */
    private float slidingTabTextSize = 32;

    private float slidingTabTextSelectSize = 38;

    /**
     * 文字颜色 black_A
     */
    private int slidingTabTextColor = Color.parseColor("#303030");

    /**
     * 文字选中颜色 red_a
     */
    private int slidingTabTextSelectColor = Color.parseColor("#ff87a0");

    /**
     * tab 背景
     */
    private int slidingTabTextBackground = R.color.trans_color;
    /**
     * 底部标签颜色 red_a
     */
    private int slidingTabIndicatorColor = Color.parseColor("#ff87a0");

    /**
     * 底部标签高度 dp
     */
    private float slidingTabIndicatorHight = 2;

    /**
     * 分割线颜色 透明
     */
    private int slidingTabDividerColor = Color.TRANSPARENT;

    /**
     * 控件样式
     * 默认  LAYOUT_STYLE_DEFAULT = 0
     * 居中  LAYOUT_STYLE_ALIGN_CENTER = 1
     * 均分  LAYOUT_STYLE_AVERAGE = 2
     */
    private int slidingTabStyle = LAYOUT_STYLE_DEFAULT;

    /**
     * 居中样式，最大显示个数
     */
    private float countPerScreen = 4.5F;

    private static final int TITLE_OFFSET_DIPS = 24;

    private int mTitleOffset;

    private int mTabViewLayoutId;
    private int mTabViewTextViewId;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;
    private onItemClick onItemClickListener;

    private final HomeSlidingTabStrip mTabStrip;

    private int mCurrentPosition = -1;

    public HomeSlidingTabLayout(Context context) {
        this(context, null);
    }

    public HomeSlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeSlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.HomeSlidingTabLayout);
        this.slidingTabTextSize = t.getDimension(R.styleable.HomeSlidingTabLayout_HomeSlidingTabTextSize, slidingTabTextSize);
        this.slidingTabTextSelectSize = t.getDimension(R.styleable.HomeSlidingTabLayout_HomeSlidingTabTextSelectSize, slidingTabTextSelectSize);
        this.slidingTabTextColor = t.getColor(R.styleable.HomeSlidingTabLayout_HomeSlidingTabTextColor, slidingTabTextColor);
        this.slidingTabTextSelectColor = t.getColor(R.styleable.HomeSlidingTabLayout_HomeSlidingTabTextSelectColor, slidingTabTextSelectColor);
        this.slidingTabTextBackground = t.getResourceId(R.styleable.HomeSlidingTabLayout_HomeSlidingTabTextBackground, slidingTabTextBackground);
        this.slidingTabIndicatorColor = t.getColor(R.styleable.HomeSlidingTabLayout_HomeSlidingTabIndicatorColor, slidingTabIndicatorColor);
        this.slidingTabDividerColor = t.getColor(R.styleable.HomeSlidingTabLayout_HomeSlidingTabDividerColor, slidingTabDividerColor);
        this.slidingTabStyle = t.getInt(R.styleable.HomeSlidingTabLayout_HomeSlidingTabStyle, slidingTabStyle);
        this.countPerScreen = t.getFloat(R.styleable.HomeSlidingTabLayout_HomeCountPerScreen, countPerScreen);
        this.slidingTabIndicatorHight = t.getDimension(R.styleable.HomeSlidingTabLayout_HomeSlidingTabIndicatorHight, slidingTabIndicatorHight);
        t.recycle();
        slidingTabTextColor = R.color.black_at;
        slidingTabTextSelectColor = R.color.red_b;
        // Disable the Scroll Bar
        setHorizontalScrollBarEnabled(false);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);
        mTabStrip = new HomeSlidingTabStrip(context);
        addView(mTabStrip, MATCH_PARENT, MATCH_PARENT);
        mTabStrip.setCustomTabColorizer(new TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return slidingTabIndicatorColor;
            }

            @Override
            public int getDividerColor(int position) {
                return slidingTabDividerColor;
            }
        });
        mTabStrip.setBottomBorderThickness(dpToPx(slidingTabIndicatorHight));
    }

    /**
     * Set the custom {@link TabColorizer} to be used.
     * <p/>
     * If you only require simple custmisation then you can use
     * {@link #setSelectedIndicatorColors(int...)} and {@link #setDividerColors(int...)} to achieve
     * similar effects.
     */
    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        mTabStrip.setCustomTabColorizer(tabColorizer);
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setSelectedIndicatorColors(int... colors) {
        mTabStrip.setSelectedIndicatorColors(colors);
    }

    /**
     * Sets the colors to be used for tab dividers. These colors are treated as a circular array.
     * Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setDividerColors(int... colors) {
        mTabStrip.setDividerColors(colors);
    }

    /**
     * Set the {@link ViewPager.OnPageChangeListener}. When using {@link HomeSlidingTabLayout} you are
     * required to set any {@link ViewPager.OnPageChangeListener} through this method. This is so
     * that the layout can update it's scroll position correctly.
     *
     * @see ViewPager#setOnPageChangeListener(ViewPager.OnPageChangeListener)
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPagerPageChangeListener = listener;
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId  id of the {@link TextView} in the inflated view
     */
    public void setCustomTabView(int layoutResId, int textViewId) {
        mTabViewLayoutId = layoutResId;
        mTabViewTextViewId = textViewId;
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager) {
        mTabStrip.removeAllViews();

        mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.clearOnPageChangeListeners();
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * {@link #setCustomTabView(int, int)}.
     */
    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
//        textView.setTypeface(Typeface.DEFAULT_BOLD);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // If we're running on ICS or newer, enable all-caps to match the Action Bar tab style
            textView.setAllCaps(true);
        }
        return textView;
    }

    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();

        for (int i = 0; i < adapter.getCount(); i++) {
            View tabView = null;
            TextView tabTitleView = null;

            if (mTabViewLayoutId != 0) {
                // If there is a custom tab view layout id set, try and inflate it
                tabView = ((Activity)mContext).getLayoutInflater().inflate(mTabViewLayoutId, mTabStrip, false);
                tabTitleView = (TextView) tabView.findViewById(mTabViewTextViewId);
            }

            if (tabView == null) {
                tabView = createDefaultTabView(getContext());
            }

            if (tabTitleView == null && TextView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
            }

            tabTitleView.setText(adapter.getPageTitle(i));
            tabView.setOnClickListener(tabClickListener);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT);
            switch (slidingTabStyle) {
                case LAYOUT_STYLE_ALIGN_CENTER:
                    params.width = (int) (mContext.getResources().getDisplayMetrics().widthPixels / countPerScreen);
                    break;
                case LAYOUT_STYLE_AVERAGE:
                    params.width = 0;
                    params.weight = 1.0f;
                    params.height = MATCH_PARENT;
                    break;
            }
            params.setMargins(0, 0, 0, dpToPx(slidingTabIndicatorHight));
            if (mTabViewLayoutId == 0) {
                tabView.setLayoutParams(params);
                tabTitleView.setGravity(Gravity.CENTER);
            } else {
            }
            tabTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, slidingTabTextSize);
            tabView.setBackgroundResource(slidingTabTextBackground);
            mTabStrip.addView(tabView);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mViewPager != null) {
            scrollToTab(mViewPager.getCurrentItem(), 0);
        }
    }

    private void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            if (positionOffset == 0) {
                try {
                    ((TextView) selectedChild).setTextSize(TypedValue.COMPLEX_UNIT_PX, slidingTabTextSelectSize);
                    if (mCurrentPosition >= 0 && mCurrentPosition != tabIndex) {
                        ((TextView) mTabStrip.getChildAt(mCurrentPosition)).setTextSize(TypedValue.COMPLEX_UNIT_PX, slidingTabTextSize);
                    }
                    mCurrentPosition = tabIndex;
                } catch (Exception e) {
                    Log.e("SlidingTabLayout", "type error!");
                }
            }
            int targetScrollX = selectedChild.getLeft() + positionOffset;
            if (tabIndex > 0 || positionOffset > 0) {
                switch (slidingTabStyle) {
                    case LAYOUT_STYLE_ALIGN_CENTER:
                        mTitleOffset = (mContext.getResources().getDisplayMetrics().widthPixels - selectedChild.getWidth()) / 2;
                        break;
                    case LAYOUT_STYLE_CUST:
                        mTitleOffset = (mContext.getResources().getDisplayMetrics().widthPixels - selectedChild.getWidth()) / 2;
                        break;
                    case LAYOUT_STYLE_AVERAGE:
//                        break;
                    case LAYOUT_STYLE_DEFAULT:
                    default:
                        mTitleOffset = dpToPx(TITLE_OFFSET_DIPS);
                }
                // If we're not at the first child and are mid-scroll, make sure we obey the offset
                targetScrollX -= mTitleOffset;
            }
            scrollTo(targetScrollX, 0);
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }
            mTabStrip.onViewPagerPageChanged(position, positionOffset);
            View selectedTitle = mTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth())
                    : 0;
            scrollToTab(position, extraOffset);
            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;
            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                mTabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }
        }
    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    if (mCurrentPosition == i) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onSameItemClickListener(i);
                        }
                        mViewPager.setCurrentItem(i, false);
                    } else {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClickListener(i);
                        }
                        mViewPager.setCurrentItem(i, false);
                    }
                    return;
                }
            }
        }
    }

    private int dpToPx(float dps) {
        return Math.round(this.getResources().getDisplayMetrics().density * dps);
    }

    /**
     * 分类的每项点击事件
     *
     * @param onItemClickListener
     */
    public void setTabOnClickListener(onItemClick onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setIsDrawDiver(boolean isDrawDiver) {
        this.isDrawDiver = isDrawDiver;
        if (mTabStrip != null) {
            mTabStrip.setIsDrawDiver(isDrawDiver);
        }
    }

    public interface onItemClick {
        void onSameItemClickListener(int position);//在相当tab的时候点击tab

        void onItemClickListener(int position);//在别的tab的时候点击一个别的tab
    }
}
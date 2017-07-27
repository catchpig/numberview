package com.zhuazhu.numberview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 创建时间:2017/7/27 14:54<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/7/27 14:54<br/>
 * 描述:
 */

public class NumberView extends LinearLayout implements View.OnClickListener{
    private TextView mOne;
    private TextView mTwo;
    private TextView mThree;
    private TextView mFour;
    private TextView mFive;
    private TextView mSix;
    private TextView mSeven;
    private TextView mEight;
    private TextView mNine;
    private TextView mZero;
    private LinearLayout mBackspace;
    private ImageView mBackspaceImg;
    private LinearLayout mUnknown;
    private TextView mUnknownTxt;
    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_number,this);
        initView();
        onClickListener();
    }
    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化控件
     */
    public void initView(){
        mOne =  findViewById(R.id.one);
        mTwo =  findViewById(R.id.two);
        mThree =  findViewById(R.id.three);
        mFour =  findViewById(R.id.four);
        mFive =  findViewById(R.id.five);
        mSix =  findViewById(R.id.six);
        mSeven =  findViewById(R.id.seven);
        mEight =  findViewById(R.id.eight);
        mNine =  findViewById(R.id.nine);
        mZero =  findViewById(R.id.zero);
        mBackspace = findViewById(R.id.backspace);
        mBackspaceImg = findViewById(R.id.backspace_img);
        mUnknown = findViewById(R.id.unknown);
        mUnknownTxt = findViewById(R.id.unknown_txt);
    }
    /**
     * 设置点击监听事件
     */
    public void onClickListener(){

        //数字监听
        mOne.setOnClickListener(this);
        mTwo.setOnClickListener(this);
        mThree.setOnClickListener(this);
        mFour.setOnClickListener(this);
        mFive.setOnClickListener(this);
        mSix.setOnClickListener(this);
        mSeven.setOnClickListener(this);
        mEight.setOnClickListener(this);
        mNine.setOnClickListener(this);
        mZero.setOnClickListener(this);

        //退格监听
        mBackspace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnBackSpaceListener!=null){
                    mOnBackSpaceListener.onBackSpace();
                }
            }
        });

        //未知按钮监听
        mUnknown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnCustomListener!=null){
                    mOnCustomListener.onCustom();
                }
            }
        });
    }

    /**
     * 设置自定义按钮显示/隐藏
     * @param visible
     */
    public void setCustomTextVisible(int visible){
        if(VISIBLE==visible){
            mUnknown.setBackgroundResource(R.drawable.selector);
        }else{
            mUnknown.setBackgroundResource(R.color.c_dedede);
        }
        mUnknownTxt.setVisibility(VISIBLE);
    }
    /**
     *  设置定义按钮的文字
     * @param text
     */
    public void setCustomText(String text){
        mUnknownTxt.setText(text);

    }

    /**
     * 设置自定义按钮的字体大小(默认15sp)
     * @param size
     */
    public void setCustomTextSize(float size){
        mUnknownTxt.setTextSize(size);
    }

    /**
     * 设置自定义按钮文字的颜色(默认#333333)
     * @param color
     */
    public void setCustomTextColor(int color){
        mUnknownTxt.setTextColor(color);
    }
    /**
     * 设置数字字体大小
     * @param textSize
     */
    public void setNumberTextSize(float textSize){
        mOne.setTextSize(textSize);
        mTwo.setTextSize(textSize);
        mThree.setTextSize(textSize);
        mFour.setTextSize(textSize);
        mFive.setTextSize(textSize);
        mSix.setTextSize(textSize);
        mSeven.setTextSize(textSize);
        mEight.setTextSize(textSize);
        mNine.setTextSize(textSize);
        mZero.setTextSize(textSize);
    }

    /**
     * 设置退格图片
     * @param mipmap
     */
    public void setBackspaceImage(int mipmap){
        mBackspaceImg.setImageResource(mipmap);
    }
    private OnNumberListener mOnNumberListener;

    /**
     * 监听数字键盘
     * @param listener
     */
    public void setOnNumberListener(OnNumberListener listener){
        this.mOnNumberListener = listener;
    }


    private OnBackSpaceListener mOnBackSpaceListener;

    /**
     * 监听退格
     * @param listener
     */
    public void setOnBackSpaceListener(OnBackSpaceListener listener){
        this.mOnBackSpaceListener = listener;
    }

    private OnCustomListener mOnCustomListener;

    /**
     * 监听自定义按钮
     * @param listener
     */
    public void setOnCustomListener(OnCustomListener listener){
        this.mOnCustomListener = listener;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String txt = "";
        if(id==R.id.one){
            txt = "1";
        }else if(id==R.id.two){
            txt = "2";
        }else if(id==R.id.three){
            txt = "3";
        }else if(id==R.id.four){
            txt = "4";
        }else if(id==R.id.five){
            txt = "5";
        }else if(id==R.id.six){
            txt = "6";
        }else if(id==R.id.seven){
            txt = "7";
        }else if(id==R.id.eight){
            txt = "8";
        }else if(id==R.id.nine){
            txt = "9";
        }else if(id==R.id.zero){
            txt = "0";
        }
        if(mOnNumberListener!=null){
            mOnNumberListener.onNumber(txt);
        }
    }

    /**
     * 左下角自定义按钮监听
     */
    public interface OnCustomListener{
        void onCustom();
    }
    /**
     * 退格回调
     */
    public interface OnBackSpaceListener{
        void onBackSpace();
    }

    /**
     * 数字键盘回调
     */
    public interface OnNumberListener{
        void onNumber(String number);
    }
}

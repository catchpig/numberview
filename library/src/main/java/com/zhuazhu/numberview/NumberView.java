package com.zhuazhu.numberview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 创建时间:2017/7/27 14:54<br/>
 * 创建人: 李涛<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2017/8/4 <br/>
 * 描述: 删除键长按清空，初始化 OnNumber,OnCustum,OnBack，如覆盖，请重新set.OnMoney失效
 *       不覆盖OnNumber,OnCustum,OnBack，需setOnMoney
 */

public class NumberView extends LinearLayout implements View.OnClickListener,View.OnLongClickListener{
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

    /**
     * 最大数额位数，5位即99999.99
     */
    private int maxLength = 5;

    /**
     * 是否为整数 默认为false
     */
    private boolean isDecimalPoint = false;

    /**
     * 数字键盘输入金额值
     */
    private String moneyValue = "";
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
        mBackspace.setOnLongClickListener(this);

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
     * 设置自定义按钮背景颜色
     * @param color
     */
    public void setCustomBackgroudColor(int color){
        mUnknown.setBackgroundColor(color);
    }

    /**
     * 设置自定义按钮背景颜色
     * @param colreRes
     * @deprecated 改变自定义按钮文字和设置自定义按钮的图片会将键盘改为整数键盘，如需小数，请手动更改
     */
    public void setCustomBackgroudResource(int colreRes){
        mUnknown.setBackgroundResource(colreRes);
        isDecimalPoint = true;
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
     * @deprecated 改变自定义按钮文字和设置自定义按钮的图片会将键盘改为整数键盘，如需小数，请手动更改
     */
    public void setCustomText(String text){
        mUnknownTxt.setText(text);
        isDecimalPoint = true;
    }

    /**
     * 设置是否为整数
     * 改变自定义按钮文字和设置自定义按钮的图片会将键盘改为整数键盘，如需小数，请手动更改
     * @param decimalPoint
     *
     */
    public void setNumberDecimalPoint(boolean decimalPoint){
        isDecimalPoint = decimalPoint;
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
     * @deprecated 大小为SP
     */
    public void setNumberTextSize(float textSize){
        mOne.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mTwo.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mThree.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mFour.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mFive.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mSix.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mSeven.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mEight.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mNine.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        mZero.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
    }

    /**
     * 设置字体大小
     * @param unit 大小单位 TypedValue.SP_DP_PX
     * @param textSize 字体大小
     * @deprecated  已隔绝其他单位
     */
    public void setNumberTextSize(int unit , float textSize){
        if(unit == TypedValue.COMPLEX_UNIT_SP || unit == TypedValue.COMPLEX_UNIT_DIP  || unit == TypedValue.COMPLEX_UNIT_PX) {
            mOne.setTextSize(unit, textSize);
            mTwo.setTextSize(unit, textSize);
            mThree.setTextSize(unit, textSize);
            mFour.setTextSize(unit, textSize);
            mFive.setTextSize(unit, textSize);
            mSix.setTextSize(unit, textSize);
            mSeven.setTextSize(unit, textSize);
            mEight.setTextSize(unit, textSize);
            mNine.setTextSize(unit, textSize);
            mZero.setTextSize(unit, textSize);
        }
    }

    public void setMaxLength(int length){
        this.maxLength = length;
    }

    /**
     * 设置退格图片
     * @param mipmap
     */
    public void setBackspaceImage(int mipmap){
        mBackspaceImg.setImageResource(mipmap);
    }
    private OnNumberListener mOnNumberListener = new OnNumberListener() {
        @Override
        public void onNumber(String number) {
            updateMoneyValue(number,1);
        }
    };

    /**
     * 监听数字键盘
     * @param listener
     */
    public void setOnNumberListener(OnNumberListener listener){
        this.mOnNumberListener = listener;
    }


    private OnBackSpaceListener mOnBackSpaceListener = new OnBackSpaceListener() {
        @Override
        public void onBackSpace() {
            updateMoneyValue("删除",11);
        }
    };

    /**
     * 监听退格
     * @param listener
     */
    public void setOnBackSpaceListener(OnBackSpaceListener listener){
        this.mOnBackSpaceListener = listener;
    }

    private OnCustomListener mOnCustomListener = new OnCustomListener() {
        @Override
        public void onCustom() {
            if(!isDecimalPoint) {
                updateMoneyValue(".", 12);
            }
        }
    };

    /**
     * 监听自定义按钮
     * @param listener
     */
    public void setOnCustomListener(OnCustomListener listener){
        this.mOnCustomListener = listener;
    }

    private OnMoneyListener moneyListener;

    /**
     *  监听金额输入
     * @param listener
     */
    public void setOnMoneyListener(OnMoneyListener listener){
        this.moneyListener = listener;
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

    @Override
    public boolean onLongClick(View view) {
        if(view.getId() == R.id.backspace){
            updateMoneyValue("清空",13);
            return true;
        }
        return false;
    }

    /**
     * 根据传递的值来改变 金额
     * @param code 标签 13-清空 11删除 12小数点
     * @param title 数额
     */
    private void updateMoneyValue(String title,int code){
        if(moneyValue.contains(".")){
            if(code == 12){
                return;
            }

            if(code < 10) {
                if (".".equals(String.valueOf(moneyValue.charAt(moneyValue.length() - 1)))) {
                    moneyValue = moneyValue + title;
                } else if(".".equals(String.valueOf(moneyValue.charAt(moneyValue.length() - 2)))){
                    moneyValue = moneyValue + title;
                } else if(".".equals(String.valueOf(moneyValue.charAt(moneyValue.length() - 3)))){

                }
                moneyListener.onMoneyValue(moneyValue);
                return;
            }
        }
        if("0".equals(moneyValue)){
            if(code == 11){
                return;
            }
            if(code == 13){
                return;
            }
            if(code == 12){
                moneyValue = moneyValue + title;
            }else {
                moneyValue = title;
            }
        }else if(code == 11){
            if(moneyValue.length() == 1){
                if("0".equals(moneyValue)){
                    return;
                }
                moneyValue = "0";
            }else {
                moneyValue = moneyValue.substring(0, moneyValue.length() - 1);
                if (".".equals(String.valueOf(moneyValue.charAt(moneyValue.length() - 1)))) {
                    moneyValue = moneyValue.substring(0, moneyValue.length() - 1);
                }
            }
        }else if(code == 13){
            moneyValue = "0";
        } else if(moneyValue.length() >= maxLength){
            if(!moneyValue.contains(".")){
                if(code == 12) {
                    moneyValue = moneyValue + title;
                }
            }
        } else {
            moneyValue = moneyValue + title;
        }
        moneyListener.onMoneyValue(moneyValue);
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
    /**
     * 金额回调
     */
    public interface OnMoneyListener{
        void onMoneyValue(String money);
    }
}

package imaisnaini.mycalc.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.ButterKnife;
import imaisnaini.mycalc.R;

/**
 * Created by lovearis on 20/09/2016.
 */
public class ImgButton extends LinearLayout{

    @BindColor(R.color.text_secondary) int mBorderColor;
    @BindColor(R.color.rvcontent_textcolor) int mDefaultBackgroundColor;
    @BindColor(R.color.borderline) int mFocusBackgroundColor;

    private Context mContext;

    private int mDefaultTextColor               = Color.GRAY;
    private int mDefaultTextSize 				= spToPx(getContext(), 20);
    private int mDefaultTextGravity             = 0x11;
    private String mText 						= null;
    private Drawable mIconResource 				= null;
    private int mIconPosition 					= 1;
    public static final int POSITION_LEFT  		= 1;

    private ImageView mIconView;
    private TextView mTextView;

    public ImgButton(Context context){
        super(context);
        this.mContext   = context;
        initCustomButton();
    }

    public ImgButton(Context context, AttributeSet attrs){
        super(context, attrs);
        this.mContext = context;
        TypedArray attrsArray 	= context.obtainStyledAttributes(attrs, R.styleable.ImgButton, 0, 0);
        initAttributesArray(attrsArray);
        attrsArray.recycle();

        initCustomButton();
    }

    private void initCustomButton(){
        ButterKnife.bind(this);
        initializeButtonContainer();

        mTextView = setupTextView();
        mIconView = setupIconView();

        ArrayList<View> views = new ArrayList<>();

        if(mIconPosition == POSITION_LEFT) {
            if (mIconView != null) { views.add(mIconView); }
            if (mTextView != null) { views.add(mTextView); }
        }

        for(View view : views){
            this.addView(view);
        }
    }

    private TextView setupTextView(){
        if (mText != null) {
            TextView textView = new TextView(mContext);
            textView.setText(mText);
            textView.setGravity(mDefaultTextGravity);
            textView.setTextColor(mDefaultTextColor);
            textView.setTextSize(pxToSp(getContext(), mDefaultTextSize));
            textView.setSingleLine(true);

            return textView;
        }
        return null;
    }

    private ImageView setupIconView(){
        if (mIconResource != null){
            ImageView iconView = new ImageView(mContext);
            iconView.setImageDrawable(mIconResource);
            iconView.setPadding(0, 0, 20, 0);

            LayoutParams iconViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            if(mTextView!=null){
                iconViewParams.gravity = Gravity.CENTER_VERTICAL;
            }
            iconView.setLayoutParams(iconViewParams);

            return iconView;
        }
        return null;
    }

    private void initAttributesArray(TypedArray attrsArray){
        mDefaultBackgroundColor 		= attrsArray.getColor(R.styleable.ImgButton_ibDefaultColor,mDefaultBackgroundColor);
        mFocusBackgroundColor 			= attrsArray.getColor(R.styleable.ImgButton_ibFocusColor,mFocusBackgroundColor);
        mDefaultTextColor 				= attrsArray.getColor(R.styleable.ImgButton_ibTextColor,mDefaultTextColor);
        mDefaultTextSize				= (int) attrsArray.getDimension(R.styleable.ImgButton_ibTextSize, mDefaultTextSize);
        String text 					= attrsArray.getString(R.styleable.ImgButton_ibText);
        mIconPosition 					= attrsArray.getInt(R.styleable.ImgButton_ibImgPosition,mIconPosition);

        try{
            mIconResource 				= attrsArray.getDrawable(R.styleable.ImgButton_ibImgSrc);
        }catch(Exception e){
            mIconResource = null;
        }
        if(text!=null) {
            mText = text;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable getRippleDrawable(Drawable defaultDrawable, Drawable focusDrawable){
        return new RippleDrawable(ColorStateList.valueOf(mFocusBackgroundColor), defaultDrawable, focusDrawable);
    }

    private void initializeButtonContainer(){
        this.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams containerParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(containerParams);
        this.setGravity(Gravity.CENTER);
        this.setClickable(true);
        this.setFocusable(true);
        this.setPadding(5, 5, 5, 5);
    }

    public static int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public String getText() {
        return mTextView.getText().toString();
    }

    public void setText(String text){
        this.mText = text;
        if(mTextView == null)
            initCustomButton();
        else
            mTextView.setText(text);
    }

    public void setImgSrc(Drawable imgSrc){
        this.mIconResource = imgSrc;
        if (mIconView == null)
            initCustomButton();
        else
            mIconView.setImageDrawable(imgSrc);
    }
}
package com.mls.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mls.library.R;

import java.util.ArrayList;
import java.util.List;


public class CircleTextImageView extends AppCompatImageView {

    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;

    private final RectF mDrawableRect = new RectF();
    private final RectF mBorderRect = new RectF();

    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private final Paint mBorderPaint = new Paint();

    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mDrawableRadius;
    private float mBorderRadius;

    private boolean mReady;
    private boolean mSetupPending;

    private int mCircleColor = Color.RED;//Default background color
    private int mCircleTextColor = Color.WHITE;//text color
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean useRandomBackgroundColor = false;//use random background color
    private boolean mSubFirstCharacter = false;

    private String text;
    private Paint paintTextForeground;
    private Paint paintTextBackground;
    private static final float DEFAULT_TEXT_SIZE_RATIO = 0.3f;
    private float textSizeRatio = DEFAULT_TEXT_SIZE_RATIO;
    private Paint.FontMetrics fontMetrics;
    private int radius;
    private int centerX;
    private int centerY;

    public CircleTextImageView(Context context) {
        super(context);
        init();
    }

    public CircleTextImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initAttr(context, attrs);
        init();
    }

    public CircleTextImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(SCALE_TYPE);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_border_width, DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColor(R.styleable.CircleImageView_border_color, DEFAULT_BORDER_COLOR);

        a.recycle();

        mReady = true;

        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
        initAttr(context, attrs);
        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circletextview);
        mCircleColor = typedArray.getColor(R.styleable.circletextview_circle_color, Color.RED);
        mCircleTextColor = typedArray.getColor(R.styleable.circletextview_circle_text_color, Color.WHITE);
        useRandomBackgroundColor = typedArray.getBoolean(R.styleable.circletextview_random_color, false);
        mSubFirstCharacter = typedArray.getBoolean(R.styleable.circletextview_sub_first_character, false);

        typedArray.recycle();
    }

    private void init() {

        paintTextForeground = new Paint();
        paintTextForeground.setColor(mCircleTextColor);
        paintTextForeground.setAntiAlias(true);
        paintTextForeground.setTextAlign(Paint.Align.CENTER);

        paintTextBackground = new Paint();
        paintTextBackground.setColor(mCircleTextColor);
        paintTextBackground.setAntiAlias(true);
        paintTextBackground.setStyle(Paint.Style.FILL);

        if (useRandomBackgroundColor) {
            mPaint.setColor(Color.parseColor(CircleTextImageUtil.getRandomColor()));
        } else {
            mPaint.setColor(mCircleColor);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100 * 2;
        int desiredHeight = 100 * 2;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (null != text && !text.trim().equals("")) {
            int realSize = (int)paintTextForeground.measureText(text) + 60;
            if (realSize < 200) {
                realSize = 100 * 2;
            }
            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = realSize;
            } else {
                //Be whatever you want
                width = realSize;
            }

            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = realSize;
            } else {
                //Be whatever you want
                height = realSize;
            }
        } else {
            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = Math.min(desiredWidth, widthSize);
            } else {
                //Be whatever you want
                width = desiredWidth;
            }

            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = Math.min(desiredHeight, heightSize);
            } else {
                //Be whatever you want
                height = desiredHeight;
            }

        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);

    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //get padding
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        //deal padding
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;

        if (null != text && !text.trim().equals("")) {
            drawText(canvas);
        } else {
            canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        }

        if (getDrawable() == null) {
            return;
        }

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius, mBitmapPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBorderRadius, mBorderPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int contentWidth = w - paddingLeft - getPaddingRight();
        int contentHeight = h - paddingTop - getPaddingBottom();
        radius = contentWidth < contentHeight ? contentWidth / 2 : contentHeight / 2;
        centerX = paddingLeft + radius;
        centerY = paddingTop + radius;
        refreshTextSizeConfig();
    }

    private void refreshTextSizeConfig() {
        paintTextForeground.setTextSize(textSizeRatio * 2 * 100);
        fontMetrics = paintTextForeground.getFontMetrics();

    }

    static class  CircleTextImageUtil{
        /**
         * Get the random color.
         * @return
         */
        private static String getRandomColor() {
            List<String> colorList = new ArrayList<String>();
            colorList.add("#303F9F");
            colorList.add("#FF4081");
            colorList.add("#59dbe0");
            colorList.add("#f57f68");
            colorList.add("#87d288");
            colorList.add("#f8b552");
            colorList.add("#990099");
            colorList.add("#90a4ae");
            colorList.add("#7baaf7");
            colorList.add("#4dd0e1");
            colorList.add("#4db6ac");
            colorList.add("#aed581");
            colorList.add("#fdd835");
            colorList.add("#f2a600");
            colorList.add("#ff8a65");
            colorList.add("#f48fb1");
            colorList.add("#7986cb");
            colorList.add("#FFFFE0");
            colorList.add("#ADD8E6");
            colorList.add("#DEB887");
            colorList.add("#C0C0C0");
            colorList.add("#AFEEEE");
            colorList.add("#F0FFF0");
            colorList.add("#FF69B4");
            colorList.add("#FFE4B5");
            colorList.add("#FFE4E1");
            colorList.add("#FFEBCD");
            colorList.add("#FFEFD5");
            colorList.add("#FFF0F5");
            colorList.add("#FFF5EE");
            colorList.add("#FFF8DC");
            colorList.add("#FFFACD");

            return colorList.get((int)(Math.random() * colorList.size()));
        }

        /**
         * Interception of the first string of characters.
         * @param str
         * @return
         */
        private static String subFirstCharacter(String str) {
            if (Character.isLetter(str.charAt(0))) {
                return Character.toUpperCase(str.charAt(0))+"";
            } else {
                return str.charAt(0) +"";
            }
        }
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        if (borderColor == mBorderColor) {
            return;
        }

        mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth == mBorderWidth) {
            return;
        }

        mBorderWidth = borderWidth;
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }

        if (mBitmap == null) {
            return;
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRect.set(0, 0, getWidth(), getHeight());
        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);

        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);
        mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);

        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }


    public void setText4CircleImage(String text) {
        if (mSubFirstCharacter) {
            this.text = CircleTextImageUtil.subFirstCharacter(text);
        } else {
            this.text = text;
        }
        invalidate();

    }

    private void drawText(Canvas canvas) {
        paintTextBackground.setColor(mCircleTextColor);
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        canvas.drawText(text, 0, text.length(), centerX, centerY + Math.abs(fontMetrics.top + fontMetrics.bottom) / 2, paintTextForeground);
    }
}
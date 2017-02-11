package com.asiainfo.gomoku.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.asiainfo.gomoku.R;
import com.asiainfo.gomoku.utils.GomokuCheckUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者:小木箱 邮箱:yangzy3@asiainfo.com 创建时间:2017年02月10日16点59分 描述:五子棋盘自定义view
 */
public class GomokuPannel extends View {

    private int mPanelWidth;
    private float mLineHeight;
    private int MAX_LINE = 10;
    private float ratioPieceOfLineHight = 3 * 1.0f / 4;

    //B白棋先手,当前轮到白棋了
    private boolean mIsWhite = true;
    //当前游戏已经结束了
    private boolean mIsGameOver = true;
    //判断当前游戏获胜赢家
    private boolean mIsWhiteWinner = true;

    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;

    private Paint mPaint = new Paint();
    private List<Point> mWhiteArray;
    private List<Point> mBlackArray;

    public GomokuPannel(Context context) {
        this(context, null);

    }

    public GomokuPannel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public GomokuPannel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {

        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);

        mWhiteArray = new ArrayList<>();
        mBlackArray = new ArrayList<>();

        mPaint.setColor(0x99ff0000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        setBackgroundColor(0x00ff0000);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heighSize = MeasureSpec.getSize(heightMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heighSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heighSize;
        } else if (heighMode == MeasureSpec.UNSPECIFIED) {

            width = widthSize;

        }
        setMeasuredDimension(width, width);

    }

    /**
     * 描述:对尺寸相关的成员变量进行赋值 创建时间:2/10/17/20:38 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;

        int pieceWidth = (int) (mLineHeight * ratioPieceOfLineHight);

        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBoard(canvas);

        drawPieces(canvas);

        checkGameOver(canvas);
    }

    /**
     * 描述: 创建时间:2/10/17/21:51 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */

    private void checkGameOver(Canvas canvas) {

        boolean whiteWin = CheckFiveInLine(mWhiteArray);
        boolean blackWin = CheckFiveInLine(mBlackArray);

        if (whiteWin || blackWin) {

            mIsGameOver = true;
            mIsWhiteWinner = whiteWin;

            String text = mIsWhiteWinner ? "白棋胜利" : "黑棋胜利";

            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

        }

    }

    private boolean CheckFiveInLine(List<Point> Points) {

        for (Point point : Points) {

            int x = point.x;
            int y = point.y;

            boolean checkHorizontal = GomokuCheckUtil.checkHorizonal(x, y, Points);
            boolean checkVertical = GomokuCheckUtil.checkVertical(x, y, Points);
            boolean checkLeftDiagonal = GomokuCheckUtil.checkLeftDiagonal(x, y, Points);
            boolean checkRightDiagonal = GomokuCheckUtil.checkRightDiagonal(x, y, Points);
            if (checkHorizontal || checkVertical || checkLeftDiagonal || checkRightDiagonal) {
                return true;
            }

        }

        return false;
    }





    /***
     * 绘制棋子
     */
    private void drawPieces(Canvas canvas) {

        for (int i = 0, n = mWhiteArray.size(); i < n; i++) {

            Point whitePoint = mWhiteArray.get(i);

            float left = (whitePoint.x + (1 - ratioPieceOfLineHight) / 2) * mLineHeight;
            float top = (whitePoint.y + (1 - ratioPieceOfLineHight) / 2) * mLineHeight;
            canvas.drawBitmap(mWhitePiece, left, top, null);


        }
        for (int i = 0, n = mBlackArray.size(); i < n; i++) {

            Point blackPoint = mBlackArray.get(i);

            float left = (blackPoint.x + (1 - ratioPieceOfLineHight) / 2) * mLineHeight;
            float top = (blackPoint.y + (1 - ratioPieceOfLineHight) / 2) * mLineHeight;
            canvas.drawBitmap(mBlackPiece, left, top, null);

        }


    }

    /**
     * 描述:绘制棋盘 创建时间:2/10/17/20:40 作者:小木箱 邮箱:yangzy3@asiainfo.com
     */

    private void drawBoard(Canvas canvas) {

        int w = mPanelWidth;
        float lineHeight = mLineHeight;

        for (int i = 0; i < MAX_LINE; i++) {

            //画横线
            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);

            //画纵坐标
            int y = (int) ((0.5 + i) * lineHeight);

            canvas.drawLine(startX, y, endX, y, mPaint);

            canvas.drawLine(y, startX, y, endX, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {

            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();

                Point point = getValidPoint(x, y);

                if (mWhiteArray.contains(point) || mBlackArray.contains(point)) {

                    return false;

                }

                if (mIsWhite) {

                    mWhiteArray.add(point);

                } else {

                    mBlackArray.add(point);

                }

                invalidate();

                mIsWhite = !mIsWhite;

                break;

            default:
                break;
        }


        return true;

    }

    private Point getValidPoint(int x, int y) {

        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }
}

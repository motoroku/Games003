package practice.games003;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class GameView extends SurfaceView {

	GameHolderCallBack gameHolderCallBack;
	int x;
	int y;

	Display display;

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
		gameHolderCallBack = new GameHolderCallBack(context);
		holder.addCallback(gameHolderCallBack);
		//

		x = getMeasuredWidth();
		y = getMeasuredHeight();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float xPoint = event.getX();

		gameHolderCallBack.getTouchEvent(xPoint);
		return super.onTouchEvent(event);
	}

	public void onClickButton(int id) {
		gameHolderCallBack.pushButton(id);
	}

}

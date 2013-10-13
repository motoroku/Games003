package practice.games003;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.Display;
import android.view.SurfaceHolder;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class GameHolderCallBack implements SurfaceHolder.Callback, Runnable {

	private SurfaceHolder holder = null;
	private Thread thread = null;
	private boolean isAttached = true;
	private SoundPool soundPool;
	private int soundId1;
	Context context;

	Baloon baloon;
	Stage stage;
	Display display;

	int viewX;
	int viewY;
	int activityX;
	int activityY;

	private long t1 = 0, t2 = 0; // �X���[�v�p�ϐ�

	public GameHolderCallBack(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		MainActivity activity = (MainActivity) context;
		display = activity.getWindowManager().getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point point = new Point();
			display.getSize(point);
			activityX = point.x;
			activityY = point.y;
		} else {
			activityX = display.getWidth();
			activityY = display.getHeight();
		}

		viewX = activity.findViewById(R.id.FrameLayoutGame).getWidth();
		viewY = activity.findViewById(R.id.FrameLayoutGame).getHeight();

		baloon = new Baloon(viewX / 2, viewY / 2);
		stage = new Stage(viewX, viewY);
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundId1 = soundPool.load(context, R.raw.se_maoudamashii_system02, 1);

		this.holder = holder;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (isAttached) {
			t1 = System.currentTimeMillis();
			// �`�揈�����J�n
			drawGameObject();

			if (baloon.xPosition - Baloon.SIZE < stage.block1.xPosition + Block.SIZE
					&& baloon.xPosition + Baloon.SIZE > stage.block1.xPosition - Block.SIZE) {
				if (baloon.yPosition - Baloon.SIZE < stage.block1.yPosition + Block.SIZE
						&& baloon.yPosition + Baloon.SIZE > stage.block1.yPosition - Block.SIZE) {
					soundPool.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f);
				}
			}

			if (baloon.xPosition - Baloon.SIZE < stage.block2.xPosition + Block.SIZE
					&& baloon.xPosition + Baloon.SIZE > stage.block2.xPosition - Block.SIZE) {
				if (baloon.yPosition - Baloon.SIZE < stage.block2.yPosition + Block.SIZE
						&& baloon.yPosition + Baloon.SIZE > stage.block2.yPosition - Block.SIZE) {
					soundPool.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f);
				}
			}

			// �X���[�v
			t2 = System.currentTimeMillis();
			if (t2 - t1 < 16) { // 1000 / 60 = 16.6666
				try {
					Thread.sleep(16 - (t2 - t1));
				} catch (InterruptedException e) {
				}
			}

		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		soundPool.release();
		isAttached = false;
		thread = null;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	public void getTouchEvent(float xPoint) {
		// TODO Auto-generated method stub
		baloon.move(xPoint);
	}

	public void pushButton(int id) {
		switch (id) {
			case R.id.buttonA:
				baloon.up(0);
				break;
			case R.id.buttonB:
				baloon.down(viewY);
				break;
			case R.id.buttonLeft:
				baloon.left(0);
				break;
			case R.id.buttonRight:
				baloon.right(viewX);
				break;
			default:
				break;
		}
	}

	private void drawGameObject() {

		if (baloon.yPosition < stage.height) {
			baloon.yPosition += Stage.SPEED;
		}

		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(0, PorterDuff.Mode.CLEAR);
		Paint baloonPaint = new Paint();
		Paint block1 = new Paint();
		Paint block2 = new Paint();
		baloonPaint.setColor(baloon.color);
		block1.setColor(stage.block1.blockColor);
		block2.setColor(stage.block2.blockColor);
		canvas.drawCircle(baloon.xPosition, baloon.yPosition, Baloon.SIZE, baloonPaint);
		canvas.drawCircle(stage.block1.xPosition, stage.block1.yPosition, Block.SIZE, block1);
		canvas.drawCircle(stage.block2.xPosition, stage.block2.yPosition, Block.SIZE, block2);
		// �`�揈�����I��
		holder.unlockCanvasAndPost(canvas);
	}
}
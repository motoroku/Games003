package practice.games003;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class GameFragment extends Fragment implements OnClickListener {

	GameView gameView;
	Button buttonA;
	Button buttonB;
	Button buttonRight;
	Button buttonLeft;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_game, container, false);
		Context context = getActivity();
		// ゲーム画面を表示させる場所のViewGroupを取得
		FrameLayout layout = (FrameLayout) v.findViewById(R.id.FrameLayoutGame);
		buttonA = (Button) v.findViewById(R.id.buttonA);
		buttonB = (Button) v.findViewById(R.id.buttonB);
		buttonLeft = (Button) v.findViewById(R.id.buttonLeft);
		buttonRight = (Button) v.findViewById(R.id.buttonRight);

		buttonA.setOnClickListener(this);
		buttonB.setOnClickListener(this);
		buttonLeft.setOnClickListener(this);
		buttonRight.setOnClickListener(this);

		// ゲーム画面のViewをインスタンス化
		gameView = new GameView(context);
		// ゲーム画面をActivityのViewGroupに追加
		layout.addView(gameView);
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		gameView.onClickButton(v.getId());
	}
}

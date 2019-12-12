/*
 * Java
 *
 * Copyright 2019 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package some.pack.name;

import java.util.Timer;
import java.util.TimerTask;

import ej.wadapps.app.BackgroundService;

/**
 *
 */
public class NewClass implements BackgroundService {

	/**
	 */
	public static void main() {
		// TODO Auto-generated method stub
		System.out.println("ok");

		Timer timer = new Timer();

		TimerTask task = new RepeatedTaskTimer(5);

		// no delay, scheduled at 10 second intervals

		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	@Override
	public void onStart() {
		main();
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

}

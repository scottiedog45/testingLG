package some.pack.name;

import java.util.TimerTask;

class RepeatedTaskTimer extends TimerTask {

	private static int completionCount = 0;
	int totalRepeatCount;

	// private final Runnable callable;

	public RepeatedTaskTimer(int repeatCount) {
		this.totalRepeatCount = repeatCount;
		// this.callable = runnable;
	}

	@Override
	public void run() {
		if (completionCount < this.totalRepeatCount) {

			System.out.println("would be grabbing data..."); //$NON-NLS-1$

			completionCount++;
		} else {
			// programatically kill the program process
			// System.exit(0);
		}
	}
}
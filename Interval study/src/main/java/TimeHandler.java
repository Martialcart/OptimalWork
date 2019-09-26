import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

class TimeHandler {
    private static final int MILLISEC_SEC = 1000;
    private static final int SEC_MINUTE = 60;
    private static final int WORK_INTERVALS = 4;

    public static void main(String[] args) {
        long longPause = 0;
        long pause;
        while (true) {
            for (int interval = 1; interval < WORK_INTERVALS; interval++) {
                System.out.println(
                        "Press enter to begin interval " + interval + "\n" +
                        "Super pause = " + clockTime(longPause));
                enterSwitch();
                pause = workCycle();
                longPause += pause;
                countDown(pause);
            }
            System.out.println(
                    "Press enter to begin interval " + WORK_INTERVALS + "\n" +
                            "Super pause = " + clockTime(longPause));
            enterSwitch();
            longPause += workCycle();
            System.out.println("Long pause");
            countDown(longPause);
            longPause = 0;
        }
    }
    /**
     *
     * @return Amount of pause this cycle
     */
    private static long workCycle() {
        System.out.println(
                "started working: " + currentTime() + "\n" +
                "Press enter for pause");
        long workTime = stopWatch();
        System.out.println("worked for: " + clockTime(workTime));
        long pause = calcPause(workTime);
        return pause;
    }
    private static void countDown(long milliSec) {
        long goal = System.currentTimeMillis() + milliSec;

        while (0 < timeLeft(goal)) {
            System.out.println(clockTime(timeLeft(goal)));
            sleep(MILLISEC_SEC);
        }
    }

    /**
     * W = A*B^P      P = Pause, W = Work,
     *
     * chinese study suggest W = 52 then P = 17
     * spanish student found W = 25 then P = 5
     *
     * @param workMilliSec work-time in milliseconds
     * @return pause-time in milliseconds
     */
    private static long calcPause(long workMilliSec) {
        //factors A and B is only calculated for minutes
        final double B = 1.046367;
        final double A = 1.610126;
        double workMinute = 1.0 * workMilliSec / MILLISEC_SEC / SEC_MINUTE;
        double pauseMinutes = workMinute * (17.0 / 52.0); //chinese study.  combining both...(A * Math.pow(B,workMinute));
        double pauseMillisecs = pauseMinutes * MILLISEC_SEC * SEC_MINUTE;
        return (long) pauseMillisecs;
    }

    /**
     * press enter to stop time-counting
     * @return end-time
     */
    private static long stopWatch(){
        long timeStart = System.currentTimeMillis();
        enterSwitch();
        return System.currentTimeMillis() - timeStart;
    }

    private static long timeLeft(long goalTime) {
        return goalTime - System.currentTimeMillis();
    }

    private static String clockTime(long time) {
        int seconds = (int) time / MILLISEC_SEC;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        return String.format("%02d:%02d:%02d%n", hours % 12, minutes % 60, seconds % 60);
    }
    private static void sleep(long millisec){
        try {
            Thread.sleep( millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    /**
     * halts the thread until enter is pressed
     */
    private static void enterSwitch(){
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
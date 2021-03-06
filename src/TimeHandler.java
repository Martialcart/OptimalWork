import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * The program calculates optimal break time after a work session.
 *
 * formula: OptimalPause = worktime  *  (optimalPause / OptimalWorktime)
 *
 *
 * based on Julia Gifford's research:
 * She tracked employees.
 * What the top 10% most effective employees had in common:
 * -working 52 minutes
 * -pausing 17 minutes
 *
 * article: https://www.themuse.com/advice/the-rule-of-52-and-17-its-random-but-it-ups-your-productivity
 *
 * @author Jan Olav Berg
 */

class TimeHandler {
    private static final int MILLISEC_SEC = 1000;
    private static final int WORK_INTERVALS = 4;
    private static final double PAUSE_RATIO = (17.0 / 52.0); //Julia Gifford research
    private static long longPause;
    public static void main(String[] args) {
        longPause = 0;
        long pause;
        while (true) {
            for (int interval = 1; interval < WORK_INTERVALS; interval++) {
                menuPrint(interval);
                enterSwitch();
                pause = workCycle();
                longPause += pause;
                countDown(pause);
            }
            menuPrint(WORK_INTERVALS);
            enterSwitch();
            longPause += workCycle();
            System.out.println("Long pause");
            countDown(longPause);
            longPause = 0;
        }
    }

    /**
     * prints menu
     * @param interval number of current work session
     */
    private static void menuPrint(int interval) {
        System.out.println(
                "Press enter to begin interval " + interval + "\n" +
                "Super pause = " + clockTime(longPause));
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
        return calcPause(workTime);
    }
    private static void countDown(long milliSec) {
        long goal = System.currentTimeMillis() + milliSec;

        while (0 < timeLeft(goal)) {
            System.out.println(clockTime(timeLeft(goal)));
            sleep(MILLISEC_SEC);
        }
    }

    /**
     * @param workMilliSec work-time in milliseconds
     * @return pause-time in milliseconds
     */
    private static long calcPause(long workMilliSec) {
        return (long) (workMilliSec * PAUSE_RATIO);
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
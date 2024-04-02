/**
 * Class TimeInterval represents the interval in which a client is disponible to be visited.
 * @author Petrasuc Ana
 */
public class TimeInterval {
    private int startTime;
    private int endTime;
    public TimeInterval(int startTime, int endTime){
        this.startTime=startTime;
        this.endTime=endTime;
    }
    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }


    @Override
    public String toString() {
        return "TimeInterval{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

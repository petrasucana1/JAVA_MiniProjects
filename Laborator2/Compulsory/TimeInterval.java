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

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

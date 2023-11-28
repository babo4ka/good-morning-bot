package goodMorningBot.interval;

public class ResponseData {

    private double start;

    private double end;

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public ResponseData(double start, double end) {
        this.start = start;
        this.end = end;
    }
}

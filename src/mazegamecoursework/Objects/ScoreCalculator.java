package mazegamecoursework.Objects;

public class ScoreCalculator {
    private final double time;
    private final int distance;

    public ScoreCalculator(double time, int distance){
        this.time = time;
        this.distance = distance;
    }

    public double CalculateScore(){
        return distance/time;
        //Calculates the score by multiplying the time taken by the natural log of the distance
    }
}

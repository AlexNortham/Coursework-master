package mazegamecoursework.Objects;

public class ScoreCalculator {
    double time;
    int distance;

    public ScoreCalculator(double time, int distance){
        this.time = time;
        this.distance = distance;
    }

    public double CalculateScore(){
        double score = (1000-time)*(Math.log(distance));
        return score;
        //Calculates the score by multiplying the time taken by the natural log of the distance
    }
}

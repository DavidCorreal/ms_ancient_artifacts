package co.com.bancolombia.model.stats;

import lombok.Getter;

@Getter
public class Stats {
    private Integer countClueFound;
    private Integer countNoClue;
    private Double ratio;

    public Stats(Integer countClueFound, Integer countNoClue) {
        this.countClueFound = countClueFound;
        this.countNoClue = countNoClue;
        this.ratio = calculateRatio();
    }

    public void setCountClueFound(Integer countClueFound) {
        this.countClueFound = countClueFound;
        this.ratio = calculateRatio();
    }

    public void setCountNoClue(Integer countNoClue) {
        this.countNoClue = countNoClue;
        this.ratio = calculateRatio();
    }

    private Double calculateRatio() {
        if (this.countNoClue == null || this.countNoClue == 0) {
            return this.countClueFound != null ? this.countClueFound.doubleValue() : 0.0;
        }
        return this.countClueFound != null ? this.countClueFound.doubleValue() / this.countNoClue : 0.0;
    }
}
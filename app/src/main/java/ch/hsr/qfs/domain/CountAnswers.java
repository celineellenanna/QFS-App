package ch.hsr.qfs.domain;

public class CountAnswers {
    private int countAnswers;
    private int countActualRoundAnswers;

    public int getCountActualRoundAnswers() {
        return countActualRoundAnswers;
    }

    public void setCountActualRoundAnswers(int countActualRoundAnswers) {
        this.countActualRoundAnswers = countActualRoundAnswers;
    }

    public int getCountUserAnswer() {
        return countAnswers;
    }

    public void setCountUserAnswer(int countUserAnswer) {
        this.countAnswers = countUserAnswer;
    }
}

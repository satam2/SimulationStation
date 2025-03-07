package prisonerdilemma;
import mvc.*;

public interface Strategy {
    boolean cooperate();
}

class AlwaysCooperate implements Strategy {
    @Override
    public boolean cooperate() {
        return true;
    }
}

class AlwaysCheat implements Strategy {
    @Override
    public boolean cooperate() {
        return false;
    }
}

class RandomlyCooperate implements Strategy {
    @Override
    public boolean cooperate() {
        return Utilities.rng.nextBoolean();
    }
}

class Tit4Tat implements Strategy {
    private boolean lastOpponentCooperated = true;

    public boolean cooperate() {
        return lastOpponentCooperated;
    }

    public void setLastOpponentCooperated(boolean value) {
        lastOpponentCooperated = value;
    }
}

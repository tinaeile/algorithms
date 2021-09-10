package org.pg4200.ex03;

import java.util.Comparator;

public class GameUserComparator implements Comparator<GameUser> {
    @Override
    public int compare(GameUser a, GameUser b) {

        int pointDiff = a.getPoints() - b.getPoints();

        if (pointDiff == 0) {
            return a.getUserId().compareTo(b.getUserId());
        }

        return pointDiff;
    }
}

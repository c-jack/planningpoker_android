package uk.cjack.planningpoker.model;

import java.util.List;

public class PokerScale {
    private List<String> scale;
    public PokerScale( final Object... objects ) {
        for( final Object object : objects )
        {
            scale.add( String.valueOf( object ) );
        }
    }

    public List<String> getScale() {
        return scale;
    }
}

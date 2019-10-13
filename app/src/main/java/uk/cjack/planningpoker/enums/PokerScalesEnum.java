package uk.cjack.planningpoker.enums;

import java.util.Arrays;
import java.util.List;

public enum PokerScalesEnum {
    FIBONACCI( Arrays.asList( "0", "0.5", "1", "2", "3", "5", "8", "13", "20", "40", "100",
            ":infinity:", ":coffee:", ":question:", ":toilet:" ) ),
    TSHIRT( Arrays.asList( "XXS", "XS", "S", "M", "L", "XL", "XXL" ) );

    private final List<String> pokerScale;

    PokerScalesEnum( final List<String> asList ) {
        this.pokerScale = asList;
    }

    public List<String> getPokerScale() {
        return pokerScale;
    }
}

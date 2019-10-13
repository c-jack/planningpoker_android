package uk.cjack.planningpoker.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity( tableName = "card_config" )
public class CardConfig implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int cardConfigId;

    @NonNull
    private String cardConfigName;

    public CardConfig( @NonNull final String cardConfigName) {
        this.cardConfigId = 0;
        this.cardConfigName = cardConfigName;
    }


    public int getCardConfigId() {
        return cardConfigId;
    }
    public void setCardConfigId( final int cardConfigId) {
        this.cardConfigId = cardConfigId;
    }


    @NonNull
    public String getCardConfigName() {
        return cardConfigName;
    }

    public void setCardConfigName( @NonNull final String cardConfigName) {
        this.cardConfigName = cardConfigName;
    }
}

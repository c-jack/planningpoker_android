package uk.cjack.planningpoker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uk.cjack.planningpoker.database.entities.CardConfig;

@Dao
public interface CardConfigDao {

    @Insert
    void insert( CardConfig cardConfig );

    @Query("SELECT * FROM card_config WHERE cardConfigId = :cardConfigId")
    CardConfig getCardConfig( final int cardConfigId );

    @Query("DELETE FROM card_config")
    void deleteAll();

    @Query("SELECT * FROM card_config ORDER BY cardConfigName ASC")
    LiveData<List<CardConfig>> getAllCardConfig();

    @Delete
    void delete( CardConfig cardConfig );

    @Update
    void update( CardConfig cardConfig );
}

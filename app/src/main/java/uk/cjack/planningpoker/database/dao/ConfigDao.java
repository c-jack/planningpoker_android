package uk.cjack.planningpoker.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uk.cjack.planningpoker.database.entities.Config;

@Dao
public interface ConfigDao {

    @Insert
    void insert( Config config );

    @Query("SELECT * FROM config WHERE configId = :configId")
    Config getConfigById( final int configId );

    @Query("SELECT * FROM config WHERE configKey = :configKey")
    Config getConfigByKey( final String configKey );

    @Query("DELETE FROM config")
    void deleteAll();

    @Query("SELECT * FROM config ORDER BY configKey ASC")
    LiveData<List<Config>> getAllConfig();

    @Delete
    void delete( Config config );

    @Update
    void update( Config config );


}

package uk.cjack.planningpoker.database.repository;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import uk.cjack.planningpoker.database.PlanningPokerDatabase;
import uk.cjack.planningpoker.database.dao.ConfigDao;
import uk.cjack.planningpoker.database.entities.Config;
import uk.cjack.planningpoker.database.enums.DatabaseActionEnum;

public class ConfigRepository {

    private final ConfigDao mConfigDao;
    private final LiveData<List<Config>> mAllConfig;

    public LiveData<List<Config>> getAllConfig() {
        return mAllConfig;
    }

    public Config getConfigById( final int configId ) {
//        mAllConfig.getValue().
        return null;
    }

    public Config getConfigByName( final String configName ) {
//        mAllConfig.getValue().
        return null;
    }

    /**
     * Constructor
     *
     * @param application application
     */
    public ConfigRepository( final Application application ) {
        final PlanningPokerDatabase db = PlanningPokerDatabase.getDatabase( application );
        mConfigDao = db.configDao();
        mAllConfig = mConfigDao.getAllConfig();
    }

    /**
     * Performs an INSERT query in a background thread
     *
     * @param config the {@link Config} object to insert
     */
    public void insert( final Config config ) {
        new DatabaseAsyncTask( mConfigDao, DatabaseActionEnum.INSERT ).execute( config );
    }


    /**
     * Performs an INSERT query in a background thread
     *
     * @param config the {@link Config} object to insert
     */
    public void insertOrUpdate( final Config config ) {
        new DatabaseAsyncTask( mConfigDao, DatabaseActionEnum.INSERT_OR_UPDATE ).execute( config );
    }

    /**
     * Performs an UPDATE query in a background thread
     *
     * @param config the {@link Config} object to insert
     */
    public void update( final Config config ) {
        new DatabaseAsyncTask( mConfigDao, DatabaseActionEnum.UPDATE ).execute( config );
    }

    /**
     * Performs an DELETE query in a background thread
     *
     * @param config the {@link Config} object to insert
     */
    public void delete( final Config config ) {
        new DatabaseAsyncTask( mConfigDao, DatabaseActionEnum.DELETE ).execute( config );
    }
}

package uk.cjack.planningpoker.database.repository;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import uk.cjack.planningpoker.database.dao.CardConfigDao;
import uk.cjack.planningpoker.database.dao.ConfigDao;
import uk.cjack.planningpoker.database.entities.CardConfig;
import uk.cjack.planningpoker.database.entities.Config;
import uk.cjack.planningpoker.database.enums.DatabaseActionEnum;

/**
 * Async task class
 */
public class DatabaseAsyncTask extends AsyncTask<Object, Void, Object> {

    private final ConfigDao mConfigDao;
    private final CardConfigDao mCardConfigDao;
    private final DatabaseActionEnum mAction;

    DatabaseAsyncTask( final ConfigDao dao, final DatabaseActionEnum action ) {
        mConfigDao = dao;
        mCardConfigDao = null;
        mAction = action;
    }

    DatabaseAsyncTask( final CardConfigDao dao, final DatabaseActionEnum action ) {
        mConfigDao = null;
        mCardConfigDao = dao;
        mAction = action;
    }

    /**
     * Common handler for doInBackground
     *
     * @param params
     * @return
     */
    @Override
    protected Object doInBackground( final Object... params ) {
        switch ( mAction ) {
            case INSERT:
                if ( params[0] instanceof CardConfig && mCardConfigDao != null ) {
                    mCardConfigDao.insert( ( CardConfig ) params[0] );
                }
                else if ( params[0] instanceof Config && mConfigDao != null ) {
                    mConfigDao.insert( ( Config ) params[0] );
                }
                break;
            case INSERT_OR_UPDATE:
                if ( params[0] instanceof CardConfig && mCardConfigDao != null ) {
                    try {
                        mCardConfigDao.insert( ( CardConfig ) params[0] );
                    }
                    catch ( final SQLiteConstraintException e ) {
                        mCardConfigDao.update( ( CardConfig ) params[0] );
                    }
                }
                else if ( params[0] instanceof Config && mConfigDao != null ) {
                    final Config config = ( Config ) params[0];
                    final Config existingConfig = mConfigDao.getConfigByKey( config.getConfigKey() );
                    if( existingConfig != null )
                    {
                        existingConfig.setConfigValue( config.getConfigValue() );
                        mConfigDao.update( existingConfig );
                    }
                    else {
                        mConfigDao.insert( config );
                    }
                }
                break;
            case UPDATE:
                if ( params[0] instanceof CardConfig && mCardConfigDao != null ) {
                    mCardConfigDao.update( ( CardConfig ) params[0] );
                }
                else if ( params[0] instanceof Config && mConfigDao != null ) {
                    mConfigDao.update( ( Config ) params[0] );
                }
                break;
            case DELETE:
                if ( params[0] instanceof CardConfig && mCardConfigDao != null ) {
                    mCardConfigDao.delete( ( CardConfig ) params[0] );
                }
                else if ( params[0] instanceof Config && mConfigDao != null ) {
                    mConfigDao.delete( ( Config ) params[0] );
                }
                break;
            case GET:
                if ( params[0] instanceof String && mConfigDao != null ) {
                    return mConfigDao.getConfigByKey( ( String ) params[0] );
                }
        }
        return null;
    }
//
//
//    public static class GetBabyAsyncTask extends AsyncTask<Integer, Void, Baby> {
//
//        private final BabyDao mConfigDao;
//
//        GetBabyAsyncTask( final BabyDao babyDao ) {
//            mConfigDao = babyDao;
//        }
//
//        @Override
//        protected Baby doInBackground( final Integer... babyId ) {
//            return mConfigDao.getBaby( babyId[0] );
//        }
//
//    }

}
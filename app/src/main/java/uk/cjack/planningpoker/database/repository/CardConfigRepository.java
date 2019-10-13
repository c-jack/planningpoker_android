package uk.cjack.planningpoker.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import uk.cjack.planningpoker.database.PlanningPokerDatabase;
import uk.cjack.planningpoker.database.dao.CardConfigDao;
import uk.cjack.planningpoker.database.entities.CardConfig;

public class CardConfigRepository {

    private final CardConfigDao mCardConfigDao;
    private final LiveData<List<CardConfig>> mAllCardConfig;

    /**
     * Constructor
     *
     * @param application application
     */
    public CardConfigRepository( final Application application ) {
        final PlanningPokerDatabase db = PlanningPokerDatabase.getDatabase( application );
        mCardConfigDao = db.cardConfigDao();
        mAllCardConfig = mCardConfigDao.getAllCardConfig();
    }

}

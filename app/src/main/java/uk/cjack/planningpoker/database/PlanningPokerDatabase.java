package uk.cjack.planningpoker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import uk.cjack.planningpoker.database.dao.CardConfigDao;
import uk.cjack.planningpoker.database.dao.ConfigDao;
import uk.cjack.planningpoker.database.entities.CardConfig;
import uk.cjack.planningpoker.database.entities.Config;

@Database(entities = { CardConfig.class, Config.class }, version = 1)
public abstract class PlanningPokerDatabase extends RoomDatabase {

    public abstract CardConfigDao cardConfigDao();
    public abstract ConfigDao configDao();
//    public abstract ActivityDao activityDao();

    private static volatile PlanningPokerDatabase INSTANCE;

    public static PlanningPokerDatabase getDatabase( final Context context ) {
        if (INSTANCE == null) {
            synchronized ( PlanningPokerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlanningPokerDatabase.class, "planning_poker_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
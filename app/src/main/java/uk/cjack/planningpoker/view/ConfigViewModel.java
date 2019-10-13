package uk.cjack.planningpoker.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uk.cjack.planningpoker.database.entities.Config;
import uk.cjack.planningpoker.database.repository.ConfigRepository;

public class ConfigViewModel extends AndroidViewModel {

    private final ConfigRepository mConfigRepository;
    private final LiveData<List<Config>> mAllConfig;


    public ConfigViewModel( final Application application ) {
        super( application );
        mConfigRepository = new ConfigRepository( application );
        mAllConfig = mConfigRepository.getAllConfig();
    }

    public LiveData<List<Config>> getAllConfig() {
        return mAllConfig;
    }

    public Config getConfigById( final int configId ) {
        return mConfigRepository.getConfigById( configId );
    }

    public Config getConfigByName( final String configKey ) {
        final List<Config> config = (List<Config>) mAllConfig.getValue();
        if ( config != null ) {
            for( Config configItem : config )
            {
              if( configItem.getConfigKey().equals( configKey ))
              {
                  return configItem;
              }
            }
        }
        return null;
    }

    public void insert( final Config config ) {
        mConfigRepository.insert( config );
    }

    public void insertOrUpdate( final Config config ) {
        mConfigRepository.insertOrUpdate( config );
    }

    public void delete( final Config config ) {
        mConfigRepository.delete( config );
    }

    public void update( final Config config ) {
        mConfigRepository.update( config );
    }
}

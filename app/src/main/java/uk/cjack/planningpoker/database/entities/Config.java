package uk.cjack.planningpoker.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity( tableName = "config", indices = {@Index(value = "configKey", unique = true)})
public class Config implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int configId;

    @NonNull
    private String configKey;

    @NonNull
    private String configValue;

    public Config( @NonNull final String configKey, @NonNull final String configValue) {
        this.configId = 0;
        this.configKey = configKey;
        this.configValue = configValue;
    }

    public int getConfigId() {
        return configId;
    }
    public void setConfigId( final int configId ) {
        this.configId = configId;
    }

    @NonNull
    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey( @NonNull final String configKey ) {
        this.configKey = configKey;
    }

    @NonNull
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue( @NonNull final String configValue ) {
        this.configValue = configValue;
    }
}

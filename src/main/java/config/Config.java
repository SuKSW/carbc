package config;


import org.json.JSONObject;

/**
 *
 */
public final class Config {
    private static final Config INSTANCE = new Config();
    private JSONObject configJson;

    private Config() {}

    public static Config getInstance() {
        return INSTANCE;
    }

    public JSONObject getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String peerName) {

        this.configJson = configJson;
    }
}

package com.pool.tronik.utils;

public class RelayConfig {

    public enum RelayOff {
        ONE(com.pool.tronik.utils.RelayOff.RELAY1), TWO(com.pool.tronik.utils.RelayOff.RELAY2),
        THREE(com.pool.tronik.utils.RelayOff.RELAY3), FOUR(com.pool.tronik.utils.RelayOff.RELAY4),
        FIFE(com.pool.tronik.utils.RelayOff.RELAY5), SIX(com.pool.tronik.utils.RelayOff.RELAY6),
        SEVEN(com.pool.tronik.utils.RelayOff.RELAY7), EIGHT(com.pool.tronik.utils.RelayOff.RELAY8);

        private String value;

         RelayOff(String value) {
            this.value = value;
        }

        public String getValue() {
             return value;
        }
    }
    public enum RelayOn {
        ONE(com.pool.tronik.utils.RelayOn.RELAY1), TWO(com.pool.tronik.utils.RelayOn.RELAY2),
        THREE(com.pool.tronik.utils.RelayOn.RELAY3), FOUR(com.pool.tronik.utils.RelayOn.RELAY4),
        FIFE(com.pool.tronik.utils.RelayOn.RELAY5), SIX(com.pool.tronik.utils.RelayOn.RELAY6),
        SEVEN(com.pool.tronik.utils.RelayOn.RELAY7), EIGHT(com.pool.tronik.utils.RelayOn.RELAY8);

        private String value;

        RelayOn(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

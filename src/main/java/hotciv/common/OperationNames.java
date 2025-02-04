package hotciv.common;

public class OperationNames {
        // Method names are prefixed with the type of the method receiver ('game') which
        // can be used in when serveral different types of objects are present at the server side
        // and is also helpful in case of failure on the server side where log files can be
        // inspected.
        public static final char SEPARATOR = '_';

        // Type prefixes
        public static final String GAME_PREFIX = "game";
        public static final String CITY_PREFIX = "city";
        public static final String UNIT_PREFIX = "unit";

        public static final String GET_WINNER = GAME_PREFIX + SEPARATOR + "get-winner";
        public static final String GET_AGE = GAME_PREFIX + SEPARATOR + "get-age";
        public static final String GET_PLAYER_IN_TURN = GAME_PREFIX + SEPARATOR + "get-player-in-turn";
        public static final String MOVE_UNIT = GAME_PREFIX + SEPARATOR + "move-unit";
        public static final String END_OF_TURN = GAME_PREFIX + SEPARATOR + "end-of-turn";
        public static final String CHANGE_WORKFORCEFOCUS_IN_CITY_AT = GAME_PREFIX + SEPARATOR + "change-workforcefocus-in-city-at";
        public static final String CHANGE_PRODUCTION_IN_CITY_AT = GAME_PREFIX + SEPARATOR + "change-production-in-city-at";
        public static final String PERFORM_UNIT_ACTION_AT = GAME_PREFIX + SEPARATOR + "perform-unit-action-at";
        public static final String GET_TILE_AT = GAME_PREFIX + SEPARATOR + "get-tile-at";
        public static final String GET_CITY_AT = GAME_PREFIX + SEPARATOR + "get-city-at";
        public static final String GET_UNIT_AT = GAME_PREFIX + SEPARATOR + "get-unit-at";


        public static final String GET_CITY_OWNER = CITY_PREFIX + SEPARATOR + "get-owner";
        public static final String GET_SIZE = CITY_PREFIX + SEPARATOR + "get-size";
        public static final String GET_TREASURY = CITY_PREFIX + SEPARATOR + "get-treasury";
        public static final String GET_PRODUCTION = CITY_PREFIX + SEPARATOR + "get-production";
        public static final String GET_WORK_FORCE_FOCUS = CITY_PREFIX + SEPARATOR + "get-work-force-production";

        public static final String GET_UNIT_OWNER = UNIT_PREFIX + SEPARATOR + "get-owner";
        public static final String GET_TYPE_STRING = UNIT_PREFIX + SEPARATOR + "get-type-string";
        public static final String GET_MOVE_COUNT = UNIT_PREFIX + SEPARATOR + "get-move-count";
        public static final String GET_DEFENSIVE_STRENGTH = UNIT_PREFIX + SEPARATOR + "get-defensive-strength";
        public static final String GET_ATTACKING_STRENGTH = UNIT_PREFIX + SEPARATOR + "get-attacking-strength";
}

package com.adipa.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 28, 2010
 * Time: 8:24:33 PM
 */
public final class DBConstants
{
    public static String getPojoName(String columnName)
    {
        StringBuilder sb = new StringBuilder();
        char[] charAr = columnName.toCharArray();

        boolean capitalize = false;
        for (char c : charAr)
        {
            if (c != '_')
            {
                sb.append(capitalize ? Character.toUpperCase(c) : c);
            }
            capitalize = c == '_';
        }
        return sb.toString();
    }

    public static String getDbName(String columnName)
    {
        StringBuilder sb = new StringBuilder();
        char[] charAr = columnName.toCharArray();

        for (int i = 0, charArLength = charAr.length; i < charArLength; i++)
        {
            char c = charAr[i];
            if (Character.isUpperCase(c))
            {
                sb.append('_');
            }
            sb.append(i==0?Character.toUpperCase(c):Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public final class Auth
    {
        public final class User
        {
            public static final String TABLE_NAME = "auth_user";
            public static final String SEQ_GEN_CLASS_NAME = "com.adipa.entities.auth.UserSequenceGenerator";
            public static final String ID_COL_USER = "user_id";
            public static final String COL_USER_NAME = "user_name";
        }
    }

    public final class Gen
    {
        public final class Config
        {
            public static final String TABLE_NAME = "gen_config";
            public static final String COL_CONFIG_NAME = "config_name";
            public static final String COL_CONFIG_VALUE = "config_value";
        }

        public final class Sequence
        {
            public static final String TABLE_NAME = "gen_sequence";
            public static final String COL_SEQUENCE_ID = "sequence_id";
            public static final String COL_NEXT_VALUE = "next_value";
        }

        public final class EntityType
        {
            public static final String TABLE_NAME = "gen_entity_type";
            public static final String SEQ_GEN_CLASS_NAME = "com.adipa.entities.gen.EntityTypeSequenceGenerator";
            public static final String ID_COL_ENTITY_TYPE = "entity_type_id";
        }

        public final class EntityPropertyType
        {
            public static final String TABLE_NAME = "gen_entity_property_type";
            public static final String ID_COL_ENTITY_TYPE = "entity_type_id";
            public static final String ID_COL_PROPERTY_TYPE = "property_type_id";
        }

        public final class EntityEntry
        {
            public static final String TABLE_NAME = "gen_entity";
            public static final String SEQ_GEN_CLASS_NAME = "com.adipa.entities.gen.EntityEntrySequenceGenerator";
            public static final String ID_COL_ENTITY = "entity_id";
            public static final String COL_ENTITY_TYPE = "entity_type_id";
        }

        public final class EntityProperty
        {
            public static final String TABLE_NAME = "gen_entity_property";
            public static final String ID_COL_ENTITY = "entity_id";
            public static final String ID_COL_ENTITY_TYPE = "entity_type_id";
            public static final String ID_COL_PROPERTY_TYPE = "property_type_id";
        }
    }
}

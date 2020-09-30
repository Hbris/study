package learn;

public enum SyncTableEnum {
    APP_ENTITY("app_entity", "insertAppEntity", "queryEntityInfoById",
            "updateAppEntity", "deleteAppEntity"),

    APP_ENTITY_FIELD("app_entity_field", "insertAppEntityField", "queryEntityFieldInfoById",
            "updateAppEntityField", "deleteAppEntityField"),

    APP_FORM_PROPERTY("app_form_property", "insertAppFormProperty", "queryAppFormPropertyInfoById",
            "updateAppFormProperty", "deleteAppFormProperty"),

    APP_COMPONENT("app_component", "insertAppComponent", "queryAppComponentInfoById",
            "updateAppComponent", "deleteAppComponent"),
    NULL();

    private String tableName;

    private String insertSql;

    private String querySql;
    private String updateSql;

    private String deleteSql;

    public String getTableName() {
        return tableName;
    }


    public String getInsertSql() {
        return insertSql;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public String getQuerySql() {
        return querySql;
    }

    public String getDeleteSql() {
        return deleteSql;
    }


    SyncTableEnum() {
    }

    SyncTableEnum(String tableName, String insertSql, String querySql, String updateSql, String deleteSql) {
        this.tableName = tableName;
        this.insertSql = insertSql;
        this.updateSql = updateSql;
        this.deleteSql = deleteSql;
        this.querySql = querySql;
    }
}

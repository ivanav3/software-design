/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public interface OpstiDomenskiObjekat {

    public abstract String getTableName();

    public abstract String getJoinTableName();

    public abstract String getColumnsForInsert();

    public abstract String getParamsForInsert();

    public abstract void setParamsForInsert(PreparedStatement statement,
            OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException;

    public abstract boolean containsAutoIncrementPK();

    public abstract void setAutoIncrementPrimaryKey(Long primaryKey);

    public abstract Object getFilter();

    public abstract void setFilter(Object filter);
    
    public abstract Object createObject();

    public abstract void setColumnsFromResultSet(ResultSet rs) throws SQLException;
    
    public abstract String updateValues();
    
    public abstract String updateCondition();

    public abstract Object getIdentifier();

}

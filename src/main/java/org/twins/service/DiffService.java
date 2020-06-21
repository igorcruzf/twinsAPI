package org.twins.service;

import io.agroal.api.AgroalDataSource;
import org.twins.model.Data;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DiffService {

    public DiffService(){}

    public List<Data> diff(AgroalDataSource defaultDataSource, AgroalDataSource twinDataSource) throws SQLException {
        Connection connection = defaultDataSource.getConnection();
        Connection twinConnection = twinDataSource.getConnection();

        ResultSet twinResultSet = twinConnection.createStatement().executeQuery("SELECT * FROM data_twin");
        StringBuilder getDiffQuery = new StringBuilder("SELECT * FROM data_twin WHERE name NOT IN (?");
        List<String> twinNames = new ArrayList<>();

        twinResultSet.next();
        twinNames.add(twinResultSet.getString("name"));
        while(twinResultSet.next()){
            twinNames.add(twinResultSet.getString("name"));
            getDiffQuery.append(", ?");
        }
        getDiffQuery.append(")");

        PreparedStatement getDifferences = connection.prepareStatement(getDiffQuery.toString());
        for(int i = 0; i < twinNames.size(); i++) {
            getDifferences.setString(i+1, twinNames.get(i));
        }
        ResultSet resultSet = getDifferences.executeQuery();

        List<Data> dataList = new ArrayList<>();
        while(resultSet.next()){
            dataList.add(new Data(resultSet.getString("name"), resultSet.getString("city"), resultSet.getInt("age")));
        }

        return dataList;
        }
    }


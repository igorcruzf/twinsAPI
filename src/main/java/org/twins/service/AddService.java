package org.twins.service;

import io.agroal.api.AgroalDataSource;
import org.twins.model.Data;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class AddService {

    public void add(AgroalDataSource dataSource, Data data) throws SQLException {
        Connection connection = dataSource.getConnection();
        String addObjectQuery = "INSERT INTO data_twin VALUES (?, ?, ?)";
        PreparedStatement addObjectDB = connection.prepareStatement(addObjectQuery);
        addObjectDB.setString(1,data.getName());
        addObjectDB.setString(2, data.getCity());
        addObjectDB.setInt(3, data.getAge());
        addObjectDB.executeUpdate();
    }
}

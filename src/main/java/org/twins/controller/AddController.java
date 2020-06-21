package org.twins.controller;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import org.twins.model.Data;
import org.twins.service.AddService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/add")
public class AddController {

    @Inject
    AgroalDataSource defaultDataSource;

    @Inject
    @DataSource("twin")
    AgroalDataSource twinDataSource;

    @Inject
    AddService addService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public boolean addBoth(Data data) throws SQLException {
        add1(data);
        add2(data);
        return true;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/1")
    public boolean add1(Data data) throws SQLException {
        addService.add(defaultDataSource, data);
        return true;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/2")
    public boolean add2(Data data) throws SQLException {
        addService.add(twinDataSource, data);
        return true;
    }

}

package org.twins.controller;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import org.twins.model.Data;
import org.twins.service.DiffService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/diff")
public class DiffController {

    @Inject
    AgroalDataSource defaultDataSource;

    @Inject
    @DataSource("twin")
    AgroalDataSource twinDataSource;

    @Inject
    DiffService diffService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<List<Data>> dbAll() throws SQLException {
        List<List<Data>> dataList = new ArrayList<>();
        dataList.add(db1());
        dataList.add(db2());
        return dataList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/1")
    public List<Data> db1() throws SQLException {
        return diffService.diff(defaultDataSource, twinDataSource);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/2")
    public List<Data> db2() throws SQLException {
        return diffService.diff(twinDataSource, defaultDataSource);
    }


}
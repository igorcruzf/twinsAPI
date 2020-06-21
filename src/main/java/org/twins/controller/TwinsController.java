package org.twins.controller;

import org.twins.model.Data;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/twins")
public class TwinsController {

    @Inject
    DiffController diffController;

    @Inject
    AddController addController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<List<Data>> addDiff() throws SQLException {
        List<List<Data>> dataList = new ArrayList<>();
        dataList.add(addDiff1());
        dataList.add(addDiff2());
        return dataList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/1")
    public List<Data> addDiff1() throws SQLException {
        List<Data> dataList = diffController.db2();
        dataList.forEach(data -> {
            try {
                addController.add1(data);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return dataList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/2")
    public List<Data> addDiff2() throws SQLException {
        List<Data> dataList = diffController.db1();
        dataList.forEach(data -> {
            try {
                addController.add2(data);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return dataList;
    }


}

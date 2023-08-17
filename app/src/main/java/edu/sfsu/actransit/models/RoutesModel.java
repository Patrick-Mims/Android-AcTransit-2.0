package edu.sfsu.actransit.models;

public class RoutesModel {
    private String description;
    private String name;
    private String routeId;

    public RoutesModel(String description, String name, String routeId) {
        this.description = description;
        this.name = name;
        this.routeId = routeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
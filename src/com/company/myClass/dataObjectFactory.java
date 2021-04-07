package com.company.myClass;

public class dataObjectFactory {
    public dataObject getData(String shapeType, String id, String name) {
        if (shapeType == null) return null;
        if (shapeType.equalsIgnoreCase("COURSE")) return new Course( id,  name);
        if (shapeType.equalsIgnoreCase("STUDENT")) return new Student( id,  name);
        return null;
    }
}

package com.company.myClass;

public class dataObjectFactory {
    public dataObject getShape(String shapeType) {
        if (shapeType == null) return null;
        if (shapeType.equalsIgnoreCase("COURSE")) return new Course();
        if (shapeType.equalsIgnoreCase("STUDENT")) return new Student();
        return null;
    }
}

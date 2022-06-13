package Reflections;


import java.util.List;

public class Relation {
    public String entityName;
    public String TypeOfRelation;

    public List<String> joinColumns;

    @Override
    public String toString() {
        return "Relation{" +
                "entityName='" + entityName + '\'' +
                ", TypeOfRelation='" + TypeOfRelation + '\'' +
                ", joinColumns=" + joinColumns +
                '}';
    }
}

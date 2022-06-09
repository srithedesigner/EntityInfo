package src.main.java.Reflections;

import java.util.List;

public class EntityInfo {

    public String name;
    public String tableName;
    public List<Relation> relationList;

    @Override
    public String toString() {
        return "EntityInfo{" +
                "name='" + name + '\'' +
                ", tableName='" + tableName + '\'' +
                ", relationList=" + relationList +
                '}';
    }
}

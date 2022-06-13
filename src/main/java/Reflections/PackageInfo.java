package Reflections;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageInfo {

    private Reflections reflections;

    public PackageInfo(String packageName) {
        this.reflections = new Reflections(packageName, new TypeAnnotationsScanner(), new SubTypesScanner(false));
    }

    public List<EntityInfo> getAllEntities(){
        List<Class<?>> entities = this.reflections.getTypesAnnotatedWith(javax.persistence.Entity.class).stream().toList();

        List<EntityInfo> entityInfos = new ArrayList<>();
        for(Class<?> entity : entities){

            List<Field> fields = Arrays.stream(entity.getDeclaredFields()).toList();
            List<Relation> relations = new ArrayList<>();
            for (Field field : fields){

                if(field.getAnnotation(javax.persistence.ManyToOne.class) != null){
                    List<String> joins = new ArrayList<>();
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "ManyToOne";

                    handle(relations, field, joins, relation);

                }

                if(field.getAnnotation(javax.persistence.OneToMany.class) != null){
                    List<String> joins = new ArrayList<>();
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "OneToMany";

                    handle(relations, field, joins, relation);

                }

                if(field.getAnnotation(javax.persistence.OneToOne.class) != null){
                    List<String> joins = new ArrayList<>();
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "ManyToOne";

                    handle(relations, field, joins, relation);

                }

                if(field.getAnnotation(javax.persistence.ManyToMany.class) != null){
                    List<String> joins = new ArrayList<>();
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "ManyToOne";

                    handle(relations, field, joins, relation);

                }




            }

            if(relations.size() != 0){
                EntityInfo entityInfo = new EntityInfo();
                entityInfo.name = entity.getName();
                entityInfo.relationList = relations;
                entityInfo.tableName = entity.getAnnotation(javax.persistence.Table.class).name();
                entityInfos.add(entityInfo);
            }


        }

        return entityInfos;





    }

    private void handle(List<Relation> relations, Field field, List<String> joins, Relation relation) {
        if(field.getAnnotation(JoinColumn.class) != null){
            String joinColumn = field.getAnnotation(JoinColumn.class).name();
            joins.add(joinColumn);
        }

        if(field.getAnnotation(JoinColumns.class) != null){
            JoinColumn[] joinColumns = field.getAnnotation(JoinColumns.class).value();
            for(JoinColumn joinColumn : joinColumns){
                joins.add(joinColumn.name());
            }
        }

        relation.joinColumns = joins;
        if(joins.size() != 0){
            relations.add(relation);
        }
    }
}

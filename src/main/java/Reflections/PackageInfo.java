package src.main.java.Reflections;

import com.google.common.reflect.Reflection;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageInfo {

    private String packageName;
    private Reflections reflections;

    public PackageInfo(String packageName) {
        this.packageName = packageName;
        this.reflections = new Reflections(packageName, new TypeAnnotationsScanner(), new SubTypesScanner(false));
    }

    public List<EntityInfo> getAllEntities(){
        List<Class<?>> entities = this.reflections.getTypesAnnotatedWith(javax.persistence.Entity.class).stream().toList();

        List<EntityInfo> entityInfos = new ArrayList<>();
        for(Class<?> entity : entities){

            List<Field> fields = Arrays.stream(entity.getDeclaredFields()).toList();
            List<Relation> relations = new ArrayList<>();
            for (Field field : fields){
                if(field.getAnnotation(javax.persistence.OneToOne.class) != null){
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "OneToOne";
                    relations.add(relation);
                }
                if(field.getAnnotation(javax.persistence.OneToMany.class) != null){
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "OneToMany";
                    relations.add(relation);
                }

                if(field.getAnnotation(javax.persistence.ManyToOne.class) != null){
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "ManyToOne";
                    relations.add(relation);
                }

                if(field.getAnnotation(javax.persistence.ManyToMany.class) != null){
                    Relation relation = new Relation();
                    relation.entityName = field.getName();
                    relation.TypeOfRelation = "ManyToMany";
                    relations.add(relation);
                }
            }

            EntityInfo entityInfo = new EntityInfo();
            entityInfo.name = entity.getName();
            entityInfo.relationList = relations;
            entityInfo.tableName = entity.getAnnotation(javax.persistence.Table.class).name();
            entityInfos.add(entityInfo);


        }

        return entityInfos;





    }
}

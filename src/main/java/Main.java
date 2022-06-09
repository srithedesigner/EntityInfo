package src.main.java;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import src.main.java.Reflections.EntityInfo;
import src.main.java.Reflections.PackageInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    /*public Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new TypeAnnotationsScanner(), new SubTypesScanner(false));
        return new HashSet<>(reflections.getTypesAnnotatedWith(Entity.class));
    }

    public static void main(String[] args) throws NoSuchFieldException {

        Set<Class<?>> annotated = new Main().findAllClassesUsingReflectionsLibrary("src.main.java");

        Class<?> user = annotated.stream().toList().get(0);

        System.out.println(Arrays.toString(user.getAnnotations()));

        System.out.println(Arrays.toString(user.getDeclaredField("tasks").getAnnotations()));




    }


    private String getCurrentPackage() {
        String packageName = this.getClass().getName();
        System.out.println(packageName);
        return packageName;
    }*/


    public static void main(String[] args) {

        PackageInfo packageInfo = new PackageInfo("src.main.java.Models");
        for(EntityInfo entityInfo : packageInfo.getAllEntities()){
            System.out.println(entityInfo);
        }
    }
}

import Reflections.EntityInfo;
import Reflections.PackageInfo;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        PackageInfo packageInfo = new PackageInfo("Models");
        for(EntityInfo entityInfo : packageInfo.getAllEntities()){
            System.out.println(entityInfo);
        }
    }
}

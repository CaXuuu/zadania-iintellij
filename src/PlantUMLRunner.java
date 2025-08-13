import java.io.File;
import java.io.FileWriter;

public class PlantUMLRunner {
    private static String plantUmlJarPath;
    public static void setPath(String path){
        plantUmlJarPath = path;
    }
public static void generate(String data, String outputPath,String fileName){
        File directory = new File(outputPath);
        directory.mkdirs();
        File file = new File(outputPath + "/" + fileName);
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
}

}

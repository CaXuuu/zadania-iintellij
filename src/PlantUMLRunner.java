import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUMLRunner {
    private static String plantUmlJarPath;

    public static void setPath(String path){
        plantUmlJarPath = path;
    }

    public static void generate(String data, String outputPath, String fileName){
        File directory = new File(outputPath);
        directory.mkdirs();

        File file = new File(outputPath + "/" + fileName);

        try{
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", plantUmlJarPath, file.getAbsolutePath());
            Process process = pb.start();
            process.waitFor();

        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
package shared.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils
{
    /**
     * The mutual path from any devices is created in order to work
     * @return mutual path
     */
    public static String getMutualPath ()
    {
        Path currentRelativePath = Paths.get("");
        String s = (currentRelativePath.toAbsolutePath().toString()).replace("\\", "/");
        return s;
    }
}

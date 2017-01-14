/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package c3p0.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    
    /**
    * Read a properties file from the classpath and return a Properties object
    * @param filename
    * @return
    * @throws IOException
    */
    static public Properties readProperties(String filename) throws IOException{
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        props.load(stream);
        return props;
    }
}

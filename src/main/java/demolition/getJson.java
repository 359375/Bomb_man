package demolition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;



import processing.data.JSONArray;
import processing.data.JSONObject;

public class getJson {
    // get detail about map from given file 
    public static JSONObject[]  getJsonA(String jsonpath) {
        String jsonStr = "";
        try {
            File file = new File(jsonpath);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            String JsonContext = jsonStr;
            JSONObject a= JSONObject.parse(JsonContext);
            JSONArray b= a.getJSONArray("levels");
            //level1 
            JSONObject c1 = b.getJSONObject(0);
            //level2
            JSONObject c2 = b.getJSONObject(1);
            //time
            JSONObject[] sss= {c1,c2};
            return  sss;
        } catch (Exception e) {
            return null;
        }
    }
    // get time from file 
    public static int  getJsontime(String jsonpath) {
        String jsonStr = "";
        try {
            File file = new File(jsonpath);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            String JsonContext = jsonStr;
            JSONObject a= JSONObject.parse(JsonContext);
        

            //time
            int c= a.getInt("lives");
            
            return  c;
        } catch (Exception e) {
            return 0;
        }
    }

    // get map arrangment by using 
    public static String getString(String filename) throws Exception{
        String jsonStr = "";
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;

        } catch (Exception e) {
            return jsonStr;
        }



    }

    
    
}


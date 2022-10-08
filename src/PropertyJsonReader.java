import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;



public class PropertyJsonReader {
     private ArrayList<Square> squares = new ArrayList<>();
	 
     public PropertyJsonReader(){
         for(int i=0;i<40;i++)
             squares.add(null);
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");
             for(Object i:Land){
				 int id = Integer.parseInt((String)((JSONObject)i).get("id"));
				 String name = (String)((JSONObject)i).get("name");
				 int cost =Integer.parseInt((String)((JSONObject)i).get("cost"));
				 squares.set(id-1, new Land(id, name, cost, "Land"));
             }
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
                int id = Integer.parseInt((String)((JSONObject)i).get("id"));
				String name = (String)((JSONObject)i).get("name");
				int cost = Integer.parseInt((String)((JSONObject)i).get("cost"));
                 squares.set(id-1, new Railroad(id, name, cost, "RailRoad"));
             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){
                 int id = Integer.parseInt((String)((JSONObject)i).get("id"));
				 String name = (String)((JSONObject)i).get("name");
				 int cost = Integer.parseInt((String)((JSONObject)i).get("cost"));
                 squares.set(id-1, new Company(id, name, cost, "Company"));
             }
             
         } catch (IOException | ParseException e){
             e.printStackTrace();
         }
     }

    public ArrayList<Square> getSquares() {
        return squares;
    }


}
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class ListJsonReader{

    private ArrayList<String> chanceArrayList = new ArrayList<>();
    private ArrayList<String> communityChestArrayList = new ArrayList<>();

    public ListJsonReader(){
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            for(Object i:chanceList){
				 String item = ((String)((JSONObject)i).get("item"));
                 chanceArrayList.add(item);
            }
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
				String item = ((String)((JSONObject)i).get("item"));
                communityChestArrayList.add(item);
            }
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getChanceArrayList() {
        return chanceArrayList;
    }

    public ArrayList<String> getCommunityChestArrayList() {
        return communityChestArrayList;
    }


}


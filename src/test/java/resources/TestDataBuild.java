package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuild {

	public AddPlace AddPlacePayload(String name, String language, String address) {
		AddPlace add = new AddPlace();
		add.setAccuracy(50);
		add.setName(name);
		add.setPhone_number("(+91) 983 876 9899");
		add.setAddress(address);
		add.setLanguage(language);
		add.setWebsite("www.google.com");
		
		List<String> a = new ArrayList<String>();
		a.add("shoe park");
		a.add("park");
		add.setTypes(a);
		
		Location l = new Location();
		l.setLat(-31.245);
		l.setLng(31.245);
		
		add.setLocation(l);
		return add;
	}
	public String DeletePlacePayload(String placeId) {
		return "{\n"
				+ "    \"place_id\":\""+placeId+"\"\n"
				+ "}";
	}
}

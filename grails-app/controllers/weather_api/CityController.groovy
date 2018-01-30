package weather_api

import org.codehaus.groovy.grails.web.json.JSONObject;

class CityController {

    def index() { redirect action: "city" }
	def city={
	}
	def status(){
		def location=params.name
		String apiKey="e66a338ea708b28f6f90fb8eb772e24f"
		String urll = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+apiKey+"&units=metric"
		StringBuilder result=new StringBuilder()
		
		URL url= new URL(urll);
		URLConnection conn=url.openConnection();
		BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while((line=rd.readLine()) !=null)
		{
			result .append(line);
		}
		rd.close();
		String s=result.toString();
		//prints the json in console
		System.out.println(s);
		JSONObject  res=new JSONObject(s.toString());
		JSONObject data1=res.getJSONObject("main");
		def d1=data1.getString("temp")
		def d2=data1.getString("humidity")
		def d3=data1.getString("pressure")
		
		JSONObject data2=res.getJSONObject("wind");
		def d4=data2.getString("speed")
		[d1:d1,d2:d2,d3:d3,d4:d4,loc:location]
		}
}

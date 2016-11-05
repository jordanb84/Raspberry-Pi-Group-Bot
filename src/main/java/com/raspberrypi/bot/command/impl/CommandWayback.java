package com.raspberrypi.bot.command.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.raspberrypi.bot.command.Command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class CommandWayback extends Command {
	public CommandWayback(){
		super("wayback", new String[] {}, "Gives you a link to the oldest version of a website, or the website on a specific date in a YYYYMMDD format (ex. 20010101)", "wayback <site> or wayback <site> <date>");
	}
	
	private JsonElement parseJson(JsonElement element, String lookingFor){
		JsonObject obj = element.getAsJsonObject();
		Set<Map.Entry<String, JsonElement>> elements = obj.entrySet();
		
		for(Map.Entry<String, JsonElement> curElement : elements){
			if(curElement.getKey().equals(lookingFor)){
				return curElement.getValue();
			}
		}
		
		return null;
	}
	
	@Override
	public void onUse(DiscordAPI api, String[] arguments, Message callMessage) throws Exception {
		String message = "";
		
		JsonParser parser = new JsonParser();
		HttpGet request = null;
		ResponseHandler<String> handler = new ResponseHandler<String>(){
			
			public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			}
			
		};;
		
		String json = "";
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		if(arguments.length == 2){
			request = new HttpGet("http://archive.org/wayback/available?url="+arguments[1]+"&timestamp=19960101");
		} else if (arguments.length == 3){
			request = new HttpGet("http://archive.org/wayback/available?url="+arguments[1]+"&timestamp="+arguments[2]);
			
		}
		
		json = client.execute(request, handler);
		
		JsonElement element = parser.parse(json);
		
		JsonElement archived_snapshots = parseJson(element, "archived_snapshots");
		JsonElement closest = parseJson(archived_snapshots, "closest");
		JsonElement url = parseJson(closest, "url");
		
		message = url.getAsString();
		callMessage.reply(message);
	}
}

package com.raspberrypi.bot.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Invite;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.server.ServerMemberAddListener;

public class EventGiveBerry implements ServerMemberAddListener {
	// Berry Eater, Berry Gobbler, Berry Legend
    public static String generalID = "208628299012898816"; // RPI #Berries channel
	//public static String GeneralID = "207079585173602304"; // Private testing
	@Override
	public void onServerMemberAdd(DiscordAPI api,User user,Server server) {
		
		// It wails in unhappiness without a Try/Catch
		Invite[] serverInvites = null;
		Collection<Role> serverRoles = server.getRoles();
		Map<String, Role> roles = new HashMap<String, Role>();
		try {
			serverInvites = server.getInvites().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		for(Role role: serverRoles){
            roles.put(role.getName(),role);	
            }
		
		for(Invite invite : serverInvites){
			if(invite.getUses() >= 250 && !invite.getCreator().getRoles(server).contains(roles.get("Berry Corp")) && !invite.getCreator().isBot() && invite.getServer().isMember(invite.getCreator() )){
				roles.get("Berry Corp").addUser(invite.getCreator());
					server.getChannelById(generalID).sendMessage(invite.getCreator().getMentionTag() + " **Got Berry Corp! THATS MENTAL!**");
					return;
		    }
			
			
			if(invite.getUses() >= 100 && !invite.getCreator().getRoles(server).contains(roles.get("Berry Legend")) && !invite.getCreator().isBot()&& invite.getServer().isMember(invite.getCreator())){
				roles.get("Berry Legend").addUser(invite.getCreator());
					server.getChannelById(generalID).sendMessage(invite.getCreator().getMentionTag() + " **Got Berry Legend! Woah!**");
					return;
		    }
			if(invite.getUses() >= 50 && !invite.getCreator().getRoles(server).contains(roles.get("Berry Gobbler")) && !invite.getCreator().isBot() && invite.getServer().isMember(invite.getCreator())){
				roles.get("Berry Gobbler").addUser(invite.getCreator());
				server.getChannelById(generalID).sendMessage(invite.getCreator().getMentionTag()+ " **Got Berry Gobbler! Cool!**");
				return;
		    }	
			if(invite.getUses() >= 100 && !invite.getCreator().getRoles(server).contains(roles.get("Berry Eater")) && !invite.getCreator().isBot()&& invite.getServer().isMember(invite.getCreator())){
				roles.get("Berry Eater").addUser(invite.getCreator());
				server.getChannelById(generalID).sendMessage(invite.getCreator().getMentionTag()+ " **Got Berry Eater!**");
				
				return;
		    }
		
		}


		}
    }

	





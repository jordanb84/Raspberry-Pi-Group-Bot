package com.raspberrypi.bot.events;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Invite;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.server.ServerJoinListener;

public class EventGiveBerry implements ServerJoinListener {
	// Berry Eater, Berry Gobbler, Berry Legend

	@Override
	public void onServerJoin(DiscordAPI API, Server Server) {
		System.out.println("Invited person");
		// It wails in unhappiness without a Try/Catch
		Invite[] serverInvites = null;
		Collection<Role> serverRoles = Server.getRoles();
		try {
			serverInvites = Server.getInvites().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		for(Invite invite : serverInvites){
			if(invite.getUses() >= 5){
				
				for(Role role: serverRoles){
					if (role.getName().equals("Berry Eater")){
						System.out.println(invite.getCode());
						System.out.println(invite.getUses());
						role.addUser(invite.getCreator());
					}
				}	
	        }

		}
    }
}


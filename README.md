# Raspberry Pi Group Bot
Discord bot for the Raspberry Pi Discord group

# Setup for Eclipse

1. Install the Egit and M2eclipse plugins if you have not already. Follow [this guide](http://agile.csc.ncsu.edu/SEMaterials/tutorials/install_plugin/index_v35.html) ( **Installing a Plug-in with an Update Site**) for each plugin, using  `http://download.eclipse.org/egit/updates` as the update site for Egit and `http://download.eclipse.org/technology/m2e/releases` as the update site for M2Eclipse

2. Clone the project from either your fork (if you made one) or this repository. **File > Import > Git > Projects from Git > Clone URI > Paste https://github.com/jordanb84/Raspberry-Pi-Group-Bot.git as the URI if you're not using a fork and fill out authentication information > Next > Next > Next > Select "Import as general project and click Next > Finish**

3. Setting up Maven: **Right click project > Configure > Convert to Maven Project** then **Right click project > Maven > Update Project > OK**

4. Adding bot token: If you do not already have a token, create one [here](https://discordapp.com/developers/applications/me). **New Application > Fill out information > Create > Create a Bot User > then under "Token" click "Click to reveal**. You now have your token. **Go back to Eclipse > [Find Launcher.java](http://i.imgur.com/yGu29pj.png) > Right click it > Run as > Java application. It will fail at first because you've not added a token yet. Press ALT + R, then press N > Select Launcher > Click the Arguments tab > Under "Program arguments", paste your token > Press Apply > Press Run**. You should now be able to run it by clicking the "Run" button on the top of Eclipse. To invite your version of the bot to a server so you may use it for testing, go [here](https://discordapp.com/developers/applications/me) again, click your application you created earlier, and find the **Client ID**. Then go to `https://discordapp.com/oauth2/authorize?client_id=%YOURID%&scope=bot&permissions=0` in your browser, replacing **%YOURID%** with the ID number.

5. **You're all set!** You should already know how to use git. Egit will allow you to do it from inside Eclipse, but I'd recommend doing it with the command line.

# TODO

1. Make TODO list. To make a suggestion, either add it here or open an issue.

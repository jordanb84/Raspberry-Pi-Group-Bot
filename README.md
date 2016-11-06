![build](https://api.travis-ci.org/jordanb84/Raspberry-Pi-Group-Bot.svg?branch=master)

# Raspberry Pi Group Bot
Discord bot for the Raspberry Pi Discord group

# Setup for Eclipse

1. Install the Egit and M2eclipse plugins if you have not already. Follow [this guide](http://agile.csc.ncsu.edu/SEMaterials/tutorials/install_plugin/index_v35.html) ( **Installing a Plug-in with an Update Site**) for each plugin, using  `http://download.eclipse.org/egit/updates` as the update site for Egit and `http://download.eclipse.org/technology/m2e/releases` as the update site for M2Eclipse

2. Clone the project from either your fork (if you made one) or this repository. **File > Import > Git > Projects from Git > Clone URI > Paste https://github.com/jordanb84/Raspberry-Pi-Group-Bot.git as the URI if you're not using a fork and fill out `authentication information > Next > Next > Next > Select "Import as general project and click Next > Finish**

3. Setting up Maven: **Right click project > Configure > Convert to Maven Project** then **Right click project > Maven > Update Project > Tick "Force Update of Snapshots/Releases" > OK**

4. Adding bot token: If you do not already have a token, create one [here](https://discordapp.com/developers/applications/me). **New Application > Fill out information > Create > Create a Bot User > then under "Token" click "Click to reveal**. You now have your token. **Go back to Eclipse > [Find Launcher.java](http://i.imgur.com/yGu29pj.png) > Right click it > Run as > Java application. It will fail at first because you've not added a token yet. Press ALT + R, then press N > Select Launcher > Click the Arguments tab > Under "Program arguments", paste your token > Press Apply > Press Run**. You should now be able to run it by clicking the "Run" button on the top of Eclipse. To invite your version of the bot to a server so you may use it for testing, go [here](https://discordapp.com/developers/applications/me) again, click your application you created earlier, and find the **Client ID**. Then go to `https://discordapp.com/oauth2/authorize?client_id=%YOURID%&scope=bot&permissions=0` in your browser, replacing **%YOURID%** with the ID number.

5. **You're all set!** You should already know how to use git. Egit will allow you to do it from inside Eclipse, but I'd recommend doing it with the command line.

# Adding your first Command

1. Under `com.raspberrypi.bot.command.impl`, create a new class for your command, eg `CommandExample`. Make it extend Command, then implement the required constructor(s)/method(s). [Here](https://github.com/jordanb84/Raspberry-Pi-Group-Bot/blob/master/src/main/java/com/raspberrypi/bot/command/impl/CommandExample.java) is an example of how a basic command should look.

2. Once your command is ready, it's time to register! Go under `com.raspberrypi.bot.Bot` and find the `registerCommands` method. Register your new command the same way the others there are registered.

3. **All done!**

# Adding your first game

0. **Introduction** - The bot provides an abstract, simple to use game API suited for easily constructing text based mini games.

1. Create a new Game class, such as `GameSnailRace` under the `com.raspberrypi.bot.game.impl` package.
   This class should extend `Game`. As you'll see once you generate the required functions and constuctor(s),
   the structure of a game is quite simple. You fill out basic information in the constructor, you perform each
   frame of logic under the `update` function, and you modify the game message for display under the `draw` function.
   Initiation of the game is done under the `create` function, and logic performed when the game is finished is done
   under the `endGame` function. All games are automatically multi threaded. All that is needed to start an instance
   of your game is for example `GameSnailRace snailRace = new GameSnailRace(args)` then `snailRace.start()`
   
2. Now you'll want to know about entities. These will be vital to almost any text game.
   For example, in the snail race game, we have the `EntitySnail` entity in
   `com.raspberrypi.bot.game.entity.impl`. All this does is store it's
   position and score, then modify those randomly each time it is called
   by the `GameSnailRace` game. The `update` function in your entity
   will be called every time the game which it is in updates the entity,
   which is usually every tick. Now that we have our `EntitySnail` which
   has a position and score, both of which are updated by the `EntitySnail`,
   the `GameSnailRace` game is able to draw each snail at it's position alongside
   it's score. The `GameSnailRace` game stores a list of snails that are in
   the race.

3. You should now have a basic understanding of how games work. If you feel something
   new is required in the API, feel free to add that functionality while implementing
   your game or request it by adding to the todo list. 

# Code Quality Standards

0. For a high quality, maintainable code base:
   
   
   Code should be simple and effective. Every piece of your code
   should do one thing and do it right, in the most obvious
   manner possible. Design patterns and naming conventions
   should be followed. I recommend you at least skim through
   [this book](http://ricardogeek.com/docs/clean_code.pdf),
   which is highly recommended and contains quotes from many
   brilliant people such as the inventor of C++.
   
   Clean code is something you learn through experience, so
   long as you have the will to concentrate on it. Start
   practicing and it will make you a far better programmer
   in the future.

# TODO

1. Snail/Emoji Races. We could have somebody build a command that people would pick emoji's or be assigned a snail to race.
2. Betting. We could also merge the betting and snail races or make them link .
3. Integrate database. It will set the database up automatically if you have database software installed and there is no existing database. Include abstract database managing functionality. This can be used for a variety of fun games and such.
4. Obstacles, powerups, and different maps for snail races. All can be achieved with a couple of nice enums.
5. Roles are automatically reassigned to people when they rejoin.
6. Later, the snail races could be made into a full fledged game aside from the mini game. In this game, it would be like a "grinding" Flash game where you play races, collect money based on how well you do, then you can use your money to buy perks and upgrades for your character. You can then unlock harder races etc to win more money.
7. dank mini game ideas


# Scandalous Soundboard

A scandalous soundboard compatible with Fabric for Minecraft 1.19.2 client and server side!

You can now directly put your audio and video files, the mod will automatically convert these files when you launch it!

## Explanation :

- Copy the jar in your mods folder
- Create a new folder named "Soundboard" (it will be automatically created)
- Put your sounds in this folder
- Run the jar (double click on it) to update the sounds
- Launch Minecraft!

### For server :

- Follow the steps above
- Just share your generated jar file to your friends
- Copy directly the jar file on your server
- Launch your server and enjoy!

You need to have the same sounds on server and client, otherwise it will bring you an error message!

### Keys : 

J : open the soundboard menu

Numpad keys : play the sounds

Numpad 0 : stop the playing sound

## Run Locally

You can run the jar file using command line too :

```bash
  java -jar jar_path/jar_name
```
## FAQ

#### Why do you need to run the jar ? Why is it not automatic ?

To play a sound you need a sounds.json file which indicates to the game what 
files exists, and a sounds folder where sounds are stored. 
The problem is that I cannot overwrite the file when it's already in use on Windows but I need
to generate these files (it works on Linux but it's generally a bad idea).

I may be could use Mixin but I do not have the skill for the moment, this is 
my first mod for Minecraft.

It's a simple soundboard, the used way is just scandalous.

## License

[MIT](https://choosealicense.com/licenses/mit/)

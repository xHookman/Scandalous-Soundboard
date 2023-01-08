package xhookman.soundboard;

import net.fabricmc.api.ModInitializer;
import xhookman.soundboard.soundboard.SoundboardServer;

import static net.fabricmc.fabric.impl.transfer.TransferApiImpl.LOGGER;

public class Soundboard implements ModInitializer {
    public static final String MOD_ID = "soundboard";
    @Override
    public void onInitialize() {
        LOGGER.info("Je suuis le serveur (" + MOD_ID + " est chargé)");
        SoundboardServer soundboard = new SoundboardServer();
        soundboard.playSoundWhenKeyPressed();
    }
}

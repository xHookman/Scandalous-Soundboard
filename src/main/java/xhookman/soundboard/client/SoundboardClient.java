package xhookman.soundboard.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static xhookman.soundboard.Soundboard.MOD_ID;


@Environment(EnvType.CLIENT)
public class SoundboardClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Je suis le client !!! (" + MOD_ID + " est chargé)");
        new xhookman.soundboard.soundboard.SoundboardClient();
    }
}

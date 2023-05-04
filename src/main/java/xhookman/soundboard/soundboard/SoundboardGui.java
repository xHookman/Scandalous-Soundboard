package xhookman.soundboard.soundboard;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Identifier;
import java.util.Hashtable;

public class SoundboardGui extends Screen {
    private final SoundboardClient soundboardClient;
    private ScrollableButtonListWidget buttonList;

    public SoundboardGui(SoundboardClient soundboardClient) {
        super(Text.of("Soundboard"));
        this.soundboardClient = soundboardClient;
    }

    @Override
    public void init() {
        super.init();
        int BUTTON_HEIGHT = 20;
        int BUTTON_WIDTH = 100;
        Hashtable<Identifier, SoundEvent> sounds = SoundboardServer.getSoundHashtable();

        buttonList = new ScrollableButtonListWidget(this, MinecraftClient.getInstance(), width, height, 0, height, BUTTON_HEIGHT + 1);
        buttonList.setRenderBackground(false);
        int iPosLastBtn = 0;

        buttonList = new ScrollableButtonListWidget(this, this.client, width, height, 0, height, BUTTON_HEIGHT + 1);
        for (Identifier soundId : sounds.keySet()) {
            buttonList.addButton(new ButtonWidget(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT,
                    Text.of(soundId.getPath() + " : Key " + (++iPosLastBtn)),
                    (buttonWidget) -> {
                        soundboardClient.playSound(soundId);
                    }));
        }

        this.addSelectableChild(buttonList);

        //add a button to stop sound in the bottom right corner of the screen
        this.addSelectableChild(new ButtonWidget(this.width - BUTTON_WIDTH - 10, this.height - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT, Text.of("Stop sound (Key 0)"), (button) -> soundboardClient.stopSound()));
    }
}

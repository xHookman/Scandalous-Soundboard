package xhookman.soundboard.soundboard;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Identifier;
import java.util.Hashtable;

public class SoundboardGui extends Screen {
    private final SoundboardClient soundboardClient;
    private final int BUTTON_HEIGHT = 20;
    private final int BUTTON_WIDTH = 100;
    private final Hashtable<Identifier, SoundEvent> sounds;

    private int iPosLastBtn;

    public SoundboardGui(SoundboardClient soundboardClient) {
        super(Text.of("Soundboard"));
        this.soundboardClient = soundboardClient;
        sounds = SoundboardServer.getSoundHashtable();
    }

    private void addButton(String text, Runnable onClick){
        int buttonSpacing = 7; // L'espacement entre les boutons

        int buttonsPerRow = (int) Math.floor((width - buttonSpacing) / (BUTTON_WIDTH + buttonSpacing));
        int maxRows = (int) Math.floor((height - 2 * buttonSpacing) / (BUTTON_HEIGHT + buttonSpacing));
        int maxButtons = buttonsPerRow * maxRows;

        int row = (int) Math.floor(iPosLastBtn / (double) buttonsPerRow);
        int col = iPosLastBtn % buttonsPerRow;
        int x = buttonSpacing + col * (BUTTON_WIDTH + buttonSpacing);
        int y = buttonSpacing + row * (BUTTON_HEIGHT + buttonSpacing);

        ButtonWidget button = new ButtonWidget(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, Text.of(text), (b) -> onClick.run());

        if(iPosLastBtn >= maxButtons){
            System.out.println("Too many buttons, not adding " + text);
            return;
        }

        this.addDrawableChild(button);
    }

    @Override
    public void init() {
        for(iPosLastBtn=0; iPosLastBtn<sounds.size(); iPosLastBtn++){
            Identifier soundId = (Identifier) sounds.keySet().toArray()[iPosLastBtn];
            addButton(soundId.getPath() + " : Key " + (iPosLastBtn + 1), () -> soundboardClient.playSound(soundId));
        }

       /* for (iPosLastBtn = 0; iPosLastBtn < 20; iPosLastBtn++) {
            addButton("Button " + (iPosLastBtn + 1), () -> System.out.println("Button " + (iPosLastBtn + 1) + " clicked"));
        }*/

        //add a button to stop sound in the bottom right corner of the screen
        this.addDrawableChild(new ButtonWidget(this.width - BUTTON_WIDTH - 10, this.height - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT, Text.of("Stop sound (Key 0)"), (button) -> soundboardClient.stopSound()));
    }
}

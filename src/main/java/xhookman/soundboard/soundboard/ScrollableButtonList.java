package xhookman.soundboard.soundboard;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import java.util.ArrayList;
import java.util.List;

public class ScrollableButtonList extends ScrollableButtonListWidget {
    private final List<ButtonWidget> buttons;
    public ScrollableButtonList(Screen parent, MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
        super(parent, client, width, height, top, bottom, itemHeight);
        this.buttons = new ArrayList<>();
        super.setRenderBackground(false);
    }
    @Override
    public void addButton(ButtonWidget button) {
        super.addButton(button);
        buttons.add(button);
    }

    //Haha yes because the onClick event of ButtonWidget does not call anything in a ScrollableButtonListWidget
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Handle mouse click events
        for (ButtonWidget buttonWidget : buttons)
            if (buttonWidget.isMouseOver(mouseX, mouseY)) {
                buttonClicked(buttonWidget);
                return true;
            }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void buttonClicked(ButtonWidget button) {
        // Handle button click events
            button.onPress();
    }
}

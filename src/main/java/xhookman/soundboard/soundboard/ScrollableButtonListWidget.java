package xhookman.soundboard.soundboard;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Collections;
import java.util.List;

public class ScrollableButtonListWidget extends ElementListWidget<ScrollableButtonListWidget.ScrollableButtonEntry> {
    private final Screen parent;

    public ScrollableButtonListWidget(Screen parent, MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
        super(client, width, height, top, bottom, itemHeight);
        this.parent = parent;
    }

    public void addButton(ButtonWidget button) {
        addEntry(new ScrollableButtonEntry(button));
    }

    @Override
    public int getScrollbarPositionX() {
        return this.width - 6;
    }

    public static class ScrollableButtonEntry extends ElementListWidget.Entry<ScrollableButtonListWidget.ScrollableButtonEntry> {

        private final ButtonWidget button;

        public ScrollableButtonEntry(ButtonWidget button) {
            this.button = button;
        }

        @Override
        public void render(MatrixStack matrices, int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
            button.y = y;
            button.render(matrices, mouseX, mouseY, delta);
        }

        @Override
        public List<? extends Element> children() {
            return Collections.singletonList(button);
        }

        @Override
        public List<? extends Selectable> selectableChildren() {
            return Collections.singletonList(button);
        }
    }
}

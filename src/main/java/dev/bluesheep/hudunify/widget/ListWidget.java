package dev.bluesheep.hudunify.widget;

import dev.bluesheep.hudunify.api.widget.AbstractWidget;
import dev.bluesheep.hudunify.api.widget.IHasChildrenWidget;
import dev.bluesheep.hudunify.api.widget.IWidget;
import dev.bluesheep.hudunify.api.widget.Anchor;
import dev.bluesheep.hudunify.function.cache.CachedValueDirection;
import dev.bluesheep.hudunify.function.cache.CachedValueInt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

import java.util.*;
import java.util.stream.Collectors;

import static dev.bluesheep.hudunify.HudUnify.rl;

public class ListWidget extends AbstractWidget implements IHasChildrenWidget {
    private transient int tick = 0;
    private transient Map<Integer, Vec2> sizes = new HashMap<>();
    private transient float maxSize = 0f;
    private transient int totalSize = 0;

    private List<IWidget> widgets;
    private String direction = "HORIZONTAL";
    private final transient CachedValueDirection directionCache = new CachedValueDirection(() -> direction);
    private String padding = "0";
    private final transient CachedValueInt paddingCache = new CachedValueInt(() -> padding);

    @Override
    public void init() {
        // TOP_LEFT以外に設定してると並び順を無視してしまうので、方向によってTOP, LEFTに強制的に設定する
        widgets.forEach(widget -> {
            Anchor anchor = widget.getAnchor();
            switch (getDirection()) {
                case HORIZONTAL -> {
                    if (anchor.getX() != 0)
                        widget.setAnchor(Anchor.fromFloat(0, anchor.getY()).name());
                }
                case VERTICAL -> {
                    if (anchor.getY() != 0)
                        widget.setAnchor(Anchor.fromFloat(anchor.getX(), 0).name());
                }
            }
        });
    }

    @Override
    public List<IWidget> getWidgets() {
        return widgets;
    }

    @Override
    public void addWidget(IWidget widget) {
        widgets.add(widget);
    }

    public Direction getDirection() {
        return directionCache.getValue();
    }

    public String getDirectionRaw() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPadding() {
        return paddingCache.getValue();
    }

    public String getPaddingRaw() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    @Override
    public ResourceLocation getId() {
        return rl("list");
    }

    @Override
    public int getWidth() {
        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            updateSizes();
        }
        return getDirection() == Direction.HORIZONTAL ? totalSize : (int) maxSize;
    }

    @Override
    public int getHeight() {
        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            updateSizes();
        }
        return getDirection() == Direction.VERTICAL ? totalSize : (int) maxSize;
    }

    private void updateSizes() {
//        LOGGER.info(String.valueOf(Minecraft.getInstance().gui.getGuiTicks()));
        sizes = widgets.stream()
                .collect(Collectors.toMap(
                        IWidget::hashCode,
                        widget -> new Vec2(widget.getWidth(), widget.getHeight())
                ));
        maxSize = sizes.values().stream()
                .map(size -> getDirection() == Direction.VERTICAL ? size.x : size.y)
                .max(Comparator.naturalOrder())
                .orElse(0f);
        totalSize = sizes.values().stream()
                .mapToInt(size -> (int) (getDirection() == Direction.HORIZONTAL ? size.x : size.y) + getPadding())
                .sum() - getPadding();
    }

    @Override
    public void render(GuiGraphics guiGraphics, float partialTick, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;

        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            updateSizes();
        }

        Direction direction = getDirection();

        int childrenOffsetX = calculatePosX(
                direction == Direction.VERTICAL ? (int) maxSize : totalSize,
                maxWidth
        ) + offsetX;
        int childrenOffsetY = calculatePosY(
                direction == Direction.HORIZONTAL ? (int) maxSize : totalSize,
                maxHeight
        ) + offsetY;
        for (IWidget widget : widgets) {
            widget.render(
                    guiGraphics, partialTick,
                    childrenOffsetX,
                    childrenOffsetY,
                    direction == Direction.VERTICAL ? (int) maxSize : totalSize,
                    direction == Direction.HORIZONTAL ? (int) maxSize : totalSize
            );
            Vec2 size = sizes.get(widget.hashCode());
            switch (direction) {
                case HORIZONTAL -> childrenOffsetX += (int) size.x + getPadding();
                case VERTICAL -> childrenOffsetY += (int) size.y + getPadding();
            }
        }
    }

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }
}

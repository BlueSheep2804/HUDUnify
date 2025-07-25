package io.github.bluesheep2804.unifyhud.widget;

import io.github.bluesheep2804.unifyhud.api.widget.AbstractWidget;
import io.github.bluesheep2804.unifyhud.api.widget.IHasChildrenWidget;
import io.github.bluesheep2804.unifyhud.api.widget.IWidget;
import io.github.bluesheep2804.unifyhud.api.widget.OriginPoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

import java.util.*;
import java.util.stream.Collectors;

import static io.github.bluesheep2804.unifyhud.UnifyHud.LOGGER;
import static io.github.bluesheep2804.unifyhud.UnifyHud.rl;

public class ListWidget extends AbstractWidget implements IHasChildrenWidget {
    private transient int tick = 0;
    private transient Map<Integer, Vec2> sizes = new HashMap<>();
    private transient float maxSize = 0f;
    private transient int totalSize = 0;

    private List<IWidget> widgets;
    private Direction direction = Direction.HORIZONTAL;
    private int padding = 0;

    @Override
    public void init() {
        // TOP_LEFT以外に設定してると並び順を無視してしまうので、方向によってTOP, LEFTに強制的に設定する
        widgets.forEach(widget -> {
            OriginPoint origin = widget.getOriginPoint();
            switch (direction) {
                case HORIZONTAL -> {
                    if (origin.getX() != 0)
                        widget.setOriginPoint(OriginPoint.fromFloat(0, origin.getY()));
                }
                case VERTICAL -> {
                    if (origin.getY() != 0)
                        widget.setOriginPoint(OriginPoint.fromFloat(origin.getX(), 0));
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
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
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
        return direction == Direction.HORIZONTAL ? totalSize : (int) maxSize;
    }

    @Override
    public int getHeight() {
        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            updateSizes();
        }
        return direction == Direction.VERTICAL ? totalSize : (int) maxSize;
    }

    private void updateSizes() {
//        LOGGER.info(String.valueOf(Minecraft.getInstance().gui.getGuiTicks()));
        sizes = widgets.stream()
                .collect(Collectors.toMap(
                        IWidget::hashCode,
                        widget -> new Vec2(widget.getWidth(), widget.getHeight())
                ));
        maxSize = sizes.values().stream()
                .map(size -> direction == Direction.VERTICAL ? size.x : size.y)
                .max(Comparator.naturalOrder())
                .orElse(0f);
        totalSize = sizes.values().stream()
                .mapToInt(size -> (int) (direction == Direction.HORIZONTAL ? size.x : size.y) + padding)
                .sum() - padding;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int offsetX, int offsetY, int maxWidth, int maxHeight) {
        if (!isVisible()) return;

        int nowTick = Minecraft.getInstance().gui.getGuiTicks();
        if (tick != nowTick) {
            tick = nowTick;
            updateSizes();
        }

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
                    guiGraphics,
                    childrenOffsetX,
                    childrenOffsetY,
                    direction == Direction.VERTICAL ? (int) maxSize : totalSize,
                    direction == Direction.HORIZONTAL ? (int) maxSize : totalSize
            );
            Vec2 size = sizes.get(widget.hashCode());
            switch (direction) {
                case HORIZONTAL -> childrenOffsetX += (int) size.x + padding;
                case VERTICAL -> childrenOffsetY += (int) size.y + padding;
            }
        }
    }

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }
}

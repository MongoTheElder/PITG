package tv.mongotheelder.pitg.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ConnectedTextureBakedModel implements IDynamicBakedModel {

    private int getOrientation(boolean north, boolean east, boolean south, boolean west) {
        int o = 0;
        if (north) o += 1;
        if (east) o += 2;
        if (south) o += 4;
        if (west) o += 8;

        return o;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        boolean north = state.getValue(BlockStateProperties.NORTH);
        boolean east = state.getValue(BlockStateProperties.EAST);
        boolean south = state.getValue(BlockStateProperties.SOUTH);
        boolean west = state.getValue(BlockStateProperties.WEST);
        boolean northSouth = north || south;
        boolean eastWest = east || west;
        boolean ne = north && east;
        boolean nw = north && west;
        boolean se = south && east;
        boolean sw = south && west;

        int orientation = getOrientation(north, east, south, west);
        if (state.getBlock() instanceof GlassPane) {
            if (north) {

            }

            return null;
        } else if (state.getBlock() instanceof DualGlassPane) {
            return null;
        }
        return null;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return null;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return null;
    }
}

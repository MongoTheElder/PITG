package tv.mongotheelder.pitg.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GlassPaneTableRecipeSerializer<T extends SingleItemRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
    final GlassPaneTableRecipeSerializer.IRecipeFactory<T> factory;

    public GlassPaneTableRecipeSerializer(GlassPaneTableRecipeSerializer.IRecipeFactory<T> p_i50146_1_) {
        this.factory = p_i50146_1_;
    }

    public T fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
        String s = JSONUtils.getAsString(p_199425_2_, "group", "");
        Ingredient ingredient;
        if (JSONUtils.isArrayNode(p_199425_2_, "ingredient")) {
            ingredient = Ingredient.fromJson(JSONUtils.getAsJsonArray(p_199425_2_, "ingredient"));
        } else {
            ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(p_199425_2_, "ingredient"));
        }

        String s1 = JSONUtils.getAsString(p_199425_2_, "result");
        int i = JSONUtils.getAsInt(p_199425_2_, "count");
        ItemStack itemstack = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
        return this.factory.create(p_199425_1_, s, ingredient, itemstack);
    }

    public T fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
        String s = p_199426_2_.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(p_199426_2_);
        ItemStack itemstack = p_199426_2_.readItem();
        return this.factory.create(p_199426_1_, s, ingredient, itemstack);
    }

    public void toNetwork(PacketBuffer p_199427_1_, T p_199427_2_) {
        p_199427_1_.writeUtf(p_199427_2_.getGroup());
        p_199427_2_.getIngredients().get(0).toNetwork(p_199427_1_);
        p_199427_1_.writeItem(p_199427_2_.getResultItem());
    }

    public interface IRecipeFactory<T extends SingleItemRecipe> {
        T create(ResourceLocation p_create_1_, String p_create_2_, Ingredient p_create_3_, ItemStack p_create_4_);
    }
}


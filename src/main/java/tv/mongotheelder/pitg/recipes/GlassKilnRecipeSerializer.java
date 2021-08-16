package tv.mongotheelder.pitg.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class GlassKilnRecipeSerializer<T extends AbstractCookingRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
    private final int defaultCookingTime;
    private final GlassKilnRecipeSerializer.IFactory<T> factory;

    public GlassKilnRecipeSerializer(GlassKilnRecipeSerializer.IFactory<T> factory, int cookingTime) {
        this.defaultCookingTime = cookingTime;
        this.factory = factory;
    }

    public T fromJson(ResourceLocation location, JsonObject json) {
        String s = JSONUtils.getAsString(json, "group", "");
        JsonElement jsonelement = (JsonElement) (JSONUtils.isArrayNode(json, "ingredient") ? JSONUtils.getAsJsonArray(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient"));
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (json.get("result").isJsonObject())
            itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
        else {
            String s1 = JSONUtils.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
                return new IllegalStateException("Item: " + s1 + " does not exist");
            }));
        }
        float f = JSONUtils.getAsFloat(json, "experience", 0.0F);
        int i = JSONUtils.getAsInt(json, "cookingtime", this.defaultCookingTime);
        return this.factory.create(location, s, ingredient, itemstack, f, i);
    }

    public T fromNetwork(ResourceLocation location, PacketBuffer packetBuffer) {
        String s = packetBuffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(packetBuffer);
        ItemStack itemstack = packetBuffer.readItem();
        float f = packetBuffer.readFloat();
        int i = packetBuffer.readVarInt();
        return this.factory.create(location, s, ingredient, itemstack, f, i);
    }

    public void toNetwork(PacketBuffer packetBuffer, T recipe) {
        packetBuffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(packetBuffer);
        packetBuffer.writeItem(recipe.getResultItem());
        packetBuffer.writeFloat(recipe.getExperience());
        packetBuffer.writeVarInt(recipe.getCookingTime());
    }

    public interface IFactory<T extends AbstractCookingRecipe> {
        T create(ResourceLocation location, String recipeName, Ingredient ingredient, ItemStack stack, float experience, int cookingTime);
    }

}

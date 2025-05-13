package com.dragonblak.mysticalengineering.datagen;

import blusunrize.immersiveengineering.api.crafting.ClocheRecipe;
import blusunrize.immersiveengineering.api.crafting.TagOutput;
import blusunrize.immersiveengineering.client.utils.ClocheRenderFunctions;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.dragonblak.mysticalengineering.MysticalEngineering;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        GetSeeds(recipeOutput);
    }

    public void GetSeeds(RecipeOutput output){
        Collection<Crop> crops = MysticalAgricultureAPI.getCropRegistry().getCrops();

        for (Crop crop : crops) {
            CreateClocheRecipe(crop, output);
        }
    }

    public void CreateClocheRecipe(Crop crop, RecipeOutput output){
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MysticalEngineering.MODID, "cloche_" + crop.getName() + "_seed");

        Ingredient soil = Ingredient.of(crop.getTier().getFarmland());
        Ingredient seed = Ingredient.of(crop.getSeedsItem());
        TagOutput result = new TagOutput(crop.getEssenceItem(),2);

        ClocheRecipe recipe = new ClocheRecipe(result,seed,soil,1200, new ClocheRenderFunctions.RenderFunctionCrop(crop.getCropBlock()));

        output.accept(id, recipe, null);
    }
}

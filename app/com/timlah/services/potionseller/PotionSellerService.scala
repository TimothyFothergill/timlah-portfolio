package com.timlah.services.potionseller

import com.timlah.models.potionseller._
import scala.util.Random

class PotionSellerService {

    val newPotion = Potion("of Weakness I", PotionType.Tincture, Seq(PotionProperty("Weakness I", "Reduces your strength in battle by 10.", PropertyType.Debuff)))
    val ingredients = Seq(
        PotionIngredient("Wormwood", "A potent ingredient that infuses itself and changes your perspective"         , PropertyType.Buff      , 1),
        PotionIngredient("Mint", "A pleasant taste that infuses yields curative properties"                         , PropertyType.Cure      , 1),
        PotionIngredient("Rose Thorns", "Dangerous and harsh tastes are extracted from this deadly beauty"          , PropertyType.Debuff    , 1),
        PotionIngredient("Rat Tail", "Unpleasant and oderous, often riddled with toxic properties"                  , PropertyType.Poison    , 1),
        PotionIngredient("Spider Legs", "Not the nicest of ingredients, but certainly boasts powers for the brave"  , PropertyType.Buff      , 2),
        PotionIngredient("Cinnamon", "A strong tasting ingredient that makes you feel like you're on the mend"      , PropertyType.Cure      , 2),
        PotionIngredient("Neverwort", "Mindblowing for all the wrong reasons, this will knock you for six"          , PropertyType.Debuff    , 2),
        PotionIngredient("Snail Entrails", "Slimy and all round disgusting - Yuck!"                                 , PropertyType.Poison    , 2),
        PotionIngredient("Coffee beans", "Bitter tasting, but gives you a serious pep to your step"                 , PropertyType.Buff      , 3),
        PotionIngredient("Tea leaf", "An incredible taste that makes you feel better both inside and out"           , PropertyType.Cure      , 3),
        PotionIngredient("Gold", "Incredibly fancy and feels like it should taste better than it does"              , PropertyType.Debuff    , 3),
        PotionIngredient("Deathweed", "Despite the name, it holds considerable powers... If that's a good thing"    , PropertyType.Poison    , 3)
    )

    def selectIngredients(): Seq[PotionIngredient] = {
        val numberOfIngredients: Int = Random.between(1,5)
        var recipe: Seq[PotionIngredient] = Seq()
        for(i <- 1 to numberOfIngredients) {
            val selection: Int = Random.between(1,ingredients.length)
            recipe = recipe.appended(ingredients.apply(selection))
        }
        recipe
    }

    def generatePotionProperties(): Seq[PotionProperty] = {
        val numberOfProperties: Int = Random.between(1,5)
        var properties: Seq[PotionProperty] = Seq()
        for(i <- 1 to numberOfProperties) {
            properties = properties.appended(PotionProperty("Weakness I", "Reduces your strength in battle by 10.", PropertyType.Debuff))
        }
        properties
    }

    def brewNewPotion(): Potion = {
        // TODO: Setup a randomiser for potions
        val newerPotion = Potion("of Weakness I", PotionType.Tincture, generatePotionProperties())
        newerPotion
    }
}

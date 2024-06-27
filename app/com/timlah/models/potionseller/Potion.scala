package com.timlah.models.potionseller

final case class Potion(name: String, potionType: PotionType, properties: Seq[PotionProperty])
final case class PotionProperty(propertyName: String, propertyDescription: String, propertyType: PropertyType)
final case class PotionIngredient(ingredientName: String, ingredientDescription: String, ingredientType: PropertyType, maxLevel: Int)

sealed trait PotionType {
    val asString: String
}
object PotionType extends {
    case object Potion      extends PotionType {override val asString = "Potion"}
    case object Tincture    extends PotionType {override val asString = "Tincture"}
    case object Elixir      extends PotionType {override val asString = "Elixir"}
    case object Salve       extends PotionType {override val asString = "Salve"}
}

sealed trait PropertyType {
    val asString: String
}
object PropertyType extends {
    case object Buff extends PropertyType { override val asString: String = "Buff" }
    case object Cure extends PropertyType { override val asString: String = "Cure" }
    case object Debuff extends PropertyType { override val asString: String = "Debuff" }
    case object Poison extends PropertyType { override val asString: String = "Poison" }
}
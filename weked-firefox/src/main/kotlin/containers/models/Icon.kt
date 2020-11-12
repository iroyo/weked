package containers.models

enum class Icon {
    BRIEFCASE,
    CART,
    CHILL,
    CIRCLE,
    DOLLAR,
    FENCE,
    FINGERPRINT,
    FOOD,
    FRUIT,
    GIFT,
    PET,
    TREE,
    VACATION;

    val path = "resource://usercontext-content/${this.name.toLowerCase()}.svg"
}
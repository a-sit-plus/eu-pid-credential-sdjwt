package at.asitplus.wallet.eupidsdjwt

enum class SelectiveDisclosureConstraints {
    always,
    allowed,
    never,
    ;

    /**
     * An extending type can specify an sd property for a claim that is marked as allowed in the extended type
     * (or where sd was omitted), changing it to either always or never. However, it MUST NOT change a claim that is
     * marked as always or never in the extended type to a different value.
     */
    fun extendFrom(
        base: SelectiveDisclosureConstraints
    ) = when (base) {
        allowed -> this // do whatever
        else -> base.also { // retain otherwise
            require(this == base) {
                "Expected child to preserve selective disclosure constraint `$base`, but got `$this`."
            }
        }
    }
}
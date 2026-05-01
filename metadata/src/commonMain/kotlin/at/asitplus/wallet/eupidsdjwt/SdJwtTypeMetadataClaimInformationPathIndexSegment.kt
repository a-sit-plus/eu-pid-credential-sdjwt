package at.asitplus.wallet.eupidsdjwt

import kotlin.jvm.JvmInline

@JvmInline
value class SdJwtTypeMetadataClaimInformationPathIndexSegment(
    val ulong: ULong
) : SdJwtTypeMetadataClaimInformationPathSegment {
    init {
        require(ulong <= Long.MAX_VALUE.toULong()) {
            "Expected value to be at most ${Long.MAX_VALUE}, but was $ulong"
        }
    }

    constructor(int: Int): this(int.also {
        require(it >= 0) {
            "Expected index segment to be non-negative, but was $it."
        }
    }.toULong())

    override fun toString() = ulong.toString()
}
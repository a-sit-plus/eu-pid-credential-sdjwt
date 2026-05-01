package at.asitplus.wallet.eupidsdjwt

import kotlin.jvm.JvmInline

@JvmInline
value class SdJwtTypeMetadataClaimInformationPathNameSegment(
    val string: String
) : SdJwtTypeMetadataClaimInformationPathSegment {

    override fun toString() = "\"$string\""
}
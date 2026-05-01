package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * This specification defines the new JWT claim vct (for verifiable credential type). Its value MUST be a case-sensitive
 * string serving as an identifier for the type of the SD-JWT VC. The vct value MUST be a Collision-Resistant Name as
 * defined in Section 2 of [RFC7515].
 */
@Serializable
@JvmInline
value class SdJwtVcType(
    val string: String
)
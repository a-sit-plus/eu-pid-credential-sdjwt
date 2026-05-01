package at.asitplus.wallet.eupidsdjwt

import at.asitplus.csp.ContentSecurityPolicySourceExpressionHash
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

/**
 * The value MUST be an "integrity metadata" string as defined in Section 3 of [W3C.SRI]. If an integrity property is
 * present for a particular claim, the Consumer of the respective document MUST verify the integrity of the retrieved
 * document as defined in Section 3.3.5 of [W3C.SRI].
 *
 * This metadata MUST be encoded in the same format as the hash-source (without the single quotes) in section 4.2 of the Content Security Policy Level 2 specification.
 * https://www.w3.org/TR/2016/REC-SRI-20160623/#integrity-metadata
 */
@Serializable(with = SdJwtTypeMetadataIntegrityHash.InlineSerializer::class)
@JvmInline
value class SdJwtTypeMetadataIntegrityHash(
    val expression: ContentSecurityPolicySourceExpressionHash
) {
    constructor(string: String): this(
        ContentSecurityPolicySourceExpressionHash.Companion("'$string'")
    )

    override fun toString() = expression.toString().removePrefix("'").removeSuffix("'")

    class InlineSerializer : KSerializer<SdJwtTypeMetadataIntegrityHash> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(
                serialName = InlineSerializer::class.qualifiedName!!,
                kind = PrimitiveKind.STRING,
            )

        override fun serialize(
            encoder: Encoder,
            value: SdJwtTypeMetadataIntegrityHash
        ) {
            encoder.encodeString(value.toString())
        }

        override fun deserialize(decoder: Decoder) = SdJwtTypeMetadataIntegrityHash(
            decoder.decodeString()
        )
    }
}
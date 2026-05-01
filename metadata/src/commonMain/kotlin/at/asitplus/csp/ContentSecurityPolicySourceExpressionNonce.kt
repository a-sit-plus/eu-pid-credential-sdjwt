package at.asitplus.csp

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

@Serializable(with = ContentSecurityPolicySourceExpressionNonce.InlineSerializer::class)
data class ContentSecurityPolicySourceExpressionNonce(
    val nonceValue: ContentSecurityPolicyBase64String,
): ContentSecurityPolicySourceExpression {
    companion object {
        fun parse(string: String): ContentSecurityPolicySourceExpressionNonce {
            require(string.startsWith("'nonce-")) {
                "Expected nonce-source to start with `'nonce-`, but got $string"
            }
            require(string.endsWith("'")) {
                "Expected nonce-source to end with `'`, but got $string"
            }
            return ContentSecurityPolicySourceExpressionNonce(
                nonceValue = ContentSecurityPolicyBase64String(
                    string.removePrefix("'nonce-").removeSuffix("'")
                )
            )
        }
    }

    class InlineSerializer : KSerializer<ContentSecurityPolicySourceExpressionNonce> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(
                serialName = InlineSerializer::class.qualifiedName!!,
                kind = PrimitiveKind.STRING,
            )

        override fun serialize(
            encoder: Encoder,
            value: ContentSecurityPolicySourceExpressionNonce
        ) {
            encoder.encodeString(value.toString())
        }

        override fun deserialize(decoder: Decoder) = parse(
            decoder.decodeString()
        )
    }

    override fun toString() = """'nonce-$nonceValue'"""
}
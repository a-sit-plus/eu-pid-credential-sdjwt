package at.asitplus.csp

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.io.encoding.Base64

@Serializable(with = ContentSecurityPolicyBase64String.InlineSerializer::class)
data class ContentSecurityPolicyBase64String(
    val string: String,
) {
    // basically already a validation as well
    val byteArray = try {
        // TODO: is this maybe too strict for CSP?
        Base64.withPadding(
            Base64.PaddingOption.ABSENT_OPTIONAL
        ).decode(string)
    } catch (it: Throwable) {
        throw Exception("Expected string to be a valid base64-encoded string, but got `${string}`.", it)
    }

    class InlineSerializer : KSerializer<ContentSecurityPolicyBase64String> {
        override val descriptor: SerialDescriptor
            get() = SerialDescriptor(
                original = String.Companion.serializer().descriptor,
                serialName = InlineSerializer::class.qualifiedName!!,
            )

        override fun serialize(
            encoder: Encoder,
            value: ContentSecurityPolicyBase64String
        ) {
            encoder.encodeString(value.string)
        }

        override fun deserialize(decoder: Decoder): ContentSecurityPolicyBase64String {
            return ContentSecurityPolicyBase64String(decoder.decodeString())
        }
    }

    override fun toString() = string

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ContentSecurityPolicyBase64String

        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode() = byteArray.contentHashCode()
}
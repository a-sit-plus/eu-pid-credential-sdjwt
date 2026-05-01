package at.asitplus.csp

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * source-list       = *WSP [ source-expression *( 1*WSP source-expression ) *WSP ]
 *                   / *WSP "'none'" *WSP
 * source-expression = scheme-source / host-source / keyword-source / nonce-source / hash-source
 * scheme-source     = scheme-part ":"
 * host-source       = [ scheme-part "://" ] host-part [ port-part ] [ path-part ]
 * keyword-source    = "'self'" / "'unsafe-inline'" / "'unsafe-eval'"
 * base64-value      = 1*( ALPHA / DIGIT / "+" / "/" )*2( "=" )
 * nonce-value       = base64-value
 * hash-value        = base64-value
 * nonce-source      = "'nonce-" nonce-value "'"
 * hash-algo         = "sha256" / "sha384" / "sha512"
 * hash-source       = "'" hash-algo "-" hash-value "'"
 * scheme-part       = <scheme production from RFC 3986, section 3.1>
 * host-part         = "*" / [ "*." ] 1*host-char *( "." 1*host-char )
 * host-char         = ALPHA / DIGIT / "-"
 * path-part         = <path production from RFC 3986, section 3.3>
 * port-part         = ":" ( 1*DIGIT / "*" )
 */
@Serializable(with = ContentSecurityPolicySourceList.OriginalInlineSerializer::class)
data class ContentSecurityPolicySourceList(
    val string: String,
) {
    @Transient
    val sourceExpressions = parseToSourceExpressions() // also includes validation

    class OriginalInlineSerializer : KSerializer<ContentSecurityPolicySourceList> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(
                serialName = OriginalInlineSerializer::class.qualifiedName!!,
                kind = PrimitiveKind.STRING,
            )

        override fun serialize(
            encoder: Encoder,
            value: ContentSecurityPolicySourceList
        ) {
            encoder.encodeString(value.string)
        }

        override fun deserialize(decoder: Decoder) = ContentSecurityPolicySourceList(
            decoder.decodeString()
        )
    }

    override fun toString() = string

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ContentSecurityPolicySourceList

        if (sourceExpressions != other.sourceExpressions) return false

        return true
    }

    override fun hashCode() = sourceExpressions.hashCode()

    /**
     * 4.2.1. Parsing Source Lists
     *
     * To parse a source list source list, the user agent MUST use an algorithm equivalent to the following:
     *
     *     1. Strip leading and trailing whitespace from source list.
     *     2. If source list is an ASCII case-insensitive match for the string 'none' (including the quotation marks), return the empty set.
     *     3. Let set of source expressions be the empty set.
     *     4. For each token returned by splitting source list on spaces, if the token matches the grammar for source-expression, add the token to the set of source expressions.
     *     5. Return the set of source expressions.
     */
    private fun parseToSourceExpressions() = string.trim(*asciiWhitespaces).let {
        if (it.isAsciiCaseInsensitiveEqualTo("'none'")) {
            listOf()
        } else {
            it.split(*asciiWhitespaces).mapNotNull {
                runCatching {
                    ContentSecurityPolicySourceExpression.Companion(it)
                }.getOrNull()
            }
        }
    }

    /**
     * A string A is an ASCII case-insensitive match for a string B, if the ASCII lowercase of A is the ASCII lowercase of B.
     * https://infra.spec.whatwg.org/#ascii-case-insensitive
     */
    private fun String.isAsciiCaseInsensitiveEqualTo(
        other: String
    ) = equals(other, ignoreCase = true)

    /**
     * ASCII whitespace is U+0009 TAB, U+000A LF, U+000C FF, U+000D CR, or U+0020 SPACE.
     * https://infra.spec.whatwg.org/#ascii-whitespace
     */
    private val asciiWhitespaces = charArrayOf(
        Char(0x0009),
        Char(0x000A),
        Char(0x000C),
        Char(0x000D),
        Char(0x0020),
    )
}
package at.asitplus.csp

import at.asitplus.rfc.Rfc3986UriSchemeName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class ContentSecurityPolicySourceExpressionScheme(
    val uriSchemeName: Rfc3986UriSchemeName
) : ContentSecurityPolicySourceExpression {
    override fun toString() = uriSchemeName.string + ":"
}
package at.asitplus.csp

import at.asitplus.rfc.Rfc3986UriPathAbsoluteOrEmpty
import at.asitplus.rfc.Rfc3986UriSchemeName

data class ContentSecurityPolicySourceExpressionHost(
    val schemeName: Rfc3986UriSchemeName? = null,
    val host: ContentSecurityPolicySourceExpressionHostPart,
    val port: ContentSecurityPolicySourceExpressionPortPart? = null,
    /**
     * In this case we always have an authority, so path-abempty is the only applicable path if we also apply the
     * semantics specified in rfc3986.
     * In any case, this class can represent any other path production anyway.
     */
    val path: Rfc3986UriPathAbsoluteOrEmpty? = null
) : ContentSecurityPolicySourceExpression {
    companion object {
        /**
         * host-source       = [ scheme-part "://" ] host-part [ port-part ] [ path-part ]
         * scheme-part       = <scheme production from RFC 3986, section 3.1>
         * host-part         = "*" / [ "*." ] 1*host-char *( "." 1*host-char )
         * host-char         = ALPHA / DIGIT / "-"
         * path-part         = <path production from RFC 3986, section 3.3>
         * port-part         = ":" ( 1*DIGIT / "*" )
         */
        operator fun invoke(string: String): ContentSecurityPolicySourceExpressionHost {
            val schemePartRegex = """(?:${Rfc3986UriSchemeName.Companion.REGEX}://)?"""
            val hostPartRegex = ContentSecurityPolicySourceExpressionHostPart.REGEX
            val portPartRegex = "(?:${ContentSecurityPolicySourceExpressionPortPart.REGEX})?"
            val pathPartRegex = """(.*)"""
            val partRegex = Regex("""$schemePartRegex$hostPartRegex$portPartRegex$pathPartRegex""")
            val match = partRegex.matchEntire(string)
            require(match != null) {
                "Expected string to be a valid source expression, but tokenization itself failed for input `$string`."
            }

            val scheme = match.groups[1]?.value?.let(::Rfc3986UriSchemeName)
            val host = match.groups[2]?.value?.let(::ContentSecurityPolicySourceExpressionHostPart)
            val port = match.groups[3]?.value?.let(::ContentSecurityPolicySourceExpressionPortPart)
            val path = match.groups[4]?.value?.takeIf {
                it.isNotEmpty()
            }?.let {
                Rfc3986UriPathAbsoluteOrEmpty(it)
            }

            require(host != null) {
                "Expected host expression to contain a host, but got `$string`."
            }

            return ContentSecurityPolicySourceExpressionHost(
                schemeName = scheme,
                host = host,
                port = port,
                path = path,
            )
        }
    }
}
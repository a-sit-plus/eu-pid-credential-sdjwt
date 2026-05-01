package at.asitplus.csp

import at.asitplus.rfc.Rfc3986UriSchemeName

sealed interface ContentSecurityPolicySourceExpression {
    companion object {
        /**
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
        operator fun invoke(string: String): ContentSecurityPolicySourceExpression {
            if (string.isNeitherSchemeNorHostSource()) {
                runCatching {
                    return ContentSecurityPolicySourceExpressionKeyword(
                        ContentSecurityPolicySourceExpressionKeywordContent.valueOf(string.removePrefix("'").removeSuffix("'"))
                    )
                }
                runCatching {
                    return ContentSecurityPolicySourceExpressionNonce.parse(string)
                }
                runCatching {
                    return ContentSecurityPolicySourceExpressionHash(string)
                }
            } else if (string.isSchemeSource()) {
                runCatching {
                    return ContentSecurityPolicySourceExpressionScheme(
                        Rfc3986UriSchemeName(string.removeSuffix(":"))
                    )
                }
            } else {
                return ContentSecurityPolicySourceExpressionHost(string)
            }
            throw IllegalArgumentException("Expected string to be a source expression, but got `$string`")
        }

        /**
         * this excludes scheme-source and host-source because of grammar requirements
         * - from rfc3986: scheme must start with a-z or A-Z
         *      scheme      = ALPHA *( ALPHA / DIGIT / "+" / "-" / "." )
         * - from grammar above: host-part must start with either * or a host-char with
         *      host-char         = ALPHA / DIGIT / "-"
         */
        private fun String.isNeitherSchemeNorHostSource() = startsWith("'") && endsWith("'")

        /**
         * Assuming the expression is either a scheme or a host source,
         * this must be scheme source, since neither host-part, port-part nor path-part may end in ":"
         */
        private fun String.isSchemeSource() = endsWith(":")
    }
}
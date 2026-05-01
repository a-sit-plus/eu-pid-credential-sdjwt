package at.asitplus.csp

import kotlin.jvm.JvmInline

@JvmInline
value class ContentSecurityPolicySourceExpressionPortPart(
    val string: String
) {
    init {
        require(Regex(REGEX).matches(string)) {
            "Expected string to be a well-formed source expression port part, but got `$string`"
        }
    }

    companion object {
        const val REGEX = """:(\*|[0-9]+)"""
    }
}
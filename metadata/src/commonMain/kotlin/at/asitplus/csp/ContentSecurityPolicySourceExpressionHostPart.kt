package at.asitplus.csp

import kotlin.jvm.JvmInline

@JvmInline
value class ContentSecurityPolicySourceExpressionHostPart(
    val string: String
) {
    init {
        require(Regex(REGEX).matches(string)) {
            "Expected string to be a well-formed source expression host part, but got `$string`"
        }
    }

    companion object {
        const val REGEX = """(\*|(?:\*\.)?[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*)"""
    }
}
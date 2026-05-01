package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Basic syntax validation for
 * https://www.w3.org/TR/css-color-3/#rgb-color
 */
@Serializable
@JvmInline
value class W3cCssRgbColor(
    val string: String
) {
    init {
        if(string.startsWith("#")) {
            require((string.length - 1) in listOf(3, 6)) {
                "Expected rgb value in hexadecimal notation to have either 3 or 6 hexadecimal digits, but got ${string.length - 1} in `$string`."
            }
            string.drop(1).forEachIndexed { index, ch ->
                require(ch in '0'..'9' || ch in 'a'..'f' || ch in 'A'..'F') {
                    "Expected rgb value in hexadecimal notation to consist of hexadecimal characters, but got `$ch` at index ${index + 1} in `$string`."
                }
            }
        } else {
            require(string.startsWith("rgb(") && string.endsWith(")")) {
                "Expected rgb value in either hexadecimal or functional notation, but got `$string`."
            }
            val parts = string.removePrefix("rgb(").removeSuffix(")").split(",").map {
                it.trim()
            }
            require(parts.size == 3) {
                "Expected rgb value in functional notation to consist of 3 parts, but got ${parts.size} in `$string`."
            }
            val allPercent = parts.all { it.endsWith("%") }
            val nonePercent = parts.none { it.endsWith("%") }

            require(allPercent || nonePercent) {
                "Expected rgb value in functional notation to consist of 3 similar parts, either all integers or percentages, but got a mix in `$string`."
            }

            val isPercentages = allPercent
            val numericParts = if(isPercentages) {
                parts.map {
                    it.dropLast(1).trim()
                }
            } else {
                parts
            }
            numericParts.forEachIndexed { index, string ->
                string.toInt(10)
            }
        }
    }
}
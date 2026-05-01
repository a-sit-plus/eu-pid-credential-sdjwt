package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SvgTemplateProperties(
    @SerialName(SerialNames.ORIENTATION)
    val svgTemplatePropertyImageOrientation: SvgTemplatePropertyImageOrientation? = null,
    @SerialName(SerialNames.COLOR_SCHEME)
    val svgTemplatePropertyColorScheme: SvgTemplatePropertyColorScheme? = null,
    @SerialName(SerialNames.CONTRAST)
    val svgTemplatePropertyContrast: SvgTemplatePropertyContrast? = null,
) {
    object SerialNames {
        const val ORIENTATION = "orientation"
        const val COLOR_SCHEME = "color_scheme"
        const val CONTRAST = "contrast"
    }
}
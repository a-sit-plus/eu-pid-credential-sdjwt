package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdJwtTypeMetadataTypeDisplayInformationRenderingMetadata(
    @SerialName(SerialNames.SIMPLE)
    val simple: SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSimple? = null,
    @SerialName(SerialNames.SVG_TEMPLATES)
    val svgTemplates: SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates? = null,
) {
    object SerialNames {
        const val SIMPLE = "simple"
        const val SVG_TEMPLATES = "svg_templates"
    }
}
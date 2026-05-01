package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSimple(
    @SerialName(SerialNames.LOGO)
    val logo: SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSimpleLogoMetadata? = null,
    @SerialName(SerialNames.BACKGROUND_IMAGE)
    val backgroundImage: SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSimpleBackgroundImageMetadata? = null,
    @SerialName(SerialNames.BACKGROUND_COLOR)
    val backgroundColor: W3cCssRgbColor? = null,
    @SerialName(SerialNames.TEXT_COLOR)
    val textColor: W3cCssRgbColor? = null,
) {
    object SerialNames {
        const val LOGO = "logo"
        const val BACKGROUND_IMAGE = "background_image"
        const val BACKGROUND_COLOR = "background_color"
        const val TEXT_COLOR = "text_color"
    }
}
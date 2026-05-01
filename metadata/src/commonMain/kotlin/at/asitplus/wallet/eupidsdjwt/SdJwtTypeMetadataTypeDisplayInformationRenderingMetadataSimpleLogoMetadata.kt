package at.asitplus.wallet.eupidsdjwt

import at.asitplus.rfc.Rfc3986UniformResourceIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSimpleLogoMetadata(
    @SerialName(SerialNames.URI)
    val uri: Rfc3986UniformResourceIdentifier,
    @SerialName(SerialNames.URI_INTEGRITY)
    val uriIntegrity: SdJwtTypeMetadataIntegrityHash? = null,
    @SerialName(SerialNames.ALTERNATIVE_TEXT)
    val alternativeText: String? = null,
) {
    object SerialNames {
        const val URI = "uri"
        const val URI_INTEGRITY = "uri#integrity"
        const val ALTERNATIVE_TEXT = "alt_text"
    }
}
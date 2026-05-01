package at.asitplus.wallet.eupidsdjwt

import at.asitplus.rfc.Rfc3986UniformResourceIdentifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SvgTemplate(
    /**
     *  uri: A URI pointing to the SVG template. This property is REQUIRED.
     */
    @SerialName(SerialNames.URI)
    val uri: Rfc3986UniformResourceIdentifier,
    /**
     *  uri#integrity: An "integrity metadata" string as described in Section 5. This property is OPTIONAL.
     */
    @SerialName(SerialNames.URI_INTEGRITY)
    val uriIntegrity: SdJwtTypeMetadataIntegrityHash? = null,
    /**
     * properties: An object containing properties for the SVG template, as described in Section 4.5.1.2.1. This
     * property is REQUIRED if more than one SVG template is present, otherwise it is OPTIONAL.
     */
    @SerialName(SerialNames.PROPERTIES)
    val properties: SvgTemplateProperties? = null,
) {
    object SerialNames {
        const val URI = "uri"
        const val URI_INTEGRITY = "uri#integrity"
        const val PROPERTIES = "properties"
    }
}
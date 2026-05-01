package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdJwtTypeMetadataTypeDisplayInformation(
    /**
     *  locale: A language tag as defined in Section 2 of [RFC5646]. This property is REQUIRED.
     */
    @SerialName(SerialNames.LOCALE)
    val locale: Rfc5646LanguageTag,
    /**
     *  name: A human-readable name for the type, intended for end users. This property is REQUIRED.
     */
    @SerialName(SerialNames.NAME)
    val name: String,
    /**
     *  description: A human-readable description for the type, intended for end users. This property is OPTIONAL.
     */
    @SerialName(SerialNames.DESCRIPTION)
    val description: String? = null,
    /**
     *  rendering: An object containing rendering information for the type, as described in Section 4.5.1. This property is OPTIONAL.
     */
    @SerialName(SerialNames.RENDERING)
    val rendering: SdJwtTypeMetadataTypeDisplayInformationRenderingMetadata? = null,
) {
    object SerialNames {
        const val LOCALE = "locale"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val RENDERING = "rendering"
    }
}
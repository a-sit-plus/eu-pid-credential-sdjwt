package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdJwtTypeMetadataClaimInformationDisplayMetadata(
    @SerialName(SerialNames.LOCALE)
    val locale: Rfc5646LanguageTag,
    @SerialName(SerialNames.LABEL)
    val label: String,
    @SerialName(SerialNames.DESCRIPTION)
    val description: String? = null,
) {
    object SerialNames {
        const val LOCALE = "locale"
        const val LABEL = "label"
        const val DESCRIPTION = "description"
    }
}
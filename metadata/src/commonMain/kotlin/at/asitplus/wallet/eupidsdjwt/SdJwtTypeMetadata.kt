package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdJwtTypeMetadata(
    @SerialName(SdJwtTypeMetadataDefinition.SerialNames.VCT)
    val vct: SdJwtVcType,
    @SerialName(SdJwtTypeMetadataDefinition.SerialNames.NAME)
    val name: String? = null,
    @SerialName(SdJwtTypeMetadataDefinition.SerialNames.DESCRIPTION)
    val description: String? = null,
    @SerialName(SdJwtTypeMetadataDefinition.SerialNames.DISPLAY)
    val display: List<SdJwtTypeMetadataTypeDisplayInformation>? = null,
    @SerialName(SdJwtTypeMetadataDefinition.SerialNames.CLAIMS)
    val claims: List<SdJwtTypeMetadataClaimInformation>? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SdJwtTypeMetadata

        if (vct != other.vct) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (display?.sortedBy { it.locale.string } != other.display?.sortedBy { it.locale.string }) return false
        if (claims?.sortedBy { it.path.toString() } != other.claims?.sortedBy { it.path.toString() }) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vct.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (display?.sortedBy { it.locale.string }?.hashCode() ?: 0)
        result = 31 * result + (claims?.sortedBy { it.path.toString() }?.hashCode() ?: 0)
        return result
    }
}
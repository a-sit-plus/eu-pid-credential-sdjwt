package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * {
 *   "vct":"https://betelgeuse.example.com/education_credential/v42",
 *   "":"Betelgeuse Education Credential - Version 42",
 *   "":"This is our education credential. Don't panic.",
 *   "":"https://galaxy.example.com/galactic-education-credential/v2",
 *   "":"sha256-ilOUJsTultOwLfz7QUcFALaRa3BP/jelX1ds04kB9yU="
 * }
 * Note: This implementation does not support going back to the original document, an integrity check would likely fail.
 */
@Serializable
data class SdJwtTypeMetadataDefinition(
    @SerialName(SerialNames.VCT)
    val vct: SdJwtVcType,
    @SerialName(SerialNames.NAME)
    val name: String? = null,
    @SerialName(SerialNames.DESCRIPTION)
    val description: String? = null,
    @SerialName(SerialNames.EXTENDS)
    val extends: SdJwtVcType? = null,
    @SerialName(SerialNames.EXTENDS_INTEGRITY)
    val extendsIntegrity: SdJwtTypeMetadataIntegrityHash? = null,
    @SerialName(SerialNames.DISPLAY)
    val display: List<SdJwtTypeMetadataTypeDisplayInformation>? = null,
    @SerialName(SerialNames.CLAIMS)
    val claims: List<SdJwtTypeMetadataClaimInformation>? = null,
) {
    object SerialNames {
        const val VCT = "vct"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val EXTENDS = "extends"
        const val EXTENDS_INTEGRITY = "extends#integrity"
        const val DISPLAY = "display"
        const val CLAIMS = "claims"
    }

    fun toSdJwtTypeMetadata(): SdJwtTypeMetadata {
        require(extends == null && extendsIntegrity == null) {
            "Expected metadata definition to not extend anything, but got `${this}`."
        }
        return SdJwtTypeMetadata(
            vct = vct,
            name = name,
            description = description,
            display = display,
            claims = claims,
        )
    }

    fun extend(base: SdJwtTypeMetadata): SdJwtTypeMetadata {
        require(extends == base.vct) {
            """Expected the extending type to specify the vct of the extended type in `extends`, but got `${extends}` instead of `${base.vct}` in `$this`"""
        }

        return SdJwtTypeMetadata(
            vct = vct,
            name = name ?: base.name,
            description = description ?: base.description,
            /**
             * When an SD-JWT VC type extends another type as described in Section 4.4, the display metadata remains
             * valid for the inheriting type unless that type defines its own display property, in which case the
             * original display metadata is ignored.
             */
            display = display ?: base.display,
            claims = claims?.let {
                val childClaims = it.associateBy {
                    it.path
                }
                val baseClaims = (base.claims ?: listOf()).associateBy {
                    it.path
                }
                (childClaims.keys + baseClaims.keys).associateWith {
                    val baseClaimInfo = baseClaims[it]
                    val childClaimInfo = childClaims[it] ?: return@associateWith baseClaimInfo
                    if (baseClaimInfo == null) {
                        return@associateWith childClaimInfo
                    }
                    childClaimInfo.extendFrom(baseClaimInfo)
                }.values.filterNotNull()
            } ?: base.claims
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SdJwtTypeMetadataDefinition

        if (vct != other.vct) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (extends != other.extends) return false
        if (extendsIntegrity != other.extendsIntegrity) return false
        if (display != other.display) return false
        if (claims?.sortedBy { it.path.toString() } != other.claims?.sortedBy { it.path.toString() }) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vct.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (extends?.hashCode() ?: 0)
        result = 31 * result + (extendsIntegrity?.hashCode() ?: 0)
        result = 31 * result + (display?.hashCode() ?: 0)
        result = 31 * result + (claims?.hashCode() ?: 0)
        return result
    }
}



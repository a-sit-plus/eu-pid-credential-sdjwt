package at.asitplus.wallet.eupidsdjwt


import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * PID scheme according to [ANNEX 3.0.1 PID Rulebook, v1.9.0 from April 2025](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/v1.9.0/docs/annexes/annex-3/annex-3.01-pid-rulebook.md)
 */
@Serializable(with = IsoIec5218GenderSerializer::class)
enum class IsoIec5218Gender(val code: UInt) {
    NOT_KNOWN(0u),
    MALE(1u),
    FEMALE(2u),
    OTHER(3u),
    INTER(4u),
    DIVERSE(5u),
    OPEN(6u),
    NOT_APPLICABLE(9u)
}

object IsoIec5218GenderSerializer : KSerializer<IsoIec5218Gender> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("IsoIec5218Gender", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): IsoIec5218Gender {
        val decoded = decoder.decodeInt().toUInt()
        return IsoIec5218Gender.entries.first { it.code == decoded }
    }

    override fun serialize(encoder: Encoder, value: IsoIec5218Gender) {
        encoder.encodeInt(value.code.toInt())
    }

}
package at.asitplus.wallet.eupidsdjwt

import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.vckJsonSerializer
import at.asitplus.wallet.lib.iso.vckCborSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlin.random.Random
import kotlin.random.nextUInt

@OptIn(ExperimentalSerializationApi::class)
class SdJwtSerializationTest : FunSpec({

    test("serialize credential") {
        val credential = EuPidCredentialSdJwt(
            familyName = randomString(),
            givenName = randomString(),
            birthDate = randomLocalDate(),
            ageEqualOrOver = AgeEqualOrOverSdJwt(
                equalOrOver12 = Random.nextBoolean(),
                equalOrOver13 = Random.nextBoolean(),
                equalOrOver14 = Random.nextBoolean(),
                equalOrOver16 = Random.nextBoolean(),
                equalOrOver18 = Random.nextBoolean(),
                equalOrOver21 = Random.nextBoolean(),
                equalOrOver25 = Random.nextBoolean(),
                equalOrOver60 = Random.nextBoolean(),
                equalOrOver62 = Random.nextBoolean(),
                equalOrOver65 = Random.nextBoolean(),
                equalOrOver68 = Random.nextBoolean(),
            ),
            ageInYears = Random.nextUInt(),
            ageBirthYear = Random.nextUInt(),
            familyNameBirth = randomString(),
            givenNameBirth = randomString(),
            placeOfBirth = PlaceOfBirthSdJwt(
                locality = randomString(),
            ),
            address = AddressSdJwt(
                formatted = randomString(),
                country = randomString(),
                region = randomString(),
                locality = randomString(),
                postalCode = randomString(),
                street = randomString(),
                houseNumber = randomString(),
            ),
            sex = IsoIec5218Gender.NOT_APPLICABLE,
            nationalities = setOf(randomString()),
            issuanceDate = if (Random.nextBoolean())
                LocalDateOrInstant.Instant(randomInstant())
            else
                LocalDateOrInstant.LocalDate(randomLocalDate()),
            expiryDate = if (Random.nextBoolean())
                LocalDateOrInstant.Instant(randomInstant())
            else
                LocalDateOrInstant.LocalDate(randomLocalDate()),
            issuingAuthority = randomString(),
            documentNumber = randomString(),
            issuingCountry = randomString(),
            issuingJurisdiction = randomString(),
            personalAdministrativeNumber = randomString(),
            portrait = Random.nextBytes(32),
            email = randomString(),
            phoneNumber = randomString(),
            trustAnchor = randomString(),
        )
        val json = vckJsonSerializer.encodeToString(credential)
        vckJsonSerializer.decodeFromString<EuPidCredentialSdJwt>(json) shouldBe credential

        val cbor = vckCborSerializer.encodeToByteArray(credential)
        vckCborSerializer.decodeFromByteArray<EuPidCredentialSdJwt>(cbor) shouldBe credential
    }

})

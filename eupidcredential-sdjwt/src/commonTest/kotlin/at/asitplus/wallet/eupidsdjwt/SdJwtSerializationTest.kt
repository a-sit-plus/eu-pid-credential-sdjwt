package at.asitplus.wallet.eupidsdjwt

import at.asitplus.signum.indispensable.cosef.io.coseCompliantSerializer
import at.asitplus.testballoon.invoke
import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.vckJsonSerializer
import de.infix.testBalloon.framework.testSuite
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlin.random.Random
import kotlin.random.nextUInt

@OptIn(ExperimentalSerializationApi::class)
val SdJwtSerializationTest by testSuite {

    "serialize credential" {

        val credential = EuPidCredentialSdJwt(
            familyName = randomString(),
            givenName = randomString(),
            birthDate = randomLocalDate(),
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

        val cbor = coseCompliantSerializer.encodeToByteArray(credential)
        coseCompliantSerializer.decodeFromByteArray<EuPidCredentialSdJwt>(cbor) shouldBe credential
    }

}

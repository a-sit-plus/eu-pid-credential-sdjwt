package at.asitplus.wallet.eupidsdjwt

import at.asitplus.jsonpath.core.NormalizedJsonPath
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

val SdJwtTypeMetadataClaimInformationPathTest by testSuite {
    test("manual examples") {
        val jsonElement = Json.decodeFromString<JsonElement>(
            """
            {
              "vct": "https://betelgeuse.example.com/education_credential/v42",
              "name": "Arthur Dent",
              "address": {
                "street_address": "42 Market Street",
                "city": "Milliways",
                "postal_code": "12345"
              },
              "degrees": [
                {
                  "type": "Bachelor of Science",
                  "university": "University of Betelgeuse"
                },
                {
                  "type": "Master of Science",
                  "university": "University of Betelgeuse"
                }
              ],
              "nationalities": ["British", "Betelgeusian"]
            }
        """.trimIndent()
        )

        SdJwtTypeMetadataClaimInformationPath("name").process(jsonElement).map {
            it.first.toString() to it.second
        } shouldBe listOf(
            (NormalizedJsonPath() + "name") to JsonPrimitive("Arthur Dent")
        ).map {
            it.first.toString() to it.second
        }
        SdJwtTypeMetadataClaimInformationPath("address").process(jsonElement).map {
            it.first.toString() to it.second
        } shouldBe listOf(
            (NormalizedJsonPath() + "address") to Json.decodeFromString<JsonElement>(
                """
                {
                "street_address": "42 Market Street",
                "city": "Milliways",
                "postal_code": "12345"
              }
            """.trimIndent()
            )
        ).map {
            it.first.toString() to it.second
        }
        SdJwtTypeMetadataClaimInformationPath("address", "street_address").process(jsonElement).map {
            it.first.toString() to it.second
        } shouldBe listOf(
            (NormalizedJsonPath() + "address" + "street_address") to JsonPrimitive("42 Market Street")
        ).map {
            it.first.toString() to it.second
        }
        (SdJwtTypeMetadataClaimInformationPath("degrees") + null + "type").process(jsonElement).map {
            it.first.toString() to it.second
        } shouldBe listOf(
            (NormalizedJsonPath() + "degrees" + 0u + "type") to JsonPrimitive("Bachelor of Science"),
            (NormalizedJsonPath() + "degrees" + 1u + "type") to JsonPrimitive("Master of Science"),
        ).map {
            it.first.toString() to it.second
        }
    }
}